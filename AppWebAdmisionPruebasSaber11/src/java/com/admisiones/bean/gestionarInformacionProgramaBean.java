/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.data.Programacasos;
import com.admisiones.data.Programaofertado;
import com.admisiones.jpa.ProgramacasosJpaController;
import com.admisiones.jpa.ProgramaofertadoJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Karen
 */
@ManagedBean
@RequestScoped
public class gestionarInformacionProgramaBean {

    private List<Programaofertado> listProgramasOfertados;
    private List<Programacasos> listProgramasCasos;
    private EntityManagerFactory emf;

    private Short SelectedPro;

    /**
     * Creates a new instance of gestionarInformacionProgramaBean
     */
    public gestionarInformacionProgramaBean() {
        listProgramasOfertados = new ArrayList();
        listProgramasCasos = new ArrayList();
    }

    public Short getSelectedPro() {
        return SelectedPro;
    }

    public void setSelectedPro(Short SelectedPro) {
        this.SelectedPro = SelectedPro;
    }

    public List<Programaofertado> getListProgramasOfertados() {
        emf = Persistence.createEntityManagerFactory("admisionesPU");
        ProgramaofertadoJpaController ctrl = new ProgramaofertadoJpaController(emf);
        listProgramasOfertados = ctrl.findProgramaofertadoEntities();
        return listProgramasOfertados;
    }

    public Short getCuposRegulares(Short proid) {
        listProgramasOfertados = this.getListProgramasOfertados();
        if (listProgramasOfertados.isEmpty()) {
            return 0;
        }

        for (Programaofertado p : listProgramasOfertados) {
            if (p.getProgramaofertadoPK().getProid() == proid) {
                return p.getProgofcuposregulares();
            }
        }
        return -1;
    }

    public Short getCuposListaEspera(Short proid) {
        listProgramasOfertados = this.getListProgramasOfertados();
        if (listProgramasOfertados.isEmpty()) {
            return 0;
        }

        for (Programaofertado p : listProgramasOfertados) {
            if (p.getProgramaofertadoPK().getProid() == proid) {
                return p.getProgoflistadeespera();
            }
        }
        return -1;
    }

    public Short getCuposTotales(Short proid){
        listProgramasOfertados = this.getListProgramasOfertados();
        if (listProgramasOfertados.isEmpty()) {
            return 0;
        }

        for (Programaofertado p : listProgramasOfertados) {
            if (p.getProgramaofertadoPK().getProid() == proid) {
                return 0;
            }
        }
        return -1;
    }
    
    public List<Programacasos> getListProgramasCasos() {
        emf = Persistence.createEntityManagerFactory("admisionesPU");
        ProgramacasosJpaController ctrl = new ProgramacasosJpaController(emf);
        listProgramasCasos = ctrl.findProgramacasosEntities();
        return listProgramasCasos;
    }

    public Short getCupos(String casoEsp, Short proid) {
        this.listProgramasCasos = this.getListProgramasCasos();
        if (listProgramasCasos.isEmpty()) {
            return 0;
        }
        for (Programacasos p : listProgramasCasos) {
            if (p.getCasosespeciales().getEspnombre().equals(casoEsp) && p.getPrograma().getProid().equals(proid)) {
                return p.getProgcasoscupos();                
            }
        }
        return -1;
    }

    public Short getDificilAcceso(Short proid) {
        this.listProgramasCasos = this.getListProgramasCasos();
        if (listProgramasCasos.isEmpty()) {
            return 0;
        }
        for (Programacasos p : listProgramasCasos) {
            if (p.getCasosespeciales().getEspnombre().equals("Difícil acceso")) {
                return p.getProgcasoscupos();
            }
        }
        return -1;
    }

    public Short getNormalistas(Short proid) {
        this.listProgramasCasos = this.getListProgramasCasos();
        if (listProgramasCasos.isEmpty()) {
            return 0;
        }

        for (Programacasos p : listProgramasCasos) {
            if (p.getCasosespeciales().getEspnombre().equals("Normalistas")) {
                return p.getProgcasoscupos();
            }
        }
        return -1;
    }

    public Short getIndigenas(Short proid) {
        this.listProgramasCasos = this.getListProgramasCasos();
        if (listProgramasCasos.isEmpty()) {
            return 0;
        }

        for (Programacasos p : listProgramasCasos) {
            if (p.getCasosespeciales().getEspnombre().equals("Indígena")) {
                return p.getProgcasoscupos();
            }
        }
        return -1;
    }

    public Short getZonaMarginal(Short proid) {
        this.listProgramasCasos = this.getListProgramasCasos();
        if (listProgramasCasos.isEmpty()) {
            return 0;
        }

        for (Programacasos p : listProgramasCasos) {
            if (p.getCasosespeciales().getEspnombre().equals("Zona Marginal")) {
                return p.getProgcasoscupos();
            }
        }
        return -1;
    }

    public Short getAfro(Short proid) {
        this.listProgramasCasos = this.getListProgramasCasos();
        if (listProgramasCasos.isEmpty()) {
            return 0;
        }

        for (Programacasos p : listProgramasCasos) {
            if (p.getCasosespeciales().getEspnombre().equals("Afrodescendiente")) {
                return p.getProgcasoscupos();
            }
        }
        return -1;
    }

    public Short getCostaPacifica(Short proid) {
        this.listProgramasCasos = this.getListProgramasCasos();
        if (listProgramasCasos.isEmpty()) {
            return 0;
        }

        for (Programacasos p : listProgramasCasos) {
            if (p.getCasosespeciales().getEspnombre().equals("Costa Pacífica")) {
                return p.getProgcasoscupos();
            }
        }
        return -1;
    }

}
