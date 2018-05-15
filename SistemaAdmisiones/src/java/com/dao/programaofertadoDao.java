/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Facultad;
import com.model.Programaofertado;
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
