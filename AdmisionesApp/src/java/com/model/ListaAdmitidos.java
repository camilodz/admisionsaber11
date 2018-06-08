package com.model;
// Generated 2/06/2018 07:20:11 PM by Hibernate Tools 4.3.1

/**
 * Clase ListaAdmitidos
 * 
 * ListaAdmitidos generated by hbm2java 
 * 
 * Contiene la lista de aspirantes ponderables que han sido admitidos a un 
 * programa
 */
public class ListaAdmitidos implements java.io.Serializable {

    /*** Atributos ***/
    
    /**
     * ID del aspirante
     */
    private short apliid;
    
    /**
     * Información del aspirante y programa al que se inscribió
     */
    private Aplica aplica;
    
    /**
     * Estado admitido o no admitido del aspirante 
     */
    private String listestado;
    
    /**
     * Tipo de admisión
     */
    private String listtipoadmitido;

    
    /*** Constructores ***/ 
    
    public ListaAdmitidos() {
    }

    public ListaAdmitidos(Aplica aplica, String listestado, String listtipoadmitido) {
        this.aplica = aplica;
        this.listestado = listestado;
        this.listtipoadmitido = listtipoadmitido;
    }
    
    
    /*** Métodos getter y setter ***/

    public short getApliid() {
        return this.apliid;
    }

    public void setApliid(short apliid) {
        this.apliid = apliid;
    }

    public Aplica getAplica() {
        return this.aplica;
    }

    public void setAplica(Aplica aplica) {
        this.aplica = aplica;
    }

    public String getListestado() {
        return this.listestado;
    }

    public void setListestado(String listestado) {
        this.listestado = listestado;
    }

    public String getListtipoadmitido() {
        return this.listtipoadmitido;
    }

    public void setListtipoadmitido(String listtipoadmitido) {
        this.listtipoadmitido = listtipoadmitido;
    }

}
