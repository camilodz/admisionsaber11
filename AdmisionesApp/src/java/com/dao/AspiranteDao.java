package com.dao;

import com.model.Aspirante;
import java.util.List;

/**
 * Contiene la declaración de los métodos para gestionar los aspirantes a todos
 * programas
 *
 * @author Proyecto II - Grupo Admisiones
 */
public interface AspiranteDao {

    /**
     * Declaración del método para listar (desde la BD) los aspirantes a todos
     * los programas
     *
     * @return lista de aspirantes de todos los programas
     */
    public List<Aspirante> mostrarAspirantes();

}
