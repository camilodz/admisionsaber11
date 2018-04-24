/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Karen
 */
@Entity
@Table(name = "periodo")
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p")
    , @NamedQuery(name = "Periodo.findByPerid", query = "SELECT p FROM Periodo p WHERE p.perid = :perid")
    , @NamedQuery(name = "Periodo.findByPerminpop", query = "SELECT p FROM Periodo p WHERE p.perminpop = :perminpop")
    , @NamedQuery(name = "Periodo.findByPerminreg", query = "SELECT p FROM Periodo p WHERE p.perminreg = :perminreg")
    , @NamedQuery(name = "Periodo.findByPervigente", query = "SELECT p FROM Periodo p WHERE p.pervigente = :pervigente")})
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PERID")
    private String perid;
    @Basic(optional = false)
    @Column(name = "PERMINPOP")
    private short perminpop;
    @Basic(optional = false)
    @Column(name = "PERMINREG")
    private short perminreg;
    @Basic(optional = false)
    @Column(name = "PERVIGENTE")
    private short pervigente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodo")
    private Collection<Programaofertado> programaofertadoCollection;

    public Periodo() {
    }

    public Periodo(String perid) {
        this.perid = perid;
    }

    public Periodo(String perid, short perminpop, short perminreg, short pervigente) {
        this.perid = perid;
        this.perminpop = perminpop;
        this.perminreg = perminreg;
        this.pervigente = pervigente;
    }

    public String getPerid() {
        return perid;
    }

    public void setPerid(String perid) {
        this.perid = perid;
    }

    public short getPerminpop() {
        return perminpop;
    }

    public void setPerminpop(short perminpop) {
        this.perminpop = perminpop;
    }

    public short getPerminreg() {
        return perminreg;
    }

    public void setPerminreg(short perminreg) {
        this.perminreg = perminreg;
    }

    public short getPervigente() {
        return pervigente;
    }

    public void setPervigente(short pervigente) {
        this.pervigente = pervigente;
    }

    public Collection<Programaofertado> getProgramaofertadoCollection() {
        return programaofertadoCollection;
    }

    public void setProgramaofertadoCollection(Collection<Programaofertado> programaofertadoCollection) {
        this.programaofertadoCollection = programaofertadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perid != null ? perid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.perid == null && other.perid != null) || (this.perid != null && !this.perid.equals(other.perid))) {
            return false;
        }
        return true;
    }

    @Override
//    public String toString() {
//        return "com.admisiones.data.Periodo[ perid=" + perid + " ]";
//    }
    
    public String toString() {
        return perid + "";
    }
    
}
