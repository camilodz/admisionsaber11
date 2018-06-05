/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.ProgramaDao;
import com.dao.ProgramaDaoImp;
import com.model.Programa;
import com.utilidades.Utilidades;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author JULIAN
 */
@Named(value = "programaBean")
@SessionScoped
public class ProgramaBean implements Serializable {

     private List<Programa> listaProgramas = new ArrayList();
     
     private List<Programa> listaProgramasPopayan = new ArrayList();
     private List<Programa> listaProgramasRegionalizacion = new ArrayList();
     
    /**
     * Creates a new instance of ProgramaBean
     */
    public ProgramaBean() {
    }

    public List<Programa> getListaProgramas() {
        return listaProgramas;
    }

    public List<Programa> getListaProgramasPopayan() {
        return listaProgramasPopayan;
    }

    public List<Programa> getListaProgramasRegionalizacion() {
        return listaProgramasRegionalizacion;
    }
    
    
    public List<Programa> getListaPro() {
        ProgramaDao proDao = new ProgramaDaoImp();
        this.listaProgramas = proDao.listarProgramas();
        return listaProgramas;
    }
    
    public void setProgramasSede(){
        if(this.listaProgramas.isEmpty()){
            this.getListaPro();
        }
        System.out.println("TAM Lista programas " + this.listaProgramas.size());
        for(int i = 0 ; i< this.listaProgramas.size(); i++){
            if(this.listaProgramas.get(i).getProsede().equals("Popayán")){
                this.listaProgramasPopayan.add(this.listaProgramas.get(i));
            }
            else if(this.listaProgramas.get(i).getProsede().equals("Regionalización")){
                this.listaProgramasRegionalizacion.add(this.listaProgramas.get(i));
            }
        }
        System.out.println("TAM Lista programas P " + this.listaProgramasPopayan.size());
        System.out.println("TAM Lista programas R" + this.listaProgramasRegionalizacion.size());
    }
    
    public void verConfiguracionInicialPO(){
        this.setProgramasSede();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfiguracionInicial/ProgramasOfertados.xhtml");
    }
    
    
}
