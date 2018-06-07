package com.dao;

import com.model.Programaofertado;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contiene la implementación de los métodos declarados en ProgramaofertadoDao
 * para gestionar los programas ofertados en un periodo académico Obtener y
 * editar los programas ofertados desde la BD
 *
 * @author Proyecto II - Grupo Admisiones
 */
public class programaofertadoDaoImp implements programaofertadoDao {

    /**
     * Implementación del método para obtener (desde la BD) la lista de los
     * programas ofertados de todas las facultades
     *
     * @return lista de los programas ofertados de todas las facultades
     */
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaPO;
    }

    /**
     * Implementación del método para modificar los cupos y ponderados ICFES
     * (desde la BD) de un programa ofertado específico
     *
     * @param po programa ofertado que se va a modificar
     */
    @Override
    public void modificarPO(Programaofertado po) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(po);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Implementación del método para listar (desde la BD) los programas
     * ofertados de una facultad específica
     *
     * @param facid id de la facultad de la cual queremos obtener los programas
     * ofertados
     * @return lista de los programas ofertados de la facultad específica
     */
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaPO;
    }

}
