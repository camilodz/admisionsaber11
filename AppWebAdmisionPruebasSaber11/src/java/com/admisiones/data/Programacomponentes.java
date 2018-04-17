/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Karen
 */
@Entity
@Table(name = "programacomponentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programacomponentes.findAll", query = "SELECT p FROM Programacomponentes p")
    , @NamedQuery(name = "Programacomponentes.findByProid", query = "SELECT p FROM Programacomponentes p WHERE p.programacomponentesPK.proid = :proid")
    , @NamedQuery(name = "Programacomponentes.findByPerid", query = "SELECT p FROM Programacomponentes p WHERE p.programacomponentesPK.perid = :perid")
    , @NamedQuery(name = "Programacomponentes.findByCompid", query = "SELECT p FROM Programacomponentes p WHERE p.programacomponentesPK.compid = :compid")
    , @NamedQuery(name = "Programacomponentes.findByProgcompponderado", query = "SELECT p FROM Programacomponentes p WHERE p.progcompponderado = :progcompponderado")})
public class Programacomponentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramacomponentesPK programacomponentesPK;
    @Basic(optional = false)
    @Column(name = "PROGCOMPPONDERADO")
    private short progcompponderado;
    @JoinColumn(name = "COMPID", referencedColumnName = "COMPID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Componentesicfes componentesicfes;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Programa programa;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodoacademico periodoacademico;

    public Programacomponentes() {
    }

    public Programacomponentes(ProgramacomponentesPK programacomponentesPK) {
        this.programacomponentesPK = programacomponentesPK;
    }

    public Programacomponentes(ProgramacomponentesPK programacomponentesPK, short progcompponderado) {
        this.programacomponentesPK = programacomponentesPK;
        this.progcompponderado = progcompponderado;
    }

    public Programacomponentes(short proid, BigDecimal perid, short compid) {
        this.programacomponentesPK = new ProgramacomponentesPK(proid, perid, compid);
    }

    public ProgramacomponentesPK getProgramacomponentesPK() {
        return programacomponentesPK;
    }

    public void setProgramacomponentesPK(ProgramacomponentesPK programacomponentesPK) {
        this.programacomponentesPK = programacomponentesPK;
    }

    public short getProgcompponderado() {
        return progcompponderado;
    }

    public void setProgcompponderado(short progcompponderado) {
        this.progcompponderado = progcompponderado;
    }

    public Componentesicfes getComponentesicfes() {
        return componentesicfes;
    }

    public void setComponentesicfes(Componentesicfes componentesicfes) {
        this.componentesicfes = componentesicfes;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Periodoacademico getPeriodoacademico() {
        return periodoacademico;
    }

    public void setPeriodoacademico(Periodoacademico periodoacademico) {
        this.periodoacademico = periodoacademico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programacomponentesPK != null ? programacomponentesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacomponentes)) {
            return false;
        }
        Programacomponentes other = (Programacomponentes) object;
        if ((this.programacomponentesPK == null && other.programacomponentesPK != null) || (this.programacomponentesPK != null && !this.programacomponentesPK.equals(other.programacomponentesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admisiones.data.Programacomponentes[ programacomponentesPK=" + programacomponentesPK + " ]";
    }
    
}
