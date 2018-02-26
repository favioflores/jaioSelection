package jaio.selection.dao;

import jaio.selection.orm.Destinatarios;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class DestinatariosDAO extends HibernateUtil implements Serializable {

    private static Log log = LogFactory.getLog(DestinatariosDAO.class);

    public List obtieneDestinatarios(Integer intNotificacionPk, Session sesion) throws HibernateException {

        List<Destinatarios> lstDestinatarios;

        Query query = sesion.createQuery("select de from Destinatarios de where de.notificaciones.id = ? ");

        query.setInteger(0, intNotificacionPk);

        lstDestinatarios = query.list();

        return lstDestinatarios;

    }

}
