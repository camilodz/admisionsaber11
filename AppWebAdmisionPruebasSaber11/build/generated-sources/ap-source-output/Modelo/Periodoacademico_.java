package Modelo;

import Modelo.Programacasos;
import Modelo.Programacomponentes;
import Modelo.Programaofertado;
import Modelo.Pruebaadicional;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-09T10:52:52")
@StaticMetamodel(Periodoacademico.class)
public class Periodoacademico_ { 

    public static volatile SingularAttribute<Periodoacademico, BigDecimal> perid;
    public static volatile SingularAttribute<Periodoacademico, Short> perestado;
    public static volatile CollectionAttribute<Periodoacademico, Programacasos> programacasosCollection;
    public static volatile CollectionAttribute<Periodoacademico, Programacomponentes> programacomponentesCollection;
    public static volatile SingularAttribute<Periodoacademico, Short> perpuntajeminregionalizacion;
    public static volatile SingularAttribute<Periodoacademico, Short> perpuntajeminpopayan;
    public static volatile CollectionAttribute<Periodoacademico, Programaofertado> programaofertadoCollection;
    public static volatile CollectionAttribute<Periodoacademico, Pruebaadicional> pruebaadicionalCollection;

}