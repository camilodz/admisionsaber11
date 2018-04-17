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
import com.admisiones.data.Periodoacademico;
import com.admisiones.data.Programa;
import com.admisiones.data.Programaofertado;
import com.admisiones.data.ProgramaofertadoPK;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Karen
 */
public class ProgramaofertadoJpaController implements Serializable {

    public ProgramaofertadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programaofertado programaofertado) throws PreexistingEntityException, Exception {
        if (programaofertado.getProgramaofertadoPK() == null) {
            programaofertado.setProgramaofertadoPK(new ProgramaofertadoPK());
        }
        programaofertado.getProgramaofertadoPK().setPerid(programaofertado.getPeriodoacademico().getPerid());
        programaofertado.getProgramaofertadoPK().setProid(programaofertado.getPrograma().getProid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodoacademico periodoacademico = programaofertado.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico = em.getReference(periodoacademico.getClass(), periodoacademico.getPerid());
                programaofertado.setPeriodoacademico(periodoacademico);
            }
            Programa programa = programaofertado.getPrograma();
            if (programa != null) {
                programa = em.getReference(programa.getClass(), programa.getProid());
                programaofertado.setPrograma(programa);
            }
            em.persist(programaofertado);
            if (periodoacademico != null) {
                periodoacademico.getProgramaofertadoList().add(programaofertado);
                periodoacademico = em.merge(periodoacademico);
            }
            if (programa != null) {
                programa.getProgramaofertadoList().add(programaofertado);
                programa = em.merge(programa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramaofertado(programaofertado.getProgramaofertadoPK()) != null) {
                throw new PreexistingEntityException("Programaofertado " + programaofertado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programaofertado programaofertado) throws NonexistentEntityException, Exception {
        programaofertado.getProgramaofertadoPK().setPerid(programaofertado.getPeriodoacademico().getPerid());
        programaofertado.getProgramaofertadoPK().setProid(programaofertado.getPrograma().getProid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programaofertado persistentProgramaofertado = em.find(Programaofertado.class, programaofertado.getProgramaofertadoPK());
            Periodoacademico periodoacademicoOld = persistentProgramaofertado.getPeriodoacademico();
            Periodoacademico periodoacademicoNew = programaofertado.getPeriodoacademico();
            Programa programaOld = persistentProgramaofertado.getPrograma();
            Programa programaNew = programaofertado.getPrograma();
            if (periodoacademicoNew != null) {
                periodoacademicoNew = em.getReference(periodoacademicoNew.getClass(), periodoacademicoNew.getPerid());
                programaofertado.setPeriodoacademico(periodoacademicoNew);
            }
            if (programaNew != null) {
                programaNew = em.getReference(programaNew.getClass(), programaNew.getProid());
                programaofertado.setPrograma(programaNew);
            }
            programaofertado = em.merge(programaofertado);
            if (periodoacademicoOld != null && !periodoacademicoOld.equals(periodoacademicoNew)) {
                periodoacademicoOld.getProgramaofertadoList().remove(programaofertado);
                periodoacademicoOld = em.merge(periodoacademicoOld);
            }
            if (periodoacademicoNew != null && !periodoacademicoNew.equals(periodoacademicoOld)) {
                periodoacademicoNew.getProgramaofertadoList().add(programaofertado);
                periodoacademicoNew = em.merge(periodoacademicoNew);
            }
            if (programaOld != null && !programaOld.equals(programaNew)) {
                programaOld.getProgramaofertadoList().remove(programaofertado);
                programaOld = em.merge(programaOld);
            }
            if (programaNew != null && !programaNew.equals(programaOld)) {
                programaNew.getProgramaofertadoList().add(programaofertado);
                programaNew = em.merge(programaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProgramaofertadoPK id = programaofertado.getProgramaofertadoPK();
                if (findProgramaofertado(id) == null) {
                    throw new NonexistentEntityException("The programaofertado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProgramaofertadoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programaofertado programaofertado;
            try {
                programaofertado = em.getReference(Programaofertado.class, id);
                programaofertado.getProgramaofertadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programaofertado with id " + id + " no longer exists.", enfe);
            }
            Periodoacademico periodoacademico = programaofertado.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico.getProgramaofertadoList().remove(programaofertado);
                periodoacademico = em.merge(periodoacademico);
            }
            Programa programa = programaofertado.getPrograma();
            if (programa != null) {
                programa.getProgramaofertadoList().remove(programaofertado);
                programa = em.merge(programa);
            }
            em.remove(programaofertado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programaofertado> findProgramaofertadoEntities() {
        return findProgramaofertadoEntities(true, -1, -1);
    }

    public List<Programaofertado> findProgramaofertadoEntities(int maxResults, int firstResult) {
        return findProgramaofertadoEntities(false, maxResults, firstResult);
    }

    private List<Programaofertado> findProgramaofertadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programaofertado.class));
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

    public Programaofertado findProgramaofertado(ProgramaofertadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programaofertado.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaofertadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programaofertado> rt = cq.from(Programaofertado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
