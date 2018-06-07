package com.dao;

import com.model.Aspirante;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contiene la implementación de los métodos para gestionar los aspirantes a
 * todos programas
 *
 * @author Proyecto II - Grupo Admisiones
 */
public class AspiranteDaoImp implements AspiranteDao {

    /**
     * Implementación del método para listar (desde la BD) los aspirantes a
     * todos los programas
     *
     * @return lista de aspirantes de todos los programas
     */
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

}
