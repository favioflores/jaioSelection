package jaio.selection.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jaio.selection.bean.UsuarioBean;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

@ManagedBean(name = "sidebarView")
@Scope("session")
@Component("sidebarView")
public class SidebarView extends BaseView implements Serializable {

	private static Log log = LogFactory.getLog(SidebarView.class);

	private static final long serialVersionUID = -1L;

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@PostConstruct
	public void init() {

		try {

			UsuarioBean usuario = (UsuarioBean) Utilitarios.obtenerSession(Constantes.SESSION_USUARIO);
			descripcion = usuario.getStrDescripcion();

		} catch (Exception e) {
			log.error(e);
		}

	}

}
