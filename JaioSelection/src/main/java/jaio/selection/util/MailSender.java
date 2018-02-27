package jaio.selection.util;

import jaio.selection.dao.NotificacionesDAO;
import jaio.selection.orm.Destinatarios;
import jaio.selection.orm.NotificacionDetalle;
import jaio.selection.orm.Notificaciones;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class MailSender extends Thread implements Serializable {

    private static List lstCorreos = new ArrayList();

    private Log log = LogFactory.getLog(MailSender.class);

    private static NotificacionesDAO objNotificacionesDAO;

    private static EHCacheManager cache;

    private static Transport transport;

    private static Session session;

    String strDominio;
    String strPuerto;
    String strUsuario;
    String strContraseña;

    public MailSender() {
        objNotificacionesDAO = new NotificacionesDAO();
        cache = new EHCacheManager();
    }

    @Override
    public void run() {

        while (true) {

            try {

                if (lstCorreos.isEmpty()) {

                    log.debug("Buscando mails");

                    /* Busca nuevos correos */
                    lstCorreos = objNotificacionesDAO.obtieneNotificaciones(new Date(), 30);

                    if (!lstCorreos.isEmpty()) {

                        log.debug("Notificaciones encontradas " + lstCorreos.size());

                        realizarEnvios(lstCorreos);

                        lstCorreos.clear();

                    }
                    this.sleep(30000);

                } else {

                    log.debug("Ocurrio un error");

                    this.stop();

                    /* Enviar Alerta por BD */
                }

            } catch (InterruptedException ex) {
                log.error(ex);
            }
        }

    }

    private void realizarEnvios(List lstCorreos) throws InterruptedException {

        Iterator itLstCorreos = lstCorreos.iterator();

        boolean boEnvioCorrecto = true;

        while (itLstCorreos.hasNext()) {

            if (transport == null || !transport.isConnected()) {
                conectarCorreoExterno();
                break;
            }

            if (transport.isConnected()) {

                Object[] obj = (Object[]) itLstCorreos.next();

                Notificaciones objNotificaciones = (Notificaciones) obj[0];

                boEnvioCorrecto = enviaNotificaciones(objNotificaciones, (List<Destinatarios>) obj[1]);

                if (boEnvioCorrecto) {
                    objNotificaciones.setEstado(Constantes.INT_ET_ESTADO_NOTIFICACION_ENVIADO);
                    objNotificaciones.setFechaEnvio(new Date());
                    objNotificacionesDAO.actualizaNotificacion(objNotificaciones);
                } else {
                    this.sleep(30000);
                }

            }

        }

    }

    public boolean enviaNotificaciones(Notificaciones objNotificaciones, List<Destinatarios> lstDestinatarios) {

        boolean flag = true;

        try {

            String mensaje = prepararContenido(objNotificaciones);

            if (Utilitarios.noEsNuloOVacio(mensaje)) {

                MimeMessage message = new MimeMessage(session);

                message.setSubject(objNotificaciones.getAsunto(), "UTF-8");

                message.setFrom(new InternetAddress(strUsuario, "Jaio Notificaciones"));

                Iterator itLstDestinatarios = lstDestinatarios.iterator();

                while (itLstDestinatarios.hasNext()) {
                    Destinatarios objDestinatarios = (Destinatarios) itLstDestinatarios.next();
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(objDestinatarios.getCorreo()));
                }

                // COVER WRAP
                MimeBodyPart wrap = new MimeBodyPart();

                // ALTERNATIVE TEXT/HTML CONTENT
                MimeMultipart cover = new MimeMultipart("alternative");

                MimeBodyPart html = new MimeBodyPart();

                html.setHeader("Content-Type", "text/plain; charset=\"utf-8\"");
                html.setHeader("Content-Transfer-Encoding", "quoted-printable");
                html.setContent(mensaje, "text/html; charset=utf-8");

                cover.addBodyPart(html);

                wrap.setHeader("Content-Type", "text/plain; charset=\"utf-8\"");
                wrap.setHeader("Content-Transfer-Encoding", "quoted-printable");
                wrap.setContent(cover, "text/plain; charset=utf-8");

                MimeMultipart content = new MimeMultipart("related");
                message.setContent(content, "UTF-8");
                content.addBodyPart(wrap);

                /*
            if (objNotificaciones.getNoAdjunto() != null) {
                BodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(objNotificaciones.getNoAdjunto())));
                adjunto.setFileName(adjunto.getDataHandler().getName());
                cover.addBodyPart(adjunto);
            }
                 */
                // SEND THE MESSAGE
                message.setSentDate(new Date());

                this.transport.sendMessage(message, message.getAllRecipients());

                return true;

            } else {
                flag = false;
            }

        } catch (Exception e) {
            flag = false;
            log.error(e);
        }

        return flag;

    }

    private String prepararContenido(Notificaciones objNotificaciones) {

        boolean flag = true;

        try {

            Properties props = new Properties();
            props.setProperty("resource.loader", "class");
            props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            
            VelocityEngine ve = new VelocityEngine();
            
            ve.init(props);

            Template t = new Template();
            VelocityContext context = new VelocityContext();

            if (objNotificaciones.getTipo() == Constantes.INT_ET_TIPO_CORREO_CLAVE) {
                t = ve.getTemplate("templates/TemplateRecuperaClave.vm");
                context = new VelocityContext();

                for (NotificacionDetalle objNotificacionDetalle : objNotificacionesDAO.obtenerNotificacionDetalle(objNotificaciones)) {
                    context.put(objNotificacionDetalle.getParametro(), new String(objNotificacionDetalle.getContenido()));
                }

            } else {

            }

            StringWriter out = new StringWriter();
            t.merge(context, out);

            return out.toString();

        } catch (Exception e) {
            log.error(e);
            return "";
        }

    }

    private void conectarCorreoExterno() {

        try {
            /*
            strDominio = cache.obtenerValor1Elemento(Constantes.INT_ET_SENDER_DOMINIO).trim();
            strPuerto = cache.obtenerValor1Elemento(Constantes.INT_ET_SENDER_PUERTO_ENVIO).trim();
            strUsuario = cache.obtenerValor1Elemento(Constantes.INT_ET_SENDER_USUARIO).trim();
            strContraseña = cache.obtenerValor1Elemento(Constantes.INT_ET_SENDER_CONTRASEÑA).trim();
             */

            strDominio = "smtp.gmail.com";
            strPuerto = "465";
            strUsuario = "jaio.mailsender@gmail.com";
            strContraseña = "i410400Frozen4play";

            Properties props = new Properties();
            props.put("mail.smtp.host", strDominio);
            props.put("mail.smtp.socketFactory.port", strPuerto);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", strPuerto);

            Session se = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(strUsuario, strContraseña);
                }
            });
            //se.setDebug(true);

            Transport tr = se.getTransport("smtp");

            tr.connect(strDominio, Integer.parseInt(strPuerto), strUsuario, strContraseña);

            log.debug("Conectado : " + tr.isConnected());

            this.session = se;
            this.transport = tr;

        } catch (NumberFormatException | MessagingException e) {
            log.error(e);
        }

    }

}
