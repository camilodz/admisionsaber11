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
@Table(name = "componentesicfes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componentesicfes.findAll", query = "SELECT c FROM Componentesicfes c")
    , @NamedQuery(name = "Componentesicfes.findByCompid", query = "SELECT c FROM Componentesicfes c WHERE c.compid = :compid")
    , @NamedQuery(name = "Componentesicfes.findByCompnombre", query = "SELECT c FROM Componentesicfes c WHERE c.compnombre = :compnombre")})
public class Componentesicfes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPID")
    private Short compid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COMPNOMBRE")
    private String compnombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentesicfes")
    private Collection<Programacomponentes> programacomponentesCollection;

    public Componentesicfes() {
    }

    public Componentesicfes(Short compid) {
        this.compid = compid;
    }

    public Componentesicfes(Short compid, String compnombre) {
        this.compid = compid;
        this.compnombre = compnombre;
    }

    public Short getCompid() {
        return compid;
    }

    public void setCompid(Short compid) {
        this.compid = compid;
    }

    public String getCompnombre() {
        return compnombre;
    }

    public void setCompnombre(String compnombre) {
        this.compnombre = compnombre;
    }

    @XmlTransient
    public Collection<Programacomponentes> getProgramacomponentesCollection() {
        return programacomponentesCollection;
    }

    public void setProgramacomponentesCollection(Collection<Programacomponentes> programacomponentesCollection) {
        this.programacomponentesCollection = programacomponentesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compid != null ? compid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componentesicfes)) {
            return false;
        }
        Componentesicfes other = (Componentesicfes) object;
        if ((this.compid == null && other.compid != null) || (this.compid != null && !this.compid.equals(other.compid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Componentesicfes[ compid=" + compid + " ]";
    }
    
}
