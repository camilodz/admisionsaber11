package com.bean;

import com.utilidades.Utilidades;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Bean gestionPonderablesBean
 *
 * Contiene los métodos para gestionar los archivos que se cargan de los
 * aspirantes
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "gestionPonderablesBean")
@ApplicationScoped
public class gestionPonderablesBean implements Serializable{
    
    /*** Atributos ***/
    
    /**
     * Nombre del archivo que contiene la información de los aspirantes 
     */
    private String nombreArcAsp;
    
    /**
     * Nombre del archivo que contiene los resultados ICFES de cada aspirante
     */
    private String nombreArcIcfes;
    
    /**
     * Nombre del programa al que aplican los aspirantes (del archivo cargado)
     */
    private String nombrePrograma;
    
    /**
     * 
     */
    private String itemSeleccionado; 
    
    
    /*** Constructor ***/
    
    /**
     * Constructor por defecto
     */
    public gestionPonderablesBean() {
        nombreArcAsp = "";
        nombreArcIcfes = "";
        nombrePrograma = "";
        itemSeleccionado = "Todos";
    }
    
    
    /*** Métodos getter y setter ***/

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
    
    /**
     * Obtener el nombre del archivo que contiene la información de los
     * aspirantes
     *
     * @return nombre del archivo que contiene la información de los aspirantes
     */
    public String getNombreArchivoAsp(){
        if(nombreArcAsp.isEmpty())
            return "Ningún Archivo Seleccionado";
        return nombreArcAsp;
    }
    
    /**
     * Obtener el nombre del archivo que contiene la información de los
     * resultados ICFES de los aspirantes
     *
     * @return nombre del archivo que contiene la información de los resultados
     * ICFES de los aspirantes
     */
    public String getNombreArchivoIcfes(){
        if(nombreArcIcfes.isEmpty())
            return "Ningún Archivo Seleccionado";
        return nombreArcIcfes;
    }
    
    
    /*** Métodos para redireccionar a las vistas ***/
    
    /**
     * Redirecciona a la vista datosProcesados.xhtml que carga los archivos
     */
    public void datosProcesados(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/datosProcesados.xhtml");
    }
    
    /**
     * Redirecciona a la vista listaProgramasNoPonderables.xhtml que lista los
     * aspirantes no ponderables por programas
     */
    public void listaNoPonderable(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasNoPonderables.xhtml");
    }
    
    /**
     * Redirecciona a la vista listaProgramasPonderables.xhtml que lista los
     * aspirantes ponderables por programas
     */
    public void listaPonderable(){
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasPonderables.xhtml");
    }

    
          
}
