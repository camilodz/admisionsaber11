/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admisiones.bean;

import admisiones.dao.facultadDao;
import admisiones.dao.facultadDaoImp;
import admisiones.model.Facultad;
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
