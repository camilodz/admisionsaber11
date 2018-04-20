/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.data.Periodoacademico;
import com.admisiones.jpa.PeriodoacademicoJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DragumLycan
 */
@ManagedBean
@RequestScoped
public class PeriodoABean{
    
    private List<Periodoacademico> listPeriodoAca = new ArrayList();
    private List<SelectItem> listPeriodoAca1;
    private EntityManagerFactory emf;
    private String selectedPeriodoAca;
    private Periodoacademico periodo;

    /**
     * Creates a new instance of PeriodoABean
     */
    public PeriodoABean() {
        periodo = new Periodoacademico();
    }
    
    public Periodoacademico getPeriodo() {
        return periodo;
    }

    public String getSelectedPerAca() {
        return selectedPeriodoAca;
    }

    public void setSelectedPerAca(String selectedPeriodoAca) {
        this.selectedPeriodoAca = selectedPeriodoAca;
    }

    public List<SelectItem> getListPeriodos() {
        emf = Persistence.createEntityManagerFactory("ProyectoII_AdmisionesSaber11PU");
        PeriodoacademicoJpaController ctrl = new PeriodoacademicoJpaController(emf);
        listPeriodoAca = ctrl.findPeriodoacademicoEntities();
        List<Periodoacademico> p = listPeriodoAca;
        this.listPeriodoAca1 = new ArrayList<SelectItem>();
        //listPeriodoAca.clear();
        for (Periodoacademico periodos : p){
            SelectItem periodoItem = new SelectItem(periodos.getPerid());
            this.listPeriodoAca1.add(periodoItem);
        }
        
        
        return listPeriodoAca1;        
    }       
    
    public String viewProgramas() {
        return "verprograma";
    }

    public void setListPeriodos(List<Periodoacademico> listPeriodoAca) {
        this.listPeriodoAca = listPeriodoAca;
    }
    
}
