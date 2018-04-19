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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "programa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p")
    , @NamedQuery(name = "Programa.findByProid", query = "SELECT p FROM Programa p WHERE p.proid = :proid")
    , @NamedQuery(name = "Programa.findByPronombre", query = "SELECT p FROM Programa p WHERE p.pronombre = :pronombre")
    , @NamedQuery(name = "Programa.findByPropruebaad", query = "SELECT p FROM Programa p WHERE p.propruebaad = :propruebaad")
    , @NamedQuery(name = "Programa.findByProsede", query = "SELECT p FROM Programa p WHERE p.prosede = :prosede")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PROID")
    private Short proid;
    @Basic(optional = false)
    @Column(name = "PRONOMBRE")
    private String pronombre;
    @Basic(optional = false)
    @Column(name = "PROPRUEBAAD")
    private short propruebaad;
    @Basic(optional = false)
    @Column(name = "PROSEDE")
    private String prosede;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
    private Collection<Programacasos> programacasosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
    private Collection<Programacomponentes> programacomponentesCollection;
    @JoinColumn(name = "FACID", referencedColumnName = "FACID")
    @ManyToOne(optional = false)
    private Facultad facid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
    private Collection<Programaofertado> programaofertadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
    private Collection<Pruebaadicional> pruebaadicionalCollection;

    public Programa() {
    }

    public Programa(Short proid) {
        this.proid = proid;
    }

    public Programa(Short proid, String pronombre, short propruebaad, String prosede) {
        this.proid = proid;
        this.pronombre = pronombre;
        this.propruebaad = propruebaad;
        this.prosede = prosede;
    }

    public Short getProid() {
        return proid;
    }

    public void setProid(Short proid) {
        this.proid = proid;
    }

    public String getPronombre() {
        return pronombre;
    }

    public void setPronombre(String pronombre) {
        this.pronombre = pronombre;
    }

    public short getPropruebaad() {
        return propruebaad;
    }

    public void setPropruebaad(short propruebaad) {
        this.propruebaad = propruebaad;
    }

    public String getProsede() {
        return prosede;
    }

    public void setProsede(String prosede) {
        this.prosede = prosede;
    }

    @XmlTransient
    public Collection<Programacasos> getProgramacasosCollection() {
        return programacasosCollection;
    }

    public void setProgramacasosCollection(Collection<Programacasos> programacasosCollection) {
        this.programacasosCollection = programacasosCollection;
    }

    @XmlTransient
    public Collection<Programacomponentes> getProgramacomponentesCollection() {
        return programacomponentesCollection;
    }

    public void setProgramacomponentesCollection(Collection<Programacomponentes> programacomponentesCollection) {
        this.programacomponentesCollection = programacomponentesCollection;
    }

    public Facultad getFacid() {
        return facid;
    }

    public void setFacid(Facultad facid) {
        this.facid = facid;
    }

    @XmlTransient
    public Collection<Programaofertado> getProgramaofertadoCollection() {
        return programaofertadoCollection;
    }

    public void setProgramaofertadoCollection(Collection<Programaofertado> programaofertadoCollection) {
        this.programaofertadoCollection = programaofertadoCollection;
    }

    @XmlTransient
    public Collection<Pruebaadicional> getPruebaadicionalCollection() {
        return pruebaadicionalCollection;
    }

    public void setPruebaadicionalCollection(Collection<Pruebaadicional> pruebaadicionalCollection) {
        this.pruebaadicionalCollection = pruebaadicionalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proid != null ? proid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.proid == null && other.proid != null) || (this.proid != null && !this.proid.equals(other.proid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admisiones.data.Programa[ proid=" + proid + " ]";
    }
    
}
