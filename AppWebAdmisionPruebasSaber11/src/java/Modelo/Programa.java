/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "Programa.findByPronombre", query = "SELECT p FROM Programa p WHERE p.pronombre = :pronombre")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROID")
    private Short proid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PRONOMBRE")
    private String pronombre;
    @JoinColumn(name = "FACID", referencedColumnName = "FACID")
    @ManyToOne(optional = false)
    private Facultad facid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
    private Collection<Programaofertado> programaofertadoCollection;

    public Programa() {
    }

    public Programa(Short proid) {
        this.proid = proid;
    }

    public Programa(Short proid, String pronombre) {
        this.proid = proid;
        this.pronombre = pronombre;
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
        return "Modelo.Programa[ proid=" + proid + " ]";
    }
    
}