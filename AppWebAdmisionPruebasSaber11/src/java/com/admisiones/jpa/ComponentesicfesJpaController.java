/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.jpa;

import com.admisiones.data.Componentesicfes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.admisiones.data.Programacomponentes;
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
public class ComponentesicfesJpaController implements Serializable {

    public ComponentesicfesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Componentesicfes componentesicfes) throws PreexistingEntityException, Exception {
        if (componentesicfes.getProgramacomponentesList() == null) {
            componentesicfes.setProgramacomponentesList(new ArrayList<Programacomponentes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Programacomponentes> attachedProgramacomponentesList = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesListProgramacomponentesToAttach : componentesicfes.getProgramacomponentesList()) {
                programacomponentesListProgramacomponentesToAttach = em.getReference(programacomponentesListProgramacomponentesToAttach.getClass(), programacomponentesListProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesList.add(programacomponentesListProgramacomponentesToAttach);
            }
            componentesicfes.setProgramacomponentesList(attachedProgramacomponentesList);
            em.persist(componentesicfes);
            for (Programacomponentes programacomponentesListProgramacomponentes : componentesicfes.getProgramacomponentesList()) {
                Componentesicfes oldComponentesicfesOfProgramacomponentesListProgramacomponentes = programacomponentesListProgramacomponentes.getComponentesicfes();
                programacomponentesListProgramacomponentes.setComponentesicfes(componentesicfes);
                programacomponentesListProgramacomponentes = em.merge(programacomponentesListProgramacomponentes);
                if (oldComponentesicfesOfProgramacomponentesListProgramacomponentes != null) {
                    oldComponentesicfesOfProgramacomponentesListProgramacomponentes.getProgramacomponentesList().remove(programacomponentesListProgramacomponentes);
                    oldComponentesicfesOfProgramacomponentesListProgramacomponentes = em.merge(oldComponentesicfesOfProgramacomponentesListProgramacomponentes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComponentesicfes(componentesicfes.getCompid()) != null) {
                throw new PreexistingEntityException("Componentesicfes " + componentesicfes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Componentesicfes componentesicfes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Componentesicfes persistentComponentesicfes = em.find(Componentesicfes.class, componentesicfes.getCompid());
            List<Programacomponentes> programacomponentesListOld = persistentComponentesicfes.getProgramacomponentesList();
            List<Programacomponentes> programacomponentesListNew = componentesicfes.getProgramacomponentesList();
            List<String> illegalOrphanMessages = null;
            for (Programacomponentes programacomponentesListOldProgramacomponentes : programacomponentesListOld) {
                if (!programacomponentesListNew.contains(programacomponentesListOldProgramacomponentes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacomponentes " + programacomponentesListOldProgramacomponentes + " since its componentesicfes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Programacomponentes> attachedProgramacomponentesListNew = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesListNewProgramacomponentesToAttach : programacomponentesListNew) {
                programacomponentesListNewProgramacomponentesToAttach = em.getReference(programacomponentesListNewProgramacomponentesToAttach.getClass(), programacomponentesListNewProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesListNew.add(programacomponentesListNewProgramacomponentesToAttach);
            }
            programacomponentesListNew = attachedProgramacomponentesListNew;
            componentesicfes.setProgramacomponentesList(programacomponentesListNew);
            componentesicfes = em.merge(componentesicfes);
            for (Programacomponentes programacomponentesListNewProgramacomponentes : programacomponentesListNew) {
                if (!programacomponentesListOld.contains(programacomponentesListNewProgramacomponentes)) {
                    Componentesicfes oldComponentesicfesOfProgramacomponentesListNewProgramacomponentes = programacomponentesListNewProgramacomponentes.getComponentesicfes();
                    programacomponentesListNewProgramacomponentes.setComponentesicfes(componentesicfes);
                    programacomponentesListNewProgramacomponentes = em.merge(programacomponentesListNewProgramacomponentes);
                    if (oldComponentesicfesOfProgramacomponentesListNewProgramacomponentes != null && !oldComponentesicfesOfProgramacomponentesListNewProgramacomponentes.equals(componentesicfes)) {
                        oldComponentesicfesOfProgramacomponentesListNewProgramacomponentes.getProgramacomponentesList().remove(programacomponentesListNewProgramacomponentes);
                        oldComponentesicfesOfProgramacomponentesListNewProgramacomponentes = em.merge(oldComponentesicfesOfProgramacomponentesListNewProgramacomponentes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = componentesicfes.getCompid();
                if (findComponentesicfes(id) == null) {
                    throw new NonexistentEntityException("The componentesicfes with id " + id + " no longer exists.");
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
            Componentesicfes componentesicfes;
            try {
                componentesicfes = em.getReference(Componentesicfes.class, id);
                componentesicfes.getCompid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The componentesicfes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Programacomponentes> programacomponentesListOrphanCheck = componentesicfes.getProgramacomponentesList();
            for (Programacomponentes programacomponentesListOrphanCheckProgramacomponentes : programacomponentesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Componentesicfes (" + componentesicfes + ") cannot be destroyed since the Programacomponentes " + programacomponentesListOrphanCheckProgramacomponentes + " in its programacomponentesList field has a non-nullable componentesicfes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(componentesicfes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Componentesicfes> findComponentesicfesEntities() {
        return findComponentesicfesEntities(true, -1, -1);
    }

    public List<Componentesicfes> findComponentesicfesEntities(int maxResults, int firstResult) {
        return findComponentesicfesEntities(false, maxResults, firstResult);
    }

    private List<Componentesicfes> findComponentesicfesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Componentesicfes.class));
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

    public Componentesicfes findComponentesicfes(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Componentesicfes.class, id);
        } finally {
            em.close();
        }
    }

    public int getComponentesicfesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Componentesicfes> rt = cq.from(Componentesicfes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
