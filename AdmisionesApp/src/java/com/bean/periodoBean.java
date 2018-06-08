package com.bean;

import com.dao.PeriodoDao;
import com.dao.PeriodoDaoImp;
import com.model.Periodo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 * Bean periodoBean
 *
 * Contiene los métodos para gestionar los periodos académicos
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "periodoBean")
@RequestScoped
public class periodoBean implements Serializable{
    
    /*** Atributos ***/
    
    /**
     * Lista de todos los periodos académicos
     */
    private List<Periodo> listaPer;
    
    /**
     * Nuevo periodo académico a configurar
     */
    private Periodo periodo  = new Periodo();
    
    /**
     * Lista de periodos académicos para usar en el combobox
     */
    private List<SelectItem> listPeriodos;
    
    
    /*** Constructor ***/
    
    /**
     * Constructor por defecto
     */
    public periodoBean() {
    }
 
    
    /*** Métodos getter y setter ***/
    
    public Periodo getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    
    /*** Métodos públicos ***/ 
    
    /**
     * Obtener la lista de todos los periodos académicos
     *
     * @return lista de los periodos académicos
     */
    public List<Periodo> getListaPer() {
        PeriodoDao perDao = new PeriodoDaoImp();
        // llama al método del Dao para obtener los periodos académicos desde la 
        // BD
        this.listaPer = perDao.mostrarPeriodos();
        return listaPer;
    }

    /**
     * Configurar un nuevo periodo académico
     */
    public void nuevoPeriodo(){
        PeriodoDao pDao = new PeriodoDaoImp();
        periodo.setPervigente(true);
        periodo.setProgramaofertados(null);
        // llama al método del Dao para agregar el nuevo periodo académico a 
        // la BD
        pDao.nuevoPeriodo(periodo);
        periodo = new Periodo();
    }
    
    /**
     * Obtener la lista de los periodos académicos para mostrar en el combobox
     * 
     * @return 
     */
    public List<SelectItem> getListPeriodos(){
        this.listPeriodos = new ArrayList<SelectItem>();
        PeriodoDao pDao = new PeriodoDaoImp();
        List<Periodo> p = pDao.mostrarPeriodos();
        this.listPeriodos.clear();
        for(Periodo per: p){
            SelectItem perItem = new SelectItem(per.getPerid());
            this.listPeriodos.add(perItem);
        }
        return this.listPeriodos;
    }
    
}
