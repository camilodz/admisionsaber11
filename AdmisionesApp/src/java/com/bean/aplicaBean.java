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
    
    private List<Aplica> listarPyN = new ArrayList<>(); // listar ponderables y no ponderables por programa

    private List<AspirantePonderable> listaPon = new ArrayList<>();
    private List<AspiranteNoPonderable> listaNoPon = new ArrayList<>();
    
    private List<AspirantePonderable> listaPonPrograma = new ArrayList<>();
    private List<AspiranteNoPonderable> listaNoPonPrograma = new ArrayList<>();
    
    private List<Integer> numeroAsp = new ArrayList<>();
    
    private Aplica aplica;
    private Aspirante aspirante;
    
    private String aspid;
    
    public aplicaBean() {
    }
    
    public List<Resultadoicfes> getListaRes() {
        return listaRes;
    }
    
    public List<AspirantePonderable> getListaPonPrograma() {
        return listaPonPrograma;
    }
    
    public List<AspiranteNoPonderable> getListaNoPonPrograma() {
        return listaNoPonPrograma;
    }
    
    public List<Aplica> getListaAsp() {
        return listaAsp;
    }
    
    public List<Aplica> getListarPyN() {
        return listarPyN;
    }
    
    public List<AspirantePonderable> getListaPon() {
        return listaPon;
    }
    
    public List<AspiranteNoPonderable> getListaNoPon() {
        return listaNoPon;
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
                    ap.setAsptipo(listaR.get(i).getRestipodocest() + "");
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

    // listar aspirantes Ponderables y no ponderables  por programa
    private List<Aplica> getListarAspirantesPyN() {
        AplicaDao adao = new AplicaDaoImp();
        listarPyN = adao.listarAspirantes(this.selectedPro);
        System.out.println("----------------------------------------------- TAM listarPyN  " + this.listarPyN.size());
        return listarPyN;
    }
    
    private void setListasPyN() {
        
        if (this.listarPyN.isEmpty()) {
            this.getListarAspirantesPyN();
        }
        for (int i = 0; i < this.listarPyN.size(); i++) {
            if (this.listarPyN.get(i).getAspirante().getAspirantePonderable().getAsptipo().equals("0")) {
                if (!this.buscarAspirantePRepetido(this.listarPyN.get(i).getAspirante().getAspid())) {
                    this.listaPonPrograma.add(this.listarPyN.get(i).getAspirante().getAspirantePonderable());
                }
            } else if (this.listarPyN.get(i).getAspirante().getAspirantePonderable().getAsptipo().equals("1")) {
                if (!this.buscarAspiranteNPRepetido(this.listarPyN.get(i).getAspirante().getAspid())) {
                    this.listaNoPonPrograma.add(this.listarPyN.get(i).getAspirante().getAspirantePonderable().getAspiranteNoPonderable());
                }
            }
            
        }
        
    }
    
    public int numAspirantesPonderables() {
        return this.listaPonPrograma.size();
    }
    
    public int numAspirantesNoPonderables() {
        return this.listaNoPonPrograma.size();
    }
    
    private boolean buscarAspirantePRepetido(int id) {
        for (int i = 0; i < this.listaPonPrograma.size(); i++) {
            if (this.listaPonPrograma.get(i).getAspid() == id) {
                return true;
            }
        }
        return false;
    }
    
    private boolean buscarAspiranteNPRepetido(int id) {
        for (int i = 0; i < this.listaNoPonPrograma.size(); i++) {
            if (this.listaNoPonPrograma.get(i).getAspid() == id) {
                return true;
            }
        }
        return false;
    }
    
    public void verAspirantesPorPrograma(String proid) {
        
        System.out.println("************ ********* ************** " + proid);
        this.setSelectedPro(proid);
        System.out.println("************ ********* ************** id programa set" + this.getSelectedPro());
        this.setListasPyN();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesPorPrograma.xhtml");
        
    }
    
    public void verAspirantesNOPorPrograma(String proid) {
        this.setSelectedPro(proid);
        this.setListasPyN();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesNOPorPrograma.xhtml");
        
    }
    
    public void reintegrar(int id) {
        for (int i = 0; i < this.listaNoPonPrograma.size(); i++) {
            if (this.listaNoPonPrograma.get(i).getAspid() == id) {
                this.listaNoPonPrograma.get(i).getAspirantePonderable().setAsptipo("0");
                this.listaPonPrograma.add(this.listaNoPonPrograma.get(i).getAspirantePonderable());
                this.listaNoPonPrograma.remove(i);
                AplicaDao apdao = new AplicaDaoImp();
                apdao.insertPonderables(this.listaNoPonPrograma.get(i).getAspirantePonderable());
                apdao.eliminarNoPonderable(this.listaNoPonPrograma.get(i));
                break;
            }
        }
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public String getAspid() {
        return aspid;
    }
    
    public List<Integer> getNumeroAsp() {
        return numeroAsp;
    }
    
    public void setInfoAspirante(){
        for(int i = 0; i< this.listaAsp.size(); i++){
            if(this.listaAsp.get(i).getAspirante().getAspid() == Short.parseShort(this.aspid)){
                
            }
        }
    }
    
    
    public void verInfoAspirante(String aspid){
        this.aspid = aspid;
        this.setInfoAspirante();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/informacionAspirante.xhtml");
    }
    
    public List<Integer> numAspirantes() {
        AplicaDao apdao = new AplicaDaoImp();
        numeroAsp = apdao.numAspirantes();
        return numeroAsp;
    }
    
    public void listaNoPonderable() {
        this.setListasPyN();        
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasNoPonderables.xhtml");
    }

    public void listaPonderable() {
        this.setListasPyN();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasPonderables.xhtml");
    }
    
    
    
}
