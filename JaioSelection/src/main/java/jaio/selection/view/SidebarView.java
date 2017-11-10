package jaio.selection.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.bean.UsuarioBean;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

@ManagedBean(name = "sidebarView")
@SessionScoped
public class SidebarView extends BaseView implements Serializable {

	private static Log log = LogFactory.getLog(SidebarView.class);

	private static final long serialVersionUID = -1L;

	private String descripcion;
	private String avatar;



	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

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

			int hora = Integer.parseInt(Utilitarios.obtenerFechaHoraSistema(Constantes.HH24));

			if (hora < 12) {
				mostrarAlerta(INFO, "side.bienvenida.persona.dia");
			} else if (hora < 18) {
				mostrarAlerta(INFO, "side.bienvenida.persona.tarde");
			} else {
				mostrarAlerta(INFO, "side.bienvenida.persona.noche");
			}

			if(usuario.getStrSexo().equals(Constantes.MASCULINO)){
				avatar="avatar_hombre.png";
			}else if(usuario.getStrSexo().equals(Constantes.FEMENINO)){
				avatar="avatar_mujer.png";
			}else{
				avatar="avatar_otro.png";
			}

		} catch (Exception e) {
			log.error(e);
		}

	}

}
