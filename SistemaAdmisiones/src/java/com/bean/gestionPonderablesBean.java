package com.bean;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author JORGE
 */
@Named(value = "gestionPonderablesBean")
@ApplicationScoped
public class gestionPonderablesBean implements Serializable{
    
    private String nombreArcAsp, nombreArcIcfes;        
    
    public gestionPonderablesBean() {
        nombreArcAsp = "";
        nombreArcIcfes = "";
    }
    
    public String getNombreArchivoAsp(){
        if(nombreArcAsp.isEmpty())
            return "Ningún Archivo Seleccionado";
        return nombreArcAsp;
    }
    
    public String getNombreArchivoIcfes(){
        if(nombreArcIcfes.isEmpty())
            return "Ningún Archivo Seleccionado";
        return nombreArcIcfes;
    }
          
}
