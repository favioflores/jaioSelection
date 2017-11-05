package jaio.selection.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jaio.selection.entity.Empresa;
import jaio.selection.util.Utilitarios;

@ManagedBean(name = "crearEmpresaView")
@Scope("view")
@Component("crearEmpresaView")
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

	@SuppressWarnings("unused")
	private void limpiar() {
		razon = "";
		fecha = Utilitarios.obtieneFechaSistema();
		imagen = null;

	}

	public void subirImagen(FileUploadEvent event) {
		try {
			imagen = event.getFile();
			imagenPreview = new DefaultStreamedContent(new ByteArrayInputStream(imagen.getContents()),
					imagen.getContentType(), imagen.getFileName());
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void crearEmpresa() {

		try {

			Empresa objEmpresa = new Empresa();

			objEmpresa.setNombre(razon);
			objEmpresa.setFechaRegistro(new Date());
			objEmpresa.setImagen(imagen.getContents());
			objEmpresa.setUsuario(Utilitarios.obtenerUsuarioEntity());

			System.out.println("Grabar");
		} catch (Exception e) {
			log.error(e);
		}
	}

}
