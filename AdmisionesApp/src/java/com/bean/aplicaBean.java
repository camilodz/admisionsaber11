package com.bean;

import com.dao.AplicaDao;
import com.dao.AplicaDaoImp;
import com.model.Aplica;
import com.model.Aspirante;
import com.model.AspiranteNoPonderable;
import com.model.AspirantePonderable;
import com.model.Resultadoicfes;
import com.utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author HP
 */
@Named(value = "aplicaBean")
@ApplicationScoped
public class aplicaBean implements Serializable {

    private List<Aplica> listar;
    private String selectedPro;
    private String selectedNomPro;
    private List<Aplica> filtro;

    private List<Resultadoicfes> listaRes;
    private List<Aplica> listaAsp;

    private List<AspirantePonderable> listaPon = new ArrayList<>();
    private List<AspiranteNoPonderable> listaNoPon = new ArrayList<>();

    private Aplica aplica;

    public aplicaBean() {
    }

    public List<Resultadoicfes> getListarResultadoIcfes() {
        AplicaDao apdao = new AplicaDaoImp();
        listaRes = apdao.listarResultados();
        return listaRes;
    }

    public List<Aplica> getListarAspirantes() {
        AplicaDao apdao = new AplicaDaoImp();
        listaAsp = apdao.mostrarAspirantes();
        return listaAsp;
    }

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

    public void cruce() {

        if ((this.getListarAspirantes().size() == 0) || (this.getListarResultadoIcfes().size() == 0)) {
            this.getListarAspirantes();
            this.getListarResultadoIcfes();
        }

        List<Resultadoicfes> listaR = this.getListarResultadoIcfes();
        List<Aplica> listaA = this.getListarAspirantes();


        if (listaR.size() == listaA.size()) {
            for (int i = 0; i < listaR.size(); i++) {

                if ((this.buscarPorSNP(listaR.get(i).getRessnp(), listaA))
                        //                        && (this.buscarPorId( listaR.get(i).getResidest(), listaA))
                        && (this.buscarPorNombre(listaR.get(i).getResnombreest(), listaA))) {

                    AspirantePonderable ap = new AspirantePonderable();
                    ap.setAspirante(listaA.get(i).getAspirante());
                    ap.setAspid(listaR.get(i).getResidest());
                    ap.setAsptipo(listaR.get(i).getRestipodocest()+"");
                    ap.setAsppondtotal(listaR.get(i).getResglobal());
                    ap.setAsplecturacritica(listaR.get(i).getReslecturacritica());
                    ap.setAspmatematicas(listaR.get(i).getResmatematicas());
                    ap.setAspcsociales(listaR.get(i).getRescsociales());
                    ap.setAspcnaturales(listaR.get(i).getRescnaturales());
                    ap.setAspingles(listaR.get(i).getResingles());
                    ap.setAsppruebaad(null);
                    
                    this.listaPon.add(ap);
                } else {
                    if ((!this.buscarPorSNP(listaR.get(i).getRessnp(), listaA))
                            //                            && (this.buscarPorId( listaR.get(i).getResidest(), listaA))
                            && (this.buscarPorNombre(listaR.get(i).getResnombreest(), listaA))) {
                        AspirantePonderable ap = new AspirantePonderable();
                        ap.setAspirante(listaA.get(i).getAspirante());
                        ap.setAsptipo("1");
                        AspiranteNoPonderable anp = new AspiranteNoPonderable();
                        anp.setAspirantePonderable(ap);
                        anp.setAspid(listaR.get(i).getResidest());
                        anp.setAspmotivo("Error SNP");
                        this.listaNoPon.add(anp);
                    }
//                    if ((this.buscarPorSNP(listaR.get(i).getRessnp(), listaA))
//                            //                            && (!this.buscarPorId( listaR.get(i).getResidest(), listaA))
//                            && (this.buscarPorNombre(listaR.get(i).getResnombreest(), listaA))) {
//                        AspiranteNoPonderable anp = new AspiranteNoPonderable();
//                        anp.setAspid(listaR.get(i).getResidest());
//                        anp.setAspmotivo("El id no coincide");
//                        this.listaNoPon.add(anp);
//                    }
                    if ((this.buscarPorSNP(listaR.get(i).getRessnp(), listaA))
                            //                            && (this.buscarPorId(listaR.get(i).getResidest(), listaA))
                            && (!this.buscarPorNombre(listaR.get(i).getResnombreest(), listaA))) {
                        AspirantePonderable ap = new AspirantePonderable();
                        ap.setAspirante(listaA.get(i).getAspirante());
                        ap.setAsptipo("1");
                        AspiranteNoPonderable anp = new AspiranteNoPonderable();                        
                        anp.setAspirantePonderable(ap);
                        anp.setAspid(listaR.get(i).getResidest());
                        anp.setAspmotivo("Error nombre");
                        this.listaNoPon.add(anp);
                    }
                }
            }
        }

        this.insertarPonderables();
        this.insertarNoPonderables();
        
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/datosProcesados.xhtml");

    }

    // insertar a la BD
    private void insertarPonderables() {
        AplicaDao apdao = new AplicaDaoImp();
        if (!this.listaPon.isEmpty()) {
            for (int i = 0; i < this.listaPon.size(); i++) {
                
                apdao.insertPonderables(this.listaPon.get(i));
                
            }
        }
    }

    private void insertarNoPonderables() {
        AplicaDao apdao = new AplicaDaoImp();
        
        if (!this.listaNoPon.isEmpty()) {
            
            for (int i = 0; i < this.listaNoPon.size(); i++) {
                apdao.insertNoPonderables(this.listaNoPon.get(i));
            }
        }
    }

    public boolean buscarPorSNP(String snp, List<Aplica> listaA) {

        for (int i = 0; i < listaA.size(); i++) {
            if (listaA.get(i).getAplisnp().equals(snp)) {
                
                return true;
            }
        }
        return false;
    }

//    public boolean buscarPorId(Integer id, List<Aplica> listaA) {
//        for (int i = 0; i < listaA.size(); i++) {
//            
//            if (listaA.get(i).getApliid() == (Short)id) {
//                
//                
//                System.out.println("2**************************");
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean buscarPorNombre(String name, List<Aplica> listaA) {
        for (int i = 0; i < listaA.size(); i++) {
            if (listaA.get(i).getAspirante().getAspnombre().equals(name)) {
                
                return true;
            }
        }
        return false;
    }

    public void verAdmitidosPublicados() {
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
    }

    public void verAdmitidosPrograma(String selectedPro) {
        this.setSelectedPro(selectedPro);
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPorPrograma.xhtml");
    }
}
