/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "programacasos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programacasos.findAll", query = "SELECT p FROM Programacasos p")
    , @NamedQuery(name = "Programacasos.findByEspid", query = "SELECT p FROM Programacasos p WHERE p.programacasosPK.espid = :espid")
    , @NamedQuery(name = "Programacasos.findByProid", query = "SELECT p FROM Programacasos p WHERE p.programacasosPK.proid = :proid")
    , @NamedQuery(name = "Programacasos.findByPerid", query = "SELECT p FROM Programacasos p WHERE p.programacasosPK.perid = :perid")
    , @NamedQuery(name = "Programacasos.findByProgcasoscupos", query = "SELECT p FROM Programacasos p WHERE p.progcasoscupos = :progcasoscupos")})
public class Programacasos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramacasosPK programacasosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROGCASOSCUPOS")
    private short progcasoscupos;
    @JoinColumn(name = "ESPID", referencedColumnName = "ESPID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Casosespeciales casosespeciales;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Programa programa;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodoacademico periodoacademico;

    public Programacasos() {
    }

    public Programacasos(ProgramacasosPK programacasosPK) {
        this.programacasosPK = programacasosPK;
    }

    public Programacasos(ProgramacasosPK programacasosPK, short progcasoscupos) {
        this.programacasosPK = programacasosPK;
        this.progcasoscupos = progcasoscupos;
    }

    public Programacasos(short espid, short proid, BigDecimal perid) {
        this.programacasosPK = new ProgramacasosPK(espid, proid, perid);
    }

    public ProgramacasosPK getProgramacasosPK() {
        return programacasosPK;
    }

    public void setProgramacasosPK(ProgramacasosPK programacasosPK) {
        this.programacasosPK = programacasosPK;
    }

    public short getProgcasoscupos() {
        return progcasoscupos;
    }

    public void setProgcasoscupos(short progcasoscupos) {
        this.progcasoscupos = progcasoscupos;
    }

    public Casosespeciales getCasosespeciales() {
        return casosespeciales;
    }

    public void setCasosespeciales(Casosespeciales casosespeciales) {
        this.casosespeciales = casosespeciales;
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
        hash += (programacasosPK != null ? programacasosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacasos)) {
            return false;
        }
        Programacasos other = (Programacasos) object;
        if ((this.programacasosPK == null && other.programacasosPK != null) || (this.programacasosPK != null && !this.programacasosPK.equals(other.programacasosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Programacasos[ programacasosPK=" + programacasosPK + " ]";
    }
    
}
