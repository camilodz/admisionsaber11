package com.bean;

import com.dao.facultadDao;
import com.dao.facultadDaoImp;
import com.model.Facultad;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

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
    
}
