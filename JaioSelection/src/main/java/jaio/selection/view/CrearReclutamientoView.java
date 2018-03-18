package jaio.selection.view;

import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.ProcesoSeleccionBean;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.ReclutamientoDAO;
import jaio.selection.orm.Empresa;
import jaio.selection.orm.ProcesoSeleccion;
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

    public List<EmpresaBean> lstEmpresas;
    public String strEmpresaSeleccionada;
    
    public List<ProcesoSeleccionBean> lstProcesos;
    public String strProcesoSeleccionado;

    @PostConstruct
    public void init() {
        limpiar();
    }

    public void limpiar() {
        lstEmpresas = new ArrayList<>();
        lstProcesos = new ArrayList<>();
        strEmpresaSeleccionada = null;
        strProcesoSeleccionado = null;
        poblarEmpresas();

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
            lstEmpresas = new ArrayList<>();
            EmpresaDAO objEmpresaDAO = new EmpresaDAO();
            List<Empresa> lstEmpresa = objEmpresaDAO.obtenerEmpresasOrdenNombre();
            lstEmpresa.stream().map((objEmpresa) -> {
                EmpresaBean objEmpresaBean = new EmpresaBean();
                objEmpresaBean.setId(objEmpresa.getId().toString());
                objEmpresaBean.setDesc(objEmpresa.getNombre());
                return objEmpresaBean;
            }).forEachOrdered((objEmpresaBean) -> {
                lstEmpresas.add(objEmpresaBean);
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
            } else {
                limpiar();
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }
    
//    public void poblarProcesos(){
//        try {
//            lstProcesos = new ArrayList<>();
//            ReclutamientoDAO objEmpresaDAO = new ReclutamientoDAO();
//            List<ProcesoSeleccion> lstEmpresa = objEmpresaDAO.obtenerEmpresasOrdenNombre();
//            lstEmpresa.stream().map((objEmpresa) -> {
//                EmpresaBean objEmpresaBean = new EmpresaBean();
//                objEmpresaBean.setId(objEmpresa.getId().toString());
//                objEmpresaBean.setDesc(objEmpresa.getNombre());
//                return objEmpresaBean;
//            }).forEachOrdered((objEmpresaBean) -> {
//                lstEmpresas.add(objEmpresaBean);
//            });
//        } catch (Exception e) {
//            mostrarAlerta(FATAL, "error.inesperado", log, e);
//        }
//    }
    
    public void seleccionaProceso(){
        
    }
    
    
    

    public String getStrEmpresaSeleccionada() {
        return strEmpresaSeleccionada;
    }

    public void setStrEmpresaSeleccionada(String strEmpresaSeleccionada) {
        this.strEmpresaSeleccionada = strEmpresaSeleccionada;
    }

    public List<EmpresaBean> getLstEmpresas() {
        return lstEmpresas;
    }

    public void setLstEmpresas(List<EmpresaBean> lstEmpresas) {
        this.lstEmpresas = lstEmpresas;
    }

    public List<ProcesoSeleccionBean> getLstProcesos() {
        return lstProcesos;
    }

    public void setLstProcesos(List<ProcesoSeleccionBean> lstProcesos) {
        this.lstProcesos = lstProcesos;
    }
    
    

}
