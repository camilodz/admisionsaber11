package com.admisiones.data;

import com.admisiones.data.Programa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-18T21:38:32")
@StaticMetamodel(Facultad.class)
public class Facultad_ { 

    public static volatile CollectionAttribute<Facultad, Programa> programaCollection;
    public static volatile SingularAttribute<Facultad, String> facnombre;
    public static volatile SingularAttribute<Facultad, Short> facid;

}