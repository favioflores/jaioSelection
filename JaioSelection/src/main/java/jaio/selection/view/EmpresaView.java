package jaio.selection.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import jaio.selection.bean.EmpresaBean;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.orm.Empresa;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

@ManagedBean(name = "empresaView")
@ViewScoped
public class EmpresaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(EmpresaView.class);

    private static final long serialVersionUID = -1L;

    private List<EmpresaBean> lstEmpresas;
    private StreamedContent img;

    private String strPrioridad;

    public String getStrPrioridad() {
        return strPrioridad;
    }

    public void setStrPrioridad(String strPrioridad) {
        this.strPrioridad = strPrioridad;
    }

    public StreamedContent getImg() {
        return img;
    }

    public void setImg(StreamedContent img) {
        this.img = img;
    }

    public List<EmpresaBean> getLstEmpresas() {
        return lstEmpresas;
    }

    public void setLstEmpresas(List<EmpresaBean> lstEmpresas) {
        this.lstEmpresas = lstEmpresas;
    }

    @PostConstruct
    public void init() {
        limpiar();
        obtenerEmpresas();

    }

    public void limpiar() {
        lstEmpresas = new ArrayList<>();
        strPrioridad = Constantes.strVacio;
        img = null;
    }

    public void ponerPrioridad(EmpresaBean objEmpresaBean) {

        try {

            EmpresaDAO objEmpresaDAO = new EmpresaDAO();

            if (Utilitarios.noEsNuloOVacio(objEmpresaBean.getOrden())) {

                boolean flag = objEmpresaDAO.actualizaPrioridad(objEmpresaBean.getId(), Integer.parseInt(objEmpresaBean.getOrden()));

                if (flag) {
                    mostrarAlerta(INFO, "organizacion.orden.correcto", null, null);
                    limpiar();
                    obtenerEmpresas();
                } else {
                    mostrarAlerta(ERROR, "organizacion.orden.error", null, null);
                }
            } else {
                mostrarAlerta(ERROR, "organizacion.orden.error", null, null);
            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void quitarPrioridad(EmpresaBean objEmpresaBean) {

        try {

            EmpresaDAO objEmpresaDAO = new EmpresaDAO();

            boolean flag = objEmpresaDAO.actualizaPrioridad(objEmpresaBean.getId(), null);

            if (flag) {
                mostrarAlerta(INFO, "organizacion.orden.correcto", null, null);
                limpiar();
                obtenerEmpresas();
            } else {
                mostrarAlerta(ERROR, "organizacion.orden.error", null, null);
            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void obtenerEmpresas() {

        try {
            lstEmpresas = new ArrayList<>();

            EmpresaDAO objEmpresaDAO = new EmpresaDAO();

            for (Empresa objEmpresa : objEmpresaDAO.obtenerEmpresas()) {
                EmpresaBean objEmpresaBean = new EmpresaBean();
                objEmpresaBean.setId(objEmpresa.getId().toString());
                objEmpresaBean.setDesc(objEmpresa.getNombre());

                if (Utilitarios.noEsNuloOVacio(objEmpresa.getOrden())) {
                    objEmpresaBean.setOrden(objEmpresa.getOrden()+"");
                }

                if (Utilitarios.noEsNuloOVacio(objEmpresa.getImagen())) {
                    objEmpresaBean.setImagen(new DefaultStreamedContent(
                            new ByteArrayInputStream(objEmpresa.getImagen()), objEmpresa.getTipo_imagen()));

                    img = new DefaultStreamedContent(new ByteArrayInputStream(objEmpresa.getImagen()),
                            objEmpresa.getTipo_imagen());
                } else {
                    img = null;
                }

                lstEmpresas.add(objEmpresaBean);
            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void abrirEditorOrganigrama(String idEmpresa) {

        try {
            Utilitarios.ponerSession(idEmpresa, Constantes.SESSION_EMPRESA);
            FacesContext.getCurrentInstance().getExternalContext().redirect("organigrama.jsf");
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void eliminarEmpresa(String idEmpresa) {

        try {

            EmpresaDAO objEmpresaDAO = new EmpresaDAO();

            boolean flag = objEmpresaDAO.eliminaEmpresa(idEmpresa);

            if (flag) {
                mostrarAlerta(INFO, "organizacion.empresa.eliminada", null, null);
            } else {
                mostrarAlerta(ERROR, "error.inesperado", null, null);
            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

}
