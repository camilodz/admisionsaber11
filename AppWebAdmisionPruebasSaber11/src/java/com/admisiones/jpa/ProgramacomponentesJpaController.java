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
import com.admisiones.data.Componentesicfes;
import com.admisiones.data.Programa;
import com.admisiones.data.Periodoacademico;
import com.admisiones.data.Programacomponentes;
import com.admisiones.data.ProgramacomponentesPK;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Karen
 */
public class ProgramacomponentesJpaController implements Serializable {

    public ProgramacomponentesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacomponentes programacomponentes) throws PreexistingEntityException, Exception {
        if (programacomponentes.getProgramacomponentesPK() == null) {
            programacomponentes.setProgramacomponentesPK(new ProgramacomponentesPK());
        }
        programacomponentes.getProgramacomponentesPK().setCompid(programacomponentes.getComponentesicfes().getCompid());
        programacomponentes.getProgramacomponentesPK().setPerid(programacomponentes.getPeriodoacademico().getPerid());
        programacomponentes.getProgramacomponentesPK().setProid(programacomponentes.getPrograma().getProid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Componentesicfes componentesicfes = programacomponentes.getComponentesicfes();
            if (componentesicfes != null) {
                componentesicfes = em.getReference(componentesicfes.getClass(), componentesicfes.getCompid());
                programacomponentes.setComponentesicfes(componentesicfes);
            }
            Programa programa = programacomponentes.getPrograma();
            if (programa != null) {
                programa = em.getReference(programa.getClass(), programa.getProid());
                programacomponentes.setPrograma(programa);
            }
            Periodoacademico periodoacademico = programacomponentes.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico = em.getReference(periodoacademico.getClass(), periodoacademico.getPerid());
                programacomponentes.setPeriodoacademico(periodoacademico);
            }
            em.persist(programacomponentes);
            if (componentesicfes != null) {
                componentesicfes.getProgramacomponentesList().add(programacomponentes);
                componentesicfes = em.merge(componentesicfes);
            }
            if (programa != null) {
                programa.getProgramacomponentesList().add(programacomponentes);
                programa = em.merge(programa);
            }
            if (periodoacademico != null) {
                periodoacademico.getProgramacomponentesList().add(programacomponentes);
                periodoacademico = em.merge(periodoacademico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramacomponentes(programacomponentes.getProgramacomponentesPK()) != null) {
                throw new PreexistingEntityException("Programacomponentes " + programacomponentes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacomponentes programacomponentes) throws NonexistentEntityException, Exception {
        programacomponentes.getProgramacomponentesPK().setCompid(programacomponentes.getComponentesicfes().getCompid());
        programacomponentes.getProgramacomponentesPK().setPerid(programacomponentes.getPeriodoacademico().getPerid());
        programacomponentes.getProgramacomponentesPK().setProid(programacomponentes.getPrograma().getProid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacomponentes persistentProgramacomponentes = em.find(Programacomponentes.class, programacomponentes.getProgramacomponentesPK());
            Componentesicfes componentesicfesOld = persistentProgramacomponentes.getComponentesicfes();
            Componentesicfes componentesicfesNew = programacomponentes.getComponentesicfes();
            Programa programaOld = persistentProgramacomponentes.getPrograma();
            Programa programaNew = programacomponentes.getPrograma();
            Periodoacademico periodoacademicoOld = persistentProgramacomponentes.getPeriodoacademico();
            Periodoacademico periodoacademicoNew = programacomponentes.getPeriodoacademico();
            if (componentesicfesNew != null) {
                componentesicfesNew = em.getReference(componentesicfesNew.getClass(), componentesicfesNew.getCompid());
                programacomponentes.setComponentesicfes(componentesicfesNew);
            }
            if (programaNew != null) {
                programaNew = em.getReference(programaNew.getClass(), programaNew.getProid());
                programacomponentes.setPrograma(programaNew);
            }
            if (periodoacademicoNew != null) {
                periodoacademicoNew = em.getReference(periodoacademicoNew.getClass(), periodoacademicoNew.getPerid());
                programacomponentes.setPeriodoacademico(periodoacademicoNew);
            }
            programacomponentes = em.merge(programacomponentes);
            if (componentesicfesOld != null && !componentesicfesOld.equals(componentesicfesNew)) {
                componentesicfesOld.getProgramacomponentesList().remove(programacomponentes);
                componentesicfesOld = em.merge(componentesicfesOld);
            }
            if (componentesicfesNew != null && !componentesicfesNew.equals(componentesicfesOld)) {
                componentesicfesNew.getProgramacomponentesList().add(programacomponentes);
                componentesicfesNew = em.merge(componentesicfesNew);
            }
            if (programaOld != null && !programaOld.equals(programaNew)) {
                programaOld.getProgramacomponentesList().remove(programacomponentes);
                programaOld = em.merge(programaOld);
            }
            if (programaNew != null && !programaNew.equals(programaOld)) {
                programaNew.getProgramacomponentesList().add(programacomponentes);
                programaNew = em.merge(programaNew);
            }
            if (periodoacademicoOld != null && !periodoacademicoOld.equals(periodoacademicoNew)) {
                periodoacademicoOld.getProgramacomponentesList().remove(programacomponentes);
                periodoacademicoOld = em.merge(periodoacademicoOld);
            }
            if (periodoacademicoNew != null && !periodoacademicoNew.equals(periodoacademicoOld)) {
                periodoacademicoNew.getProgramacomponentesList().add(programacomponentes);
                periodoacademicoNew = em.merge(periodoacademicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProgramacomponentesPK id = programacomponentes.getProgramacomponentesPK();
                if (findProgramacomponentes(id) == null) {
                    throw new NonexistentEntityException("The programacomponentes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProgramacomponentesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacomponentes programacomponentes;
            try {
                programacomponentes = em.getReference(Programacomponentes.class, id);
                programacomponentes.getProgramacomponentesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacomponentes with id " + id + " no longer exists.", enfe);
            }
            Componentesicfes componentesicfes = programacomponentes.getComponentesicfes();
            if (componentesicfes != null) {
                componentesicfes.getProgramacomponentesList().remove(programacomponentes);
                componentesicfes = em.merge(componentesicfes);
            }
            Programa programa = programacomponentes.getPrograma();
            if (programa != null) {
                programa.getProgramacomponentesList().remove(programacomponentes);
                programa = em.merge(programa);
            }
            Periodoacademico periodoacademico = programacomponentes.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico.getProgramacomponentesList().remove(programacomponentes);
                periodoacademico = em.merge(periodoacademico);
            }
            em.remove(programacomponentes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacomponentes> findProgramacomponentesEntities() {
        return findProgramacomponentesEntities(true, -1, -1);
    }

    public List<Programacomponentes> findProgramacomponentesEntities(int maxResults, int firstResult) {
        return findProgramacomponentesEntities(false, maxResults, firstResult);
    }

    private List<Programacomponentes> findProgramacomponentesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programacomponentes.class));
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

    public Programacomponentes findProgramacomponentes(ProgramacomponentesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacomponentes.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacomponentesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programacomponentes> rt = cq.from(Programacomponentes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
