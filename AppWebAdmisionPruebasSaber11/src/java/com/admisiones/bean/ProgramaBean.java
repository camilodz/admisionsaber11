/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.data.Programa;
import com.admisiones.jpa.ProgramaJpaController;
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
public class ProgramaBean {

    private List<Programa> listProgramas = new ArrayList();
    private EntityManagerFactory emf;
    private String selectedFac;

    /**
     * Creates a new instance of ProgramaBean
     */
    public ProgramaBean() {
    }

    public String getSelectedFac() {
        return selectedFac;
    }

    public void setSelectedFac(String selectedFac) {
        this.selectedFac = selectedFac;
    }

    public List<Programa> getListProgramas() {
        emf = Persistence.createEntityManagerFactory("admisionesPU");
        ProgramaJpaController ctrl = new ProgramaJpaController(emf);
        listProgramas = ctrl.findProgramaEntities();
        return listProgramas;
    }

    public List<Programa> getProgramasFacultad() {
        if(listProgramas.isEmpty()) {
            getListProgramas();
        }
        List<Programa> listProgramasFac = new ArrayList();
        for(Programa p : listProgramas) {            
            if(p.getFacid().getFacid() == Short.parseShort(selectedFac)) {
                listProgramasFac.add(p);
            }
        }
        return listProgramasFac;
    }

}
