/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.data.Periodo;
import com.admisiones.jpa.PeriodoJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Karen
 */
@ManagedBean
@RequestScoped
public class PeriodoBean {

    private List<Periodo> listPeriodos = new ArrayList();
    private EntityManagerFactory emf;
    
    /**
     * Creates a new instance of PeriodoBean
     */
    public PeriodoBean() {
    }
    
    public List<Periodo> getListPeriodos() {
        emf = Persistence.createEntityManagerFactory("AdmisionesSaberPU");
        PeriodoJpaController ctrl = new PeriodoJpaController(emf);
        listPeriodos = ctrl.findPeriodoEntities();
        return listPeriodos;        
    }
    
    public String getPeriodoActual(){
        if (listPeriodos.isEmpty()) {
            this.getListPeriodos();
        }
        for (Periodo p : listPeriodos) {            
            if (p.getPervigente() == 1 ) {
                return p.getPerid() + "";
            }
        }
        return "No hay periodo actual establecido";
    }
    
    public void createPeriodo() throws Exception {
        emf = Persistence.createEntityManagerFactory("AdmisionesSaberPU");
        PeriodoJpaController ctrl = new PeriodoJpaController(emf);
        ctrl.create(new Periodo());    
    }

    
}
