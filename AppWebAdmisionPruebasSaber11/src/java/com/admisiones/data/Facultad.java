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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "facultad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f")
    , @NamedQuery(name = "Facultad.findByFacid", query = "SELECT f FROM Facultad f WHERE f.facid = :facid")
    , @NamedQuery(name = "Facultad.findByFacnombre", query = "SELECT f FROM Facultad f WHERE f.facnombre = :facnombre")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FACID")
    private Short facid;
    @Basic(optional = false)
    @Column(name = "FACNOMBRE")
    private String facnombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facid")
    private Collection<Programa> programaCollection;

    public Facultad() {
    }

    public Facultad(Short facid) {
        this.facid = facid;
    }

    public Facultad(Short facid, String facnombre) {
        this.facid = facid;
        this.facnombre = facnombre;
    }

    public Short getFacid() {
        return facid;
    }

    public void setFacid(Short facid) {
        this.facid = facid;
    }

    public String getFacnombre() {
        return facnombre;
    }

    public void setFacnombre(String facnombre) {
        this.facnombre = facnombre;
    }

    @XmlTransient
    public Collection<Programa> getProgramaCollection() {
        return programaCollection;
    }

    public void setProgramaCollection(Collection<Programa> programaCollection) {
        this.programaCollection = programaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facid != null ? facid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.facid == null && other.facid != null) || (this.facid != null && !this.facid.equals(other.facid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admisiones.data.Facultad[ facid=" + facid + " ]";
    }
    
}
