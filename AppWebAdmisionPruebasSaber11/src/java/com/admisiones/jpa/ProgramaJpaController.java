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
import com.admisiones.data.Facultad;
import com.admisiones.data.Programa;
import com.admisiones.data.Programacasos;
import java.util.ArrayList;
import java.util.List;
import com.admisiones.data.Programacomponentes;
import com.admisiones.data.Programaofertado;
import com.admisiones.data.Pruebaadicional;
import com.admisiones.jpa.exceptions.IllegalOrphanException;
import com.admisiones.jpa.exceptions.NonexistentEntityException;
import com.admisiones.jpa.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Karen
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        if (programa.getProgramacasosList() == null) {
            programa.setProgramacasosList(new ArrayList<Programacasos>());
        }
        if (programa.getProgramacomponentesList() == null) {
            programa.setProgramacomponentesList(new ArrayList<Programacomponentes>());
        }
        if (programa.getProgramaofertadoList() == null) {
            programa.setProgramaofertadoList(new ArrayList<Programaofertado>());
        }
        if (programa.getPruebaadicionalList() == null) {
            programa.setPruebaadicionalList(new ArrayList<Pruebaadicional>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad facid = programa.getFacid();
            if (facid != null) {
                facid = em.getReference(facid.getClass(), facid.getFacid());
                programa.setFacid(facid);
            }
            List<Programacasos> attachedProgramacasosList = new ArrayList<Programacasos>();
            for (Programacasos programacasosListProgramacasosToAttach : programa.getProgramacasosList()) {
                programacasosListProgramacasosToAttach = em.getReference(programacasosListProgramacasosToAttach.getClass(), programacasosListProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosList.add(programacasosListProgramacasosToAttach);
            }
            programa.setProgramacasosList(attachedProgramacasosList);
            List<Programacomponentes> attachedProgramacomponentesList = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesListProgramacomponentesToAttach : programa.getProgramacomponentesList()) {
                programacomponentesListProgramacomponentesToAttach = em.getReference(programacomponentesListProgramacomponentesToAttach.getClass(), programacomponentesListProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesList.add(programacomponentesListProgramacomponentesToAttach);
            }
            programa.setProgramacomponentesList(attachedProgramacomponentesList);
            List<Programaofertado> attachedProgramaofertadoList = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoListProgramaofertadoToAttach : programa.getProgramaofertadoList()) {
                programaofertadoListProgramaofertadoToAttach = em.getReference(programaofertadoListProgramaofertadoToAttach.getClass(), programaofertadoListProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoList.add(programaofertadoListProgramaofertadoToAttach);
            }
            programa.setProgramaofertadoList(attachedProgramaofertadoList);
            List<Pruebaadicional> attachedPruebaadicionalList = new ArrayList<Pruebaadicional>();
            for (Pruebaadicional pruebaadicionalListPruebaadicionalToAttach : programa.getPruebaadicionalList()) {
                pruebaadicionalListPruebaadicionalToAttach = em.getReference(pruebaadicionalListPruebaadicionalToAttach.getClass(), pruebaadicionalListPruebaadicionalToAttach.getPruebaadicionalPK());
                attachedPruebaadicionalList.add(pruebaadicionalListPruebaadicionalToAttach);
            }
            programa.setPruebaadicionalList(attachedPruebaadicionalList);
            em.persist(programa);
            if (facid != null) {
                facid.getProgramaList().add(programa);
                facid = em.merge(facid);
            }
            for (Programacasos programacasosListProgramacasos : programa.getProgramacasosList()) {
                Programa oldProgramaOfProgramacasosListProgramacasos = programacasosListProgramacasos.getPrograma();
                programacasosListProgramacasos.setPrograma(programa);
                programacasosListProgramacasos = em.merge(programacasosListProgramacasos);
                if (oldProgramaOfProgramacasosListProgramacasos != null) {
                    oldProgramaOfProgramacasosListProgramacasos.getProgramacasosList().remove(programacasosListProgramacasos);
                    oldProgramaOfProgramacasosListProgramacasos = em.merge(oldProgramaOfProgramacasosListProgramacasos);
                }
            }
            for (Programacomponentes programacomponentesListProgramacomponentes : programa.getProgramacomponentesList()) {
                Programa oldProgramaOfProgramacomponentesListProgramacomponentes = programacomponentesListProgramacomponentes.getPrograma();
                programacomponentesListProgramacomponentes.setPrograma(programa);
                programacomponentesListProgramacomponentes = em.merge(programacomponentesListProgramacomponentes);
                if (oldProgramaOfProgramacomponentesListProgramacomponentes != null) {
                    oldProgramaOfProgramacomponentesListProgramacomponentes.getProgramacomponentesList().remove(programacomponentesListProgramacomponentes);
                    oldProgramaOfProgramacomponentesListProgramacomponentes = em.merge(oldProgramaOfProgramacomponentesListProgramacomponentes);
                }
            }
            for (Programaofertado programaofertadoListProgramaofertado : programa.getProgramaofertadoList()) {
                Programa oldProgramaOfProgramaofertadoListProgramaofertado = programaofertadoListProgramaofertado.getPrograma();
                programaofertadoListProgramaofertado.setPrograma(programa);
                programaofertadoListProgramaofertado = em.merge(programaofertadoListProgramaofertado);
                if (oldProgramaOfProgramaofertadoListProgramaofertado != null) {
                    oldProgramaOfProgramaofertadoListProgramaofertado.getProgramaofertadoList().remove(programaofertadoListProgramaofertado);
                    oldProgramaOfProgramaofertadoListProgramaofertado = em.merge(oldProgramaOfProgramaofertadoListProgramaofertado);
                }
            }
            for (Pruebaadicional pruebaadicionalListPruebaadicional : programa.getPruebaadicionalList()) {
                Programa oldProgramaOfPruebaadicionalListPruebaadicional = pruebaadicionalListPruebaadicional.getPrograma();
                pruebaadicionalListPruebaadicional.setPrograma(programa);
                pruebaadicionalListPruebaadicional = em.merge(pruebaadicionalListPruebaadicional);
                if (oldProgramaOfPruebaadicionalListPruebaadicional != null) {
                    oldProgramaOfPruebaadicionalListPruebaadicional.getPruebaadicionalList().remove(pruebaadicionalListPruebaadicional);
                    oldProgramaOfPruebaadicionalListPruebaadicional = em.merge(oldProgramaOfPruebaadicionalListPruebaadicional);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getProid()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getProid());
            Facultad facidOld = persistentPrograma.getFacid();
            Facultad facidNew = programa.getFacid();
            List<Programacasos> programacasosListOld = persistentPrograma.getProgramacasosList();
            List<Programacasos> programacasosListNew = programa.getProgramacasosList();
            List<Programacomponentes> programacomponentesListOld = persistentPrograma.getProgramacomponentesList();
            List<Programacomponentes> programacomponentesListNew = programa.getProgramacomponentesList();
            List<Programaofertado> programaofertadoListOld = persistentPrograma.getProgramaofertadoList();
            List<Programaofertado> programaofertadoListNew = programa.getProgramaofertadoList();
            List<Pruebaadicional> pruebaadicionalListOld = persistentPrograma.getPruebaadicionalList();
            List<Pruebaadicional> pruebaadicionalListNew = programa.getPruebaadicionalList();
            List<String> illegalOrphanMessages = null;
            for (Programacasos programacasosListOldProgramacasos : programacasosListOld) {
                if (!programacasosListNew.contains(programacasosListOldProgramacasos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacasos " + programacasosListOldProgramacasos + " since its programa field is not nullable.");
                }
            }
            for (Programacomponentes programacomponentesListOldProgramacomponentes : programacomponentesListOld) {
                if (!programacomponentesListNew.contains(programacomponentesListOldProgramacomponentes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacomponentes " + programacomponentesListOldProgramacomponentes + " since its programa field is not nullable.");
                }
            }
            for (Programaofertado programaofertadoListOldProgramaofertado : programaofertadoListOld) {
                if (!programaofertadoListNew.contains(programaofertadoListOldProgramaofertado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programaofertado " + programaofertadoListOldProgramaofertado + " since its programa field is not nullable.");
                }
            }
            for (Pruebaadicional pruebaadicionalListOldPruebaadicional : pruebaadicionalListOld) {
                if (!pruebaadicionalListNew.contains(pruebaadicionalListOldPruebaadicional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pruebaadicional " + pruebaadicionalListOldPruebaadicional + " since its programa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facidNew != null) {
                facidNew = em.getReference(facidNew.getClass(), facidNew.getFacid());
                programa.setFacid(facidNew);
            }
            List<Programacasos> attachedProgramacasosListNew = new ArrayList<Programacasos>();
            for (Programacasos programacasosListNewProgramacasosToAttach : programacasosListNew) {
                programacasosListNewProgramacasosToAttach = em.getReference(programacasosListNewProgramacasosToAttach.getClass(), programacasosListNewProgramacasosToAttach.getProgramacasosPK());
                attachedProgramacasosListNew.add(programacasosListNewProgramacasosToAttach);
            }
            programacasosListNew = attachedProgramacasosListNew;
            programa.setProgramacasosList(programacasosListNew);
            List<Programacomponentes> attachedProgramacomponentesListNew = new ArrayList<Programacomponentes>();
            for (Programacomponentes programacomponentesListNewProgramacomponentesToAttach : programacomponentesListNew) {
                programacomponentesListNewProgramacomponentesToAttach = em.getReference(programacomponentesListNewProgramacomponentesToAttach.getClass(), programacomponentesListNewProgramacomponentesToAttach.getProgramacomponentesPK());
                attachedProgramacomponentesListNew.add(programacomponentesListNewProgramacomponentesToAttach);
            }
            programacomponentesListNew = attachedProgramacomponentesListNew;
            programa.setProgramacomponentesList(programacomponentesListNew);
            List<Programaofertado> attachedProgramaofertadoListNew = new ArrayList<Programaofertado>();
            for (Programaofertado programaofertadoListNewProgramaofertadoToAttach : programaofertadoListNew) {
                programaofertadoListNewProgramaofertadoToAttach = em.getReference(programaofertadoListNewProgramaofertadoToAttach.getClass(), programaofertadoListNewProgramaofertadoToAttach.getProgramaofertadoPK());
                attachedProgramaofertadoListNew.add(programaofertadoListNewProgramaofertadoToAttach);
            }
            programaofertadoListNew = attachedProgramaofertadoListNew;
            programa.setProgramaofertadoList(programaofertadoListNew);
            List<Pruebaadicional> attachedPruebaadicionalListNew = new ArrayList<Pruebaadicional>();
            for (Pruebaadicional pruebaadicionalListNewPruebaadicionalToAttach : pruebaadicionalListNew) {
                pruebaadicionalListNewPruebaadicionalToAttach = em.getReference(pruebaadicionalListNewPruebaadicionalToAttach.getClass(), pruebaadicionalListNewPruebaadicionalToAttach.getPruebaadicionalPK());
                attachedPruebaadicionalListNew.add(pruebaadicionalListNewPruebaadicionalToAttach);
            }
            pruebaadicionalListNew = attachedPruebaadicionalListNew;
            programa.setPruebaadicionalList(pruebaadicionalListNew);
            programa = em.merge(programa);
            if (facidOld != null && !facidOld.equals(facidNew)) {
                facidOld.getProgramaList().remove(programa);
                facidOld = em.merge(facidOld);
            }
            if (facidNew != null && !facidNew.equals(facidOld)) {
                facidNew.getProgramaList().add(programa);
                facidNew = em.merge(facidNew);
            }
            for (Programacasos programacasosListNewProgramacasos : programacasosListNew) {
                if (!programacasosListOld.contains(programacasosListNewProgramacasos)) {
                    Programa oldProgramaOfProgramacasosListNewProgramacasos = programacasosListNewProgramacasos.getPrograma();
                    programacasosListNewProgramacasos.setPrograma(programa);
                    programacasosListNewProgramacasos = em.merge(programacasosListNewProgramacasos);
                    if (oldProgramaOfProgramacasosListNewProgramacasos != null && !oldProgramaOfProgramacasosListNewProgramacasos.equals(programa)) {
                        oldProgramaOfProgramacasosListNewProgramacasos.getProgramacasosList().remove(programacasosListNewProgramacasos);
                        oldProgramaOfProgramacasosListNewProgramacasos = em.merge(oldProgramaOfProgramacasosListNewProgramacasos);
                    }
                }
            }
            for (Programacomponentes programacomponentesListNewProgramacomponentes : programacomponentesListNew) {
                if (!programacomponentesListOld.contains(programacomponentesListNewProgramacomponentes)) {
                    Programa oldProgramaOfProgramacomponentesListNewProgramacomponentes = programacomponentesListNewProgramacomponentes.getPrograma();
                    programacomponentesListNewProgramacomponentes.setPrograma(programa);
                    programacomponentesListNewProgramacomponentes = em.merge(programacomponentesListNewProgramacomponentes);
                    if (oldProgramaOfProgramacomponentesListNewProgramacomponentes != null && !oldProgramaOfProgramacomponentesListNewProgramacomponentes.equals(programa)) {
                        oldProgramaOfProgramacomponentesListNewProgramacomponentes.getProgramacomponentesList().remove(programacomponentesListNewProgramacomponentes);
                        oldProgramaOfProgramacomponentesListNewProgramacomponentes = em.merge(oldProgramaOfProgramacomponentesListNewProgramacomponentes);
                    }
                }
            }
            for (Programaofertado programaofertadoListNewProgramaofertado : programaofertadoListNew) {
                if (!programaofertadoListOld.contains(programaofertadoListNewProgramaofertado)) {
                    Programa oldProgramaOfProgramaofertadoListNewProgramaofertado = programaofertadoListNewProgramaofertado.getPrograma();
                    programaofertadoListNewProgramaofertado.setPrograma(programa);
                    programaofertadoListNewProgramaofertado = em.merge(programaofertadoListNewProgramaofertado);
                    if (oldProgramaOfProgramaofertadoListNewProgramaofertado != null && !oldProgramaOfProgramaofertadoListNewProgramaofertado.equals(programa)) {
                        oldProgramaOfProgramaofertadoListNewProgramaofertado.getProgramaofertadoList().remove(programaofertadoListNewProgramaofertado);
                        oldProgramaOfProgramaofertadoListNewProgramaofertado = em.merge(oldProgramaOfProgramaofertadoListNewProgramaofertado);
                    }
                }
            }
            for (Pruebaadicional pruebaadicionalListNewPruebaadicional : pruebaadicionalListNew) {
                if (!pruebaadicionalListOld.contains(pruebaadicionalListNewPruebaadicional)) {
                    Programa oldProgramaOfPruebaadicionalListNewPruebaadicional = pruebaadicionalListNewPruebaadicional.getPrograma();
                    pruebaadicionalListNewPruebaadicional.setPrograma(programa);
                    pruebaadicionalListNewPruebaadicional = em.merge(pruebaadicionalListNewPruebaadicional);
                    if (oldProgramaOfPruebaadicionalListNewPruebaadicional != null && !oldProgramaOfPruebaadicionalListNewPruebaadicional.equals(programa)) {
                        oldProgramaOfPruebaadicionalListNewPruebaadicional.getPruebaadicionalList().remove(pruebaadicionalListNewPruebaadicional);
                        oldProgramaOfPruebaadicionalListNewPruebaadicional = em.merge(oldProgramaOfPruebaadicionalListNewPruebaadicional);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = programa.getProid();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
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
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getProid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Programacasos> programacasosListOrphanCheck = programa.getProgramacasosList();
            for (Programacasos programacasosListOrphanCheckProgramacasos : programacasosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Programacasos " + programacasosListOrphanCheckProgramacasos + " in its programacasosList field has a non-nullable programa field.");
            }
            List<Programacomponentes> programacomponentesListOrphanCheck = programa.getProgramacomponentesList();
            for (Programacomponentes programacomponentesListOrphanCheckProgramacomponentes : programacomponentesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Programacomponentes " + programacomponentesListOrphanCheckProgramacomponentes + " in its programacomponentesList field has a non-nullable programa field.");
            }
            List<Programaofertado> programaofertadoListOrphanCheck = programa.getProgramaofertadoList();
            for (Programaofertado programaofertadoListOrphanCheckProgramaofertado : programaofertadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Programaofertado " + programaofertadoListOrphanCheckProgramaofertado + " in its programaofertadoList field has a non-nullable programa field.");
            }
            List<Pruebaadicional> pruebaadicionalListOrphanCheck = programa.getPruebaadicionalList();
            for (Pruebaadicional pruebaadicionalListOrphanCheckPruebaadicional : pruebaadicionalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Pruebaadicional " + pruebaadicionalListOrphanCheckPruebaadicional + " in its pruebaadicionalList field has a non-nullable programa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Facultad facid = programa.getFacid();
            if (facid != null) {
                facid.getProgramaList().remove(programa);
                facid = em.merge(facid);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    public Programa findPrograma(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
