/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.utilidades.Utilidades;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author JORGE
 */
@Named(value = "templateBean")
@Dependent
public class templateBean {

    /**
     * Creates a new instance of templateBean
     */
    public templateBean() {
    }
    
    public void verConfiguracionProgramas(){        
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfigurarProgramas/listarFacultades.xhtml");
    }
    
    public void verListadosDeAdmitidos(){        
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
    }
    
    public void verConfiguracionInicial(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfiguracionInicial/ConfigPeriodo.xhtml");
    }
    
    public void gestionarPonderables(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/seleccionArchivos.xhtml");
    }
    
    public void verPaginaPrincipal(){        
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/vistaInicio.xhtml");
    }

}
