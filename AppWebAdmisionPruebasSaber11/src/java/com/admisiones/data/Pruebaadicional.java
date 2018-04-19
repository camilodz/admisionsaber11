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
 * @author JORGE
 */
@Entity
@Table(name = "pruebaadicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pruebaadicional.findAll", query = "SELECT p FROM Pruebaadicional p")
    , @NamedQuery(name = "Pruebaadicional.findByProid", query = "SELECT p FROM Pruebaadicional p WHERE p.pruebaadicionalPK.proid = :proid")
    , @NamedQuery(name = "Pruebaadicional.findByPerid", query = "SELECT p FROM Pruebaadicional p WHERE p.pruebaadicionalPK.perid = :perid")
    , @NamedQuery(name = "Pruebaadicional.findByPruebaponderado", query = "SELECT p FROM Pruebaadicional p WHERE p.pruebaponderado = :pruebaponderado")})
public class Pruebaadicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruebaadicionalPK pruebaadicionalPK;
    @Basic(optional = false)
    @Column(name = "PRUEBAPONDERADO")
    private short pruebaponderado;
    @JoinColumn(name = "PERID", referencedColumnName = "PERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodoacademico periodoacademico;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Programa programa;

    public Pruebaadicional() {
    }

    public Pruebaadicional(PruebaadicionalPK pruebaadicionalPK) {
        this.pruebaadicionalPK = pruebaadicionalPK;
    }

    public Pruebaadicional(PruebaadicionalPK pruebaadicionalPK, short pruebaponderado) {
        this.pruebaadicionalPK = pruebaadicionalPK;
        this.pruebaponderado = pruebaponderado;
    }

    public Pruebaadicional(short proid, BigDecimal perid) {
        this.pruebaadicionalPK = new PruebaadicionalPK(proid, perid);
    }

    public PruebaadicionalPK getPruebaadicionalPK() {
        return pruebaadicionalPK;
    }

    public void setPruebaadicionalPK(PruebaadicionalPK pruebaadicionalPK) {
        this.pruebaadicionalPK = pruebaadicionalPK;
    }

    public short getPruebaponderado() {
        return pruebaponderado;
    }

    public void setPruebaponderado(short pruebaponderado) {
        this.pruebaponderado = pruebaponderado;
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
        hash += (pruebaadicionalPK != null ? pruebaadicionalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pruebaadicional)) {
            return false;
        }
        Pruebaadicional other = (Pruebaadicional) object;
        if ((this.pruebaadicionalPK == null && other.pruebaadicionalPK != null) || (this.pruebaadicionalPK != null && !this.pruebaadicionalPK.equals(other.pruebaadicionalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admisiones.data.Pruebaadicional[ pruebaadicionalPK=" + pruebaadicionalPK + " ]";
    }
    
}
