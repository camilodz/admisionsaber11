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
    
    private String nombreArcAsp, nombreArcIcfes, nombrePrograma, itemSeleccionado;        
    
    public gestionPonderablesBean() {
        nombreArcAsp = "";
        nombreArcIcfes = "";
        nombrePrograma = "";
        itemSeleccionado = "Todos";
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

    public String getItemSeleccionado() {
        return itemSeleccionado;
    }

    public void setItemSeleccionado(String itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    /*Navegar entre vistas*/
    public void datosProcesados(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/datosProcesados.xhtml");
    }
    
    public void listaNoPonderable(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaAspirantesNoPonderables.xhtml");
    }
    public void listaPonderable(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaAspirantesPonderables.xhtml");
    }

    public void verAspirantesPorPrograma(String nombrePro){
        this.setNombrePrograma(nombrePro);
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesPorPrograma.xhtml");
    }
    public void verAspirantesNOPorPrograma(String nombrePro){
        this.setNombrePrograma(nombrePro);
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesNOPorPrograma.xhtml");
    }
          
}
