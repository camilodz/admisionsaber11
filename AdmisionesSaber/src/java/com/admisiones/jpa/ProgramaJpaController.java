/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.admisiones.data.Facultad;
import com.admisiones.data.Programa;
import com.admisiones.data.Programaofertado;
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
 * @author Karen
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        if (programa.getProgramaofertadoCollection() == null) {
            programa.setProgramaofertadoCollection(new ArrayList<Programaofertado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facid = programa.getFacid();
            if (facid != null) {
                facid = em.getReference(facid.getClass(), facid.getFacid());
                programa.setFacid(facid);
            }
            Collection<Programaofertado> attachedProgramaofertadoCollection = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoCollectionProgramaofertadoToAttach : programa.getProgramaofertadoCollection()) {
                programaofertadoCollectionProgramaofertadoToAttach = em.getReference(programaofertadoCollectionProgramaofertadoToAttach.getClass(), programaofertadoCollectionProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoCollection.add(programaofertadoCollectionProgramaofertadoToAttach);
            }
            programa.setProgramaofertadoCollection(attachedProgramaofertadoCollection);
            em.persist(programa);
            if (facid != null) {
                facid.getProgramaCollection().add(programa);
                facid = em.merge(facid);
            }
            for (Programaofertado programaofertadoCollectionProgramaofertado : programa.getProgramaofertadoCollection()) {
                Programa oldProgramaOfProgramaofertadoCollectionProgramaofertado = programaofertadoCollectionProgramaofertado.getPrograma();
                programaofertadoCollectionProgramaofertado.setPrograma(programa);
                programaofertadoCollectionProgramaofertado = em.merge(programaofertadoCollectionProgramaofertado);
                if (oldProgramaOfProgramaofertadoCollectionProgramaofertado != null) {
                    oldProgramaOfProgramaofertadoCollectionProgramaofertado.getProgramaofertadoCollection().remove(programaofertadoCollectionProgramaofertado);
                    oldProgramaOfProgramaofertadoCollectionProgramaofertado = em.merge(oldProgramaOfProgramaofertadoCollectionProgramaofertado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getProid()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getProid());
            Facultad facidOld = persistentPrograma.getFacid();
            Facultad facidNew = programa.getFacid();
            Collection<Programaofertado> programaofertadoCollectionOld = persistentPrograma.getProgramaofertadoCollection();
            Collection<Programaofertado> programaofertadoCollectionNew = programa.getProgramaofertadoCollection();
            List<String> illegalOrphanMessages = null;
            for (Programaofertado programaofertadoCollectionOldProgramaofertado : programaofertadoCollectionOld) {
                if (!programaofertadoCollectionNew.contains(programaofertadoCollectionOldProgramaofertado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programaofertado " + programaofertadoCollectionOldProgramaofertado + " since its programa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facidNew != null) {
                facidNew = em.getReference(facidNew.getClass(), facidNew.getFacid());
                programa.setFacid(facidNew);
            }
            Collection<Programaofertado> attachedProgramaofertadoCollectionNew = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoCollectionNewProgramaofertadoToAttach : programaofertadoCollectionNew) {
                programaofertadoCollectionNewProgramaofertadoToAttach = em.getReference(programaofertadoCollectionNewProgramaofertadoToAttach.getClass(), programaofertadoCollectionNewProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoCollectionNew.add(programaofertadoCollectionNewProgramaofertadoToAttach);
            }
            programaofertadoCollectionNew = attachedProgramaofertadoCollectionNew;
            programa.setProgramaofertadoCollection(programaofertadoCollectionNew);
            programa = em.merge(programa);
            if (facidOld != null && !facidOld.equals(facidNew)) {
                facidOld.getProgramaCollection().remove(programa);
                facidOld = em.merge(facidOld);
            }
            if (facidNew != null && !facidNew.equals(facidOld)) {
                facidNew.getProgramaCollection().add(programa);
                facidNew = em.merge(facidNew);
            }
            for (Programaofertado programaofertadoCollectionNewProgramaofertado : programaofertadoCollectionNew) {
                if (!programaofertadoCollectionOld.contains(programaofertadoCollectionNewProgramaofertado)) {
                    Programa oldProgramaOfProgramaofertadoCollectionNewProgramaofertado = programaofertadoCollectionNewProgramaofertado.getPrograma();
                    programaofertadoCollectionNewProgramaofertado.setPrograma(programa);
                    programaofertadoCollectionNewProgramaofertado = em.merge(programaofertadoCollectionNewProgramaofertado);
                    if (oldProgramaOfProgramaofertadoCollectionNewProgramaofertado != null && !oldProgramaOfProgramaofertadoCollectionNewProgramaofertado.equals(programa)) {
                        oldProgramaOfProgramaofertadoCollectionNewProgramaofertado.getProgramaofertadoCollection().remove(programaofertadoCollectionNewProgramaofertado);
                        oldProgramaOfProgramaofertadoCollectionNewProgramaofertado = em.merge(oldProgramaOfProgramaofertadoCollectionNewProgramaofertado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = programa.getProid();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
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
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getProid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Programaofertado> programaofertadoCollectionOrphanCheck = programa.getProgramaofertadoCollection();
            for (Programaofertado programaofertadoCollectionOrphanCheckProgramaofertado : programaofertadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Programaofertado " + programaofertadoCollectionOrphanCheckProgramaofertado + " in its programaofertadoCollection field has a non-nullable programa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Facultad facid = programa.getFacid();
            if (facid != null) {
                facid.getProgramaCollection().remove(programa);
                facid = em.merge(facid);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    public Programa findPrograma(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
