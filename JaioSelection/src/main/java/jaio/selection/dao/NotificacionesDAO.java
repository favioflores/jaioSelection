package jaio.selection.dao;

import jaio.selection.orm.Destinatarios;
import jaio.selection.orm.NotificacionDetalle;
import jaio.selection.orm.Notificaciones;
import jaio.selection.orm.Usuario;
import jaio.selection.util.Constantes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class NotificacionesDAO extends HibernateUtil implements Serializable {

    private static final Log log = LogFactory.getLog(NotificacionesDAO.class);

    public List obtieneNotificaciones(Date dtFechaEntrega, Integer intLimiteNotificaciones) throws HibernateException {

        List lstMails = new ArrayList();

        try {

            iniciaSession();

            Query query = session.createQuery("from Notificaciones where estado = ? ");

            query.setInteger(0, Constantes.INT_ET_ESTADO_NOTIFICACION_REGISTRADO);
            query.setMaxResults(intLimiteNotificaciones);

            List<Notificaciones> lstNotificaciones = query.list();

            if (!lstNotificaciones.isEmpty()) {

                Iterator itLstNotificaciones = lstNotificaciones.iterator();

                DestinatariosDAO objDestinatariosDAO = new DestinatariosDAO();

                Notificaciones objNotificaciones;

                while (itLstNotificaciones.hasNext()) {

                    objNotificaciones = (Notificaciones) itLstNotificaciones.next();

                    List<Destinatarios> lstDestinatarios = objDestinatariosDAO.obtieneDestinatarios(objNotificaciones.getId(), session);

                    if (!lstDestinatarios.isEmpty()) {

                        Object[] obj = {objNotificaciones, lstDestinatarios};

                        lstMails.add(obj);

                    }

                }

            }

        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }

        return lstMails;

    }

    public boolean actualizaNotificacion(Notificaciones objNotificaciones) throws HibernateException {

        boolean flag = true;
        try {
            iniciaSession();
            session.update(objNotificaciones);
            guardarCambios();

        } catch (HibernateException he) {
            flag = false;
            manejaException(log, he);
        } finally {
            cerrarSession();
        }

        return flag;
    }

    public boolean guardaNotificacion(Notificaciones objNotificaciones) {

        boolean flag = true;

        try {

            iniciaSession();

            session.save(objNotificaciones);

            guardarCambios();

        } catch (Exception he) {
            flag = false;
            manejaException(log, he);
        } finally {
            cerrarSession();
        }

        return flag;

    }

    public List<NotificacionDetalle> obtenerNotificacionDetalle(Notificaciones notificaciones) {

        iniciaSession();

        try {

            Query query = session.createQuery("From NotificacionDetalle n where n.notificaciones.id = ? ");

            query.setInteger(0, notificaciones.getId());

            return query.list();

        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }

        return new ArrayList<>();

    }

}
