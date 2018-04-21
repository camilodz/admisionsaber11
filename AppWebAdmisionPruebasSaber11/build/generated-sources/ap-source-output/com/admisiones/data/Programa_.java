package com.admisiones.data;

import com.admisiones.data.Facultad;
import com.admisiones.data.Programacasos;
import com.admisiones.data.Programacomponentes;
import com.admisiones.data.Programaofertado;
import com.admisiones.data.Pruebaadicional;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-21T09:08:37")
@StaticMetamodel(Programa.class)
public class Programa_ { 

    public static volatile CollectionAttribute<Programa, Programacasos> programacasosCollection;
    public static volatile CollectionAttribute<Programa, Programacomponentes> programacomponentesCollection;
    public static volatile SingularAttribute<Programa, String> prosede;
    public static volatile SingularAttribute<Programa, Short> proid;
    public static volatile SingularAttribute<Programa, String> pronombre;
    public static volatile SingularAttribute<Programa, Facultad> facid;
    public static volatile CollectionAttribute<Programa, Programaofertado> programaofertadoCollection;
    public static volatile CollectionAttribute<Programa, Pruebaadicional> pruebaadicionalCollection;
    public static volatile SingularAttribute<Programa, Short> propruebaad;

}