package com.model;
// Generated 11/05/2018 10:11:48 PM by Hibernate Tools 4.3.1



/**
 * ListaAdmitidos generated by hbm2java
 */
public class ListaAdmitidos  implements java.io.Serializable {


     private short aplid;
     private Aplica aplica;
     private String listestado;
     private String listtipoadmitido;
     private boolean listverificado;

    public ListaAdmitidos() {
    }

    public ListaAdmitidos(Aplica aplica, String listestado, String listtipoadmitido, boolean listverificado) {
       this.aplica = aplica;
       this.listestado = listestado;
       this.listtipoadmitido = listtipoadmitido;
       this.listverificado = listverificado;
    }
   
    public short getAplid() {
        return this.aplid;
    }
    
    public void setAplid(short aplid) {
        this.aplid = aplid;
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
    public boolean isListverificado() {
        return this.listverificado;
    }
    
    public void setListverificado(boolean listverificado) {
        this.listverificado = listverificado;
    }




}


