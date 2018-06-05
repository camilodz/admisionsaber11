package com.dao;

import com.model.Aplica;
import com.model.Aspirante;
import com.model.AspiranteNoPonderable;
import com.model.AspirantePonderable;
import com.model.Resultadoicfes;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class AplicaDaoImp implements AplicaDao{

    @Override
    public List<Aplica> mostrarAplica(String selectedPro) {
        List<Aplica> listAplica = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirante Where proid ="+ selectedPro;
        try {
            listAplica = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listAplica;
    }
    
     @Override
    public List<Aplica> mostrarAspirantes() {
        List<Aplica> listAspirantes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Aplica as a inner join fetch a.aspirante";
        
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

    @Override
    public void insertPonderables(AspirantePonderable a) {
        
         Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(a);     
            
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
    public void insertNoPonderables(AspiranteNoPonderable a) {
        
         Session session = null;
        try{
            
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(a);
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

    
    // Listar aspirantes ponderables y no ponderables por programa
    @Override
    public List<Aplica> listarAspirantes(String proid) {
        System.out.println("********************************* " + proid);
        List<Aplica> listaA = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirante where proid = " +proid;
        try {
            listaA = session.createQuery(hql).list();
            transaction.commit();
            session.close();            
        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        } 
        return listaA;    
        
        
        //FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirante
    }
    
     @Override
    public List<Integer> numAspirantes() {
        
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Integer> listaAspirantes = new ArrayList<>();
        
        try {
            
            listaAspirantes = session.createQuery("select count(*) FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirante group by(proid)").list();
            
            session.close();    
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        }   
        return listaAspirantes;
    }

    @Override
    public void eliminarNoPonderable(AspiranteNoPonderable a) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(a);
            
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

    
    
    
}
