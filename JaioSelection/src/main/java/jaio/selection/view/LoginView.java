package jaio.selection.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.bean.UsuarioBean;
import jaio.selection.dao.UsuarioDAO;
import jaio.selection.orm.Usuario;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginView")
@RequestScoped
public class LoginView extends BaseView implements Serializable {

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

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String captha = params.get("g-recaptcha-response");

        boolean blValido = false;

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String ipAddress = request.getRemoteAddr();

        if (!ipAddress.equals("127.0.0.1") && !ipAddress.equals("0:0:0:0:0:0:0:1")) {
            if (validaConexionGoogle()) {
                //if (true) {
                if (!captchaInvalido(captha)) {
                    blValido = true;
                } else {
                    blValido = false;
                }
            } else {
                blValido = false;
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

                     UsuarioDAO objUsuarioDAO = new UsuarioDAO();
                    Usuario objUsuario = objUsuarioDAO.obtenerUsuario(usuario, contrasena);

                    if (Utilitarios.esNuloOVacio(objUsuario)) {
                        flag = false;
                    } else {
                        Utilitarios.ponerSession(new UsuarioBean(objUsuario), Constantes.SESSION_USUARIO);
                    }

                }

                if (!flag) {
                    mostrarAlerta(ERROR, "login.usuario.contrasena.incorrecto", null, null);
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("bienvenida.jsf");
                }

                usuario = "";
                contrasena = "";

            } catch (Exception ex) {
                mostrarAlerta(FATAL, "error.inesperado", log, ex);
            }

        }
    }

    private boolean validaConexionGoogle() throws Exception {

        try {

            String url = "https://www.google.com/";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.connect();

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
            return false;
        }
        return true;

    }

    private boolean captchaInvalido(String str) throws Exception {

        try {

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
            } else {
                mostrarAlerta(ERROR, "captcha.invalido", null, null);
            }

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);

        }

        return true;

    }

    public void terminarSesion() {

        try {

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.getAttribute(Constantes.SESSION_USUARIO);
            session.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("iniciar.jsf");

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);
        }

    }

}
