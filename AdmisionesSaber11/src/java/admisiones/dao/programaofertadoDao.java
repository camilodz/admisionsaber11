/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admisiones.dao;

import admisiones.model.Facultad;
import admisiones.model.Programaofertado;
import java.util.List;

/**
 *
 * @author Karen
 */
public interface programaofertadoDao {
    // Método para mostrar lista de programas ofertados
    public List<Programaofertado> listarPO();
    public void modificarPO(Programaofertado po);
    public List<Programaofertado> listarPOFacultad(String facid);
}
