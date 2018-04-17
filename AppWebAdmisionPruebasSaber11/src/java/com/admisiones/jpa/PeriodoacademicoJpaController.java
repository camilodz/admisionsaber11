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
import java.util.List;
import com.admisiones.data.Programacomponentes;
import com.admisiones.data.Programaofertado;
import com.admisiones.data.Pruebaadicional;
import com.admisiones.jpa.exceptions.IllegalOrphanException;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Karen
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
        if (periodoacademico.getProgramacasosList() == null) {
            periodoacademico.setProgramacasosList(new ArrayList<Programacasos>());
        }
        if (periodoacademico.getProgramacomponentesList() == null) {
            periodoacademico.setProgramacomponentesList(new ArrayList<Programacomponentes>());
        }
        if (periodoacademico.getProgramaofertadoList() == null) {
            periodoacademico.setProgramaofertadoList(new ArrayList<Programaofertado>());
        }
        if (periodoacademico.getPruebaadicionalList() == null) {
            periodoacademico.setPruebaadicionalList(new ArrayList<Pruebaadicional>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Programacasos> attachedProgramacasosList = new ArrayList<Programacasos>();
            for (Programacasos programacasosListProgramacasosToAttach : periodoacademico.getProgramacasosList()) {
                programacasosListProgramacasosToAttach = em.getReference(programacasosListProgramacasosToAttach.getClass(), programacasosListProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosList.add(programacasosListProgramacasosToAttach);
            }
            periodoacademico.setProgramacasosList(attachedProgramacasosList);
            List<Programacomponentes> attachedProgramacomponentesList = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesListProgramacomponentesToAttach : periodoacademico.getProgramacomponentesList()) {
                programacomponentesListProgramacomponentesToAttach = em.getReference(programacomponentesListProgramacomponentesToAttach.getClass(), programacomponentesListProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesList.add(programacomponentesListProgramacomponentesToAttach);
            }
            periodoacademico.setProgramacomponentesList(attachedProgramacomponentesList);
            List<Programaofertado> attachedProgramaofertadoList = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoListProgramaofertadoToAttach : periodoacademico.getProgramaofertadoList()) {
                programaofertadoListProgramaofertadoToAttach = em.getReference(programaofertadoListProgramaofertadoToAttach.getClass(), programaofertadoListProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoList.add(programaofertadoListProgramaofertadoToAttach);
            }
            periodoacademico.setProgramaofertadoList(attachedProgramaofertadoList);
            List<Pruebaadicional> attachedPruebaadicionalList = new ArrayList<Pruebaadicional>();
            for (Pruebaadicional pruebaadicionalListPruebaadicionalToAttach : periodoacademico.getPruebaadicionalList()) {
                pruebaadicionalListPruebaadicionalToAttach = em.getReference(pruebaadicionalListPruebaadicionalToAttach.getClass(), pruebaadicionalListPruebaadicionalToAttach.getPruebaadicionalPK());
                attachedPruebaadicionalList.add(pruebaadicionalListPruebaadicionalToAttach);
            }
            periodoacademico.setPruebaadicionalList(attachedPruebaadicionalList);
            em.persist(periodoacademico);
            for (Programacasos programacasosListProgramacasos : periodoacademico.getProgramacasosList()) {
                Periodoacademico oldPeriodoacademicoOfProgramacasosListProgramacasos = programacasosListProgramacasos.getPeriodoacademico();
                programacasosListProgramacasos.setPeriodoacademico(periodoacademico);
                programacasosListProgramacasos = em.merge(programacasosListProgramacasos);
                if (oldPeriodoacademicoOfProgramacasosListProgramacasos != null) {
                    oldPeriodoacademicoOfProgramacasosListProgramacasos.getProgramacasosList().remove(programacasosListProgramacasos);
                    oldPeriodoacademicoOfProgramacasosListProgramacasos = em.merge(oldPeriodoacademicoOfProgramacasosListProgramacasos);
                }
            }
            for (Programacomponentes programacomponentesListProgramacomponentes : periodoacademico.getProgramacomponentesList()) {
                Periodoacademico oldPeriodoacademicoOfProgramacomponentesListProgramacomponentes = programacomponentesListProgramacomponentes.getPeriodoacademico();
                programacomponentesListProgramacomponentes.setPeriodoacademico(periodoacademico);
                programacomponentesListProgramacomponentes = em.merge(programacomponentesListProgramacomponentes);
                if (oldPeriodoacademicoOfProgramacomponentesListProgramacomponentes != null) {
                    oldPeriodoacademicoOfProgramacomponentesListProgramacomponentes.getProgramacomponentesList().remove(programacomponentesListProgramacomponentes);
                    oldPeriodoacademicoOfProgramacomponentesListProgramacomponentes = em.merge(oldPeriodoacademicoOfProgramacomponentesListProgramacomponentes);
                }
            }
            for (Programaofertado programaofertadoListProgramaofertado : periodoacademico.getProgramaofertadoList()) {
                Periodoacademico oldPeriodoacademicoOfProgramaofertadoListProgramaofertado = programaofertadoListProgramaofertado.getPeriodoacademico();
                programaofertadoListProgramaofertado.setPeriodoacademico(periodoacademico);
                programaofertadoListProgramaofertado = em.merge(programaofertadoListProgramaofertado);
                if (oldPeriodoacademicoOfProgramaofertadoListProgramaofertado != null) {
                    oldPeriodoacademicoOfProgramaofertadoListProgramaofertado.getProgramaofertadoList().remove(programaofertadoListProgramaofertado);
                    oldPeriodoacademicoOfProgramaofertadoListProgramaofertado = em.merge(oldPeriodoacademicoOfProgramaofertadoListProgramaofertado);
                }
            }
            for (Pruebaadicional pruebaadicionalListPruebaadicional : periodoacademico.getPruebaadicionalList()) {
                Periodoacademico oldPeriodoacademicoOfPruebaadicionalListPruebaadicional = pruebaadicionalListPruebaadicional.getPeriodoacademico();
                pruebaadicionalListPruebaadicional.setPeriodoacademico(periodoacademico);
                pruebaadicionalListPruebaadicional = em.merge(pruebaadicionalListPruebaadicional);
                if (oldPeriodoacademicoOfPruebaadicionalListPruebaadicional != null) {
                    oldPeriodoacademicoOfPruebaadicionalListPruebaadicional.getPruebaadicionalList().remove(pruebaadicionalListPruebaadicional);
                    oldPeriodoacademicoOfPruebaadicionalListPruebaadicional = em.merge(oldPeriodoacademicoOfPruebaadicionalListPruebaadicional);
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
            List<Programacasos> programacasosListOld = persistentPeriodoacademico.getProgramacasosList();
            List<Programacasos> programacasosListNew = periodoacademico.getProgramacasosList();
            List<Programacomponentes> programacomponentesListOld = persistentPeriodoacademico.getProgramacomponentesList();
            List<Programacomponentes> programacomponentesListNew = periodoacademico.getProgramacomponentesList();
            List<Programaofertado> programaofertadoListOld = persistentPeriodoacademico.getProgramaofertadoList();
            List<Programaofertado> programaofertadoListNew = periodoacademico.getProgramaofertadoList();
            List<Pruebaadicional> pruebaadicionalListOld = persistentPeriodoacademico.getPruebaadicionalList();
            List<Pruebaadicional> pruebaadicionalListNew = periodoacademico.getPruebaadicionalList();
            List<String> illegalOrphanMessages = null;
            for (Programacasos programacasosListOldProgramacasos : programacasosListOld) {
                if (!programacasosListNew.contains(programacasosListOldProgramacasos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacasos " + programacasosListOldProgramacasos + " since its periodoacademico field is not nullable.");
                }
            }
            for (Programacomponentes programacomponentesListOldProgramacomponentes : programacomponentesListOld) {
                if (!programacomponentesListNew.contains(programacomponentesListOldProgramacomponentes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacomponentes " + programacomponentesListOldProgramacomponentes + " since its periodoacademico field is not nullable.");
                }
            }
            for (Programaofertado programaofertadoListOldProgramaofertado : programaofertadoListOld) {
                if (!programaofertadoListNew.contains(programaofertadoListOldProgramaofertado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programaofertado " + programaofertadoListOldProgramaofertado + " since its periodoacademico field is not nullable.");
                }
            }
            for (Pruebaadicional pruebaadicionalListOldPruebaadicional : pruebaadicionalListOld) {
                if (!pruebaadicionalListNew.contains(pruebaadicionalListOldPruebaadicional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pruebaadicional " + pruebaadicionalListOldPruebaadicional + " since its periodoacademico field is not nullable.");
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
            periodoacademico.setProgramacasosList(programacasosListNew);
            List<Programacomponentes> attachedProgramacomponentesListNew = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesListNewProgramacomponentesToAttach : programacomponentesListNew) {
                programacomponentesListNewProgramacomponentesToAttach = em.getReference(programacomponentesListNewProgramacomponentesToAttach.getClass(), programacomponentesListNewProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesListNew.add(programacomponentesListNewProgramacomponentesToAttach);
            }
            programacomponentesListNew = attachedProgramacomponentesListNew;
            periodoacademico.setProgramacomponentesList(programacomponentesListNew);
            List<Programaofertado> attachedProgramaofertadoListNew = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoListNewProgramaofertadoToAttach : programaofertadoListNew) {
                programaofertadoListNewProgramaofertadoToAttach = em.getReference(programaofertadoListNewProgramaofertadoToAttach.getClass(), programaofertadoListNewProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoListNew.add(programaofertadoListNewProgramaofertadoToAttach);
            }
            programaofertadoListNew = attachedProgramaofertadoListNew;
            periodoacademico.setProgramaofertadoList(programaofertadoListNew);
            List<Pruebaadicional> attachedPruebaadicionalListNew = new ArrayList<Pruebaadicional>();
            for (Pruebaadicional pruebaadicionalListNewPruebaadicionalToAttach : pruebaadicionalListNew) {
                pruebaadicionalListNewPruebaadicionalToAttach = em.getReference(pruebaadicionalListNewPruebaadicionalToAttach.getClass(), pruebaadicionalListNewPruebaadicionalToAttach.getPruebaadicionalPK());
                attachedPruebaadicionalListNew.add(pruebaadicionalListNewPruebaadicionalToAttach);
            }
            pruebaadicionalListNew = attachedPruebaadicionalListNew;
            periodoacademico.setPruebaadicionalList(pruebaadicionalListNew);
            periodoacademico = em.merge(periodoacademico);
            for (Programacasos programacasosListNewProgramacasos : programacasosListNew) {
                if (!programacasosListOld.contains(programacasosListNewProgramacasos)) {
                    Periodoacademico oldPeriodoacademicoOfProgramacasosListNewProgramacasos = programacasosListNewProgramacasos.getPeriodoacademico();
                    programacasosListNewProgramacasos.setPeriodoacademico(periodoacademico);
                    programacasosListNewProgramacasos = em.merge(programacasosListNewProgramacasos);
                    if (oldPeriodoacademicoOfProgramacasosListNewProgramacasos != null && !oldPeriodoacademicoOfProgramacasosListNewProgramacasos.equals(periodoacademico)) {
                        oldPeriodoacademicoOfProgramacasosListNewProgramacasos.getProgramacasosList().remove(programacasosListNewProgramacasos);
                        oldPeriodoacademicoOfProgramacasosListNewProgramacasos = em.merge(oldPeriodoacademicoOfProgramacasosListNewProgramacasos);
                    }
                }
            }
            for (Programacomponentes programacomponentesListNewProgramacomponentes : programacomponentesListNew) {
                if (!programacomponentesListOld.contains(programacomponentesListNewProgramacomponentes)) {
                    Periodoacademico oldPeriodoacademicoOfProgramacomponentesListNewProgramacomponentes = programacomponentesListNewProgramacomponentes.getPeriodoacademico();
                    programacomponentesListNewProgramacomponentes.setPeriodoacademico(periodoacademico);
                    programacomponentesListNewProgramacomponentes = em.merge(programacomponentesListNewProgramacomponentes);
                    if (oldPeriodoacademicoOfProgramacomponentesListNewProgramacomponentes != null && !oldPeriodoacademicoOfProgramacomponentesListNewProgramacomponentes.equals(periodoacademico)) {
                        oldPeriodoacademicoOfProgramacomponentesListNewProgramacomponentes.getProgramacomponentesList().remove(programacomponentesListNewProgramacomponentes);
                        oldPeriodoacademicoOfProgramacomponentesListNewProgramacomponentes = em.merge(oldPeriodoacademicoOfProgramacomponentesListNewProgramacomponentes);
                    }
                }
            }
            for (Programaofertado programaofertadoListNewProgramaofertado : programaofertadoListNew) {
                if (!programaofertadoListOld.contains(programaofertadoListNewProgramaofertado)) {
                    Periodoacademico oldPeriodoacademicoOfProgramaofertadoListNewProgramaofertado = programaofertadoListNewProgramaofertado.getPeriodoacademico();
                    programaofertadoListNewProgramaofertado.setPeriodoacademico(periodoacademico);
                    programaofertadoListNewProgramaofertado = em.merge(programaofertadoListNewProgramaofertado);
                    if (oldPeriodoacademicoOfProgramaofertadoListNewProgramaofertado != null && !oldPeriodoacademicoOfProgramaofertadoListNewProgramaofertado.equals(periodoacademico)) {
                        oldPeriodoacademicoOfProgramaofertadoListNewProgramaofertado.getProgramaofertadoList().remove(programaofertadoListNewProgramaofertado);
                        oldPeriodoacademicoOfProgramaofertadoListNewProgramaofertado = em.merge(oldPeriodoacademicoOfProgramaofertadoListNewProgramaofertado);
                    }
                }
            }
            for (Pruebaadicional pruebaadicionalListNewPruebaadicional : pruebaadicionalListNew) {
                if (!pruebaadicionalListOld.contains(pruebaadicionalListNewPruebaadicional)) {
                    Periodoacademico oldPeriodoacademicoOfPruebaadicionalListNewPruebaadicional = pruebaadicionalListNewPruebaadicional.getPeriodoacademico();
                    pruebaadicionalListNewPruebaadicional.setPeriodoacademico(periodoacademico);
                    pruebaadicionalListNewPruebaadicional = em.merge(pruebaadicionalListNewPruebaadicional);
                    if (oldPeriodoacademicoOfPruebaadicionalListNewPruebaadicional != null && !oldPeriodoacademicoOfPruebaadicionalListNewPruebaadicional.equals(periodoacademico)) {
                        oldPeriodoacademicoOfPruebaadicionalListNewPruebaadicional.getPruebaadicionalList().remove(pruebaadicionalListNewPruebaadicional);
                        oldPeriodoacademicoOfPruebaadicionalListNewPruebaadicional = em.merge(oldPeriodoacademicoOfPruebaadicionalListNewPruebaadicional);
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
            List<Programacasos> programacasosListOrphanCheck = periodoacademico.getProgramacasosList();
            for (Programacasos programacasosListOrphanCheckProgramacasos : programacasosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Programacasos " + programacasosListOrphanCheckProgramacasos + " in its programacasosList field has a non-nullable periodoacademico field.");
            }
            List<Programacomponentes> programacomponentesListOrphanCheck = periodoacademico.getProgramacomponentesList();
            for (Programacomponentes programacomponentesListOrphanCheckProgramacomponentes : programacomponentesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Programacomponentes " + programacomponentesListOrphanCheckProgramacomponentes + " in its programacomponentesList field has a non-nullable periodoacademico field.");
            }
            List<Programaofertado> programaofertadoListOrphanCheck = periodoacademico.getProgramaofertadoList();
            for (Programaofertado programaofertadoListOrphanCheckProgramaofertado : programaofertadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Programaofertado " + programaofertadoListOrphanCheckProgramaofertado + " in its programaofertadoList field has a non-nullable periodoacademico field.");
            }
            List<Pruebaadicional> pruebaadicionalListOrphanCheck = periodoacademico.getPruebaadicionalList();
            for (Pruebaadicional pruebaadicionalListOrphanCheckPruebaadicional : pruebaadicionalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodoacademico (" + periodoacademico + ") cannot be destroyed since the Pruebaadicional " + pruebaadicionalListOrphanCheckPruebaadicional + " in its pruebaadicionalList field has a non-nullable periodoacademico field.");
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
