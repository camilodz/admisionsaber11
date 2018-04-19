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
@Table(name = "casosespeciales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Casosespeciales.findAll", query = "SELECT c FROM Casosespeciales c")
    , @NamedQuery(name = "Casosespeciales.findByEspid", query = "SELECT c FROM Casosespeciales c WHERE c.espid = :espid")
    , @NamedQuery(name = "Casosespeciales.findByEspnombre", query = "SELECT c FROM Casosespeciales c WHERE c.espnombre = :espnombre")})
public class Casosespeciales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ESPID")
    private Short espid;
    @Basic(optional = false)
    @Column(name = "ESPNOMBRE")
    private String espnombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casosespeciales")
    private Collection<Programacasos> programacasosCollection;

    public Casosespeciales() {
    }

    public Casosespeciales(Short espid) {
        this.espid = espid;
    }

    public Casosespeciales(Short espid, String espnombre) {
        this.espid = espid;
        this.espnombre = espnombre;
    }

    public Short getEspid() {
        return espid;
    }

    public void setEspid(Short espid) {
        this.espid = espid;
    }

    public String getEspnombre() {
        return espnombre;
    }

    public void setEspnombre(String espnombre) {
        this.espnombre = espnombre;
    }

    @XmlTransient
    public Collection<Programacasos> getProgramacasosCollection() {
        return programacasosCollection;
    }

    public void setProgramacasosCollection(Collection<Programacasos> programacasosCollection) {
        this.programacasosCollection = programacasosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (espid != null ? espid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Casosespeciales)) {
            return false;
        }
        Casosespeciales other = (Casosespeciales) object;
        if ((this.espid == null && other.espid != null) || (this.espid != null && !this.espid.equals(other.espid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admisiones.data.Casosespeciales[ espid=" + espid + " ]";
    }
    
}
