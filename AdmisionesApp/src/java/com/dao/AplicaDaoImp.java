package com.dao;

import com.model.Aplica;
import com.model.AspiranteNoPonderable;
import com.model.AspirantePonderable;
import com.model.Resultadoicfes;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Contiene la implementación de los métodos para gestionar la información de un
 * aspirante PONDERABLE y NO PONDERABLE que aplica a un un programa específico
 *
 * @author Proyecto II - Grupo Admisiones
 */
public class AplicaDaoImp implements AplicaDao {

    /**
     * Implementación del método para listar (desde la BD) los aspirantes de un
     * programa específico
     *
     * @param selectedPro id del programa del cual se van a listar los
     * aspirantes
     * @return lista de aspirantes del pro
     */
    @Override
    public List<Aplica> mostrarAplica(String selectedPro) {
        List<Aplica> listAplica = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirante Where proid =" + selectedPro;
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

    /**
     * Implementación del método para listar (desde la BD) todos los aspirantes
     * a los programas ofertados
     *
     * @return lista de los aspirantes de todos los programas
     */
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

    /**
     * Implementación del método para listar (desde la BD) los resultados ICFES
     * de todos los aspirantes
     *
     * @return lista de los resultados ICFES de todos los aspirantes
     */
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaR;
    }

    /**
     * Implementación del método para agregar (a la BD) un aspirante a la lista
     * de PONDERABLES (podría ser admitido)
     *
     * @param a aspirante ponderable (información del aspirante + los resultados
     * ICFES)
     */
    @Override
    public void insertPonderables(AspirantePonderable a) {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(a);

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
     * Implementación del método para agregar (a la BD) un aspirante a la lista
     * de NO PONDERABLES (bajo ninguna circunstancia será admitido)
     *
     * @param a aspirante ponderable (información del aspirante + el motivo por
     * el cual no se pudo ponderar)
     */
    @Override
    public void insertNoPonderables(AspiranteNoPonderable a) {

        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(a);
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
     * Implementación del método para eliminar (desde la BD) un aspirante de la
     * lista de NO PONDERABLES (Para poder reintegrarlo a ponderables)
     *
     * @param a aspirante no ponderable que se va a pasar a la lista de
     * ponderables
     */
    @Override
    public void eliminarNoPonderable(AspiranteNoPonderable a) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(a);

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
     * Implementación del método para listar (desde la BD) los aspirantes
     * PONDERABLES y NO PONDERABLES de un programa específico
     *
     * @param proid programa del cual se van a listar los aspirantes
     * @return lista de los aspirantes de un programa específico
     */
    @Override
    public List<Aplica> listarAspirantes(String proid) {
        System.out.println("********************************* " + proid);
        List<Aplica> listaA = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirante where proid = " + proid;
        try {
            listaA = session.createQuery(hql).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaA;
    }

    /**
     * Implementación del método para obtener (desde la BD) el número de
     * aspirantes de cada programa
     *
     * @return lista de la cantidad de aspirantes de cada programa
     */
    @Override
    public List<Integer> numAspirantes() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Integer> listaAspirantes = new ArrayList<>();

        try {

            listaAspirantes = session.createQuery("select count(*) FROM Aplica as a inner join fetch a.programaofertado inner join fetch a.aspirante group by(proid)").list();

            session.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return listaAspirantes;
    }

}
