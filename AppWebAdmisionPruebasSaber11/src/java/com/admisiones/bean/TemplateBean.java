/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author JORGE
 */
@ManagedBean
@RequestScoped
public class TemplateBean {

    /**
     * Creates a new instance of TemplateBean
     */
    
    public TemplateBean() {
    }

    public String verConfiguracionProgramas(){
        return "verConfigProg";
    }
    
}
