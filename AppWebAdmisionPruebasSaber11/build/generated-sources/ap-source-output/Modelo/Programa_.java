package Modelo;

import Modelo.Facultad;
import Modelo.Programacasos;
import Modelo.Programacomponentes;
import Modelo.Programaofertado;
import Modelo.Pruebaadicional;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-09T10:52:52")
@StaticMetamodel(Programa.class)
public class Programa_ { 

    public static volatile CollectionAttribute<Programa, Programacasos> programacasosCollection;
    public static volatile CollectionAttribute<Programa, Programacomponentes> programacomponentesCollection;
    public static volatile SingularAttribute<Programa, Short> proid;
    public static volatile SingularAttribute<Programa, String> pronombre;
    public static volatile SingularAttribute<Programa, Facultad> facid;
    public static volatile CollectionAttribute<Programa, Programaofertado> programaofertadoCollection;
    public static volatile CollectionAttribute<Programa, Pruebaadicional> pruebaadicionalCollection;
    public static volatile SingularAttribute<Programa, Short> propruebaad;

}