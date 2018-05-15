package com.dao;

import com.model.Aplica;
import com.util.HibernateUtil;
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
        String hql = "FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirantePonderable inner join fetch a.listaAdmitidos Where proid ="+ selectedPro + "Order by asppondtotal desc";
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
    
}
