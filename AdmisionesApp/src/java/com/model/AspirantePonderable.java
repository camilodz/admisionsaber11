package com.model;
// Generated 2/06/2018 07:20:11 PM by Hibernate Tools 4.3.1

/**
 * Clase AspirantePonderable
 * 
 * AspirantePonderable generated by hbm2java 
 * 
 * Contiene la información de un aspirante que se puede ponderar
 */
public class AspirantePonderable implements java.io.Serializable {

    /*** Atributos ***/ 
    
    /**
     * ID del aspirante 
     */
    private int aspid;
    
    /**
     * Aspirante que se puede ponderar
     */
    private Aspirante aspirante;
    
    /**
     * Tipo de Identificación del aspirante 
     */
    private String asptipo;
    
    /**
     * Ponderado total del aspirante 
     */
    private short asppondtotal;
    
    /**
     * Porcentaje lectura crítica
     */
    private short asplecturacritica;
    
    /**
     * Porcentaje matemáticas
     */
    private short aspmatematicas;
    
    /**
     * Porcentaje ciencias sociales
     */
    private short aspcsociales;
    
    /**
     * Porcentaje ciencias naturales
     */
    private short aspcnaturales;
    
    /**
     * Porcentaje ingléss
     */
    private short aspingles;
    
    /**
     * Porcentaje prueba adicional
     */
    private Short asppruebaad;
    
    /**
     * Referencia al Aspirante no ponderable 
     */
    private AspiranteNoPonderable aspiranteNoPonderable;

    
    /*** Constructores ***/ 
    
    public AspirantePonderable() {
    }

    public AspirantePonderable(Aspirante aspirante, String asptipo, short asppondtotal, short asplecturacritica, short aspmatematicas, short aspcsociales, short aspcnaturales, short aspingles) {
        this.aspirante = aspirante;
        this.asptipo = asptipo;
        this.asppondtotal = asppondtotal;
        this.asplecturacritica = asplecturacritica;
        this.aspmatematicas = aspmatematicas;
        this.aspcsociales = aspcsociales;
        this.aspcnaturales = aspcnaturales;
        this.aspingles = aspingles;
    }

    public AspirantePonderable(Aspirante aspirante, String asptipo, short asppondtotal, short asplecturacritica, short aspmatematicas, short aspcsociales, short aspcnaturales, short aspingles, Short asppruebaad, AspiranteNoPonderable aspiranteNoPonderable) {
        this.aspirante = aspirante;
        this.asptipo = asptipo;
        this.asppondtotal = asppondtotal;
        this.asplecturacritica = asplecturacritica;
        this.aspmatematicas = aspmatematicas;
        this.aspcsociales = aspcsociales;
        this.aspcnaturales = aspcnaturales;
        this.aspingles = aspingles;
        this.asppruebaad = asppruebaad;
        this.aspiranteNoPonderable = aspiranteNoPonderable;
    }

    
    /*** Métodos getter y setter ***/ 
    
    public int getAspid() {
        return this.aspid;
    }

    public void setAspid(int aspid) {
        this.aspid = aspid;
    }

    public Aspirante getAspirante() {
        return this.aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public String getAsptipo() {
        return this.asptipo;
    }

    public void setAsptipo(String asptipo) {
        this.asptipo = asptipo;
    }

    public short getAsppondtotal() {
        return this.asppondtotal;
    }

    public void setAsppondtotal(short asppondtotal) {
        this.asppondtotal = asppondtotal;
    }

    public short getAsplecturacritica() {
        return this.asplecturacritica;
    }

    public void setAsplecturacritica(short asplecturacritica) {
        this.asplecturacritica = asplecturacritica;
    }

    public short getAspmatematicas() {
        return this.aspmatematicas;
    }

    public void setAspmatematicas(short aspmatematicas) {
        this.aspmatematicas = aspmatematicas;
    }

    public short getAspcsociales() {
        return this.aspcsociales;
    }

    public void setAspcsociales(short aspcsociales) {
        this.aspcsociales = aspcsociales;
    }

    public short getAspcnaturales() {
        return this.aspcnaturales;
    }

    public void setAspcnaturales(short aspcnaturales) {
        this.aspcnaturales = aspcnaturales;
    }

    public short getAspingles() {
        return this.aspingles;
    }

    public void setAspingles(short aspingles) {
        this.aspingles = aspingles;
    }

    public Short getAsppruebaad() {
        return this.asppruebaad;
    }

    public void setAsppruebaad(Short asppruebaad) {
        this.asppruebaad = asppruebaad;
    }

    public AspiranteNoPonderable getAspiranteNoPonderable() {
        return this.aspiranteNoPonderable;
    }

    public void setAspiranteNoPonderable(AspiranteNoPonderable aspiranteNoPonderable) {
        this.aspiranteNoPonderable = aspiranteNoPonderable;
    }

}
