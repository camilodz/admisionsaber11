package com.bean;

import com.utilidades.Utilidades;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Bean templateBean 
 * 
 * Permite gestionar las rutas para acceder a las vistas
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "templateBean")
@ApplicationScoped
public class templateBean {

    /*** Constructor ***/
    
    /**
     * Constructor por defecto
     */
    public templateBean() {
    }

    /*** Métodos públicos ***/
    
    /**
     * Redirecciona a la vista listarFacultades.xhtml para listar todas las
     * facultades
     */
    public void verConfiguracionProgramas() {
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfigurarProgramas/listarFacultades.xhtml");
    }

    /**
     * Redirecciona a la vista del listado de admitidos:
     * listarAdmitidosPublicado.xhtml
     */
    public void verListadosDeAdmitidos() {
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
    }

    /**
     * Redirecciona a la vista ConfigPeriodo.xhtml para editar la configuración
     * de cada programa (cupos y ponderados ICFES)
     */
    public void verConfiguracionInicial() {
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ConfiguracionInicial/ConfigPeriodo.xhtml");
    }

    /**
     * Redirecciona a la vista seleccionArchivos.xhtml para cargar los archivos
     * (resultadosICFES y aspirantes a cada programa)
     */
    public void gestionarPonderables() {
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/seleccionArchivos.xhtml");
    }

    /**
     * Redirecciona a la vista de inicio: vistaInicio.xhtml
     */
    public void verPaginaPrincipal() {
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/vistaInicio.xhtml");
    }

}
