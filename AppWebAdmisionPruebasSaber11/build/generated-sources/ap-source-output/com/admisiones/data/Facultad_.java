package com.admisiones.data;

import com.admisiones.data.Programa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-17T08:10:21")
@StaticMetamodel(Facultad.class)
public class Facultad_ { 

    public static volatile SingularAttribute<Facultad, String> facnombre;
    public static volatile ListAttribute<Facultad, Programa> programaList;
    public static volatile SingularAttribute<Facultad, Short> facid;

}