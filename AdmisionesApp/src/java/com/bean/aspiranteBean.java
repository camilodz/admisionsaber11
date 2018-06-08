package com.bean;

import com.dao.AspiranteDaoImp;
import com.model.Aspirante;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import com.dao.AspiranteDao;

/**
 * Bean aspiranteBean
 *
 * Contiene los métodos para gestionar los aspirantes 
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "aspirantePonderableBean")
@ViewScoped
public class aspiranteBean implements Serializable{
    
    /*** Atributos ***/ 
    
    /**
     * Lista de los aspirantes
     */
    private List<Aspirante> listar;
    
    /**
     * Aspirante que se clasifica en ponderable o no ponderable 
     */
    private Aspirante aspirante;
    
    
    /*** Constructor ***/
    
    /**
     * Constructor por defecto
     */
    public aspiranteBean() {
    }

    
    /*** Métodos getter y setter ***/

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }
    
    
    /*** Métodos públicos ***/
    
    /**
     * Obtener la lista de todos los aspirantes 
     * 
     * @return lista de los aspirantes
     */
    public List<Aspirante> getListar() {
        AspiranteDao aspdao = new AspiranteDaoImp();
        listar = aspdao.mostrarAspirantes();
        return listar;
    }
    
    
}
