/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.jpa;

import com.admisiones.data.Periodoacademico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.admisiones.data.Programacasos;
import java.util.ArrayList;
import java.util.Collection;
import com.admisiones.data.Programacomponentes;
import com.admisiones.data.Programaofertado;
import com.admisiones.data.Pruebaadicional;
import com.admisiones.jpa.exceptions.IllegalOrphanException;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JORGE
 */
public class PeriodoacademicoJpaController implements Serializable {

    public PeriodoacademicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodoacademico periodoacademico) throws PreexistingEntityException, Exception {
        if (periodoacademico.getProgramacasosCollection() == null) {
            periodoacademico.setProgramacasosCollection(new ArrayList<Programacasos>());
        }
        if (periodoacademico.getProgramacomponentesCollection() == null) {
            periodoacademico.setProgramacomponentesCollection(new ArrayList<Programacomponentes>());
        }
        if (periodoacademico.getProgramaofertadoCollection() == null) {
            periodoacademico.setProgramaofertadoCollection(new ArrayList<Programaofertado>());
        }
        if (periodoacademico.getPruebaadicionalCollection() == null) {
            periodoacademico.setPruebaadicionalCollection(new ArrayList<Pruebaadicional>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Programacasos> attachedProgramacasosCollection = new ArrayList<Programacasos>();
            for (Programacasos programacasosCollectionProgramacasosToAttach : periodoacademico.getProgramacasosCollection()) {
                programacasosCollectionProgramacasosToAttach = em.getReference(programacasosCollectionProgramacasosToAttach.getClass(), programacasosCollectionProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosCollection.add(programacasosCollectionProgramacasosToAttach);
            }
            periodoacademico.setProgramacasosCollection(attachedProgramacasosCollection);
            Collection<Programacomponentes> attachedProgramacomponentesCollection = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesCollectionProgramacomponentesToAttach : periodoacademico.getProgramacomponentesCollection()) {
                programacomponentesCollectionProgramacomponentesToAttach = em.getReference(programacomponentesCollectionProgramacomponentesToAttach.getClass(), programacomponentesCollectionProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesCollection.add(programacomponentesCollectionProgramacomponentesToAttach);
            }
            periodoacademico.setProgramacomponentesCollection(attachedProgramacomponentesCollection);
            Collection<Programaofertado> attachedProgramaofertadoCollection = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoCollectionProgramaofertadoToAttach : periodoacademico.getProgramaofertadoCollection()) {
                programaofertadoCollectionProgramaofertadoToAttach = em.getReference(programaofertadoCollectionProgramaofertadoToAttach.getClass(), programaofertadoCollectionProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoCollection.add(programaofertadoCollectionProgramaofertadoToAttach);
            }
            periodoacademico.setProgramaofertadoCollection(attachedProgramaofertadoCollection);
            Collection<Pruebaadicional> attachedPruebaadicionalCollection = new ArrayList<Pruebaadicional>();
            for (Pruebaadicional pruebaadicionalCollectionPruebaadicionalToAttach : periodoacademico.getPruebaadicionalCollection()) {
                pruebaadicionalCollectionPruebaadicionalToAttach = em.getReference(pruebaadicionalCollectionPruebaadicionalToAttach.getClass(), pruebaadicionalCollectionPruebaadicionalToAttach.getPruebaadicionalPK());
                attachedPruebaadicionalCollection.add(pruebaadicionalCollectionPruebaadicionalToAttach);
            }
            periodoacademico.setPruebaadicionalCollection(attachedPruebaadicionalCollection);
            em.persist(periodoacademico);
            for (Programacasos programacasosCollectionProgramacasos : periodoacademico.getProgramacasosCollection()) {
                Periodoacademico oldPeriodoacademicoOfProgramacasosCollectionProgramacasos = programacasosCollectionProgramacasos.getPeriodoacademico();
                programacasosCollectionProgramacasos.setPeriodoacademico(periodoacademico);
                programacasosCollectionProgramacasos = em.merge(programacasosCollectionProgramacasos);
                if (oldPeriodoacademicoOfProgramacasosCollectionProgramacasos != null) {
                    oldPeriodoacademicoOfProgramacasosCollectionProgramacasos.getProgramacasosCollection().remove(programacasosCollectionProgramacasos);
                    oldPeriodoacademicoOfProgramacasosCollectionProgramacasos = em.merge(oldPeriodoacademicoOfProgramacasosCollectionProgramacasos);
                }
            }
            for (Programacomponentes programacomponentesCollectionProgramacomponentes : periodoacademico.getProgramacomponentesCollection()) {
                Periodoacademico oldPeriodoacademicoOfProgramacomponentesCollectionProgramacomponentes = programacomponentesCollectionProgramacomponentes.getPeriodoacademico();
                programacomponentesCollectionProgramacomponentes.setPeriodoacademico(periodoacademico);
                programacomponentesCollectionProgramacomponentes = em.merge(programacomponentesCollectionProgramacomponentes);
                if (oldPeriodoacademicoOfProgramacomponentesCollectionProgramacomponentes != null) {
                    oldPeriodoacademicoOfProgramacomponentesCollectionProgramacomponentes.getProgramacomponentesCollection().remove(programacomponentesCollectionProgramacomponentes);
                    oldPeriodoacademicoOfProgramacomponentesCollectionProgramacomponentes = em.merge(oldPeriodoacademicoOfProgramacomponentesCollectionProgramacomponentes);
                }
            }
            for (Programaofertado programaofertadoCollectionProgramaofertado : periodoacademico.getProgramaofertadoCollection()) {
                Periodoacademico oldPeriodoacademicoOfProgramaofertadoCollectionProgramaofertado = programaofertadoCollectionProgramaofertado.getPeriodoacademico();
                programaofertadoCollectionProgramaofertado.setPeriodoacademico(periodoacademico);
                programaofertadoCollectionProgramaofertado = em.merge(programaofertadoCollectionProgramaofertado);
                if (oldPeriodoacademicoOfProgramaofertadoCollectionProgramaofertado != null) {
                    oldPeriodoacademicoOfProgramaofertadoCollectionProgramaofertado.getProgramaofertadoCollection().remove(programaofertadoCollectionProgramaofertado);
                    oldPeriodoacademicoOfProgramaofertadoCollectionProgramaofertado = em.merge(oldPeriodoacademicoOfProgramaofertadoCollectionProgramaofertado);
                }
            }
            for (Pruebaadicional pruebaadicionalCollectionPruebaadicional : periodoacademico.getPruebaadicionalCollection()) {
                Periodoacademico oldPeriodoacademicoOfPruebaadicionalCollectionPruebaadicional = pruebaadicionalCollectionPruebaadicional.getPeriodoacademico();
                pruebaadicionalCollectionPruebaadicional.setPeriodoacademico(periodoacademico);
                pruebaadicionalCollectionPruebaadicional = em.merge(pruebaadicionalCollectionPruebaadicional);
                if (oldPeriodoacademicoOfPruebaadicionalCollectionPruebaadicional != null) {
                    oldPeriodoacademicoOfPruebaadicionalCollectionPruebaadicional.getPruebaadicionalCollection().remove(pruebaadicionalCollectionPruebaadicional);
                    oldPeriodoacademicoOfPruebaadicionalCollectionPruebaadicional = em.merge(oldPeriodoacademicoOfPruebaadicionalCollectionPruebaadicional);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeriodoacademico(periodoacademico.getPerid()) != null) {
                throw new PreexistingEntityException("Periodoacademico " + periodoacademico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodoacademico periodoacademico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodoacademico persistentPeriodoacademico = em.find(Periodoacademico.class, periodoacademico.getPerid());
            Collection<Programacasos> programacasosCollectionOld = persistentPeriodoacademico.getProgramacasosCollection();
            Collection<Programacasos> programacasosCollectionNew = periodoacademico.getProgramacasosCollection();
            Collection<Programacomponentes> programacomponentesCollectionOld = persistentPeriodoacademico.getProgramacomponentesCollection();
            Collection<Programacomponentes> programacomponentesCollectionNew = periodoacademico.getProgramacomponentesCollection();
            Collection<Programaofertado> programaofertadoCollectionOld = persistentPeriodoacademico.getProgramaofertadoCollection();
            Collection<Programaofertado> programaofertadoCollectionNew = periodoacademico.getProgramaofertadoCollection();
            Collection<Pruebaadicional> pruebaadicionalCollectionOld = persistentPeriodoacademico.getPruebaadicionalCollection();
            Collection<Pruebaadicional> pruebaadicionalCollectionNew = periodoacademico.getPruebaadicionalCollection();
            List<String> illegalOrphanMessages = null;
            for (Programacasos programacasosCollectionOldProgramacasos : programacasosCollectionOld) {
                if (!programacasosCollectionNew.contains(programacasosCollectionOldProgramacasos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacasos " + programacasosCollectionOldProgramacasos + " since its periodoacademico field is not nullable.");
                }
            }
            for (Programacomponentes programacomponentesCollectionOldProgramacomponentes : programacomponentesCollectionOld) {
                if (!programacomponentesCollectionNew.contains(programacomponentesCollectionOldProgramacomponentes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacomponentes " + programacomponentesCollectionOldProgramacomponentes + " since its periodoacademico field is not nullable.");
                }
            }
            for (Programaofertado programaofertadoCollectionOldProgramaofertado : programaofertadoCollectionOld) {
                if (!programaofertadoCollectionNew.contains(programaofertadoCollectionOldProgramaofertado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programaofertado " + programaofertadoCollectionOldProgramaofertado + " since its periodoacademico field is not nullable.");
                }
            }
            for (Pruebaadicional pruebaadicionalCollectionOldPruebaadicional : pruebaadicionalCollectionOld) {
                if (!pruebaadicionalCollectionNew.contains(pruebaadicionalCollectionOldPruebaadicional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pruebaadicional " + pruebaadicionalCollectionOldPruebaadicional + " since its periodoacademico field is not nullable.");
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
            periodoacademico.setProgramacasosCollection(programacasosCollectionNew);
            Collection<Programacomponentes> attachedProgramacomponentesCollectionNew = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesCollectionNewProgramacomponentesToAttach : programacomponentesCollectionNew) {
                programacomponentesCollectionNewProgramacomponentesToAttach = em.getReference(programacomponentesCollectionNewProgramacomponentesToAttach.getClass(), programacomponentesCollectionNewProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesCollectionNew.add(programacomponentesCollectionNewProgramacomponentesToAttach);
            }
            programacomponentesCollectionNew = attachedProgramacomponentesCollectionNew;
            periodoacademico.setProgramacomponentesCollection(programacomponentesCollectionNew);
            Collection<Programaofertado> attachedProgramaofertadoCollectionNew = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoCollectionNewProgramaofertadoToAttach : programaofertadoCollectionNew) {
                programaofertadoCollectionNewProgramaofertadoToAttach = em.getReference(programaofertadoCollectionNewProgramaofertadoToAttach.getClass(), programaofertadoCollectionNewProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoCollectionNew.add(programaofertadoCollectionNewProgramaofertadoToAttach);
            }
            programaofertadoCollectionNew = attachedProgramaofertadoCollectionNew;
            periodoacademico.setProgramaofertadoCollection(programaofertadoCollectionNew);
            Collection<Pruebaadicional> attachedPruebaadicionalCollectionNew = new ArrayList<Pruebaadicional>();
            for (Pruebaadicional pruebaadicionalCollectionNewPruebaadicionalToAttach : pruebaadicionalCollectionNew) {
                pruebaadicionalCollectionNewPruebaadicionalToAttach = em.getReference(pruebaadicionalCollectionNewPruebaadicionalToAttach.getClass(), pruebaadicionalCollectionNewPruebaadicionalToAttach.getPruebaadicionalPK());
                attachedPruebaadicionalCollectionNew.add(pruebaadicionalCollectionNewPruebaadicionalToAttach);
            }
            pruebaadicionalCollectionNew = attachedPruebaadicionalCollectionNew;
            periodoacademico.setPruebaadicionalCollection(pruebaadicionalCollectionNew);
            periodoacademico = em.merge(periodoacademico);
            for (Programacasos programacasosCollectionNewProgramacasos : programacasosCollectionNew) {
                if (!programacasosCollectionOld.contains(programacasosCollectionNewProgramacasos)) {
                    Periodoacademico oldPeriodoacademicoOfProgramacasosCollectionNewProgramacasos = programacasosCollectionNewProgramacasos.getPeriodoacademico();
                    programacasosCollectionNewProgramacasos.setPeriodoacademico(periodoacademico);
                    programacasosCollectionNewProgramacasos = em.merge(programacasosCollectionNewProgramacasos);
                    if (oldPeriodoacademicoOfProgramacasosCollectionNewProgramacasos != null && !oldPeriodoacademicoOfProgramacasosCollectionNewProgramacasos.equals(periodoacademico)) {
                        oldPeriodoacademicoOfProgramacasosCollectionNewProgramacasos.getProgramacasosCollection().remove(programacasosCollectionNewProgramacasos);
                        oldPeriodoacademicoOfProgramacasosCollectionNewProgramacasos = em.merge(oldPeriodoacademicoOfProgramacasosCollectionNewProgramacasos);
                    }
                }
            }
            for (Programacomponentes programacomponentesCollectionNewProgramacomponentes : programacomponentesCollectionNew) {
                if (!programacomponentesCollectionOld.contains(programacomponentesCollectionNewProgramacomponentes)) {
                    Periodoacademico oldPeriodoacademicoOfProgramacomponentesCollectionNewProgramacomponentes = programacomponentesCollectionNewProgramacomponentes.getPeriodoacademico();
                    programacomponentesCollectionNewProgramacomponentes.setPeriodoacademico(periodoacademico);
                    programacomponentesCollectionNewProgramacomponentes = em.merge(programacomponentesCollectionNewProgramacomponentes);
                    if (oldPeriodoacademicoOfProgramacomponentesCollectionNewProgramacomponentes != null && !oldPeriodoacademicoOfProgramacomponentesCollectionNewProgramacomponentes.equals(periodoacademico)) {
                        oldPeriodoacademicoOfProgramacomponentesCollectionNewProgramacomponentes.getProgramacomponentesCollection().remove(programacomponentesCollectionNewProgramacomponentes);
                        oldPeriodoacademicoOfProgramacomponentesCollectionNewProgramacomponentes = em.merge(oldPeriodoacademicoOfProgramacomponentesCollectionNewProgramacomponentes);
                    }
                }
            }
            for (Programaofertado programaofertadoCollectionNewProgramaofertado : programaofertadoCollectionNew) {
                if (!programaofertadoCollectionOld.contains(programaofertadoCollectionNewProgramaofertado)) {
                    Periodoacademico oldPeriodoacademicoOfProgramaofertadoCollectionNewProgramaofertado = programaofertadoCollectionNewProgramaofertado.getPeriodoacademico();
                    programaofertadoCollectionNewProgramaofertado.setPeriodoacademico(periodoacademico);
                    programaofertadoCollectionNewProgramaofertado = em.merge(programaofertadoCollectionNewProgramaofertado);
                    if (oldPeriodoacademicoOfProgramaofertadoCollectionNewProgramaofertado != null && !oldPeriodoacademicoOfProgramaofertadoCollectionNewProgramaofertado.equals(periodoacademico)) {
                        oldPeriodoacademicoOfProgramaofertadoCollectionNewProgramaofertado.getProgramaofertadoCollection().remove(programaofertadoCollectionNewProgramaofertado);
                        oldPeriodoacademicoOfProgramaofertadoCollectionNewProgramaofertado = em.merge(oldPeriodoacademicoOfProgramaofertadoCollectionNewProgramaofertado);
                    }
                }
            }
            for (Pruebaadicional pruebaadicionalCollectionNewPruebaadicional : pruebaadicionalCollectionNew) {
                if (!pruebaadicionalCollectionOld.contains(pruebaadicionalCollectionNewPruebaadicional)) {
                    Periodoacademico oldPeriodoacademicoOfPruebaadicionalCollectionNewPruebaadicional = pruebaadicionalCollectionNewPruebaadicional.getPeriodoacademico();
                    pruebaadicionalCollectionNewPruebaadicional.setPeriodoacademico(periodoacademico);
                    pruebaadicionalCollectionNewPruebaadicional = em.merge(pruebaadicionalCollectionNewPruebaadicional);
                    if (oldPeriodoacademicoOfPruebaadicionalCollectionNewPruebaadicional != null && !oldPeriodoacademicoOfPruebaadicionalCollectionNewPruebaadicional.equals(periodoacademico)) {
                        oldPeriodoacademicoOfPruebaadicionalCollectionNewPruebaadicional.getPruebaadicionalCollection().remove(pruebaadicionalCollectionNewPruebaadicional);
                        oldPeriodoacademicoOfPruebaadicionalCollectionNewPruebaadicional = em.merge(oldPeriodoacademicoOfPruebaadicionalCollectionNewPruebaadicional);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = periodoacademico.getPerid();
                if (findPeriodoacademico(id) == null) {
                    throw new NonexistentEntityException("The periodoacademico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodoacademico periodoacademico;
            try {
                periodoacademico = em.getReference(Periodoacademico.class, id);
                periodoacademico.getPerid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodoacademico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Programacasos> programacasosCollectionOrphanCheck = periodoacademico.getProgramacasosCollection();
            for (Programacasos programacasosCollectionOrphanCheckProgramacasos : programacasosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Programacasos " + programacasosCollectionOrphanCheckProgramacasos + " in its programacasosCollection field has a non-nullable periodoacademico field.");
            }
            Collection<Programacomponentes> programacomponentesCollectionOrphanCheck = periodoacademico.getProgramacomponentesCollection();
            for (Programacomponentes programacomponentesCollectionOrphanCheckProgramacomponentes : programacomponentesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Programacomponentes " + programacomponentesCollectionOrphanCheckProgramacomponentes + " in its programacomponentesCollection field has a non-nullable periodoacademico field.");
            }
            Collection<Programaofertado> programaofertadoCollectionOrphanCheck = periodoacademico.getProgramaofertadoCollection();
            for (Programaofertado programaofertadoCollectionOrphanCheckProgramaofertado : programaofertadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Programaofertado " + programaofertadoCollectionOrphanCheckProgramaofertado + " in its programaofertadoCollection field has a non-nullable periodoacademico field.");
            }
            Collection<Pruebaadicional> pruebaadicionalCollectionOrphanCheck = periodoacademico.getPruebaadicionalCollection();
            for (Pruebaadicional pruebaadicionalCollectionOrphanCheckPruebaadicional : pruebaadicionalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Pruebaadicional " + pruebaadicionalCollectionOrphanCheckPruebaadicional + " in its pruebaadicionalCollection field has a non-nullable periodoacademico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(periodoacademico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodoacademico> findPeriodoacademicoEntities() {
        return findPeriodoacademicoEntities(true, -1, -1);
    }

    public List<Periodoacademico> findPeriodoacademicoEntities(int maxResults, int firstResult) {
        return findPeriodoacademicoEntities(false, maxResults, firstResult);
    }

    private List<Periodoacademico> findPeriodoacademicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodoacademico.class));
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

    public Periodoacademico findPeriodoacademico(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodoacademico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodoacademicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periodoacademico> rt = cq.from(Periodoacademico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
