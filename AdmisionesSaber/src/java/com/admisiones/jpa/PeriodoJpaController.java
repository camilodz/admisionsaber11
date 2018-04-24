/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.jpa;

import com.admisiones.data.Periodo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class PeriodoJpaController implements Serializable {

    public PeriodoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodo periodo) throws PreexistingEntityException, Exception {
        if (periodo.getProgramaofertadoCollection() == null) {
            periodo.setProgramaofertadoCollection(new ArrayList<Programaofertado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Programaofertado> attachedProgramaofertadoCollection = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoCollectionProgramaofertadoToAttach : periodo.getProgramaofertadoCollection()) {
                programaofertadoCollectionProgramaofertadoToAttach = em.getReference(programaofertadoCollectionProgramaofertadoToAttach.getClass(), programaofertadoCollectionProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoCollection.add(programaofertadoCollectionProgramaofertadoToAttach);
            }
            periodo.setProgramaofertadoCollection(attachedProgramaofertadoCollection);
            em.persist(periodo);
            for (Programaofertado programaofertadoCollectionProgramaofertado : periodo.getProgramaofertadoCollection()) {
                Periodo oldPeriodoOfProgramaofertadoCollectionProgramaofertado = programaofertadoCollectionProgramaofertado.getPeriodo();
                programaofertadoCollectionProgramaofertado.setPeriodo(periodo);
                programaofertadoCollectionProgramaofertado = em.merge(programaofertadoCollectionProgramaofertado);
                if (oldPeriodoOfProgramaofertadoCollectionProgramaofertado != null) {
                    oldPeriodoOfProgramaofertadoCollectionProgramaofertado.getProgramaofertadoCollection().remove(programaofertadoCollectionProgramaofertado);
                    oldPeriodoOfProgramaofertadoCollectionProgramaofertado = em.merge(oldPeriodoOfProgramaofertadoCollectionProgramaofertado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeriodo(periodo.getPerid()) != null) {
                throw new PreexistingEntityException("Periodo " + periodo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodo periodo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo persistentPeriodo = em.find(Periodo.class, periodo.getPerid());
            Collection<Programaofertado> programaofertadoCollectionOld = persistentPeriodo.getProgramaofertadoCollection();
            Collection<Programaofertado> programaofertadoCollectionNew = periodo.getProgramaofertadoCollection();
            List<String> illegalOrphanMessages = null;
            for (Programaofertado programaofertadoCollectionOldProgramaofertado : programaofertadoCollectionOld) {
                if (!programaofertadoCollectionNew.contains(programaofertadoCollectionOldProgramaofertado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programaofertado " + programaofertadoCollectionOldProgramaofertado + " since its periodo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Programaofertado> attachedProgramaofertadoCollectionNew = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoCollectionNewProgramaofertadoToAttach : programaofertadoCollectionNew) {
                programaofertadoCollectionNewProgramaofertadoToAttach = em.getReference(programaofertadoCollectionNewProgramaofertadoToAttach.getClass(), programaofertadoCollectionNewProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoCollectionNew.add(programaofertadoCollectionNewProgramaofertadoToAttach);
            }
            programaofertadoCollectionNew = attachedProgramaofertadoCollectionNew;
            periodo.setProgramaofertadoCollection(programaofertadoCollectionNew);
            periodo = em.merge(periodo);
            for (Programaofertado programaofertadoCollectionNewProgramaofertado : programaofertadoCollectionNew) {
                if (!programaofertadoCollectionOld.contains(programaofertadoCollectionNewProgramaofertado)) {
                    Periodo oldPeriodoOfProgramaofertadoCollectionNewProgramaofertado = programaofertadoCollectionNewProgramaofertado.getPeriodo();
                    programaofertadoCollectionNewProgramaofertado.setPeriodo(periodo);
                    programaofertadoCollectionNewProgramaofertado = em.merge(programaofertadoCollectionNewProgramaofertado);
                    if (oldPeriodoOfProgramaofertadoCollectionNewProgramaofertado != null && !oldPeriodoOfProgramaofertadoCollectionNewProgramaofertado.equals(periodo)) {
                        oldPeriodoOfProgramaofertadoCollectionNewProgramaofertado.getProgramaofertadoCollection().remove(programaofertadoCollectionNewProgramaofertado);
                        oldPeriodoOfProgramaofertadoCollectionNewProgramaofertado = em.merge(oldPeriodoOfProgramaofertadoCollectionNewProgramaofertado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = periodo.getPerid();
                if (findPeriodo(id) == null) {
                    throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo periodo;
            try {
                periodo = em.getReference(Periodo.class, id);
                periodo.getPerid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Programaofertado> programaofertadoCollectionOrphanCheck = periodo.getProgramaofertadoCollection();
            for (Programaofertado programaofertadoCollectionOrphanCheckProgramaofertado : programaofertadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodo (" + periodo + ") cannot be destroyed since the Programaofertado " + programaofertadoCollectionOrphanCheckProgramaofertado + " in its programaofertadoCollection field has a non-nullable periodo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(periodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodo> findPeriodoEntities() {
        return findPeriodoEntities(true, -1, -1);
    }

    public List<Periodo> findPeriodoEntities(int maxResults, int firstResult) {
        return findPeriodoEntities(false, maxResults, firstResult);
    }

    private List<Periodo> findPeriodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodo.class));
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

    public Periodo findPeriodo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periodo> rt = cq.from(Periodo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
