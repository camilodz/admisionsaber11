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
import com.admisiones.data.Pruebaadicional;
import com.admisiones.data.PruebaadicionalPK;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JORGE
 */
public class PruebaadicionalJpaController implements Serializable {

    public PruebaadicionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pruebaadicional pruebaadicional) throws PreexistingEntityException, Exception {
        if (pruebaadicional.getPruebaadicionalPK() == null) {
            pruebaadicional.setPruebaadicionalPK(new PruebaadicionalPK());
        }
        pruebaadicional.getPruebaadicionalPK().setPerid(pruebaadicional.getPeriodoacademico().getPerid());
        pruebaadicional.getPruebaadicionalPK().setProid(pruebaadicional.getPrograma().getProid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodoacademico periodoacademico = pruebaadicional.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico = em.getReference(periodoacademico.getClass(), periodoacademico.getPerid());
                pruebaadicional.setPeriodoacademico(periodoacademico);
            }
            Programa programa = pruebaadicional.getPrograma();
            if (programa != null) {
                programa = em.getReference(programa.getClass(), programa.getProid());
                pruebaadicional.setPrograma(programa);
            }
            em.persist(pruebaadicional);
            if (periodoacademico != null) {
                periodoacademico.getPruebaadicionalCollection().add(pruebaadicional);
                periodoacademico = em.merge(periodoacademico);
            }
            if (programa != null) {
                programa.getPruebaadicionalCollection().add(pruebaadicional);
                programa = em.merge(programa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPruebaadicional(pruebaadicional.getPruebaadicionalPK()) != null) {
                throw new PreexistingEntityException("Pruebaadicional " + pruebaadicional + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pruebaadicional pruebaadicional) throws NonexistentEntityException, Exception {
        pruebaadicional.getPruebaadicionalPK().setPerid(pruebaadicional.getPeriodoacademico().getPerid());
        pruebaadicional.getPruebaadicionalPK().setProid(pruebaadicional.getPrograma().getProid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pruebaadicional persistentPruebaadicional = em.find(Pruebaadicional.class, pruebaadicional.getPruebaadicionalPK());
            Periodoacademico periodoacademicoOld = persistentPruebaadicional.getPeriodoacademico();
            Periodoacademico periodoacademicoNew = pruebaadicional.getPeriodoacademico();
            Programa programaOld = persistentPruebaadicional.getPrograma();
            Programa programaNew = pruebaadicional.getPrograma();
            if (periodoacademicoNew != null) {
                periodoacademicoNew = em.getReference(periodoacademicoNew.getClass(), periodoacademicoNew.getPerid());
                pruebaadicional.setPeriodoacademico(periodoacademicoNew);
            }
            if (programaNew != null) {
                programaNew = em.getReference(programaNew.getClass(), programaNew.getProid());
                pruebaadicional.setPrograma(programaNew);
            }
            pruebaadicional = em.merge(pruebaadicional);
            if (periodoacademicoOld != null && !periodoacademicoOld.equals(periodoacademicoNew)) {
                periodoacademicoOld.getPruebaadicionalCollection().remove(pruebaadicional);
                periodoacademicoOld = em.merge(periodoacademicoOld);
            }
            if (periodoacademicoNew != null && !periodoacademicoNew.equals(periodoacademicoOld)) {
                periodoacademicoNew.getPruebaadicionalCollection().add(pruebaadicional);
                periodoacademicoNew = em.merge(periodoacademicoNew);
            }
            if (programaOld != null && !programaOld.equals(programaNew)) {
                programaOld.getPruebaadicionalCollection().remove(pruebaadicional);
                programaOld = em.merge(programaOld);
            }
            if (programaNew != null && !programaNew.equals(programaOld)) {
                programaNew.getPruebaadicionalCollection().add(pruebaadicional);
                programaNew = em.merge(programaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PruebaadicionalPK id = pruebaadicional.getPruebaadicionalPK();
                if (findPruebaadicional(id) == null) {
                    throw new NonexistentEntityException("The pruebaadicional with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PruebaadicionalPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pruebaadicional pruebaadicional;
            try {
                pruebaadicional = em.getReference(Pruebaadicional.class, id);
                pruebaadicional.getPruebaadicionalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pruebaadicional with id " + id + " no longer exists.", enfe);
            }
            Periodoacademico periodoacademico = pruebaadicional.getPeriodoacademico();
            if (periodoacademico != null) {
                periodoacademico.getPruebaadicionalCollection().remove(pruebaadicional);
                periodoacademico = em.merge(periodoacademico);
            }
            Programa programa = pruebaadicional.getPrograma();
            if (programa != null) {
                programa.getPruebaadicionalCollection().remove(pruebaadicional);
                programa = em.merge(programa);
            }
            em.remove(pruebaadicional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pruebaadicional> findPruebaadicionalEntities() {
        return findPruebaadicionalEntities(true, -1, -1);
    }

    public List<Pruebaadicional> findPruebaadicionalEntities(int maxResults, int firstResult) {
        return findPruebaadicionalEntities(false, maxResults, firstResult);
    }

    private List<Pruebaadicional> findPruebaadicionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pruebaadicional.class));
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

    public Pruebaadicional findPruebaadicional(PruebaadicionalPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pruebaadicional.class, id);
        } finally {
            em.close();
        }
    }

    public int getPruebaadicionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pruebaadicional> rt = cq.from(Pruebaadicional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
