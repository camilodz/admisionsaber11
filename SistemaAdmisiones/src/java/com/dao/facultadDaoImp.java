/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Facultad;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Karen
 */
public class facultadDaoImp implements facultadDao{

    @Override
    public List<Facultad> listarFac() {
        List<Facultad> listaFac = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Facultad";
        try {
            listaFac = session.createQuery(hql).list();
            transaction.commit();
            session.close();            
        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } 
        return listaFac;
    }
    
}
