package jaio.selection.view;

import jaio.selection.bean.CandidatoBean;
import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.ProcesoSeleccionBean;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.ReclutamientoDAO;
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

    //informacion de reclutados
    public List<CandidatoBean> listaReclutados = new ArrayList<>();

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

    public void abrirRegistroReclutamiento(String id) {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada)) {
                Utilitarios.ponerSession(strEmpresaSeleccionada, Constantes.SESSION_BATERIA);
                if (Utilitarios.noEsNuloOVacio(id)) {
                    Utilitarios.ponerSession(id, Constantes.SESSION_ID_PROCESO);
                } else {
                    Utilitarios.ponerSession(strProcesoSeleccionado, Constantes.SESSION_ID_PROCESO);
                }

                FacesContext.getCurrentInstance().getExternalContext().redirect("registrarReclutamiento.jsf");
            }
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
                obtenerInfoReclutados(strEmpresaSeleccionada, null);

            } else {
                limpiar();
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void poblarProcesos() {
        try {
            listaProcesos = new ArrayList<>();
            lockProceso = false;
            ReclutamientoDAO objReclutamiento = new ReclutamientoDAO();
            for (Object proceso : objReclutamiento.obtenerProcesos(strEmpresaSeleccionada)) {
                Object o[] = (Object[]) proceso;
                ProcesoSeleccionBean procesoBean = new ProcesoSeleccionBean();
                procesoBean.setId(o[0].toString());
                procesoBean.setDescripcion(o[1].toString());
                listaProcesos.add(procesoBean);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesparado", log, e);
        }
    }

    public void seleccionaProceso() {
        try {
            if (Utilitarios.noEsNuloOVacio(strProcesoSeleccionado) && !strProcesoSeleccionado.equals("-1")) {
                obtenerInfoReclutados(strEmpresaSeleccionada, strProcesoSeleccionado);
            } else {
                obtenerInfoReclutados(strEmpresaSeleccionada, null);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void obtenerInfoReclutados(String idEmpresa, String idProceso) {
        try {
            listaReclutados = new ArrayList<>();
            ReclutamientoDAO objReclutamientoDao = new ReclutamientoDAO();
            for (Object o : objReclutamientoDao.obtenerReclutados(idEmpresa, idProceso)) {
                Object obj[] = (Object[]) o;
                CandidatoBean objCandidato = new CandidatoBean();
                objCandidato.setId(obj[0].toString());
                objCandidato.setNombre(obj[1].toString());
                objCandidato.setApellidoParterno(obj[2].toString());
                objCandidato.setApellidoMaterno(obj[3].toString());
                objCandidato.setNroDocumento(obj[4].toString());
                objCandidato.setMovil(obj[6].toString());
                objCandidato.setDireccion(obj[8].toString());
                listaReclutados.add(objCandidato);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void eliminarReclutado(String id) {
        try {
            ReclutamientoDAO objReclutamientoDao = new ReclutamientoDAO();
            objReclutamientoDao.eliminarReclutados(id);
            obtenerInfoReclutados(strEmpresaSeleccionada,strProcesoSeleccionado);
            mostrarAlerta(INFO, "Se elimino el registro correctamente", null, null, ".");
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

    public List<CandidatoBean> getListaReclutados() {
        return listaReclutados;
    }

    public void setListaReclutados(List<CandidatoBean> listaReclutados) {
        this.listaReclutados = listaReclutados;
    }

    public String getStrProcesoSeleccionado() {
        return strProcesoSeleccionado;
    }

    public void setStrProcesoSeleccionado(String strProcesoSeleccionado) {
        this.strProcesoSeleccionado = strProcesoSeleccionado;
    }

}
