package jaio.selection.view;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.util.Utilitarios;

public abstract class BaseView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Log logBase = LogFactory.getLog(BaseView.class);

    public static final Severity ERROR = FacesMessage.SEVERITY_ERROR;
    public static final Severity INFO = FacesMessage.SEVERITY_INFO;
    public static final Severity WARN = FacesMessage.SEVERITY_WARN;
    public static final Severity FATAL = FacesMessage.SEVERITY_FATAL;

    public String msg(String key, Object... params) {

        String result = null;

        try {

            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
            result = bundle.getString(key);

            if (Utilitarios.noEsNuloOVacio(params)) {
                MessageFormat mf = new MessageFormat(bundle.getString(key));
                result = mf.format(params, new StringBuffer(), null).toString();
            }

        } catch (MissingResourceException e) {
            logBase.error(e);
        }
        return result;
    }

    public boolean existeMsg(String key) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        } catch (MissingResourceException e) {
            return false;
        }
        return true;
    }

    public void mostrarAlerta(Severity severity, String key, Log log, Exception e, Object... params) {
        try {
            if (existeMsg(key)) {
                FacesMessage message = new FacesMessage(severity, msg(key, params), null);
                FacesContext.getCurrentInstance().addMessage(null, message);

                if (Utilitarios.noEsNuloOVacio(e)) {
                    log.error(e);
                }
            } else {
                FacesMessage message = new FacesMessage(severity, key, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            logBase.error(ex);
            mostrarError(ex);
        }
    }

    private void mostrarError(Exception e) {
        try {
            FacesMessage message = new FacesMessage(FATAL, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            logBase.error(ex);
        }
    }

}
