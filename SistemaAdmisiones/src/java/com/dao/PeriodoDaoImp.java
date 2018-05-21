/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Facultad;
import com.model.Periodo;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Karen
 */
public class PeriodoDaoImp implements PeriodoDao {

    @Override
    public List<Periodo> mostrarPeriodos() {
        List<Periodo> listaPer = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Periodo";
        try {
            listaPer = session.createQuery(hql).list();
            transaction.commit();
            session.close();            
        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } 
        return listaPer;
    }    

    @Override
    public void nuevoPeriodo(Periodo per) {
     
        Session session = null;
        try{
            
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(per);
        session.getTransaction().commit();
            System.out.println(".....");
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session != null)
                session.close();
        } 
    }
    
}
