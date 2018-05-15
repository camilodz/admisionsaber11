/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.AspirantePonderableDao;
import com.dao.AspirantePonderableDaoImp;
import com.model.AspirantePonderable;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author HP
 */
@Named(value = "aspirantePonderableBean")
@ViewScoped
public class AspirantePonderableBean implements Serializable{
    private List<AspirantePonderable> listar;
    private AspirantePonderable aspirante;
    /**
     * Creates a new instance of AspirantePonderableBean
     */
    public AspirantePonderableBean() {
    }

    public List<AspirantePonderable> getListar() {
        AspirantePonderableDao aspdao = new AspirantePonderableDaoImp();
        listar = aspdao.mostrarAspirantes();
        return listar;
    }

    public AspirantePonderable getAspirante() {
        return aspirante;
    }

    public void setAspirante(AspirantePonderable aspirante) {
        this.aspirante = aspirante;
    }
    
    
}
