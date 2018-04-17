/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admisiones.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
 * @author Karen
 */
@Entity
@Table(name = "periodoacademico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodoacademico.findAll", query = "SELECT p FROM Periodoacademico p")
    , @NamedQuery(name = "Periodoacademico.findByPerid", query = "SELECT p FROM Periodoacademico p WHERE p.perid = :perid")
    , @NamedQuery(name = "Periodoacademico.findByPerpuntajeminpopayan", query = "SELECT p FROM Periodoacademico p WHERE p.perpuntajeminpopayan = :perpuntajeminpopayan")
    , @NamedQuery(name = "Periodoacademico.findByPerpuntajeminregionalizacion", query = "SELECT p FROM Periodoacademico p WHERE p.perpuntajeminregionalizacion = :perpuntajeminregionalizacion")
    , @NamedQuery(name = "Periodoacademico.findByPerestado", query = "SELECT p FROM Periodoacademico p WHERE p.perestado = :perestado")})
public class Periodoacademico implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PERID")
    private BigDecimal perid;
    @Basic(optional = false)
    @Column(name = "PERPUNTAJEMINPOPAYAN")
    private short perpuntajeminpopayan;
    @Basic(optional = false)
    @Column(name = "PERPUNTAJEMINREGIONALIZACION")
    private short perpuntajeminregionalizacion;
    @Basic(optional = false)
    @Column(name = "PERESTADO")
    private short perestado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoacademico")
    private List<Programacasos> programacasosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoacademico")
    private List<Programacomponentes> programacomponentesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoacademico")
    private List<Programaofertado> programaofertadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoacademico")
    private List<Pruebaadicional> pruebaadicionalList;

    public Periodoacademico() {
    }

    public Periodoacademico(BigDecimal perid) {
        this.perid = perid;
    }

    public Periodoacademico(BigDecimal perid, short perpuntajeminpopayan, short perpuntajeminregionalizacion, short perestado) {
        this.perid = perid;
        this.perpuntajeminpopayan = perpuntajeminpopayan;
        this.perpuntajeminregionalizacion = perpuntajeminregionalizacion;
        this.perestado = perestado;
    }

    public BigDecimal getPerid() {
        return perid;
    }

    public void setPerid(BigDecimal perid) {
        this.perid = perid;
    }

    public short getPerpuntajeminpopayan() {
        return perpuntajeminpopayan;
    }

    public void setPerpuntajeminpopayan(short perpuntajeminpopayan) {
        this.perpuntajeminpopayan = perpuntajeminpopayan;
    }

    public short getPerpuntajeminregionalizacion() {
        return perpuntajeminregionalizacion;
    }

    public void setPerpuntajeminregionalizacion(short perpuntajeminregionalizacion) {
        this.perpuntajeminregionalizacion = perpuntajeminregionalizacion;
    }

    public short getPerestado() {
        return perestado;
    }

    public void setPerestado(short perestado) {
        this.perestado = perestado;
    }

    @XmlTransient
    public List<Programacasos> getProgramacasosList() {
        return programacasosList;
    }

    public void setProgramacasosList(List<Programacasos> programacasosList) {
        this.programacasosList = programacasosList;
    }

    @XmlTransient
    public List<Programacomponentes> getProgramacomponentesList() {
        return programacomponentesList;
    }

    public void setProgramacomponentesList(List<Programacomponentes> programacomponentesList) {
        this.programacomponentesList = programacomponentesList;
    }

    @XmlTransient
    public List<Programaofertado> getProgramaofertadoList() {
        return programaofertadoList;
    }

    public void setProgramaofertadoList(List<Programaofertado> programaofertadoList) {
        this.programaofertadoList = programaofertadoList;
    }

    @XmlTransient
    public List<Pruebaadicional> getPruebaadicionalList() {
        return pruebaadicionalList;
    }

    public void setPruebaadicionalList(List<Pruebaadicional> pruebaadicionalList) {
        this.pruebaadicionalList = pruebaadicionalList;
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
        if (!(object instanceof Periodoacademico)) {
            return false;
        }
        Periodoacademico other = (Periodoacademico) object;
        if ((this.perid == null && other.perid != null) || (this.perid != null && !this.perid.equals(other.perid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.admisiones.data.Periodoacademico[ perid=" + perid + " ]";
    }
    
}
