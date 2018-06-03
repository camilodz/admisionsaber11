/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Aspirante;
import com.model.Resultadoicfes;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author JULIAN
 */
public class cruceDatosDaoImp implements cruceDatosDao{

    @Override
    public List<Aspirante> mostrarAspirantes() {
        List<Aspirante> listAspirantes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Aspirante";
        
        try {
            listAspirantes = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listAspirantes;
    }
    
    @Override
    public List<Resultadoicfes> listarResultados() {
         List<Resultadoicfes> listaR = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Resultadoicfes";
        try {
            listaR = session.createQuery(hql).list();
            transaction.commit();
            session.close();            
        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } 
        return listaR;
    }
    
}
