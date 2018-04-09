package Modelo;

import Modelo.Periodoacademico;
import Modelo.Programa;
import Modelo.ProgramaofertadoPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-09T10:52:52")
@StaticMetamodel(Programaofertado.class)
public class Programaofertado_ { 

    public static volatile SingularAttribute<Programaofertado, ProgramaofertadoPK> programaofertadoPK;
    public static volatile SingularAttribute<Programaofertado, Periodoacademico> periodoacademico;
    public static volatile SingularAttribute<Programaofertado, Short> progofcuposregulares;
    public static volatile SingularAttribute<Programaofertado, Short> progoflistadeespera;
    public static volatile SingularAttribute<Programaofertado, Programa> programa;

}