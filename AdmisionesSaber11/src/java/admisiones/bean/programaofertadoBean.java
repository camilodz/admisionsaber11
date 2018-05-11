/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admisiones.bean;

import admisiones.dao.programaofertadoDao;
import admisiones.dao.programaofertadoDaoImp;
import admisiones.model.Periodo;
import admisiones.model.Programaofertado;
import admisiones.utilidades.Utilidades;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Karen
 */
@Named(value = "programaofertadoBean")
@ApplicationScoped
public class programaofertadoBean implements Serializable {

    private List<Programaofertado> listaPO;
    private Programaofertado pof;
    
    private String selectedFac;
    
    public programaofertadoBean() {
    }

    public Programaofertado getPof() {
        return pof;
    }

    public void setPof(Programaofertado pof) {
        this.pof = pof;
    }

    public List<Programaofertado> getListaPO() {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        this.listaPO = poDao.listarPO();
        return listaPO;
    }
    
    public List<Programaofertado> getListaPOFacultad() {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        System.out.println("************** selected - " + selectedFac);
        this.listaPO = poDao.listarPOFacultad(selectedFac);
        return listaPO;
    }

    public void modificarPO() {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        poDao.modificarPO(pof);
        this.pof = new Programaofertado();
    }
    
    public Periodo getPeriodoPO(){
        
        return this.pof.getPeriodo();
    }

    public String getSelectedFac() {
        return selectedFac;
    }
    
    public void setSelectedFac(String selectedFac) {
        this.selectedFac = selectedFac;
    }    
    
    /*Métodos para Redireccionar entre Páginas*/
    public void verProgramasFacultad(String selectedFac){
        this.setSelectedFac(selectedFac);
        Utilidades.redireccionar("/Admisiones-Hibernate/faces/Vistas/ConfigurarProgramas/listarProgramasOfertados.xhtml");
    }
    
    public void configProgramasFacultad(String selectedFac){
        this.setSelectedFac(selectedFac);
        Utilidades.redireccionar("/Admisiones-Hibernate/faces/Vistas/ConfigurarProgramas/listarProgramasOfertados.xhtml");
    }
    
}
