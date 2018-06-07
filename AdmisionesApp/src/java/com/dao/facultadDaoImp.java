package com.dao;

import com.model.Facultad;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contiene la implementación de los métodos declarados en facultadDao para
 * gestionar las facultades
 *
 * @author Proyecto II - Grupo Admisiones
 */
public class facultadDaoImp implements facultadDao {

    /**
     * Implementación del método para obtener (desde la BD) la lista de las
     * facultades
     *
     * @return lista de las facultades
     */
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaFac;
    }

    /**
     * Implementación del método para obtener (desde la BD) el id de una
     * facultad dado su nombre
     *
     * @param nombreFac nombre de la facultad de la cual se quiere obtener su id
     * @return id de la facultad específica
     */
    @Override
    public String getIdFacultad(String nombreFac) {
        String id = "";
        List<Facultad> listaFac = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Facultad";
        try {
            listaFac = session.createQuery(hql).list();
            for (Facultad listaFac1 : listaFac) {
                if (listaFac1.getFacnombre().equalsIgnoreCase(nombreFac)) {
                    id = Short.toString(listaFac1.getFacid());
                }
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return id;
    }

}
