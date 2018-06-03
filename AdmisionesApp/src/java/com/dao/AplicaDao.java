package com.dao;

import com.model.Aplica;
import java.util.List;

/**
 *
 * @author HP
 */
public interface AplicaDao {
    public List<Aplica> mostrarAplica(String selectedPro);
}
