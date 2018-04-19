/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.jpa;

import com.admisiones.data.Facultad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.admisiones.data.Programa;
import com.admisiones.jpa.exceptions.IllegalOrphanException;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JORGE
 */
public class FacultadJpaController implements Serializable {

    public FacultadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facultad facultad) throws PreexistingEntityException, Exception {
        if (facultad.getProgramaCollection() == null) {
            facultad.setProgramaCollection(new ArrayList<Programa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Programa> attachedProgramaCollection = new ArrayList<Programa>();
            for (Programa programaCollectionProgramaToAttach : facultad.getProgramaCollection()) {
                programaCollectionProgramaToAttach = em.getReference(programaCollectionProgramaToAttach.getClass(), programaCollectionProgramaToAttach.getProid());
                attachedProgramaCollection.add(programaCollectionProgramaToAttach);
            }
            facultad.setProgramaCollection(attachedProgramaCollection);
            em.persist(facultad);
            for (Programa programaCollectionPrograma : facultad.getProgramaCollection()) {
                Facultad oldFacidOfProgramaCollectionPrograma = programaCollectionPrograma.getFacid();
                programaCollectionPrograma.setFacid(facultad);
                programaCollectionPrograma = em.merge(programaCollectionPrograma);
                if (oldFacidOfProgramaCollectionPrograma != null) {
                    oldFacidOfProgramaCollectionPrograma.getProgramaCollection().remove(programaCollectionPrograma);
                    oldFacidOfProgramaCollectionPrograma = em.merge(oldFacidOfProgramaCollectionPrograma);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacultad(facultad.getFacid()) != null) {
                throw new PreexistingEntityException("Facultad " + facultad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facultad facultad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad persistentFacultad = em.find(Facultad.class, facultad.getFacid());
            Collection<Programa> programaCollectionOld = persistentFacultad.getProgramaCollection();
            Collection<Programa> programaCollectionNew = facultad.getProgramaCollection();
            List<String> illegalOrphanMessages = null;
            for (Programa programaCollectionOldPrograma : programaCollectionOld) {
                if (!programaCollectionNew.contains(programaCollectionOldPrograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programa " + programaCollectionOldPrograma + " since its facid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Programa> attachedProgramaCollectionNew = new ArrayList<Programa>();
            for (Programa programaCollectionNewProgramaToAttach : programaCollectionNew) {
                programaCollectionNewProgramaToAttach = em.getReference(programaCollectionNewProgramaToAttach.getClass(), programaCollectionNewProgramaToAttach.getProid());
                attachedProgramaCollectionNew.add(programaCollectionNewProgramaToAttach);
            }
            programaCollectionNew = attachedProgramaCollectionNew;
            facultad.setProgramaCollection(programaCollectionNew);
            facultad = em.merge(facultad);
            for (Programa programaCollectionNewPrograma : programaCollectionNew) {
                if (!programaCollectionOld.contains(programaCollectionNewPrograma)) {
                    Facultad oldFacidOfProgramaCollectionNewPrograma = programaCollectionNewPrograma.getFacid();
                    programaCollectionNewPrograma.setFacid(facultad);
                    programaCollectionNewPrograma = em.merge(programaCollectionNewPrograma);
                    if (oldFacidOfProgramaCollectionNewPrograma != null && !oldFacidOfProgramaCollectionNewPrograma.equals(facultad)) {
                        oldFacidOfProgramaCollectionNewPrograma.getProgramaCollection().remove(programaCollectionNewPrograma);
                        oldFacidOfProgramaCollectionNewPrograma = em.merge(oldFacidOfProgramaCollectionNewPrograma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = facultad.getFacid();
                if (findFacultad(id) == null) {
                    throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facultad;
            try {
                facultad = em.getReference(Facultad.class, id);
                facultad.getFacid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Programa> programaCollectionOrphanCheck = facultad.getProgramaCollection();
            for (Programa programaCollectionOrphanCheckPrograma : programaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facultad (" + facultad + ") cannot be destroyed since the Programa " + programaCollectionOrphanCheckPrograma + " in its programaCollection field has a non-nullable facid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(facultad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facultad> findFacultadEntities() {
        return findFacultadEntities(true, -1, -1);
    }

    public List<Facultad> findFacultadEntities(int maxResults, int firstResult) {
        return findFacultadEntities(false, maxResults, firstResult);
    }

    private List<Facultad> findFacultadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facultad.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Facultad findFacultad(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facultad.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facultad> rt = cq.from(Facultad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
