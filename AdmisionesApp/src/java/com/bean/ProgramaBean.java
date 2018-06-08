package com.bean;

import com.dao.ProgramaDao;
import com.dao.ProgramaDaoImp;
import com.model.Programa;
import com.utilidades.Utilidades;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean ProgramaBean
 *
 * Contiene los métodos para gestionar los programas de una facultad 
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "programaBean")
@SessionScoped
public class ProgramaBean implements Serializable {

    /*** Atributos ***/ 
    
    /**
     * Lista de todos los programas
     */
    private List<Programa> listaProgramas = new ArrayList();
    
    /**
     * Lista de los programas pertenecientes a Popayán
     */
    private List<Programa> listaProgramasPopayan = new ArrayList();
    
    /**
     * Lista de los programas pertenecientes a regionalización
     */
    private List<Programa> listaProgramasRegionalizacion = new ArrayList();

     
    /*** Constructor ***/
    
    /**
     * Constructor por defecto
     */
    public ProgramaBean() {
    }

    
    /*** Métodos getter y setter ***/
    
    public List<Programa> getListaProgramas() {
        return listaProgramas;
    }

    public List<Programa> getListaProgramasPopayan() {
        return listaProgramasPopayan;
    }

    public List<Programa> getListaProgramasRegionalizacion() {
        return listaProgramasRegionalizacion;
    }
    
    
    /*** Métodos públicos ***/ 
    
    /**
     * Obtener la lista de todos los programas 
     * 
     * @return lista de los programas 
     */
    public List<Programa> getListaPro() {
        ProgramaDao proDao = new ProgramaDaoImp();
        this.listaProgramas = proDao.listarProgramas(); // llama al método del Dao 
                                                        // para obtener los programas desde la BD
        return listaProgramas;
    }
    
    /**
     * Obtener los programas de Popayán y de Regionalización. Se agregan los
     * programas a las listas: listaProgramasPopayan y
     * listaProgramasRegionalizacion, respectivamente
     */
    public void setProgramasSede(){
        // Si la lista de los programas está vacía, la llena. 
        if(this.listaProgramas.isEmpty()){
            this.getListaPro();
        }
        // Recorre la lista de los programas        
        for(int i = 0 ; i< this.listaProgramas.size(); i++){
            // si el programa es de Popayán, lo agrega a la lista: listaProgramasPopayan
            if(this.listaProgramas.get(i).getProsede().equals("Popayán")){
                this.listaProgramasPopayan.add(this.listaProgramas.get(i));
            }
            // si el programa es de regionalización, lo agrega a la lista: listaProgramasRegionalizacion
            else if(this.listaProgramas.get(i).getProsede().equals("Regionalización")){
                this.listaProgramasRegionalizacion.add(this.listaProgramas.get(i));
            }
        }
    }
    
    
    /*** Métodos para redireccionar a las vistas ***/ 
    
    /**
     * Redirecciona a la vista ProgramasOfertados.xhtml para listar los
     * programas ofertados 
     */
    public void verConfiguracionInicialPO(){        
        this.setProgramasSede(); // Se llena la lista de los programas de Popayán y s
                                 // de Regionalización respectivamente 
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfiguracionInicial/ProgramasOfertados.xhtml");
    }
    
    
}
