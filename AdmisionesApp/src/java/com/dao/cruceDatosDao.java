/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Aspirante;
import com.model.Resultadoicfes;
import java.util.List;

/**
 *
 * @author JULIAN
 */
public interface cruceDatosDao {
    public List<Aspirante> mostrarAspirantes();
    public List<Resultadoicfes> listarResultados();
    
    
}
