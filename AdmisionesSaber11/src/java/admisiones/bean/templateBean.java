/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admisiones.bean;

import admisiones.utilidades.Utilidades;
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
        Utilidades.redireccionar("/Admisiones-Hibernate/faces/Vistas/ConfigurarProgramas/listarFacultades.xhtml");
    }
    
}
