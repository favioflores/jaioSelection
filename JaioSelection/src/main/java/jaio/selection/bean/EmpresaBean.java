package jaio.selection.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import jaio.selection.dao.EmpresaDAO;
import jaio.selection.orm.Empresa;
import jaio.selection.util.Utilitarios;
import jaio.selection.view.EmpresaView;

public class EmpresaBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(EmpresaBean.class);

	private String id;
	private String desc;
	private StreamedContent imagen;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public StreamedContent getImagen() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {

			try {
				String empId = context.getExternalContext().getRequestParameterMap().get("empId");

				if (Utilitarios.noEsNuloOVacio(empId)) {
					Empresa objEmpresa = new EmpresaDAO().obtenerEmpresa(empId);

					imagen = new DefaultStreamedContent(new ByteArrayInputStream(objEmpresa.getImagen()),
							objEmpresa.getTipo_imagen());
					return imagen;
				}

			} catch (Exception e) {
				log.error(e);
			}

			return null;

		}

	}

	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

}
