package com.model;
// Generated 2/06/2018 07:20:11 PM by Hibernate Tools 4.3.1



/**
 * Resultadoicfes generated by hbm2java
 * Contiene la información de los archivos del ICFES (información del ICFES de un aspirante)
 */
public class Resultadoicfes  implements java.io.Serializable {


     private String ressnp;
     private String resperiodo;
     private String resnombreest;
     private byte restipodocest;
     private int residest;
     private short resglobal;
     private short reslecturacritica;
     private short resmatematicas;
     private short rescsociales;
     private short rescnaturales;
     private short resingles;

    public Resultadoicfes() {
    }

    public Resultadoicfes(String ressnp, String resperiodo, String resnombreest, byte restipodocest, int residest, short resglobal, short reslecturacritica, short resmatematicas, short rescsociales, short rescnaturales, short resingles) {
       this.ressnp = ressnp;
       this.resperiodo = resperiodo;
       this.resnombreest = resnombreest;
       this.restipodocest = restipodocest;
       this.residest = residest;
       this.resglobal = resglobal;
       this.reslecturacritica = reslecturacritica;
       this.resmatematicas = resmatematicas;
       this.rescsociales = rescsociales;
       this.rescnaturales = rescnaturales;
       this.resingles = resingles;
    }
   
    public String getRessnp() {
        return this.ressnp;
    }
    
    public void setRessnp(String ressnp) {
        this.ressnp = ressnp;
    }
    public String getResperiodo() {
        return this.resperiodo;
    }
    
    public void setResperiodo(String resperiodo) {
        this.resperiodo = resperiodo;
    }
    public String getResnombreest() {
        return this.resnombreest;
    }
    
    public void setResnombreest(String resnombreest) {
        this.resnombreest = resnombreest;
    }
    public byte getRestipodocest() {
        return this.restipodocest;
    }
    
    public void setRestipodocest(byte restipodocest) {
        this.restipodocest = restipodocest;
    }
    public int getResidest() {
        return this.residest;
    }
    
    public void setResidest(int residest) {
        this.residest = residest;
    }
    public short getResglobal() {
        return this.resglobal;
    }
    
    public void setResglobal(short resglobal) {
        this.resglobal = resglobal;
    }
    public short getReslecturacritica() {
        return this.reslecturacritica;
    }
    
    public void setReslecturacritica(short reslecturacritica) {
        this.reslecturacritica = reslecturacritica;
    }
    public short getResmatematicas() {
        return this.resmatematicas;
    }
    
    public void setResmatematicas(short resmatematicas) {
        this.resmatematicas = resmatematicas;
    }
    public short getRescsociales() {
        return this.rescsociales;
    }
    
    public void setRescsociales(short rescsociales) {
        this.rescsociales = rescsociales;
    }
    public short getRescnaturales() {
        return this.rescnaturales;
    }
    
    public void setRescnaturales(short rescnaturales) {
        this.rescnaturales = rescnaturales;
    }
    public short getResingles() {
        return this.resingles;
    }
    
    public void setResingles(short resingles) {
        this.resingles = resingles;
    }




}


