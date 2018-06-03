/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;


import com.dao.ResultadoIcfesDao;
import com.dao.ResultadoIcfesDaoImp;
import com.model.Resultadoicfes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JULIAN
 */
@Named(value = "resultadoIcfesBean")
@SessionScoped
public class resultadoIcfesBean implements Serializable {

    private List<Resultadoicfes>listaRes;
    private Resultadoicfes res  = new Resultadoicfes();
    
    public resultadoIcfesBean() {
    }

    public Resultadoicfes getRes() {
        return res;
    }

    public void setRes(Resultadoicfes res) {
        this.res = res;
    }

    public List<Resultadoicfes> getListaRes() {
        return listaRes;
    }
    
    public List<Resultadoicfes> getListaPer() {
        ResultadoIcfesDao resDao = new ResultadoIcfesDaoImp();
        this.listaRes = resDao.listarResultados();
        return listaRes;
    }
    
}
