package jaio.selection.view;

import jaio.selection.bean.AreaBean;
import jaio.selection.bean.BateriaPersonalizadaBean;
import jaio.selection.bean.ConvertirDatosBean;
import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.PerfilBean;
import jaio.selection.dao.AreaDAO;
import jaio.selection.dao.BateriaPersonalizadaDAO;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.PerfilDAO;
import jaio.selection.orm.Area;
import jaio.selection.orm.Empresa;
import jaio.selection.orm.Perfil;
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
    
    private List<EmpresaBean> lstEmpresas;
    private List<PerfilBean> lstPerfil;
    private List<AreaBean> lstArea;
    private String strEmpresaSeleccionada;
    private String strPerfilSeleccionado;
    private String strAreaSeleccionada;
    
    private List<BateriaPersonalizadaBean> lstBateriaPersonalizadaBeans;
    
    public List<AreaBean> getLstArea() {
        return lstArea;
    }
    
    public void setLstArea(List<AreaBean> lstArea) {
        this.lstArea = lstArea;
    }
    
    public String getStrAreaSeleccionada() {
        return strAreaSeleccionada;
    }
    
    public void setStrAreaSeleccionada(String strAreaSeleccionada) {
        this.strAreaSeleccionada = strAreaSeleccionada;
    }
    
    public String getStrPerfilSeleccionado() {
        return strPerfilSeleccionado;
    }
    
    public void setStrPerfilSeleccionado(String strPerfilSeleccionado) {
        this.strPerfilSeleccionado = strPerfilSeleccionado;
    }
    
    public String getStrEmpresaSeleccionada() {
        return strEmpresaSeleccionada;
    }
    
    public void setStrEmpresaSeleccionada(String strEmpresaSeleccionada) {
        this.strEmpresaSeleccionada = strEmpresaSeleccionada;
    }
    
    public List<PerfilBean> getLstPerfil() {
        return lstPerfil;
    }
    
    public void setLstPerfil(List<PerfilBean> lstPerfil) {
        this.lstPerfil = lstPerfil;
    }
    
    public List<EmpresaBean> getLstEmpresas() {
        return lstEmpresas;
    }
    
    public void setLstEmpresas(List<EmpresaBean> lstEmpresas) {
        this.lstEmpresas = lstEmpresas;
    }
    
    public List<BateriaPersonalizadaBean> getLstBateriaPersonalizadaBeans() {
        return lstBateriaPersonalizadaBeans;
    }
    
    public void setLstBateriaPersonalizadaBeans(List<BateriaPersonalizadaBean> lstBateriaPersonalizadaBeans) {
        this.lstBateriaPersonalizadaBeans = lstBateriaPersonalizadaBeans;
    }
    
    @PostConstruct
    public void init() {
        limpiar();
    }
    
    public void limpiar() {
        lstEmpresas = new ArrayList<>();
        lstArea = new ArrayList<>();
        lstPerfil = new ArrayList<>();
        strEmpresaSeleccionada = null;
        poblarEmpresas();
        
    }
    
    /**
     * metodo para cargar los procesos por empresa
     */
    public void cargarBateriaPersonalizada() {
        lstBateriaPersonalizadaBeans = new ArrayList<BateriaPersonalizadaBean>();
        if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada)){
            BateriaPersonalizadaDAO objBateriaPersonalizadaDAO = new BateriaPersonalizadaDAO();
            List lstBaterias = objBateriaPersonalizadaDAO.obtenerProcesosRegistrados(strEmpresaSeleccionada);
            Iterator it = lstBaterias.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                BateriaPersonalizadaBean objBateriaPersonalizadaBean = new BateriaPersonalizadaBean();
                objBateriaPersonalizadaBean.setNombre(obj[0].toString());
                objBateriaPersonalizadaBean.setFechaCreacion(obj[1].toString());
                objBateriaPersonalizadaBean.setNombrePerfil(obj[2].toString());
                lstBateriaPersonalizadaBeans.add(objBateriaPersonalizadaBean);
            }
        }
    }

    /**
     * metodo que trae la empresa seleccionada
     */
    public void seleccionaEmpresa() {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada) && !strEmpresaSeleccionada.equals("-1")) {
                EmpresaDAO objEmpresaDAO = new EmpresaDAO();
                Empresa objEmpresa = objEmpresaDAO.obtenerEmpresa(strEmpresaSeleccionada);
                poblarArea();
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

    /**
     * metodo que trae la area seleccionada
     */
    public void seleccionaArea() {
        try {
            if (Utilitarios.noEsNuloOVacio(strAreaSeleccionada)) {
                if (!strAreaSeleccionada.equals("-1")) {
                    AreaDAO objAreaDAO = new AreaDAO();
                    Area objArea = objAreaDAO.obtenerArea(strAreaSeleccionada);
                    mostrarAlerta(INFO, "proceso.seleccion.area", null, null, objArea.getDescripcion());
                    poblarPerfil();
                }
            } else {
                mostrarAlerta(ERROR, "error.inesperado", null, null);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    /**
     * metodo que trae las areas por empresa
     */
    public void poblarArea() {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada) && !strEmpresaSeleccionada.equals("-1")) {
                lstArea = new ArrayList<>();
                AreaDAO objAreaDAO = new AreaDAO();
                List<Area> lstAreas = objAreaDAO.obtenerAreasXEmpresa(strEmpresaSeleccionada);
                
                for (Area objArea : lstAreas) {
                    AreaBean objAreaBean = new AreaBean();
                    objAreaBean.setId(objArea.getId().toString());
                    objAreaBean.setDescripcion(objArea.getDescripcion());
                    lstArea.add(objAreaBean);
                };
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }
    
    public void seleccionaPerfil() {
        try {
            if (Utilitarios.noEsNuloOVacio(strPerfilSeleccionado)) {
                PerfilDAO objPerfilDao = new PerfilDAO();
                Perfil objPerfil = objPerfilDao.obtenerPerfil(strPerfilSeleccionado);
                mostrarAlerta(INFO, "proceso.seleccion.perfil", null, null, objPerfil.getNombre());
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    /**
     * metodo que trae los perfiles por area
     */
    public void poblarPerfil() {
        try {
            if (Utilitarios.noEsNuloOVacio(strAreaSeleccionada) && !strAreaSeleccionada.equals("-1")) {
                lstPerfil = new ArrayList<>();
                PerfilDAO objPerfilDAO = new PerfilDAO();
                List<Perfil> lstPerfiles = objPerfilDAO.obtenerPerfilesXArea(strAreaSeleccionada);
                for (Perfil objPerfil : lstPerfiles) {
                    PerfilBean objPerfilBean = new PerfilBean();
                    objPerfilBean.setId(objPerfil.getId().toString());
                    objPerfilBean.setDescripcion(objPerfil.getNombre());
                    lstPerfil.add(objPerfilBean);
                };
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    /**
     * metodo para ir a la pantalla Crear Bateria
     *
     * @param idEmpresa <code>String</code>
     */
    public void abrirCrearBateria(String idEmpresa) {
        try {
            Utilitarios.ponerSession(idEmpresa, Constantes.SESSION_EMPRESA);
            FacesContext.getCurrentInstance().getExternalContext().redirect("crearBateria.jsf");
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }
}
