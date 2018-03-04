/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "programaofertado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programaofertado.findAll", query = "SELECT p FROM Programaofertado p")
    , @NamedQuery(name = "Programaofertado.findByProid", query = "SELECT p FROM Programaofertado p WHERE p.programaofertadoPK.proid = :proid")
    , @NamedQuery(name = "Programaofertado.findByPofperiodo", query = "SELECT p FROM Programaofertado p WHERE p.programaofertadoPK.pofperiodo = :pofperiodo")
    , @NamedQuery(name = "Programaofertado.findByPofcuposreg", query = "SELECT p FROM Programaofertado p WHERE p.pofcuposreg = :pofcuposreg")
    , @NamedQuery(name = "Programaofertado.findByPofcuposzonamarginal", query = "SELECT p FROM Programaofertado p WHERE p.pofcuposzonamarginal = :pofcuposzonamarginal")
    , @NamedQuery(name = "Programaofertado.findByPofcuposdificilacceso", query = "SELECT p FROM Programaofertado p WHERE p.pofcuposdificilacceso = :pofcuposdificilacceso")
    , @NamedQuery(name = "Programaofertado.findByPofcuposnormalista", query = "SELECT p FROM Programaofertado p WHERE p.pofcuposnormalista = :pofcuposnormalista")
    , @NamedQuery(name = "Programaofertado.findByPofcuposindigena", query = "SELECT p FROM Programaofertado p WHERE p.pofcuposindigena = :pofcuposindigena")
    , @NamedQuery(name = "Programaofertado.findByPofcuposafrodescendiente", query = "SELECT p FROM Programaofertado p WHERE p.pofcuposafrodescendiente = :pofcuposafrodescendiente")
    , @NamedQuery(name = "Programaofertado.findByPofcuposcostapacifica", query = "SELECT p FROM Programaofertado p WHERE p.pofcuposcostapacifica = :pofcuposcostapacifica")
    , @NamedQuery(name = "Programaofertado.findByPofpondlecturacritica", query = "SELECT p FROM Programaofertado p WHERE p.pofpondlecturacritica = :pofpondlecturacritica")
    , @NamedQuery(name = "Programaofertado.findByPofpondmatematicas", query = "SELECT p FROM Programaofertado p WHERE p.pofpondmatematicas = :pofpondmatematicas")
    , @NamedQuery(name = "Programaofertado.findByPofpondcsociales", query = "SELECT p FROM Programaofertado p WHERE p.pofpondcsociales = :pofpondcsociales")
    , @NamedQuery(name = "Programaofertado.findByPofpondcnaturales", query = "SELECT p FROM Programaofertado p WHERE p.pofpondcnaturales = :pofpondcnaturales")
    , @NamedQuery(name = "Programaofertado.findByPofpondingles", query = "SELECT p FROM Programaofertado p WHERE p.pofpondingles = :pofpondingles")
    , @NamedQuery(name = "Programaofertado.findByPofpondpruebaad", query = "SELECT p FROM Programaofertado p WHERE p.pofpondpruebaad = :pofpondpruebaad")
    , @NamedQuery(name = "Programaofertado.findByPofesofertado", query = "SELECT p FROM Programaofertado p WHERE p.pofesofertado = :pofesofertado")})
public class Programaofertado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramaofertadoPK programaofertadoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFCUPOSREG")
    private short pofcuposreg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFCUPOSZONAMARGINAL")
    private short pofcuposzonamarginal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFCUPOSDIFICILACCESO")
    private short pofcuposdificilacceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFCUPOSNORMALISTA")
    private short pofcuposnormalista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFCUPOSINDIGENA")
    private short pofcuposindigena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFCUPOSAFRODESCENDIENTE")
    private short pofcuposafrodescendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFCUPOSCOSTAPACIFICA")
    private short pofcuposcostapacifica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFPONDLECTURACRITICA")
    private short pofpondlecturacritica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFPONDMATEMATICAS")
    private short pofpondmatematicas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFPONDCSOCIALES")
    private short pofpondcsociales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFPONDCNATURALES")
    private short pofpondcnaturales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFPONDINGLES")
    private short pofpondingles;
    @Column(name = "POFPONDPRUEBAAD")
    private Short pofpondpruebaad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POFESOFERTADO")
    private short pofesofertado;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Programa programa;

    public Programaofertado() {
    }

    public Programaofertado(ProgramaofertadoPK programaofertadoPK) {
        this.programaofertadoPK = programaofertadoPK;
    }

    public Programaofertado(ProgramaofertadoPK programaofertadoPK, short pofcuposreg, short pofcuposzonamarginal, short pofcuposdificilacceso, short pofcuposnormalista, short pofcuposindigena, short pofcuposafrodescendiente, short pofcuposcostapacifica, short pofpondlecturacritica, short pofpondmatematicas, short pofpondcsociales, short pofpondcnaturales, short pofpondingles, short pofesofertado) {
        this.programaofertadoPK = programaofertadoPK;
        this.pofcuposreg = pofcuposreg;
        this.pofcuposzonamarginal = pofcuposzonamarginal;
        this.pofcuposdificilacceso = pofcuposdificilacceso;
        this.pofcuposnormalista = pofcuposnormalista;
        this.pofcuposindigena = pofcuposindigena;
        this.pofcuposafrodescendiente = pofcuposafrodescendiente;
        this.pofcuposcostapacifica = pofcuposcostapacifica;
        this.pofpondlecturacritica = pofpondlecturacritica;
        this.pofpondmatematicas = pofpondmatematicas;
        this.pofpondcsociales = pofpondcsociales;
        this.pofpondcnaturales = pofpondcnaturales;
        this.pofpondingles = pofpondingles;
        this.pofesofertado = pofesofertado;
    }

    //Adicional
    public String getEsOfertado() {
        if(this.pofesofertado == 1)
            return "Si";
        else
            return "No";
    }
    
    public Programaofertado(short proid, String pofperiodo) {
        this.programaofertadoPK = new ProgramaofertadoPK(proid, pofperiodo);
    }

    public ProgramaofertadoPK getProgramaofertadoPK() {
        return programaofertadoPK;
    }

    public void setProgramaofertadoPK(ProgramaofertadoPK programaofertadoPK) {
        this.programaofertadoPK = programaofertadoPK;
    }

    public short getPofcuposreg() {
        return pofcuposreg;
    }

    public void setPofcuposreg(short pofcuposreg) {
        this.pofcuposreg = pofcuposreg;
    }

    public short getPofcuposzonamarginal() {
        return pofcuposzonamarginal;
    }

    public void setPofcuposzonamarginal(short pofcuposzonamarginal) {
        this.pofcuposzonamarginal = pofcuposzonamarginal;
    }

    public short getPofcuposdificilacceso() {
        return pofcuposdificilacceso;
    }

    public void setPofcuposdificilacceso(short pofcuposdificilacceso) {
        this.pofcuposdificilacceso = pofcuposdificilacceso;
    }

    public short getPofcuposnormalista() {
        return pofcuposnormalista;
    }

    public void setPofcuposnormalista(short pofcuposnormalista) {
        this.pofcuposnormalista = pofcuposnormalista;
    }

    public short getPofcuposindigena() {
        return pofcuposindigena;
    }

    public void setPofcuposindigena(short pofcuposindigena) {
        this.pofcuposindigena = pofcuposindigena;
    }

    public short getPofcuposafrodescendiente() {
        return pofcuposafrodescendiente;
    }

    public void setPofcuposafrodescendiente(short pofcuposafrodescendiente) {
        this.pofcuposafrodescendiente = pofcuposafrodescendiente;
    }

    public short getPofcuposcostapacifica() {
        return pofcuposcostapacifica;
    }

    public void setPofcuposcostapacifica(short pofcuposcostapacifica) {
        this.pofcuposcostapacifica = pofcuposcostapacifica;
    }

    public short getPofpondlecturacritica() {
        return pofpondlecturacritica;
    }

    public void setPofpondlecturacritica(short pofpondlecturacritica) {
        this.pofpondlecturacritica = pofpondlecturacritica;
    }

    public short getPofpondmatematicas() {
        return pofpondmatematicas;
    }

    public void setPofpondmatematicas(short pofpondmatematicas) {
        this.pofpondmatematicas = pofpondmatematicas;
    }

    public short getPofpondcsociales() {
        return pofpondcsociales;
    }

    public void setPofpondcsociales(short pofpondcsociales) {
        this.pofpondcsociales = pofpondcsociales;
    }

    public short getPofpondcnaturales() {
        return pofpondcnaturales;
    }

    public void setPofpondcnaturales(short pofpondcnaturales) {
        this.pofpondcnaturales = pofpondcnaturales;
    }

    public short getPofpondingles() {
        return pofpondingles;
    }

    public void setPofpondingles(short pofpondingles) {
        this.pofpondingles = pofpondingles;
    }

    public Short getPofpondpruebaad() {
        return pofpondpruebaad;
    }

    public void setPofpondpruebaad(Short pofpondpruebaad) {
        this.pofpondpruebaad = pofpondpruebaad;
    }

    public short getPofesofertado() {
        return pofesofertado;
    }

    public void setPofesofertado(short pofesofertado) {
        this.pofesofertado = pofesofertado;
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
        hash += (programaofertadoPK != null ? programaofertadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaofertado)) {
            return false;
        }
        Programaofertado other = (Programaofertado) object;
        if ((this.programaofertadoPK == null && other.programaofertadoPK != null) || (this.programaofertadoPK != null && !this.programaofertadoPK.equals(other.programaofertadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Programaofertado[ programaofertadoPK=" + programaofertadoPK + " ]";
    }
    
}