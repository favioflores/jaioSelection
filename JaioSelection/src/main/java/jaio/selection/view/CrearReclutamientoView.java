package jaio.selection.view;

import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.ProcesoSeleccionBean;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.ReclutamientoDAO;
import jaio.selection.orm.Candidato;
import jaio.selection.orm.Empresa;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;
import static jaio.selection.view.BaseView.FATAL;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "crearReclutamientoView")
@ViewScoped
public class CrearReclutamientoView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(CrearReclutamientoView.class);
    private static final long serialVersionUID = -1L;

    //combo de empresas
    public List<EmpresaBean> listaEmpresas;
    public String strEmpresaSeleccionada;

    //combo de reclutados por empresa
    public List<ProcesoSeleccionBean> listaProcesos;
    public String strProcesoSeleccionado;
    private boolean lockProceso;

    @PostConstruct
    public void init() {
        limpiar();
    }

    public void limpiar() {
        poblarEmpresas();
        listaProcesos = new ArrayList<>();
        strEmpresaSeleccionada = null;
        strProcesoSeleccionado = null;
        lockProceso = true;
    }

    public void abrirRegistroReclutamiento() {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada)) {
                Utilitarios.ponerSession(strEmpresaSeleccionada, Constantes.SESSION_BATERIA);
            }
            Utilitarios.ponerSession(null, Constantes.SESSION_ID_BATERIA);
            FacesContext.getCurrentInstance().getExternalContext().redirect("registrarReclutamiento.jsf");
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void poblarEmpresas() {
        try {
            listaEmpresas = new ArrayList<>();
            EmpresaDAO objEmpresaDAO = new EmpresaDAO();
            List<Empresa> lstEmpresa = objEmpresaDAO.obtenerEmpresasOrdenNombre();
            lstEmpresa.stream().map((objEmpresa) -> {
                EmpresaBean objEmpresaBean = new EmpresaBean();
                objEmpresaBean.setId(objEmpresa.getId().toString());
                objEmpresaBean.setDesc(objEmpresa.getNombre());
                return objEmpresaBean;
            }).forEachOrdered((objEmpresaBean) -> {
                listaEmpresas.add(objEmpresaBean);
            });
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void seleccionaEmpresa() {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada) && !strEmpresaSeleccionada.equals("-1")) {
                EmpresaDAO objEmpresaDAO = new EmpresaDAO();
                Empresa objEmpresa = objEmpresaDAO.obtenerEmpresa(strEmpresaSeleccionada);
                mostrarAlerta(INFO, "proceso.seleccion.empresa", null, null, objEmpresa.getNombre());
                poblarProcesos();
            } else {
                limpiar();
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }
    
    
    public void poblarProcesos(){
        try {
            listaProcesos = new ArrayList<>();
            lockProceso = false;
            ReclutamientoDAO objReclutamiento = new ReclutamientoDAO();
            List listaP = objReclutamiento.obtenerProcesos(strEmpresaSeleccionada);
            for(Object proceso : listaP){
                Object o[] = (Object[])proceso;
                ProcesoSeleccionBean procesoBean = new ProcesoSeleccionBean();
                procesoBean.setId(o[0].toString());
                procesoBean.setDescripcion(o[1].toString());
                listaProcesos.add(procesoBean);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesparado", log, e);
        }
    }

    public void obtenerInfoReclutados(String id) {
        try {
            
            ReclutamientoDAO objEmpresaDAO = new ReclutamientoDAO();
            List<Candidato> lstCandidato = objEmpresaDAO.obtenerReclutados(id);
            for (Candidato candidato : lstCandidato) {

            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }
    
    
    

    public void seleccionaProceso() {

    }

    public String getStrEmpresaSeleccionada() {
        return strEmpresaSeleccionada;
    }

    public void setStrEmpresaSeleccionada(String strEmpresaSeleccionada) {
        this.strEmpresaSeleccionada = strEmpresaSeleccionada;
    }

    public List<EmpresaBean> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<EmpresaBean> lstEmpresas) {
        this.listaEmpresas = lstEmpresas;
    }

    public List<ProcesoSeleccionBean> getListaProcesos() {
        return listaProcesos;
    }

    public void setListaProcesos(List<ProcesoSeleccionBean> lstProcesos) {
        this.listaProcesos = lstProcesos;
    }

    public boolean isLockProceso() {
        return lockProceso;
    }

    public void setLockProceso(boolean lockProceso) {
        this.lockProceso = lockProceso;
    }
    

}
