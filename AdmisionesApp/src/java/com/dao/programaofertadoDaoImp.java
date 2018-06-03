/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Programaofertado;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Karen
 */
public class programaofertadoDaoImp implements programaofertadoDao{

    @Override
    public List<Programaofertado> listarPO() {
        List<Programaofertado> listaPO = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Programaofertado as po inner join fetch po.programa";
        try {
            listaPO = session.createQuery(hql).list();
            transaction.commit();
            session.close();            
        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } 
        return listaPO;
    }

    @Override
    public void modificarPO(Programaofertado po) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(po);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
            
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public List<Programaofertado> listarPOFacultad(String facid) {
        List<Programaofertado> listaPO = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("************ VALOR FAC ID " + facid);
        String hql = "FROM Programaofertado as po inner join fetch po.programa WHERE facid = " + facid;
        try {
            listaPO = session.createQuery(hql).list();
            transaction.commit();
            session.close();            
        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } 
        return listaPO;
    }
    
}
