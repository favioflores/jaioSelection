package jaio.selection.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import jaio.selection.dao.EmpresaDAO;
import jaio.selection.orm.Empresa;
import jaio.selection.util.Utilitarios;

@ManagedBean(name = "crearEmpresaView")
@SessionScoped
public class CrearEmpresaView extends BaseView implements Serializable {

	private static Log log = LogFactory.getLog(CrearEmpresaView.class);

	private static final long serialVersionUID = -1L;

	private String razon;
	private String fecha;
	private UploadedFile imagen;
	private StreamedContent imagenPreview;

	public StreamedContent getImagenPreview() {
		return imagenPreview;
	}

	public void setImagenPreview(StreamedContent imagenPreview) {
		this.imagenPreview = imagenPreview;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public UploadedFile getImagen() {
		return imagen;
	}

	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}

	@PostConstruct
	public void init(){
		limpiar();
	}

	public void limpiar() {
		razon = "";
		fecha = Utilitarios.obtieneFechaSistema();
		imagen = null;
		imagenPreview = null;

	}

	public void subirImagen(FileUploadEvent event) {
		try {

			imagen = event.getFile();
			imagenPreview = new DefaultStreamedContent(new ByteArrayInputStream(imagen.getContents()),
					imagen.getContentType(), imagen.getFileName());
		} catch (Exception e) {
			mostrarAlerta(FATAL, "error.inesperado", log, e);
		}
	}

	public void crearEmpresa() {

		try {

			EmpresaDAO objEmpresaDAO = new EmpresaDAO();

			Empresa empresa = new Empresa();
			empresa.setNombre(razon);
			empresa.setFechaRegistro(new Date());

			if(Utilitarios.noEsNuloOVacio(imagen)){
				empresa.setImagen(imagen.getContents());
				empresa.setTipo_imagen(imagen.getContentType());
			}
			empresa.setUsuario(Utilitarios.obtenerUsuarioEntity());

			objEmpresaDAO.grabar(empresa);

			limpiar();

			mostrarAlerta(INFO, "organizacion.empresa.creada", null, null);

		} catch (Exception e) {
			mostrarAlerta(FATAL, "error.inesperado", log, e);
		}
	}

}
