package com.model;
// Generated 11/05/2018 10:11:48 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Programa generated by hbm2java
 */
public class Programa  implements java.io.Serializable {


     private short proid;
     private Facultad facultad;
     private String pronombre;
     private String prosede;
     private Set<Programaofertado> programaofertados = new HashSet<Programaofertado>(0);

    public Programa() {
    }

	
    public Programa(short proid, String pronombre, String prosede) {
        this.proid = proid;
        this.pronombre = pronombre;
        this.prosede = prosede;
    }
    public Programa(short proid, Facultad facultad, String pronombre, String prosede, Set<Programaofertado> programaofertados) {
       this.proid = proid;
       this.facultad = facultad;
       this.pronombre = pronombre;
       this.prosede = prosede;
       this.programaofertados = programaofertados;
    }
   
    public short getProid() {
        return this.proid;
    }
    
    public void setProid(short proid) {
        this.proid = proid;
    }
    public Facultad getFacultad() {
        return this.facultad;
    }
    
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    public String getPronombre() {
        return this.pronombre;
    }
    
    public void setPronombre(String pronombre) {
        this.pronombre = pronombre;
    }
    public String getProsede() {
        return this.prosede;
    }
    
    public void setProsede(String prosede) {
        this.prosede = prosede;
    }
    public Set<Programaofertado> getProgramaofertados() {
        return this.programaofertados;
    }
    
    public void setProgramaofertados(Set<Programaofertado> programaofertados) {
        this.programaofertados = programaofertados;
    }




}


