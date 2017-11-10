package jaio.selection.view;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseView implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(BaseView.class);

	public static final Severity ERROR = FacesMessage.SEVERITY_ERROR;
	public static final Severity INFO = FacesMessage.SEVERITY_INFO;
	public static final Severity WARN = FacesMessage.SEVERITY_WARN;
	public static final Severity FATAL = FacesMessage.SEVERITY_FATAL;

    public String msg(String key) {

        String result = null;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
            result = bundle.getString(key);
        } catch (MissingResourceException e) {
        	log.error(e);
        }
        return result;
    }

    public void mostrarAlerta(Severity severity, String key){
        try {
        	FacesMessage message = new FacesMessage(severity, msg(key),null);
        	FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        	log.error(e);
        	mostrarError(e);
        }
    }

    public void mostrarError(Exception e){
        try {
        	FacesMessage message = new FacesMessage(FATAL, e.getMessage(),null);
        	FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
        	log.error(ex);
        }
    }

}
