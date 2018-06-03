package com.dao;

import com.model.Aplica;
import com.model.Aspirante;
import com.model.AspiranteNoPonderable;
import com.model.AspirantePonderable;
import com.model.Resultadoicfes;
import java.util.List;

/**
 *
 * @author HP
 */
public interface AplicaDao {
    public List<Aplica> mostrarAplica(String selectedPro);
    
    public List<Aplica> mostrarAspirantes();
    public List<Resultadoicfes> listarResultados();
    
    public void insertPonderables(AspirantePonderable a);
    public void insertNoPonderables(AspiranteNoPonderable a);
}
