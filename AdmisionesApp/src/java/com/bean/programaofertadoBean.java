package com.bean;

import com.dao.*;
import com.model.Facultad;
import com.model.Programaofertado;
import com.utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Bean programaofertadoBean
 * 
 * Contiene los métodos para gestionar los programas ofertados de un periodo académico
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "programaofertadoBean")
@ApplicationScoped
public class programaofertadoBean implements Serializable {

    /*** Atributos ***/
    
    /**
     * Lista de los programas ofertados de un periodo académico
     */
    private List<Programaofertado> listaPO = new ArrayList();
    
    /**
     * Programas ofertados que se filtraron por nombre
     */
    private List<Programaofertado> filtro;
    
    /**
     * Programa ofertado que se puede configurar
     */
    private Programaofertado pof;

    /**
     * Id de la facultad 
     */
    private String selectedFac;
    
    /**
     * Nombre de la facultad
     */
    private String nombreFac;
    
    /**
     * Id del programa ofertado
     */
    private String selectedPro;

    
    /*** Métodos getter y setter ***/
    
    public Programaofertado getPof() {    
        return pof;
    }

    public void setPof(Programaofertado pof) {
        this.pof = pof;
    }
    
    public List<Programaofertado> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Programaofertado> filtro) {
        this.filtro = filtro;
    }

    public String getSelectedFac() {
        return selectedFac;
    }

    public void setSelectedFac(String selectedFac) {
        this.selectedFac = selectedFac;
    }

    public String getNombreFac() {
        return nombreFac;
    }

    public void setNombreFac(String nombreFac) {
        this.nombreFac = nombreFac;
    }

    public String getSelectedPro() {
        return selectedPro;
    }
    
    public void setSelectedPro(String selectedPro) {
        this.selectedPro = selectedPro;
    }
    
    /*** Constructor por defecto ***/
    
    public programaofertadoBean() {
    }

    
    /*** Métodos públicos ***/  

    /**
     * Obtener la lista de todos los programas ofertados 
     * 
     * @return lista de todos los programas ofertados 
     */
    public List<Programaofertado> getListaPO() {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        // llama al método del Dao para obtener los programas ofertados desde la 
        // BD
        this.listaPO = poDao.listarPO();
        return listaPO;
    }
    
    /**
     * Obtener la lista de programas ofertados de una facultad específica
     *
     * @return lista de programas ofertados de una facultad específica
     */
    public List<Programaofertado> getListaPOFacultad() {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        // llama al método del Dao para obtener desde la BD los programas 
        // ofertados de una facultad específica 
        this.listaPO = poDao.listarPOFacultad(selectedFac);
        return listaPO;
    }
    
    /**
     * Obtener la lista de los programas ofertados de una facultad específica 
     * 
     * @param parametro id de la facultad 
     * @return lista de programas ofertados de una facultad específica
     */
    public List<Programaofertado> getListaPOFacultadPrograma(String parametro) {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        this.setIdFacSeleccionada(parametro);
        if (selectedFac.equals("")) {
            return this.getListaPO();
        }
        // llama al método del Dao para obtener desde la BD los programas 
        // ofertados de una facultad específica 
        this.listaPO = poDao.listarPOFacultad(selectedFac);
        return listaPO;
    }

    /**
     * Obtener el id de una fzcultad dado su nombre
     *
     * @param nombreFac nombre de la facultad
     */
    private void setIdFacSeleccionada(String nombreFac) {
        facultadDao faDao = new facultadDaoImp();
        // obtener el id de una facultad desde la BD
        selectedFac = faDao.getIdFacultad(nombreFac);
    }

    /**
     * Editar la información de un programa ofertado (cupos y ponderados ICFES)
     */
    public void modificarPO() {
        programaofertadoDao poDao = new programaofertadoDaoImp();
        // Modificar (en la BD) los cupos y ponderados ICFES de un programa 
        // ofertado 
        poDao.modificarPO(pof);
        this.pof = new Programaofertado();
    }

    /**
     * Obtener el nombre de la facultad dado el id (global)
     */
    public void modifyNombreFac() {
        facultadDao facDao = new facultadDaoImp();
        // se obtiene la lista de facultades desde la BD
        List<Facultad> lista = facDao.listarFac();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getFacid() == Short.parseShort(this.selectedFac)) {
                this.setNombreFac(lista.get(i).getFacnombre());
            }
        }
    }
   
    /*** Métodos para redireccionar a las vistas ***/
    
    /**
     * Redireccionar a la vista listarProgramasOfertados.xhtml para listar los
     * programas ofertados de una facultad específica
     *
     * @param selectedFac id de la facultad
     */
    public void verProgramasFacultad(String selectedFac) {
        this.setSelectedFac(selectedFac);
        this.modifyNombreFac();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfigurarProgramas/listarProgramasOfertados.xhtml");
    }

    /**
     * Redireccionar a la vista listarProgramasOfertados.xhtml para listar los
     * programas ofertados de una facultad específica
     *
     * @param selectedFac id de la facultad
     */
    public void configProgramasFacultad(String selectedFac) {
        this.setSelectedFac(selectedFac);
        this.modifyNombreFac();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfigurarProgramas/listarProgramasOfertados.xhtml");
    }

}
