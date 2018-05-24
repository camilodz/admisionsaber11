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
    
    private String nombreArcAsp, nombreArcIcfes, nombrePrograma;        
    
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

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }   

    /*Navegar entre vistas*/
    public void datosProcesados(){
        Utilidades.redireccionar("/SistemaAdmisiones/faces/Vistas/GestionarPonderables/datosProcesados.xhtml");
    }
    
    public void listaNoPonderable(){
        Utilidades.redireccionar("/SistemaAdmisiones/faces/Vistas/GestionarPonderables/listaAspirantesNoPonderables.xhtml");
    }

    public void verAspirantesPorPrograma(String nombrePro){
        this.setNombrePrograma(nombrePro);
        Utilidades.redireccionar("/SistemaAdmisiones/faces/Vistas/GestionarPonderables/verAspirantesPorPrograma.xhtml");
    }
          
}
