package com.dao;

import com.model.Periodo;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contiene la implementación de los métodos para gestionar los periodos
 * académicos
 *
 * @author Proyecto II - Grupo Admisiones
 */
public class PeriodoDaoImp implements PeriodoDao {

    /**
     * Implementación del método para obtener (desde la BD) la lista de todos
     * los periodos académicos
     *
     * @return lista de los periodos académicos
     */
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaPer;
    }

    /**
     * Declaración del método para agregar (a la BD) un nuevo periodo académico
     *
     * @param per nuevo periodo académico que se va a configurar
     */
    @Override
    public void nuevoPeriodo(Periodo per) {

        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(per);
            session.getTransaction().commit();
            System.out.println(".....");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
