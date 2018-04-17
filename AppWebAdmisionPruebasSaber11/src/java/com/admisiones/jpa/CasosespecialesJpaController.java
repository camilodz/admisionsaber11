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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Karen
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
        if (casosespeciales.getProgramacasosList() == null) {
            casosespeciales.setProgramacasosList(new ArrayList<Programacasos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Programacasos> attachedProgramacasosList = new ArrayList<Programacasos>();
            for (Programacasos programacasosListProgramacasosToAttach : casosespeciales.getProgramacasosList()) {
                programacasosListProgramacasosToAttach = em.getReference(programacasosListProgramacasosToAttach.getClass(), programacasosListProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosList.add(programacasosListProgramacasosToAttach);
            }
            casosespeciales.setProgramacasosList(attachedProgramacasosList);
            em.persist(casosespeciales);
            for (Programacasos programacasosListProgramacasos : casosespeciales.getProgramacasosList()) {
                Casosespeciales oldCasosespecialesOfProgramacasosListProgramacasos = programacasosListProgramacasos.getCasosespeciales();
                programacasosListProgramacasos.setCasosespeciales(casosespeciales);
                programacasosListProgramacasos = em.merge(programacasosListProgramacasos);
                if (oldCasosespecialesOfProgramacasosListProgramacasos != null) {
                    oldCasosespecialesOfProgramacasosListProgramacasos.getProgramacasosList().remove(programacasosListProgramacasos);
                    oldCasosespecialesOfProgramacasosListProgramacasos = em.merge(oldCasosespecialesOfProgramacasosListProgramacasos);
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
            List<Programacasos> programacasosListOld = persistentCasosespeciales.getProgramacasosList();
            List<Programacasos> programacasosListNew = casosespeciales.getProgramacasosList();
            List<String> illegalOrphanMessages = null;
            for (Programacasos programacasosListOldProgramacasos : programacasosListOld) {
                if (!programacasosListNew.contains(programacasosListOldProgramacasos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacasos " + programacasosListOldProgramacasos + " since its casosespeciales field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Programacasos> attachedProgramacasosListNew = new ArrayList<Programacasos>();
            for (Programacasos programacasosListNewProgramacasosToAttach : programacasosListNew) {
                programacasosListNewProgramacasosToAttach = em.getReference(programacasosListNewProgramacasosToAttach.getClass(), programacasosListNewProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosListNew.add(programacasosListNewProgramacasosToAttach);
            }
            programacasosListNew = attachedProgramacasosListNew;
            casosespeciales.setProgramacasosList(programacasosListNew);
            casosespeciales = em.merge(casosespeciales);
            for (Programacasos programacasosListNewProgramacasos : programacasosListNew) {
                if (!programacasosListOld.contains(programacasosListNewProgramacasos)) {
                    Casosespeciales oldCasosespecialesOfProgramacasosListNewProgramacasos = programacasosListNewProgramacasos.getCasosespeciales();
                    programacasosListNewProgramacasos.setCasosespeciales(casosespeciales);
                    programacasosListNewProgramacasos = em.merge(programacasosListNewProgramacasos);
                    if (oldCasosespecialesOfProgramacasosListNewProgramacasos != null && !oldCasosespecialesOfProgramacasosListNewProgramacasos.equals(casosespeciales)) {
                        oldCasosespecialesOfProgramacasosListNewProgramacasos.getProgramacasosList().remove(programacasosListNewProgramacasos);
                        oldCasosespecialesOfProgramacasosListNewProgramacasos = em.merge(oldCasosespecialesOfProgramacasosListNewProgramacasos);
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
            List<Programacasos> programacasosListOrphanCheck = casosespeciales.getProgramacasosList();
            for (Programacasos programacasosListOrphanCheckProgramacasos : programacasosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Casosespeciales (" + casosespeciales + ") cannot be destroyed since the Programacasos " + programacasosListOrphanCheckProgramacasos + " in its programacasosList field has a non-nullable casosespeciales field.");
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
