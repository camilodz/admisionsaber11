package com.bean;

import com.dao.AplicaDao;
import com.dao.AplicaDaoImp;
import com.model.Aplica;
import com.utilidades.Utilidades;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author HP
 */
@Named(value = "aplicaBean")
@ApplicationScoped
public class AplicaBean implements Serializable{
    private List<Aplica> listar;
    private String selectedPro;
    private String selectedNomPro;
    private List<Aplica> filtro;
    
    public List<Aplica> getListar() {        
        AplicaDao apdao = new AplicaDaoImp();
        listar = apdao.mostrarAplica(selectedPro);
        return listar;
    }

    public List<Aplica> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Aplica> filtro) {
        this.filtro = filtro;
    }

    public Aplica getAplica() {
        return aplica;
    }

    public String getSelectedNomPro() {
        return selectedNomPro;
    }

    public void setSelectedNomPro(String selectedNomPro) {
        this.selectedNomPro = selectedNomPro;
    }
    

    public void setAplica(Aplica aplica) {
        this.aplica = aplica;
    }

    public String getSelectedPro() {
        return selectedPro;
    }

    public void setSelectedPro(String selectedPro) {
        this.selectedPro = selectedPro;
    }
    
    
    private Aplica aplica;
    /**
     * Creates a new instance of AplicaBean
     */
    public AplicaBean() {
    }
    public void verAdmitidosPublicados(){
        Utilidades.redireccionar("/SistemaAdmisiones/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
    }
    public void verAdmitidosPrograma(String selectedPro){
        this.setSelectedPro(selectedPro);
        Utilidades.redireccionar("/SistemaAdmisiones/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPorPrograma.xhtml");
    }
}
