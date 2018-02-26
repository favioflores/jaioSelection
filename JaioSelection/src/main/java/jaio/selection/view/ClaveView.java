package jaio.selection.view;

import jaio.selection.dao.NotificacionesDAO;
import jaio.selection.dao.UsuarioDAO;
import jaio.selection.orm.Destinatarios;
import jaio.selection.orm.NotificacionDetalle;
import jaio.selection.orm.Notificaciones;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ManagedBean(name = "claveView")
@RequestScoped
public class ClaveView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(ClaveView.class);

    private static final long serialVersionUID = -1L;

    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public void enviarClaveCorreo() {

        try {

            UsuarioDAO objUsuarioDAO = new UsuarioDAO();

            if (objUsuarioDAO.existeUsuario(correo)) {

                Utilitarios objUtilitarios = new Utilitarios();

                NotificacionesDAO objNotificacionesDAO = new NotificacionesDAO();

                Notificaciones objNotificaciones = new Notificaciones();
                objNotificaciones.setAsunto("Correo de notificacion clave");
                objNotificaciones.setEstado(Constantes.INT_ET_ESTADO_NOTIFICACION_REGISTRADO);
                objNotificaciones.setFechaRegistro(new Date());
                objNotificaciones.setTipo(Constantes.INT_ET_TIPO_CORREO_CLAVE);

                Destinatarios objDestinatarios = new Destinatarios(objNotificaciones, correo);
                objNotificaciones.getDestinatarioss().add(objDestinatarios);

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$USUARIO", correo.getBytes()));

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$FECHA_HORA_ENVIO", Utilitarios.formatearFecha(new Date(), Constantes.DDMMYYYY_HH24_MI_SS).getBytes()));

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$APPLICACION", "JAIO 360".getBytes()));

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$PARRAFO1", msg("correo.clave.parrafo1").getBytes()));

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$CLAVE", correo.getBytes()));

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$PARRAFO2", msg("correo.clave.parrafo2").getBytes()));

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$PARRAFO0", msg("correo.clave.parrafo0").getBytes()));

                objNotificaciones.getNotificaciondetalles().add(
                        new NotificacionDetalle(objNotificaciones, "$PARRAFO3", msg("correo.clave.parrafo3").getBytes()));

                if (objNotificacionesDAO.guardaNotificacion(objNotificaciones)) {
                    mostrarAlerta(INFO, "Envió correctamente", null, null);
                } else {
                    mostrarAlerta(FATAL, "error.inesperado", null, null);
                }

            }

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);
        }

    }

}
