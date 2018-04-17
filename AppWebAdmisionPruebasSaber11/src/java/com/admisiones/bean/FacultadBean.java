/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.data.Facultad;
import com.admisiones.jpa.FacultadJpaController;
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

public class FacultadBean {
    
    private List<Facultad> listFacultades = new ArrayList();
    private EntityManagerFactory emf;
    private String selectedFac;       
    
    /**
     * Creates a new instance of FacultadBean
     */
    public FacultadBean() {
    }

    public String getSelectedFac() {
        return selectedFac;
    }

    public void setSelectedFac(String selectedFac) {
        this.selectedFac = selectedFac;
    }

    public List<Facultad> getListFacultades() {
        emf = Persistence.createEntityManagerFactory("admisionesPU");
        FacultadJpaController ctrl = new FacultadJpaController(emf);
        listFacultades = ctrl.findFacultadEntities();
        return listFacultades;        
    }       
    
    public String viewProgramas() {
        return "verprograma";
    }

    public void setListFacultades(List<Facultad> listFacultades) {
        this.listFacultades = listFacultades;
    }
    
    
    
    
    
}
