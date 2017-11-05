package jaio.selection.view;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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

}
