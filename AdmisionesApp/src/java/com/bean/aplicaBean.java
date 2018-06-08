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
 * Bean aplicaBean
 *
 * Contiene los métodos para gestionar los aspirantes que aplican a un programa
 * específico
 *
 * @author Proyecto II - Grupo Admisiones
 */
@Named(value = "aplicaBean")
@ApplicationScoped
public class aplicaBean implements Serializable {
    
    /*** Atributos ***/ 
    
    /**
     * Lista de todos los aspirantes 
     */
    private List<Aplica> listar;
    
    /**
     * * Lista los aspirantes que aplican a un programa específico
     */
    private List<Aplica> listaAsp;
    
    /**
     * Lista de los resultados ICFES de cada aspirante
     */
    private List<Resultadoicfes> listaRes;
    
    /**
     * Lista de los aspirantes ponderables y no ponderables de cada programa
     */
    private List<Aplica> listarPyN = new ArrayList<>(); 
    
    /**
     * Lista de todos los aspirantes ponderables
     */
    private List<AspirantePonderable> listaPon = new ArrayList<>();
    
    /**
     * Lista de todos los aspirantes no ponderables 
     */
    private List<AspiranteNoPonderable> listaNoPon = new ArrayList<>();
    
    /**
     * Lista de los aspirantes ponderables de un programa específico
     */
    private List<AspirantePonderable> listaPonPrograma = new ArrayList<>();
    
    /**
     * Lista de los aspirantes no ponderables de un programa específico
     */
    private List<AspiranteNoPonderable> listaNoPonPrograma = new ArrayList<>();
    
    /**
     * Aspirante que puede ser clasificado en ponderable y no ponderable
     */
    private Aplica aplica;
    
    /**
     * Información de un aspirante 
     */
    private Aspirante aspirante; 
    
    /**
     * Id del programa 
     */
    private String selectedPro;
    
    /**
     * Nombre del programa
     */
    private String selectedNomPro;
    
    /**
     * Id del aspirante 
     */
    private String aspid;
    
    
    /*** Constructor ***/ 
    
    /**
     * Constructor por defecto
     */
    public aplicaBean() {
    }
    
    
    /*** Métodos getter y setter ***/
    
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
    
    public Aplica getAplica() {
        return aplica;
    }
    
    public void setAplica(Aplica aplica) {
        this.aplica = aplica;
    }
    
    public String getSelectedNomPro() {
        return selectedNomPro;
    }
    
    public void setSelectedNomPro(String selectedNomPro) {
        this.selectedNomPro = selectedNomPro;
    }    
    
    public String getSelectedPro() {
        return selectedPro;
    }
    
    public void setSelectedPro(String selectedPro) {
        this.selectedPro = selectedPro;
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
    
    
    /*** Métodos públicos ***/ 
    
    /**
     * Obtener la lista de los resultados ICFES de todos los aspirantes
     * 
     * @return lista de los resultados ICFES de los aspirantes 
     */
    public List<Resultadoicfes> getListarResultadoIcfes() {
        AplicaDao apdao = new AplicaDaoImp();
        listaRes = apdao.listarResultados(); // llama al método del Dao 
                                             // para obtener la lista de los 
                                             // resultados ICFES desde la BD
        return listaRes;
    }
    
    /**
     * Obtener la lista de todos los aspirantes 
     * 
     * @return lista de los aspirantes 
     */
    public List<Aplica> getListarAspirantes() {
        AplicaDao apdao = new AplicaDaoImp();
        listaAsp = apdao.mostrarAspirantes(); // obtener la lista desde la BD
        return listaAsp;
    }
    
    /**
     * Obtener la lista de los aspirantes de un programa específico
     * 
     * @return lista de los aspirantes de un programa específico
     */
    public List<Aplica> getListar() {
        AplicaDao apdao = new AplicaDaoImp();
        listar = apdao.mostrarAplica(selectedPro); // obtener la lista desde la BD
        return listar;
    }

    /**
     * Cruzar la información de los aspirantes con los resultados ICFES de cada
     * aspirante para clasificarlos en aspirantes PONDERABLES y NO PONDERABLES
     */
    public void cruce() {        
        // si las listas de aspitantes y resultados ICFES de los aspirantes están 
        // vacias, se llenan. 
        if (this.getListarAspirantes().isEmpty() || this.getListarResultadoIcfes().isEmpty()) {
            this.getListarAspirantes();
            this.getListarResultadoIcfes();
        }        
        
        // listas auxiliares de resultados ICFES y aspirantes 
        List<Resultadoicfes> listaR = this.getListarResultadoIcfes();
        List<Aplica> listaA = this.getListarAspirantes();
        
        // si el número de aspirantes es igual al número de resultados ICFES de 
        // los aspirantes
        if (listaR.size() == listaA.size()) {
            // se recorre la lista de los resultados ICFES de cada aspirante 
            for (int i = 0; i < listaR.size(); i++) {            
                // se valida si existe en SNP registrado en el ICFES, en la lista 
                // de aspirantes inscritos
                if ((this.buscarPorSNP(listaR.get(i).getRessnp(), listaA))
                        && (this.buscarPorNombre(listaR.get(i).getResnombreest(), listaA))) {
                    // se crea un aspirante ponderable y se agrega a la lista de 
                    // Aspirantes Ponderables 
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
                    // si no existe el SNP en la lista de aspirantes inscritos 
                    if ((!this.buscarPorSNP(listaR.get(i).getRessnp(), listaA))
                            && (this.buscarPorNombre(listaR.get(i).getResnombreest(), listaA))) {
                        // se crea un aspirante ponderable con estado 1, 
                        // y se agrega a la lista de Aspirantes No Ponderables 
                        AspirantePonderable ap = new AspirantePonderable();
                        ap.setAspirante(listaA.get(i).getAspirante());
                        ap.setAsptipo("1"); // el estado 1 significa: NO PONDERABLE
                        AspiranteNoPonderable anp = new AspiranteNoPonderable();
                        anp.setAspirantePonderable(ap);
                        anp.setAspid(listaR.get(i).getResidest());
                        anp.setAspmotivo("Error SNP"); // se agrega el motivo por el cual es NO ponderable
                        this.listaNoPon.add(anp);
                    }
                    // si no existe el nombre (registrado en el ICFES) en la lista 
                    // de aspirantes inscritos 
                    if ((this.buscarPorSNP(listaR.get(i).getRessnp(), listaA))
                            && (!this.buscarPorNombre(listaR.get(i).getResnombreest(), listaA))) {
                        // se crea un aspirante ponderable con estado 1, 
                        // y se agrega a la lista de Aspirantes No Ponderables 
                        AspirantePonderable ap = new AspirantePonderable();
                        ap.setAspirante(listaA.get(i).getAspirante());
                        ap.setAsptipo("1"); // el estado 1 significa: NO PONDERABLE
                        AspiranteNoPonderable anp = new AspiranteNoPonderable();
                        anp.setAspirantePonderable(ap);
                        anp.setAspid(listaR.get(i).getResidest());
                        anp.setAspmotivo("Error nombre"); // se agrega el motivo por el cual es NO ponderable
                        this.listaNoPon.add(anp);
                    }
                }
            }
        }
        
        // se insertan los aspirantes ponderables a la BD
        this.insertarPonderables();
        // se insertan los aspirantes no ponderables a la BD
        this.insertarNoPonderables();
        // se redirecciona a la vista datos Procesados.xhtml  
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/datosProcesados.xhtml");
        
    }

    /**
     * Cambiar el estado de un aspirante NO PONDERABLE a PONDERABLE (desde la BD)
     * @param id id del aspirante 
     */
    public void reintegrar(int id) {
        // re recorre la lista de los aspirantes no ponderables 
        for (int i = 0; i < this.listaNoPonPrograma.size(); i++) {
            if (this.listaNoPonPrograma.get(i).getAspid() == id) {
                // se cambia el estado DE 1 a 0: PONDERABLE 
                this.listaNoPonPrograma.get(i).getAspirantePonderable().setAsptipo("0"); 
                // se agrega a la lista de aspirantes ponderables 
                this.listaPonPrograma.add(this.listaNoPonPrograma.get(i).getAspirantePonderable());
                // se elimina de la lista de no ponderables 
                this.listaNoPonPrograma.remove(i);
                AplicaDao apdao = new AplicaDaoImp();
                // se inserta (desde la BD) en la lista de aspirantes ponderables 
                apdao.insertPonderables(this.listaNoPonPrograma.get(i).getAspirantePonderable());
                // se elimina (desde la BD) de la lista de aspirantes no ponderables
                apdao.eliminarNoPonderable(this.listaNoPonPrograma.get(i));
                break;
            }
        }
    }

    
    /*** Métodos privados ***/
    
    /**
     * Obtener la información de un aspitante específico, dado su ID, para
     * mostrarla en la opción VER de la vista que contiene el listado de
     * aspirantes
     */
    private void setInfoAspirante(){
        // se recorre la lista de aspirantes
        for(int i = 0; i< this.listaAsp.size(); i++){
            // si encuentra el aspirante con el ID global, se obtiene la 
            // información 
            if(this.listaAsp.get(i).getAspirante().getAspid() == Short.parseShort(this.aspid)){
                this.aspirante = new Aspirante(); // se inicializa el aspirante 
                // se modifican sus atributos
                this.aspirante.setAspnombre(this.listaAsp.get(i).getAspirante().getAspnombre());
                this.aspirante.setAspcorreo(this.listaAsp.get(i).getAspirante().getAspcorreo());
                this.aspirante.setAspfechanac(this.listaAsp.get(i).getAspirante().getAspfechanac());
                this.aspirante.setAspid(this.listaAsp.get(i).getAspirante().getAspid());
                this.aspirante.setAsptipodoc(this.listaAsp.get(i).getAspirante().getAsptipodoc());
                this.aspirante.setAspirantePonderable(this.listaAsp.get(i).getAspirante().getAspirantePonderable());
            }
        }
    }
    
    /**
     * Buscar si el SNP con el que un aspirante se inscribió a un programa,
     * coincide con el SNP del ICFES
     *
     * @param snp número SNP del ICFES del aspirante
     * @param listaA lista de los aspirantes
     * @return 
     *      true: existe el SNP en la lista de aspirantes 
     *      false: no existe el SNP en la lista de aspirantes
     */
    private boolean buscarPorSNP(String snp, List<Aplica> listaA) {
        // se recorre la lista para buscar el SNP del asirante
        for (int i = 0; i < listaA.size(); i++) {
            if (listaA.get(i).getAplisnp().equals(snp)) {                
                return true;
            }
        }
        return false;
    }
    
    /**
     * Buscar si el nombre con el que un aspirante se inscribió a un programa,
     * coincide con el nombre con el que se registró en el ICFES
     * 
     * @param name nombre registrado en el ICFES del aspirante 
     * @param listaA lista de los aspirantes
     * @return 
     *      true: existe el nombre en la lista de aspirantes 
     *      false: no existe el nombre en la lista de aspirantes
     */
    private boolean buscarPorNombre(String name, List<Aplica> listaA) {
        // se recorre la lista para buscar el nombre del aspirante 
        for (int i = 0; i < listaA.size(); i++) {
            if (listaA.get(i).getAspirante().getAspnombre().equals(name)) {
                return true;
            }
        }
        return false;
    }
   
    /**
     * Obtener la lista de los aspirantes ponderables y no ponderables de un
     * programa específico
     *
     * @return lista de los aspirantes ponderables y no ponderables de un
     * programa específico
     */
    private List<Aplica> getListarAspirantesPyN() {
        AplicaDao adao = new AplicaDaoImp();
        listarPyN = adao.listarAspirantes(this.selectedPro); // obtener los datos 
                                                             // desde la BD
        return listarPyN;
    }
    
    /**
     * Llenar las listas de aspirantes PONDERABLES y NO PONDERABLES
     * respectivamente
     */
    private void setListasPyN() {
        // si la lista de asporantes ponderables y no ponderables está vacía, se llena
        if (this.listarPyN.isEmpty()) {
            this.getListarAspirantesPyN();
        }
        
        // se recorre la lista de aspirantes 
        for (int i = 0; i < this.listarPyN.size(); i++) {
            // si el aspirante tiene el estado 0 (en la BD), es ponderable, se 
            // agrega a la lista de ponderables 
            if (this.listarPyN.get(i).getAspirante().getAspirantePonderable().getAsptipo().equals("0")) {
                // se agrega solo una vez a la lista de ponderables 
                if (!this.buscarAspirantePRepetido(this.listarPyN.get(i).getAspirante().getAspid())) {
                    this.listaPonPrograma.add(this.listarPyN.get(i).getAspirante().getAspirantePonderable());
                }
            // si el aspirante tiene el estado 1 (en la BD), es NO ponderable, 
            // se agrega a la lista de no ponderables 
            } else if (this.listarPyN.get(i).getAspirante().getAspirantePonderable().getAsptipo().equals("1")) {
                // se agrega solo una vez a la lista de no ponderables 
                if (!this.buscarAspiranteNPRepetido(this.listarPyN.get(i).getAspirante().getAspid())) {
                    this.listaNoPonPrograma.add(this.listarPyN.get(i).getAspirante().getAspirantePonderable().getAspiranteNoPonderable());
                }
            }
        }        
    }
    
    /**
     * Insertar los aspirantes ponderables en la BD
     */
    private void insertarPonderables() {
        AplicaDao apdao = new AplicaDaoImp();
        // si la lista de ponderables tiene aspirantes
        if (!this.listaPon.isEmpty()) {
            for (int i = 0; i < this.listaPon.size(); i++) {
                // se llama al método del DAO para insertar cada aspirante ponderable 
                apdao.insertPonderables(this.listaPon.get(i)); 
                
            }
        }
    }
    
    /**
     * Insertar los aspirantes no ponderables en la BD
     */
    private void insertarNoPonderables() {
        AplicaDao apdao = new AplicaDaoImp();  
        // si la lista de no ponderables tiene aspirantes
        if (!this.listaNoPon.isEmpty()) {            
            for (int i = 0; i < this.listaNoPon.size(); i++) {
                // se llama al método del DAO para insertar cada aspirante no ponderable 
                apdao.insertNoPonderables(this.listaNoPon.get(i));
            }
        }
    }
    
    /**
     * Verificar si un aspirante ya está en la lista de aspirantes ponderables
     *
     * @param id ID del aspirante ponderable
     * @return
     *      true: existe el aspirante en la lista de aspirantes ponderables
     *      false: no existe el aspirante en la lista de aspirantes ponderables
     */
    private boolean buscarAspirantePRepetido(int id) {
        // se recorre la lista de aspirantes ponderables de un programa
        for (int i = 0; i < this.listaPonPrograma.size(); i++) {
            if (this.listaPonPrograma.get(i).getAspid() == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verificar si un aspirante ya está en la lista de aspirantes no ponderables
     *
     * @param id ID del aspirante no ponderable
     * @return
     *      true: existe el aspirante en la lista de aspirantes no ponderables
     *      false: no existe el aspirante en la lista de aspirantes no
     *      ponderables
     */
    private boolean buscarAspiranteNPRepetido(int id) {
        // se recorre la lista de aspirantes no ponderables de un programa
        for (int i = 0; i < this.listaNoPonPrograma.size(); i++) {
            if (this.listaNoPonPrograma.get(i).getAspid() == id) {
                return true;
            }
        }
        return false;
    }
    
    
    /*** Métodos para redireccionar a las vistas ***/
    
    /**
     * Redirecciona a la vista listarAdmitidosPorPrograma.xhtml para listar los
     * admitidos de un programa específico
     * 
     * @param selectedPro ID del programa
     */
    public void verAdmitidosPrograma(String selectedPro) {
        this.setSelectedPro(selectedPro); // se configura el ID del programa
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPorPrograma.xhtml");
    }
    
    /**
     * Redirecciona a la vista verAspirantesPorPrograma.xhtml para ver la lista
     * de los aspirantes ponderables de un programa específico
     * 
     * @param proid ID del programa
     */
    public void verAspirantesPorPrograma(String proid) {
        // se configura el programa 
        this.setSelectedPro(proid); 
        // se llenan las listas de aspirantes ponderables y no ponderables del programa
        this.setListasPyN(); 
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesPorPrograma.xhtml");
        
    }
    
    /**
     * Redirecciona a la vista verAspirantesNOPorPrograma.xhtml para ver la lista
     * de los aspirantes no ponderables de un programa específico
     * 
     * @param proid ID del programa
     */
    public void verAspirantesNOPorPrograma(String proid) {
        // se configura el programa 
        this.setSelectedPro(proid);
        // se llenan las listas de aspirantes ponderables y no ponderables del programa
        this.setListasPyN();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/verAspirantesNOPorPrograma.xhtml");
        
    }
    
    /**
     * Redirecciona a la vista listarAdmitidosPublicado.xhtml para ver los
     * admitidos de cada programa
     */
    public void verAdmitidosPublicados() {
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
    }
    
    /**
     * Redirecciona a la vista informacionAspirante.xhtml para ver la
     * información de un aspirante
     * 
     * @param aspid ID del aspirante 
     */
    public void verInfoAspirante(String aspid){
        // se configura el ID del aspirante
        this.aspid = aspid;        
        // se obtiene el aspirante con el ID 
        this.setInfoAspirante();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/informacionAspirante.xhtml");
    }
    
    /**
     * Redirecciona a la vista listaProgramasNoPonderables.xhtml para mostrar la
     * lista de los programas y sus aspirantes no ponderables
     */
    public void listaNoPonderable() {
        // se llenan las listas de ponderables y no ponderables 
        this.setListasPyN();        
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasNoPonderables.xhtml");
    }

    /**
     * Redirecciona a la vista listaProgramasPonderables.xhtml para mostrar la
     * lista de los programas y sus aspirantes ponderables
     */
    public void listaPonderable() {
        // se llenan las listas de ponderables y no ponderables 
        this.setListasPyN();
        Utilidades.redireccionar("/AdmisionesApp/faces/Vistas/GestionarPonderables/listaProgramasPonderables.xhtml");
    }
        
}
