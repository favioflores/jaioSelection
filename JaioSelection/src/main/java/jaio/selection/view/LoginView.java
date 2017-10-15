package jaio.selection.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.seletion.bean.UsuarioBean;

@ManagedBean(name = "loginView")
@ViewScoped
public class LoginView implements Serializable {

	private static Log log = LogFactory.getLog(LoginView.class);

	private static final long serialVersionUID = -1L;

	private String usuario;

	private String contraseña;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void abandonarSistema(ActionEvent event) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saliendo", "Muchas gracias por su visita");

		FacesContext.getCurrentInstance().addMessage(null, message);

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
			if (validaConexionGoogle()) {
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

				if (usuario.isEmpty() || contraseña.isEmpty()) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y contraseña requeridos",null);
				} else {
					if (usuario.equals("admin") && contraseña.equals("admin")) {
	                    FacesContext.getCurrentInstance().getExternalContext().redirect("bienvenida.jsf");
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y contraseña incorrectos",null);
						usuario = "";
						contraseña = "";
					}
				}

			} catch (Exception ex) {
				log.error(ex);
			}

			if (message != null) {
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	/*
	 * public void cerrarSesion(){
	 * 
	 * try {
	 * 
	 * HttpSession session = (HttpSession)
	 * FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	 * usuarioInfo = (UsuarioInfo) session.getAttribute("usuarioInfo");
	 * session.invalidate(); registraHistorialAcceso(usuarioInfo.getIntUsuarioPk(),
	 * true, null, new Date(), usuarioInfo.getIntHistorialPk());
	 * FacesContext.getCurrentInstance().getExternalContext().redirect("iniciar.jsf"
	 * );
	 * 
	 * } catch (IOException ex) { log.error(ex); }
	 * 
	 * }
	 */
	public UsuarioBean obtenerUsuarioInfo() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		return (UsuarioBean) session.getAttribute("usuarioInfo");

	}

	public void ingresaSistema() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
		} catch (IOException ex) {
			log.error(ex);
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

	private boolean validaConexionGoogle() throws Exception {

		try {

			String url = "https://www.google.com/";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.connect();

		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;

	}
	/*
	 * public void timeout() throws IOException {
	 * 
	 * HttpSession session = (HttpSession)
	 * FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	 * usuarioInfo = (UsuarioInfo) session.getAttribute("usuarioInfo");
	 * session.invalidate(); registraHistorialAcceso(usuarioInfo.getIntUsuarioPk(),
	 * true, null, new Date(), usuarioInfo.getIntHistorialPk());
	 * //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	 * 
	 * }
	 */
}
