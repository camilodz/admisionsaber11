package com.dao;

import com.model.Programa;
import java.util.List;

/**
 * Contiene la declaración de los métodos para gestionar los programas de cada
 * facultad
 *
 * @author Proyecto II - Grupo Admisiones
 */
public interface ProgramaDao {

    /**
     * Declaración del método para obtener (desde la BD) la lista de los
     * programas de todas las facultades
     *
     * @return lista de los programas de todas las facultades
     */
    public List<Programa> listarProgramas();

}
