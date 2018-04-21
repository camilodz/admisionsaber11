/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.bean;

import com.admisiones.data.*;
import com.admisiones.jpa.*;
import com.admisiones.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JORGE
 */
@ManagedBean
@RequestScoped
public class gestorAdmisionesBean {

    private List<Facultad> listFacultades;
    private List<Programaofertado> listProgramas;
    private List<Periodoacademico> listPeriodosAcademicos;
    private List<Programacasos> listProgramaCasos;
    private List<Programacomponentes> listProgramaCompIcfes;
    private List<Pruebaadicional> listPruebaAd;
    private Short facSelected;
    private EntityManagerFactory emf;

    /**
     * Creates a new instance of gestorAdmisionesBean
     */
    public gestorAdmisionesBean() {
        listFacultades = new ArrayList();
        listProgramas = new ArrayList();
        listPeriodosAcademicos = new ArrayList<>();
        listProgramaCasos = new ArrayList<>();
        listProgramaCompIcfes = new ArrayList<>();
        listPruebaAd = new ArrayList<>();
        emf = Persistence.createEntityManagerFactory("ProyectoII_AdmisionesSaber11PU");
        this.facSelected = 100;
    }

    public Short getFacSelected() {
        return facSelected;
    }

    public void setFacSelected(Short facSelected) {
        this.facSelected = facSelected;
    }

    public List<Facultad> getListFacultades() {
        FacultadJpaController ctrl = new FacultadJpaController(emf);
        listFacultades = ctrl.findFacultadEntities();
        return listFacultades;
    }

    public String getFacNombre() {
        if (listFacultades.isEmpty()) {
            getListFacultades();
        }
        for (Facultad f : listFacultades) {
            if (f.getFacid() == facSelected) {
                return f.getFacnombre();
            }
        }
        return "NO hay Facultad Asociada.";
    }

    public void viewProgramas(Short idFac) {
        this.setFacSelected(idFac);
        Utilidades.redireccionar("/ProyectoII_AdmisionesSaber11/faces/Vistas/ConfigurarProgramas/viewProgramasFacultad.xhtml");
    }

    public String getPeriodoAcademicoActual() {
        if (listPeriodosAcademicos.isEmpty()) {
            listPeriodosAcademicos = this.getListPeriodosAcademicos();
        }
        for (Periodoacademico pa : listPeriodosAcademicos) {
            if (pa.getPerestado() == 1) {
                return "" + pa.getPerid();
            }
        }

        return "";
    }

    private List<Periodoacademico> getListPeriodosAcademicos() {
        if (listPeriodosAcademicos.isEmpty()) {
            PeriodoacademicoJpaController paC = new PeriodoacademicoJpaController(emf);
            listPeriodosAcademicos = paC.findPeriodoacademicoEntities();
            return listPeriodosAcademicos;
        } else {
            return listPeriodosAcademicos;
        }
    }

    public List<Programaofertado> getProgramasFacultad() {
        if (listProgramas.isEmpty()) {
            getListProgramas();
        }
        List<Programaofertado> listProgramasFac = new ArrayList();
        for (Programaofertado p : listProgramas) {
            if (p.getPrograma().getFacid().getFacid() == this.facSelected) {
                listProgramasFac.add(p);
            }
        }
        return listProgramasFac;
    }

    private List<Programaofertado> getListProgramas() {
        ProgramaofertadoJpaController ctrl = new ProgramaofertadoJpaController(emf);
        listProgramas = ctrl.findProgramaofertadoEntities();
        return listProgramas;
    }

    private List<Programacasos> getListProgramasCasos() {
        ProgramacasosJpaController ctrl = new ProgramacasosJpaController(emf);
        listProgramaCasos = ctrl.findProgramacasosEntities();
        return listProgramaCasos;
    }

    public int getCupos(String casoEsp, Short idProg) {
        if (listProgramaCasos.isEmpty()) {
            getListProgramasCasos();
        }
        for (Programacasos p : listProgramaCasos) {
            if (p.getCasosespeciales().getEspnombre().equals(casoEsp) && p.getPrograma().getProid() == idProg) {
                return p.getProgcasoscupos();
            }
        }
        return -1;
    }

    public int getCuposTotal(String tipo, Short idProg) {
        if (listProgramas.isEmpty()) {
            getListProgramas();
        }
        for (Programaofertado p : listProgramas) {
            if (p.getPrograma().getProid() == idProg) {
                if (tipo.equalsIgnoreCase("Cupos Regulares")) {
                    return p.getProgofcuposregulares();
                } else if (tipo.equalsIgnoreCase("Cupos Totales")) {
                    return p.getProgofcupostotales();
                } else if (tipo.equalsIgnoreCase("Lista Espera")) {
                    return p.getProgoflistadeespera();
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    private List<Programacomponentes> getListProgramasCompIcfes() {
        ProgramacomponentesJpaController ctrl = new ProgramacomponentesJpaController(emf);
        listProgramaCompIcfes = ctrl.findProgramacomponentesEntities();
        return listProgramaCompIcfes;
    }

    public int getComponenteIcfes(String compIcfes, Short idProg) {
        if (listProgramaCompIcfes.isEmpty()) {
            getListProgramasCompIcfes();
        }
        for (Programacomponentes p : listProgramaCompIcfes) {
            if (p.getComponentesicfes().getCompnombre().equals(compIcfes) && p.getPrograma().getProid() == idProg) {
                return p.getProgcompponderado();
            }
        }
        return -1;
    }

    private List<Pruebaadicional> getListPruebaAd() {
        PruebaadicionalJpaController ctrl = new PruebaadicionalJpaController(emf);
        listPruebaAd = ctrl.findPruebaadicionalEntities();
        return listPruebaAd;
    }

    public int getPonderadoPruebaAd(Short idProg) {
        if (listPruebaAd.isEmpty()) {
            getListPruebaAd();
        }
        for (Pruebaadicional p : listPruebaAd) {
            if (p.getPrograma().getProid() == idProg) {
                return p.getPruebaponderado();
            }
        }
        return 0;
    }

    public int getPonderadoPruebaIcfes(Short idProg) {
        if (listPruebaAd.isEmpty()) {
            getListPruebaAd();
        }
        for (Pruebaadicional p : listPruebaAd) {
            if (p.getPrograma().getProid() == idProg) {
                return 100 - p.getPruebaponderado();
            }
        }
        return 100;
    }

    public void volver() {
        Utilidades.redireccionar("/ProyectoII_AdmisionesSaber11/faces/Vistas/ConfigurarProgramas/ListarFacultades.xhtml");
    }

    public void configProgramas() {
        Utilidades.redireccionar("/ProyectoII_AdmisionesSaber11/faces/Vistas/ConfigurarProgramas/configProgramasFacultad.xhtml");
    }

}
