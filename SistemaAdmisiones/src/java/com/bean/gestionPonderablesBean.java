package com.bean;

import com.utilidades.Utilidades;
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

    /*Navegar entre vistas*/
    public void datosProcesados(){
        Utilidades.redireccionar("/SistemaAdmisiones/faces/Vistas/GestionarPonderables/datosProcesados.xhtml");
    }
    
    public void listaNoPonderable(){
        Utilidades.redireccionar("/SistemaAdmisiones/faces/Vistas/GestionarPonderables/listaAspirantesNoPonderables.xhtml");
    }
          
}
