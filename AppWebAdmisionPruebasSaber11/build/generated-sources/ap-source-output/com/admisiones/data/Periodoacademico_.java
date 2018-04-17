package com.admisiones.data;

import com.admisiones.data.Programacasos;
import com.admisiones.data.Programacomponentes;
import com.admisiones.data.Programaofertado;
import com.admisiones.data.Pruebaadicional;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-17T08:10:21")
@StaticMetamodel(Periodoacademico.class)
public class Periodoacademico_ { 

    public static volatile SingularAttribute<Periodoacademico, BigDecimal> perid;
    public static volatile SingularAttribute<Periodoacademico, Short> perestado;
    public static volatile ListAttribute<Periodoacademico, Programaofertado> programaofertadoList;
    public static volatile ListAttribute<Periodoacademico, Programacasos> programacasosList;
    public static volatile SingularAttribute<Periodoacademico, Short> perpuntajeminregionalizacion;
    public static volatile ListAttribute<Periodoacademico, Pruebaadicional> pruebaadicionalList;
    public static volatile SingularAttribute<Periodoacademico, Short> perpuntajeminpopayan;
    public static volatile ListAttribute<Periodoacademico, Programacomponentes> programacomponentesList;

}