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
@Table(name = "programaofertado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programaofertado.findAll", query = "SELECT p FROM Programaofertado p")
    , @NamedQuery(name = "Programaofertado.findByProid", query = "SELECT p FROM Programaofertado p WHERE p.programaofertadoPK.proid = :proid")
    , @NamedQuery(name = "Programaofertado.findByPerid", query = "SELECT p FROM Programaofertado p WHERE p.programaofertadoPK.perid = :perid")
    , @NamedQuery(name = "Programaofertado.findByProgofcuposregulares", query = "SELECT p FROM Programaofertado p WHERE p.progofcuposregulares = :progofcuposregulares")
    , @NamedQuery(name = "Programaofertado.findByProgoflistadeespera", query = "SELECT p FROM Programaofertado p WHERE p.progoflistadeespera = :progoflistadeespera")})
public class Programaofertado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramaofertadoPK programaofertadoPK;
    @Basic(optional = false)
    @Column(name = "PROGOFCUPOSREGULARES")
    private short progofcuposregulares;
    @Basic(optional = false)
    @Column(name = "PROGOFLISTADEESPERA")
    private short progoflistadeespera;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodoacademico periodoacademico;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Programa programa;

    public Programaofertado() {
    }

    public Programaofertado(ProgramaofertadoPK programaofertadoPK) {
        this.programaofertadoPK = programaofertadoPK;
    }

    public Programaofertado(ProgramaofertadoPK programaofertadoPK, short progofcuposregulares, short progoflistadeespera) {
        this.programaofertadoPK = programaofertadoPK;
        this.progofcuposregulares = progofcuposregulares;
        this.progoflistadeespera = progoflistadeespera;
    }

    public Programaofertado(short proid, BigDecimal perid) {
        this.programaofertadoPK = new ProgramaofertadoPK(proid, perid);
    }

    public ProgramaofertadoPK getProgramaofertadoPK() {
        return programaofertadoPK;
    }

    public void setProgramaofertadoPK(ProgramaofertadoPK programaofertadoPK) {
        this.programaofertadoPK = programaofertadoPK;
    }

    public short getProgofcuposregulares() {
        return progofcuposregulares;
    }

    public void setProgofcuposregulares(short progofcuposregulares) {
        this.progofcuposregulares = progofcuposregulares;
    }

    public short getProgoflistadeespera() {
        return progoflistadeespera;
    }

    public void setProgoflistadeespera(short progoflistadeespera) {
        this.progoflistadeespera = progoflistadeespera;
    }

    public Periodoacademico getPeriodoacademico() {
        return periodoacademico;
    }

    public void setPeriodoacademico(Periodoacademico periodoacademico) {
        this.periodoacademico = periodoacademico;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programaofertadoPK != null ? programaofertadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaofertado)) {
            return false;
        }
        Programaofertado other = (Programaofertado) object;
        if ((this.programaofertadoPK == null && other.programaofertadoPK != null) || (this.programaofertadoPK != null && !this.programaofertadoPK.equals(other.programaofertadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admisiones.data.Programaofertado[ programaofertadoPK=" + programaofertadoPK + " ]";
    }
    
}
