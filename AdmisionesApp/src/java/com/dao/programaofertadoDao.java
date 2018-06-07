package com.dao;

import com.model.Programaofertado;
import java.util.List;

/**
 * Contiene la declaración de los métodos para gestionar los programas ofertados
 * en un periodo académico Obtener y editar los programas ofertados desde la BD
 *
 * @author Proyecto II - Grupo Admisiones
 */
public interface programaofertadoDao {

    /**
     * Declaración del método para obtener (desde la BD) la lista de los
     * programas ofertados de todas las facultades
     *
     * @return lista de los programas ofertados de todas las facultades
     */
    public List<Programaofertado> listarPO();

    /**
     * Declaración del método para modificar los cupos y ponderados ICFES de un
     * programa ofertado específico
     *
     * @param po programa ofertado que se va a modificar
     */
    public void modificarPO(Programaofertado po);

    /**
     * Declaración del método para listar los programas ofertados de una
     * facultad específica
     *
     * @param facid id de la facultad de la cual queremos obtener los programas
     * ofertados
     * @return lista de los programas ofertados de la facultad específica
     */
    public List<Programaofertado> listarPOFacultad(String facid);
}
