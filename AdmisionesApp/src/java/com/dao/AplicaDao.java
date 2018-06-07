package com.dao;

import com.model.Aplica;
import com.model.AspiranteNoPonderable;
import com.model.AspirantePonderable;
import com.model.Resultadoicfes;
import java.util.List;

/**
 * Contiene la declaración de los métodos para gestionar la información de un
 * aspirante PONDERABLE y NO PONDERABLE que aplica a un un programa específico
 *
 * @author Proyecto II - Grupo Admisiones
 */
public interface AplicaDao {

    /**
     * Declaración del método para listar (desde la BD) los aspirantes de un
     * programa específico
     *
     * @param selectedPro id del programa del cual se van a listar los
     * aspirantes
     * @return lista de aspirantes del pro
     */
    public List<Aplica> mostrarAplica(String selectedPro);

    /**
     * Declaracion del método para listar (desde la BD) todos los aspirantes a
     * los programas ofertados
     *
     * @return lista de los aspirantes de todos los programas
     */
    public List<Aplica> mostrarAspirantes();

    /**
     * Declaracion del método para listar (desde la BD) los resultados ICFES de
     * todos los aspirantes
     *
     * @return lista de los resultados ICFES de todos los aspirantes
     */
    public List<Resultadoicfes> listarResultados();

    /**
     * Declaración del método para agregar (a la BD) un aspirante a la lista de
     * PONDERABLES (podría ser admitido)
     *
     * @param a aspirante ponderable (información del aspirante + los resultados
     * ICFES)
     */
    public void insertPonderables(AspirantePonderable a);

    /**
     * Declaración del método para agregar (a la BD) un aspirante a la lista de
     * NO PONDERABLES (bajo ninguna circunstancia será admitido)
     *
     * @param a aspirante ponderable (información del aspirante + el motivo por
     * el cual no se pudo ponderar)
     */
    public void insertNoPonderables(AspiranteNoPonderable a);

    /**
     * Declaración del método para eliminar (desde la BD) un aspirante de la
     * lista de NO PONDERABLES (Para poder reintegrarlo a ponderables)
     *
     * @param a aspirante no ponderable que se va a pasar a la lista de
     * ponderables
     */
    public void eliminarNoPonderable(AspiranteNoPonderable a);

    /**
     * Declaración del método para listar (desde la BD) los aspirantes
     * PONDERABLES y NO PONDERABLES de un programa específico
     *
     * @param proid programa del cual se van a listar los aspirantes
     * @return lista de los aspirantes de un programa específico
     */
    public List<Aplica> listarAspirantes(String proid);

    /**
     * Declaración del método para obtener (desde la BD) el número de aspirantes
     * de cada programa
     *
     * @return lista de la cantidad de aspirantes de cada programa
     */
    public List<Integer> numAspirantes();

}
