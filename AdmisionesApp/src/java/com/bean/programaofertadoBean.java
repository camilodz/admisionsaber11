package com.bean;

import com.dao.*;
import com.model.Facultad;
import com.model.Periodo;
import com.model.Programaofertado;
import com.utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Karen
 */
@Named(value = "programaofertadoBean")
@ApplicationScoped
public class programaofertadoBean implements Serializable {

    private List<Programaofertado> listaPO = new ArrayList();
    private List<Programaofertado> filtro;
    private Programaofertado pof;

    private String selectedFac;
    private String selectedPro;
    
    private String nombreFac;

    public String getSelectedPro() {
        return selectedPro;
    }
    
    public String getNombreFac(){
        return this.nombreFac;
    }
    
    public void setNombreFac(String fac){
        this.nombreFac = fac;
    }

    public void setSelectedPro(String selectedPro) {
        this.selectedPro = selectedPro;
    }

    public List<Programaofertado> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Programaofertado> filtro) {
        this.filtro = filtro;
    }

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

    /*metodos utilizados para buscar programas de una facultad*/
    public List<Programaofertado> getListaPOFacultadPrograma(String parametro) {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        this.setIdFacSeleccionada(parametro);
        if (selectedFac.equals("")) {
            return this.getListaPO();
        }
        this.listaPO = poDao.listarPOFacultad(selectedFac);
        return listaPO;
    }

    private void setIdFacSeleccionada(String nombreFac) {
        facultadDao faDao = new facultadDaoImp();
        selectedFac = faDao.getIdFacultad(nombreFac);
    }

    //hasta aqui
    public void modificarPO() {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        poDao.modificarPO(pof);
        this.pof = new Programaofertado();
    }

    public Periodo getPeriodoPO() {

        return this.pof.getPeriodo();
    }

    public String getSelectedFac() {
        return selectedFac;
    }

    public void setSelectedFac(String selectedFac) {
        this.selectedFac = selectedFac;
    }

    /*Métodos para Redireccionar entre Páginas*/
    public void verProgramasFacultad(String selectedFac) {
        this.setSelectedFac(selectedFac);
        this.modifyNombreFac();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfigurarProgramas/listarProgramasOfertados.xhtml");
    }

    public void configProgramasFacultad(String selectedFac) {
        this.setSelectedFac(selectedFac);
        this.modifyNombreFac();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfigurarProgramas/listarProgramasOfertados.xhtml");
    }

    public void modifyNombreFac() {       
        
        facultadDao facDao = new facultadDaoImp();
        List<Facultad> lista = facDao.listarFac();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getFacid() == Short.parseShort(this.selectedFac)) {
                this.setNombreFac(lista.get(i).getFacnombre());
            }
        }
    }

}
