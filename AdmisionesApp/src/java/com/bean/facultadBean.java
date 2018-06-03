package com.bean;

import com.dao.facultadDao;
import com.dao.facultadDaoImp;
import com.model.Facultad;
import com.model.Programaofertado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Karen
 */
@Named(value = "facultadBean")
@RequestScoped
public class facultadBean implements Serializable {

    private List<Facultad> listaFac;
    private Facultad fac;
    
    private Short selectedFac;  
    
    public facultadBean() {
    }

    public Facultad getFac() {
        return fac;
    }

    public void setFac(Facultad fac) {
        this.fac = fac;
    }
    
    
    
    public List<Facultad> getListaFac() {
        facultadDao facDao = new facultadDaoImp();
        this.listaFac = facDao.listarFac();
        return listaFac;
    }

    public Short getSelectedFac() {
        return selectedFac;
    }

    public void setSelectedFac(Short selectedFac) {
        this.selectedFac = selectedFac;
    }
    
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
