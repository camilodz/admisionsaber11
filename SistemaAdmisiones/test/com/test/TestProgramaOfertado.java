/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.bean.programaofertadoBean;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karen
 */
public class TestProgramaOfertado {
    
    public TestProgramaOfertado() {
    }
    
        
    @Test
    public void testListProgramasOfertados(){
        programaofertadoBean f = new programaofertadoBean();
        int tamListBD = 7; // No. de programas ofertados que hay en la base de datos (9)
        int resultado = f.getListaPO().size(); // No. de programas ofertados que retorna el método del bean
        assertEquals(tamListBD, resultado);
        if(tamListBD != resultado){
         fail("El tamaño de la lista es diferente");   
        }       
        
    }
    
    @Test
    public void testListProgramasOfertadosPorFacultad(){
        programaofertadoBean f = new programaofertadoBean();
        int tamListBD = 4; // No. de programas ofertados de la facultad de Ing. Electronica que hay en la base de datos (9)
        int resultado = f.getListaPOFacultad().size(); // No. de programas ofertados que retorna el método del bean
        assertEquals(tamListBD, resultado);
        if(tamListBD != resultado){
         fail("El tamaño de la lista es diferente");   
        }       
        
    }
    
}
