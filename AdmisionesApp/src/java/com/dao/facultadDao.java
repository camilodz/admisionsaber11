package com.dao;

import com.model.Facultad;
import java.util.List;

/**
 * Interface facultadDao
 * 
 * Contiene la declaración de los métodos para gestionar las facultades
 *
 * @author Proyecto II - Grupo Admisiones
 */
public interface facultadDao {

    /*** Declaración de los métodos ***/
    
    /**
     * Declaración del método para obtener (desde la BD) la lista de las
     * facultades
     *
     * @return lista de las facultades
     */
    public List<Facultad> listarFac();

    /**
     * Declaración del método para obtener (desde la BD) el id de una facultad
     * dado su nombre
     *
     * @param nombreFac nombre de la facultad de la cual se quiere obtener su id
     * @return id de la facultad específicada
     */
    public String getIdFacultad(String nombreFac);
}
