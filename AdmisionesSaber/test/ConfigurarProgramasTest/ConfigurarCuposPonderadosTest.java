package ConfigurarProgramasTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.admisiones.bean.ProgramaOfertadoBean;
import com.admisiones.data.Programaofertado;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JULIAN
 */
public final class ConfigurarCuposPonderadosTest {
    
    ProgramaOfertadoBean proBean;
    List<Programaofertado> cupos;
    List<Programaofertado> cuposTest;
    
    
    public ConfigurarCuposPonderadosTest() {
        cupos = new ArrayList<Programaofertado>();
        cuposTest = new ArrayList<Programaofertado>();
        llenarTest();
    }
    
    @Before
    public void before() {
        proBean = new ProgramaOfertadoBean();
    }
    
    @Test
    public void test (){
        cupos.add(this.proBean.getListProgramasOfertados().get(0));
        assertNotNull(cupos);
        assertTrue(cupos.get(0).equals(cuposTest.get(0)));
    }
    
    public void llenarTest(){
        Programaofertado progOf = new Programaofertado();
        progOf.setPrograma(this.proBean.getListProgramasOfertados().get(0).getPrograma());
        progOf.setPeriodo(this.proBean.getListProgramasOfertados().get(0).getPeriodo());
        progOf.setProgramaofertadoPK(this.proBean.getListProgramasOfertados().get(0).getProgramaofertadoPK());
        //Cupos
        progOf.setPofcuposreg(Short.parseShort(""));
        progOf.setPofcuposdificilacceso(Short.parseShort(""));
        progOf.setPofcuposnormalista(Short.parseShort(""));
        progOf.setPofcuposindigena(Short.parseShort(""));
        progOf.setPofcuposzonamarginal(Short.parseShort(""));
        progOf.setPofcuposafrodescendiente(Short.parseShort(""));
        progOf.setPofcuposcostapacifica(Short.parseShort(""));
        progOf.setPoftotalcupos(Short.parseShort(""));
        progOf.setPoflistaespera(Short.parseShort(""));
        //Ponderados
        progOf.setPofpondlecturacritica(Short.parseShort(""));
        progOf.setPofpondmatematicas(Short.parseShort(""));
        progOf.setPofpondcsociales(Short.parseShort(""));
        progOf.setPofpondcnaturales(Short.parseShort(""));
        progOf.setPofpondingles(Short.parseShort(""));
        progOf.setPofpondpruebaad(Short.parseShort(""));
        
        this.cuposTest.add(progOf);
        
    }
}
