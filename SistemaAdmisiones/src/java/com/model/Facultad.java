package com.model;
// Generated 11/05/2018 10:11:48 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Facultad generated by hbm2java
 */
public class Facultad  implements java.io.Serializable {


     private short facid;
     private String facnombre;
     private Set<Programa> programas = new HashSet<Programa>(0);

    public Facultad() {
    }

	
    public Facultad(short facid, String facnombre) {
        this.facid = facid;
        this.facnombre = facnombre;
    }
    public Facultad(short facid, String facnombre, Set<Programa> programas) {
       this.facid = facid;
       this.facnombre = facnombre;
       this.programas = programas;
    }
   
    public short getFacid() {
        return this.facid;
    }
    
    public void setFacid(short facid) {
        this.facid = facid;
    }
    public String getFacnombre() {
        return this.facnombre;
    }
    
    public void setFacnombre(String facnombre) {
        this.facnombre = facnombre;
    }
    public Set<Programa> getProgramas() {
        return this.programas;
    }
    
    public void setProgramas(Set<Programa> programas) {
        this.programas = programas;
    }




}


