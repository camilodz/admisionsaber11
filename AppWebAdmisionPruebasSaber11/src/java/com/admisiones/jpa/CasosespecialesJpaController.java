/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.jpa;

import com.admisiones.data.Casosespeciales;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.admisiones.data.Programacasos;
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
public class CasosespecialesJpaController implements Serializable {

    public CasosespecialesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Casosespeciales casosespeciales) throws PreexistingEntityException, Exception {
        if (casosespeciales.getProgramacasosCollection() == null) {
            casosespeciales.setProgramacasosCollection(new ArrayList<Programacasos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Programacasos> attachedProgramacasosCollection = new ArrayList<Programacasos>();
            for (Programacasos programacasosCollectionProgramacasosToAttach : casosespeciales.getProgramacasosCollection()) {
                programacasosCollectionProgramacasosToAttach = em.getReference(programacasosCollectionProgramacasosToAttach.getClass(), programacasosCollectionProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosCollection.add(programacasosCollectionProgramacasosToAttach);
            }
            casosespeciales.setProgramacasosCollection(attachedProgramacasosCollection);
            em.persist(casosespeciales);
            for (Programacasos programacasosCollectionProgramacasos : casosespeciales.getProgramacasosCollection()) {
                Casosespeciales oldCasosespecialesOfProgramacasosCollectionProgramacasos = programacasosCollectionProgramacasos.getCasosespeciales();
                programacasosCollectionProgramacasos.setCasosespeciales(casosespeciales);
                programacasosCollectionProgramacasos = em.merge(programacasosCollectionProgramacasos);
                if (oldCasosespecialesOfProgramacasosCollectionProgramacasos != null) {
                    oldCasosespecialesOfProgramacasosCollectionProgramacasos.getProgramacasosCollection().remove(programacasosCollectionProgramacasos);
                    oldCasosespecialesOfProgramacasosCollectionProgramacasos = em.merge(oldCasosespecialesOfProgramacasosCollectionProgramacasos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCasosespeciales(casosespeciales.getEspid()) != null) {
                throw new PreexistingEntityException("Casosespeciales " + casosespeciales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Casosespeciales casosespeciales) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Casosespeciales persistentCasosespeciales = em.find(Casosespeciales.class, casosespeciales.getEspid());
            Collection<Programacasos> programacasosCollectionOld = persistentCasosespeciales.getProgramacasosCollection();
            Collection<Programacasos> programacasosCollectionNew = casosespeciales.getProgramacasosCollection();
            List<String> illegalOrphanMessages = null;
            for (Programacasos programacasosCollectionOldProgramacasos : programacasosCollectionOld) {
                if (!programacasosCollectionNew.contains(programacasosCollectionOldProgramacasos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacasos " + programacasosCollectionOldProgramacasos + " since its casosespeciales field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Programacasos> attachedProgramacasosCollectionNew = new ArrayList<Programacasos>();
            for (Programacasos programacasosCollectionNewProgramacasosToAttach : programacasosCollectionNew) {
                programacasosCollectionNewProgramacasosToAttach = em.getReference(programacasosCollectionNewProgramacasosToAttach.getClass(), programacasosCollectionNewProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosCollectionNew.add(programacasosCollectionNewProgramacasosToAttach);
            }
            programacasosCollectionNew = attachedProgramacasosCollectionNew;
            casosespeciales.setProgramacasosCollection(programacasosCollectionNew);
            casosespeciales = em.merge(casosespeciales);
            for (Programacasos programacasosCollectionNewProgramacasos : programacasosCollectionNew) {
                if (!programacasosCollectionOld.contains(programacasosCollectionNewProgramacasos)) {
                    Casosespeciales oldCasosespecialesOfProgramacasosCollectionNewProgramacasos = programacasosCollectionNewProgramacasos.getCasosespeciales();
                    programacasosCollectionNewProgramacasos.setCasosespeciales(casosespeciales);
                    programacasosCollectionNewProgramacasos = em.merge(programacasosCollectionNewProgramacasos);
                    if (oldCasosespecialesOfProgramacasosCollectionNewProgramacasos != null && !oldCasosespecialesOfProgramacasosCollectionNewProgramacasos.equals(casosespeciales)) {
                        oldCasosespecialesOfProgramacasosCollectionNewProgramacasos.getProgramacasosCollection().remove(programacasosCollectionNewProgramacasos);
                        oldCasosespecialesOfProgramacasosCollectionNewProgramacasos = em.merge(oldCasosespecialesOfProgramacasosCollectionNewProgramacasos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = casosespeciales.getEspid();
                if (findCasosespeciales(id) == null) {
                    throw new NonexistentEntityException("The casosespeciales with id " + id + " no longer exists.");
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
            Casosespeciales casosespeciales;
            try {
                casosespeciales = em.getReference(Casosespeciales.class, id);
                casosespeciales.getEspid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casosespeciales with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Programacasos> programacasosCollectionOrphanCheck = casosespeciales.getProgramacasosCollection();
            for (Programacasos programacasosCollectionOrphanCheckProgramacasos : programacasosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Casosespeciales (" + casosespeciales + ") cannot be destroyed since the Programacasos " + programacasosCollectionOrphanCheckProgramacasos + " in its programacasosCollection field has a non-nullable casosespeciales field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(casosespeciales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Casosespeciales> findCasosespecialesEntities() {
        return findCasosespecialesEntities(true, -1, -1);
    }

    public List<Casosespeciales> findCasosespecialesEntities(int maxResults, int firstResult) {
        return findCasosespecialesEntities(false, maxResults, firstResult);
    }

    private List<Casosespeciales> findCasosespecialesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Casosespeciales.class));
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

    public Casosespeciales findCasosespeciales(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Casosespeciales.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasosespecialesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Casosespeciales> rt = cq.from(Casosespeciales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
