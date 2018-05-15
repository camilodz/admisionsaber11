/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.bean.aplicaBean;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karen
 */
public class TestListaAdmitidos {
    
    public TestListaAdmitidos() {
    }
    
    
            
    @Test
    public void testListProgramasOfertados(){
        aplicaBean f = new aplicaBean();
        int tamListBD = 200; // No. de admitidos que hay en la base de datos (9) 
        int resultado = f.getListar().size(); // No. de programas ofertados que retorna el método del bean
        assertEquals(tamListBD, resultado);
        if(tamListBD != resultado){
         fail("El tamaño de la lista es diferente");   
        }       
        
    }
    
}
