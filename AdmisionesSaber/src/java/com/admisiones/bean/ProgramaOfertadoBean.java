/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.data.Programa;
import com.admisiones.data.Programaofertado;
import com.admisiones.jpa.ProgramaofertadoJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Karen
 */
@ManagedBean
@RequestScoped
public class ProgramaOfertadoBean {

    private List<Programaofertado> listProgramasOfertados = new ArrayList();
    private EntityManagerFactory emf;
    
    private Short selectedFac;
    
    private Programaofertado programaof;
    private ProgramaofertadoJpaController programaofCtrl;
    
    /**
     * Creates a new instance of ProgramaOfertadoBean
     */
    public ProgramaOfertadoBean() {
    }

    public Short getSelectedFac() {
        return selectedFac;
    }

    public void setSelectedFac(Short selectedFac) {
        this.selectedFac = selectedFac;
    }

    public List<Programaofertado> getListProgramasOfertados() {
        emf = Persistence.createEntityManagerFactory("AdmisionesSaberPU");
        ProgramaofertadoJpaController ctrl = new ProgramaofertadoJpaController(emf);
        listProgramasOfertados = ctrl.findProgramaofertadoEntities();
        return listProgramasOfertados;
    }
    
     public List<Programaofertado> getProgramasFacultad() {
        if(listProgramasOfertados.isEmpty()) {
            getListProgramasOfertados();
        }
        List<Programaofertado> listProgramasFac = new ArrayList();
        for(Programaofertado p : listProgramasOfertados) {            
            if(p.getPrograma().getFacid().getFacid() == selectedFac) {
                listProgramasFac.add(p);
            }
        }
        return listProgramasFac;
    }
     
      public int getPorcentajePI(Short proid) {
        if(listProgramasOfertados.isEmpty()) {
            getListProgramasOfertados();
        }
        for(Programaofertado p : listProgramasOfertados) {            
            if(p.getPrograma().getProid() == proid) {
                if(p.getPofpondpruebaad() == -1)
                    return 100;
                else
                    return 100 - p.getPofpondpruebaad();
            }
        }
        return 0;
    }
      
    public void onRowEdit(RowEditEvent event) {
        System.out.println("----- *** ");
        FacesMessage msg = new FacesMessage("Programa Edited", ((Programaofertado) event.getObject()).getPrograma().getProid().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelado", ((Programaofertado) event.getObject()).getPrograma().getProid().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void modificar(Programaofertado obj) throws Exception{
        this.programaofCtrl.edit(obj);
        
    }

}
