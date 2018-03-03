package Modelo;

import Modelo.Facultad;
import Modelo.Programaofertado;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-03T17:20:33")
@StaticMetamodel(Programa.class)
public class Programa_ { 

    public static volatile SingularAttribute<Programa, Short> proid;
    public static volatile SingularAttribute<Programa, String> pronombre;
    public static volatile SingularAttribute<Programa, Facultad> facid;
    public static volatile CollectionAttribute<Programa, Programaofertado> programaofertadoCollection;

}