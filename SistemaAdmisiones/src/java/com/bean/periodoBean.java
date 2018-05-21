/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.PeriodoDao;
import com.dao.PeriodoDaoImp;
import com.dao.facultadDao;
import com.dao.facultadDaoImp;
import com.model.Facultad;
import com.model.Periodo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Karen
 */
@Named(value = "periodoBean")
@RequestScoped
public class periodoBean  implements Serializable{

    private List<Periodo> listaPer;
    private Periodo periodo  = new Periodo();
    
    private List<SelectItem> listPeriodos;
    /**
     * Creates a new instance of periodoBean
     */
    public periodoBean() {
        
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
    
    
    public List<Periodo> getListaPer() {
        PeriodoDao perDao = new PeriodoDaoImp();
        this.listaPer = perDao.mostrarPeriodos();
        return listaPer;
    }
    
    public void nuevoPeriodo(){
        PeriodoDao pDao = new PeriodoDaoImp();
        System.out.println("*********** " + periodo.getPerid());
        System.out.println("*********** " + periodo.getPerminpop());
        System.out.println("*********** " + periodo.getPerminreg());
//        periodo.setPerid("2018.2");
        periodo.setPervigente(true);
        periodo.setProgramaofertados(null);
        pDao.nuevoPeriodo(periodo);
        periodo = new Periodo();
    }
    
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
