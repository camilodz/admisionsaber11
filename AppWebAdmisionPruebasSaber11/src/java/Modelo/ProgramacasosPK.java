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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JORGE
 */
@Embeddable
public class ProgramacasosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ESPID")
    private short espid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROID")
    private short proid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERID")
    private BigDecimal perid;

    public ProgramacasosPK() {
    }

    public ProgramacasosPK(short espid, short proid, BigDecimal perid) {
        this.espid = espid;
        this.proid = proid;
        this.perid = perid;
    }

    public short getEspid() {
        return espid;
    }

    public void setEspid(short espid) {
        this.espid = espid;
    }

    public short getProid() {
        return proid;
    }

    public void setProid(short proid) {
        this.proid = proid;
    }

    public BigDecimal getPerid() {
        return perid;
    }

    public void setPerid(BigDecimal perid) {
        this.perid = perid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) espid;
        hash += (int) proid;
        hash += (perid != null ? perid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacasosPK)) {
            return false;
        }
        ProgramacasosPK other = (ProgramacasosPK) object;
        if (this.espid != other.espid) {
            return false;
        }
        if (this.proid != other.proid) {
            return false;
        }
        if ((this.perid == null && other.perid != null) || (this.perid != null && !this.perid.equals(other.perid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ProgramacasosPK[ espid=" + espid + ", proid=" + proid + ", perid=" + perid + " ]";
    }
    
}
