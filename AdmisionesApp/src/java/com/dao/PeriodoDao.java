package com.dao;

import com.model.Periodo;
import java.util.List;

/**
 * Interface PeriodoDAo
 * 
 * Contiene la declaración de los métodos para gestionar los periodos académicos
 *
 * @author Proyecto II - Grupo Admisiones
 */
public interface PeriodoDao {

    /*** Declaración de los métodos ***/
    
    /**
     * Declaración del método para obtener (desde la BD) la lista de todos los
     * periodos académicos
     *
     * @return lista de los periodos académicos
     */
    public List<Periodo> mostrarPeriodos();

    /**
     * Declaración del método para agregar (a la BD) un nuevo periodo académico
     *
     * @param per nuevo periodo académico que se va a configurar
     */
    public void nuevoPeriodo(Periodo per);
}
