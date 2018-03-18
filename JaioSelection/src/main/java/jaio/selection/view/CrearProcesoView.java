package jaio.selection.view;

import jaio.selection.bean.BateriaPersonalizadaBean;
import jaio.selection.bean.CompetenciaBean;
import jaio.selection.bean.EmpresaBean;
import jaio.selection.dao.BateriaPersonalizadaDAO;
import jaio.selection.dao.EmpresaDAO;
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
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "crearProcesoView")
@ViewScoped
public class CrearProcesoView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(CrearProcesoView.class);
    private static final long serialVersionUID = -1L;

    public List<EmpresaBean> lstEmpresas;
    public String strEmpresaSeleccionada;
    private String countEvaluacion;
    private List<BateriaPersonalizadaBean> listaBateriasRegistradas;
    private List<CompetenciaBean> lstCompetenciaBean;
    private List listNombreEva;

    @PostConstruct
    public void init() {
        limpiar();
    }

    public void limpiar() {
        listaBateriasRegistradas = new ArrayList<>();
        lstEmpresas = new ArrayList<>();
        lstCompetenciaBean = new ArrayList();
        strEmpresaSeleccionada = null;
        countEvaluacion = null;
        poblarEmpresas();

    }

    public void abrirCrearProceso() {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada)) {
                Utilitarios.ponerSession(strEmpresaSeleccionada, Constantes.SESSION_BATERIA);
            }
            Utilitarios.ponerSession(null, Constantes.SESSION_ID_BATERIA);
            FacesContext.getCurrentInstance().getExternalContext().redirect("crearBateria.jsf");
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void abrirReclutamiento(BateriaPersonalizadaBean objBean) {
        try {
            if (Utilitarios.noEsNuloOVacio(objBean.getIdPs())) {
                Utilitarios.ponerSession(objBean.getIdPs(), Constantes.SESSION_ID_PROCESO);
                FacesContext.getCurrentInstance().getExternalContext().redirect("registrarReclutamiento.jsf");
            } else {
                mostrarAlerta(FATAL, "error.inesperado", log, null);
            }
            
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void cargarBateriasRegistradas() {
        try {
            listaBateriasRegistradas = new ArrayList<>();
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada)) {
                BateriaPersonalizadaDAO objBateriaPersonalizadaDAO = new BateriaPersonalizadaDAO();
                List lstBaterias = objBateriaPersonalizadaDAO.obtenerProcesosRegistrados(strEmpresaSeleccionada);
                Iterator it = lstBaterias.iterator();
                while (it.hasNext()) {
                    Object obj[] = (Object[]) it.next();
                    BateriaPersonalizadaBean objBateriaPersonalizadaBean = new BateriaPersonalizadaBean();
                    objBateriaPersonalizadaBean.setId(obj[0].toString());
                    objBateriaPersonalizadaBean.setNombre(obj[1].toString());
                    objBateriaPersonalizadaBean.setFechaCreacion(obj[2].toString());
                    objBateriaPersonalizadaBean.setNombrePerfil(obj[3].toString());
                    objBateriaPersonalizadaBean.setIdPs(obj[4].toString());
                    listaBateriasRegistradas.add(objBateriaPersonalizadaBean);
                }
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void verResumenBateria(BateriaPersonalizadaBean objBean) {
        try {

            BateriaPersonalizadaDAO objBateriaPersonalizadaDAO = new BateriaPersonalizadaDAO();
            countEvaluacion = null;
            lstCompetenciaBean = new ArrayList<>();
            listNombreEva = new ArrayList<>();
            countEvaluacion = objBateriaPersonalizadaDAO.obtenerNumeroEvaluaciones(objBean.getId());
            List objList = objBateriaPersonalizadaDAO.obtenerCompetenciaParaResumen(objBean.getId());

            Iterator it = objList.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                CompetenciaBean objBean1 = new CompetenciaBean();
                objBean1.setNombre(obj[0].toString());
                lstCompetenciaBean.add(objBean1);
            }
            
            for(Object n : objBateriaPersonalizadaDAO.obtenerNombreDeEva(objBean.getId())){
                listNombreEva.add(n);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void editarBateria(BateriaPersonalizadaBean objBean) {
        try {
            if (Utilitarios.noEsNuloOVacio(objBean.getId())) {
                Utilitarios.ponerSession(objBean.getId(), Constantes.SESSION_ID_BATERIA);
                FacesContext.getCurrentInstance().getExternalContext().redirect("crearBateria.jsf");
            } else {
                mostrarAlerta(FATAL, "error.inesperado", log, null);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void eliminarBateria(BateriaPersonalizadaBean objBean) {
        try {
            BateriaPersonalizadaDAO objDAO = new BateriaPersonalizadaDAO();
            boolean result = objDAO.eliminarBateriaRegistrada(objBean.getId());
            if (result) {
                mostrarAlerta(INFO, "proceso.mensaje.eliminado", null, null);
                cargarBateriasRegistradas();
            } else {
                mostrarAlerta(INFO, "proceso.mensaje.errorEliminar", null, null);
            }
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

    /**
     * metodo que trae las empresas
     */
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

    public List<BateriaPersonalizadaBean> getListaBateriasRegistradas() {
        return listaBateriasRegistradas;
    }

    public void setListaBateriasRegistradas(List<BateriaPersonalizadaBean> listaBateriasRegistradas) {
        this.listaBateriasRegistradas = listaBateriasRegistradas;
    }

    public List<CompetenciaBean> getLstCompetenciaBean() {
        return lstCompetenciaBean;
    }

    public void setLstCompetenciaBean(List<CompetenciaBean> lstCompetenciaBean) {
        this.lstCompetenciaBean = lstCompetenciaBean;
    }

    public String getCountEvaluacion() {
        return countEvaluacion;
    }

    public void setCountEvaluacion(String countEvaluacion) {
        this.countEvaluacion = countEvaluacion;
    }

    public List getListNombreEva() {
        return listNombreEva;
    }

    public void setListNombreEva(List listNombreEva) {
        this.listNombreEva = listNombreEva;
    }
    

}
