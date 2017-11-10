package jaio.selection.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.UsuarioBean;
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

		obtenerEmpresas();

	}

	public void obtenerEmpresas() {

		try {
			lstEmpresas = new ArrayList<EmpresaBean>();

			EmpresaDAO objEmpresaDAO = new EmpresaDAO();

			for (Empresa objEmpresa : objEmpresaDAO.obtenerEmpresas()) {
				EmpresaBean objEmpresaBean = new EmpresaBean();
				objEmpresaBean.setId(objEmpresa.getId().toString());
				objEmpresaBean.setDesc(objEmpresa.getNombre());

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
			log.error(e);
		}

	}

	public void abrirEditorOrganigrama(String idEmpresa) {

		try {
			Utilitarios.ponerSession(idEmpresa,Constantes.SESSION_EMPRESA);
			FacesContext.getCurrentInstance().getExternalContext().redirect("organigrama.jsf");
		} catch (Exception e) {
			log.error(e);
		}
	}

}
