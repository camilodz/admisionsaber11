/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.AspiranteDaoImp;
import com.model.Aspirante;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import com.dao.AspiranteDao;

/**
 *
 * @author HP
 */
@Named(value = "aspirantePonderableBean")
@ViewScoped
public class aspiranteBean implements Serializable{
    private List<Aspirante> listar;
    private Aspirante aspirante;
    
    /**
     * Creates a new instance of AspirantePonderableBean
     */
    public aspiranteBean() {
    }

    public List<Aspirante> getListar() {
        AspiranteDao aspdao = new AspiranteDaoImp();
        listar = aspdao.mostrarAspirantes();
        return listar;
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }
    
    
}
