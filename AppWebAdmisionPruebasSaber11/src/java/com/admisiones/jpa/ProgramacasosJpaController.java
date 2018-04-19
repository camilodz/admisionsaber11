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
import com.admisiones.data.Casosespeciales;
import com.admisiones.data.Programa;
import com.admisiones.data.Periodoacademico;
import com.admisiones.data.Programacasos;
import com.admisiones.data.ProgramacasosPK;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JORGE
 */
public class ProgramacasosJpaController implements Serializable {

    public ProgramacasosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacasos programacasos) throws PreexistingEntityException, Exception {
        if (programacasos.getProgramacasosPK() == null) {
            programacasos.setProgramacasosPK(new ProgramacasosPK());
        }
        programacasos.getProgramacasosPK().setPerid(programacasos.getPeriodoacademico().getPerid());
        programacasos.getProgramacasosPK().setProid(programacasos.getPrograma().getProid());
        programacasos.getProgramacasosPK().setEspid(programacasos.getCasosespeciales().getEspid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Casosespeciales casosespeciales = programacasos.getCasosespeciales();
            if (casosespeciales != null) {
                casosespeciales = em.getReference(casosespeciales.getClass(), casosespeciales.getEspid());
                programacasos.setCasosespeciales(casosespeciales);
            }
            Programa programa = programacasos.getPrograma();
            if (programa != null) {
                programa = em.getReference(programa.getClass(), programa.getProid());
                programacasos.setPrograma(programa);
            }
            Periodoacademico periodoacademico = programacasos.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico = em.getReference(periodoacademico.getClass(), periodoacademico.getPerid());
                programacasos.setPeriodoacademico(periodoacademico);
            }
            em.persist(programacasos);
            if (casosespeciales != null) {
                casosespeciales.getProgramacasosCollection().add(programacasos);
                casosespeciales = em.merge(casosespeciales);
            }
            if (programa != null) {
                programa.getProgramacasosCollection().add(programacasos);
                programa = em.merge(programa);
            }
            if (periodoacademico != null) {
                periodoacademico.getProgramacasosCollection().add(programacasos);
                periodoacademico = em.merge(periodoacademico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramacasos(programacasos.getProgramacasosPK()) != null) {
                throw new PreexistingEntityException("Programacasos " + programacasos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacasos programacasos) throws NonexistentEntityException, Exception {
        programacasos.getProgramacasosPK().setPerid(programacasos.getPeriodoacademico().getPerid());
        programacasos.getProgramacasosPK().setProid(programacasos.getPrograma().getProid());
        programacasos.getProgramacasosPK().setEspid(programacasos.getCasosespeciales().getEspid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacasos persistentProgramacasos = em.find(Programacasos.class, programacasos.getProgramacasosPK());
            Casosespeciales casosespecialesOld = persistentProgramacasos.getCasosespeciales();
            Casosespeciales casosespecialesNew = programacasos.getCasosespeciales();
            Programa programaOld = persistentProgramacasos.getPrograma();
            Programa programaNew = programacasos.getPrograma();
            Periodoacademico periodoacademicoOld = persistentProgramacasos.getPeriodoacademico();
            Periodoacademico periodoacademicoNew = programacasos.getPeriodoacademico();
            if (casosespecialesNew != null) {
                casosespecialesNew = em.getReference(casosespecialesNew.getClass(), casosespecialesNew.getEspid());
                programacasos.setCasosespeciales(casosespecialesNew);
            }
            if (programaNew != null) {
                programaNew = em.getReference(programaNew.getClass(), programaNew.getProid());
                programacasos.setPrograma(programaNew);
            }
            if (periodoacademicoNew != null) {
                periodoacademicoNew = em.getReference(periodoacademicoNew.getClass(), periodoacademicoNew.getPerid());
                programacasos.setPeriodoacademico(periodoacademicoNew);
            }
            programacasos = em.merge(programacasos);
            if (casosespecialesOld != null && !casosespecialesOld.equals(casosespecialesNew)) {
                casosespecialesOld.getProgramacasosCollection().remove(programacasos);
                casosespecialesOld = em.merge(casosespecialesOld);
            }
            if (casosespecialesNew != null && !casosespecialesNew.equals(casosespecialesOld)) {
                casosespecialesNew.getProgramacasosCollection().add(programacasos);
                casosespecialesNew = em.merge(casosespecialesNew);
            }
            if (programaOld != null && !programaOld.equals(programaNew)) {
                programaOld.getProgramacasosCollection().remove(programacasos);
                programaOld = em.merge(programaOld);
            }
            if (programaNew != null && !programaNew.equals(programaOld)) {
                programaNew.getProgramacasosCollection().add(programacasos);
                programaNew = em.merge(programaNew);
            }
            if (periodoacademicoOld != null && !periodoacademicoOld.equals(periodoacademicoNew)) {
                periodoacademicoOld.getProgramacasosCollection().remove(programacasos);
                periodoacademicoOld = em.merge(periodoacademicoOld);
            }
            if (periodoacademicoNew != null && !periodoacademicoNew.equals(periodoacademicoOld)) {
                periodoacademicoNew.getProgramacasosCollection().add(programacasos);
                periodoacademicoNew = em.merge(periodoacademicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProgramacasosPK id = programacasos.getProgramacasosPK();
                if (findProgramacasos(id) == null) {
                    throw new NonexistentEntityException("The programacasos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProgramacasosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacasos programacasos;
            try {
                programacasos = em.getReference(Programacasos.class, id);
                programacasos.getProgramacasosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacasos with id " + id + " no longer exists.", enfe);
            }
            Casosespeciales casosespeciales = programacasos.getCasosespeciales();
            if (casosespeciales != null) {
                casosespeciales.getProgramacasosCollection().remove(programacasos);
                casosespeciales = em.merge(casosespeciales);
            }
            Programa programa = programacasos.getPrograma();
            if (programa != null) {
                programa.getProgramacasosCollection().remove(programacasos);
                programa = em.merge(programa);
            }
            Periodoacademico periodoacademico = programacasos.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico.getProgramacasosCollection().remove(programacasos);
                periodoacademico = em.merge(periodoacademico);
            }
            em.remove(programacasos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacasos> findProgramacasosEntities() {
        return findProgramacasosEntities(true, -1, -1);
    }

    public List<Programacasos> findProgramacasosEntities(int maxResults, int firstResult) {
        return findProgramacasosEntities(false, maxResults, firstResult);
    }

    private List<Programacasos> findProgramacasosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programacasos.class));
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

    public Programacasos findProgramacasos(ProgramacasosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacasos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacasosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programacasos> rt = cq.from(Programacasos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
