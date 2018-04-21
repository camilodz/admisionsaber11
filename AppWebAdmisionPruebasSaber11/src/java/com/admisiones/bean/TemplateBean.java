/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.utilidades.Utilidades;
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

    public void verConfiguracionProgramas(){
        Utilidades.redireccionar("/ProyectoII_AdmisionesSaber11/faces/Vistas/ConfigurarProgramas/ListarFacultades.xhtml");
    }
    
}
