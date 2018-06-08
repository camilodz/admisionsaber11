package com.dao;

import com.model.Programa;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase ProgramaDaoImp
 * 
 * Contiene la implementación de los métodos para gestionar los programas de
 * cada facultad
 * 
 * Implementa los métodos de la interface ProgramaDao
 *
 * @author Proyecto II - Grupo Admisiones
 */
public class ProgramaDaoImp implements ProgramaDao {

    /*** Implementación de los métodos ***/
    
    /**
     * Implementación del método para obtener (desde la BD) la lista de los
     * programas de todas las facultades
     *
     * @return lista de los programas de todas las facultades
     */
    @Override
    public List<Programa> listarProgramas() {

        List<Programa> listaFac = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Programa";
        try {
            listaFac = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaFac;

    }

}
