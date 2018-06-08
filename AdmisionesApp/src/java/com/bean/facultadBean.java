package com.bean;

import com.dao.facultadDao;
import com.dao.facultadDaoImp;
import com.model.Facultad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 * Bean facultadBean
 *
 * Contiene los métodos para gestionar las facultades
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "facultadBean")
@RequestScoped
public class facultadBean implements Serializable {

    /*** Atributos ***/ 
    
    /**
     * Lista de las facultades
     */
    private List<Facultad> listaFac;
    
    /**
     * Facultad de la cual se van a obtener los programas ofertados
     */
    private Facultad fac;
    
    /**
     * Id de la facultad
     */
    private Short selectedFac;  
    
    /*** Constructor ***/
    
    /**
     * Constructor por defecto
     */
    public facultadBean() {
    }

    
    /*** Métodos getter y setter ***/
    
    public Facultad getFac() {
        return fac;
    }

    public void setFac(Facultad fac) {
        this.fac = fac;
    }
    
    public Short getSelectedFac() {
        return selectedFac;
    }

    public void setSelectedFac(Short selectedFac) {
        this.selectedFac = selectedFac;
    }
    
    
    /*** Métodos públicos ***/
    
    /**
     * Obtener la lista de todas las facultades 
     * 
     * @return lista de todas las facultades
     */
    public List<Facultad> getListaFac() {
        facultadDao facDao = new facultadDaoImp();
        this.listaFac = facDao.listarFac();
        return listaFac;
    }

    /**
     * Obtener la lista de las facultades para mostrar en el combobox
     * 
     * @return lista de las facultades
     */
    public List<SelectItem> facultades(){
        List<SelectItem> listaPro = new ArrayList<>();
        this.getListaFac();
        String nombrePro;
        for (Facultad facultades : listaFac) {
            nombrePro = facultades.getFacnombre();
            listaPro.add(new SelectItem(nombrePro));
        }
        return listaPro;
    }

}
