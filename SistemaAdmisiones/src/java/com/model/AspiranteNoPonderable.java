package com.model;
// Generated 11/05/2018 10:11:48 PM by Hibernate Tools 4.3.1



/**
 * AspiranteNoPonderable generated by hbm2java
 */
public class AspiranteNoPonderable  implements java.io.Serializable {


     private int aspid;
     private AspirantePonderable aspirantePonderable;
     private String aspmotivo;

    public AspiranteNoPonderable() {
    }

    public AspiranteNoPonderable(AspirantePonderable aspirantePonderable, String aspmotivo) {
       this.aspirantePonderable = aspirantePonderable;
       this.aspmotivo = aspmotivo;
    }
   
    public int getAspid() {
        return this.aspid;
    }
    
    public void setAspid(int aspid) {
        this.aspid = aspid;
    }
    public AspirantePonderable getAspirantePonderable() {
        return this.aspirantePonderable;
    }
    
    public void setAspirantePonderable(AspirantePonderable aspirantePonderable) {
        this.aspirantePonderable = aspirantePonderable;
    }
    public String getAspmotivo() {
        return this.aspmotivo;
    }
    
    public void setAspmotivo(String aspmotivo) {
        this.aspmotivo = aspmotivo;
    }




}


