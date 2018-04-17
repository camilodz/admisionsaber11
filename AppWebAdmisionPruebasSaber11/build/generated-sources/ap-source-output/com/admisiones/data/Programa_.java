package com.admisiones.data;

import com.admisiones.data.Facultad;
import com.admisiones.data.Programacasos;
import com.admisiones.data.Programacomponentes;
import com.admisiones.data.Programaofertado;
import com.admisiones.data.Pruebaadicional;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-17T08:10:21")
@StaticMetamodel(Programa.class)
public class Programa_ { 

    public static volatile ListAttribute<Programa, Programaofertado> programaofertadoList;
    public static volatile ListAttribute<Programa, Programacasos> programacasosList;
    public static volatile SingularAttribute<Programa, String> prosede;
    public static volatile SingularAttribute<Programa, Short> proid;
    public static volatile ListAttribute<Programa, Pruebaadicional> pruebaadicionalList;
    public static volatile SingularAttribute<Programa, String> pronombre;
    public static volatile SingularAttribute<Programa, Facultad> facid;
    public static volatile SingularAttribute<Programa, Short> propruebaad;
    public static volatile ListAttribute<Programa, Programacomponentes> programacomponentesList;

}