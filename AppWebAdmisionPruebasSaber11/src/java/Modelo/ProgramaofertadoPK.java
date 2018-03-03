/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JORGE
 */
@Embeddable
public class ProgramaofertadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PROID")
    private short proid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "POFPERIODO")
    private String pofperiodo;

    public ProgramaofertadoPK() {
    }

    public ProgramaofertadoPK(short proid, String pofperiodo) {
        this.proid = proid;
        this.pofperiodo = pofperiodo;
    }

    public short getProid() {
        return proid;
    }

    public void setProid(short proid) {
        this.proid = proid;
    }

    public String getPofperiodo() {
        return pofperiodo;
    }

    public void setPofperiodo(String pofperiodo) {
        this.pofperiodo = pofperiodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proid;
        hash += (pofperiodo != null ? pofperiodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaofertadoPK)) {
            return false;
        }
        ProgramaofertadoPK other = (ProgramaofertadoPK) object;
        if (this.proid != other.proid) {
            return false;
        }
        if ((this.pofperiodo == null && other.pofperiodo != null) || (this.pofperiodo != null && !this.pofperiodo.equals(other.pofperiodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ProgramaofertadoPK[ proid=" + proid + ", pofperiodo=" + pofperiodo + " ]";
    }
    
}
