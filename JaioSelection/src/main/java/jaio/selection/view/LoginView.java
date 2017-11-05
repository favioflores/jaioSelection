package jaio.selection.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jaio.selection.bean.UsuarioBean;
import jaio.selection.entity.Usuario;
import jaio.selection.service.UsuarioService;
import jaio.selection.util.Utilitarios;

@ManagedBean(name = "loginView")
@Scope("request")
@Component("loginView")
public class LoginView extends BaseView implements Serializable {

	@Autowired
	private UsuarioService usuarioService;

	private static Log log = LogFactory.getLog(LoginView.class);

	private static final long serialVersionUID = -1L;


	private String usuario;
	private String contrasena;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getcontrasena() {
		return contrasena;
	}

	public void setcontrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void iniciarSesion() throws Exception {

		FacesMessage message = null;

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String captha = params.get("g-recaptcha-response");

		boolean blValido = false;

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String ipAddress = request.getRemoteAddr();

		if (!ipAddress.equals("127.0.0.1") && !ipAddress.equals("0:0:0:0:0:0:0:1")) {
			//if (validaConexionGoogle()) {
			if (true) {
				if (captchaInvalido(captha)) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de inicio de sesion",
							"Captcha invalido");
					FacesContext.getCurrentInstance().addMessage(null, message);
				} else {
					blValido = true;
				}
			} else {
				blValido = true;
			}
		} else {
			blValido = true;
		}

		if (blValido) {

			try {

				boolean flag = true;

				if (usuario.isEmpty() || contrasena.isEmpty()) {

					flag = false;

				} else {

					Usuario objUsuario = usuarioService.obtenerUsuario(usuario, contrasena);

					if(Utilitarios.esNuloOVacio(objUsuario)){
						flag = false;
					}else{
						Utilitarios.ponerSession(new UsuarioBean(objUsuario),"userSession");
					}

				}

				if(!flag){
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg("login.usuario.contrasena.incorrecto"),null);
				}else{
			        FacesContext.getCurrentInstance().getExternalContext().redirect("bienvenida.jsf");
				}

				usuario = "";
				contrasena = "";

			} catch (Exception ex) {
				log.error(ex);
			}

			if (message != null) {
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	private boolean captchaInvalido(String str) throws Exception {

		String url = "https://www.google.com/recaptcha/api/siteverify?secret=6LeGgf4SAAAAAOfMo7YjjuDgNdRwsVG3HE5z2hp8&response="
				+ str;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;

		String rpta = "";

		while ((inputLine = in.readLine()) != null) {
			rpta += inputLine;
		}

		in.close();

		if (rpta.indexOf("true") > 0) {
			return false;
		}

		return true;

	}

	private void validaConexionGoogle(){

		try {

			String url = "https://www.google.com/";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.connect();

		} catch (Exception e) {
			log.error(e);
		}

	}

}
