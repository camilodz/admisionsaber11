/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.bean.facultadBean;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karen
 */
public class TestFacultad {
    
    public TestFacultad() {
    }
    
    
    @Test
    public void testListFacultades(){
        facultadBean f = new facultadBean();
        int tamListBD = 8; // No. de facultades que hay en la base de datos (9)
        int resultado = f.getListaFac().size(); // No. de facultades que retorna el método del bean
        assertEquals(tamListBD, resultado);
        if(tamListBD != resultado){
         fail("El tamaño de la lista es diferente");   
        }       
        
    }
}
