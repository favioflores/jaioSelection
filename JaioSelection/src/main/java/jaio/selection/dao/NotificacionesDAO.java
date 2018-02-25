package jaio.selection.dao;

import jaio.selection.orm.Destinatarios;
import jaio.selection.orm.Notificaciones;
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

            Query query = session.createQuery("from Notificaciones where DATE_FORMAT(fechaRegistro, '%Y-%m-%d') <= DATE_FORMAT(?, '%Y-%m-%d') and estado = ? ");

            query.setDate(0, dtFechaEntrega);
            query.setInteger(1, Constantes.INT_ET_ESTADO_NOTIFICACION_PENDIENTE);
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

    public Integer guardaNotificacion(Notificaciones objNotificaciones) throws HibernateException {

        Integer id = 0;

        try {
            iniciaSession();
            id = (Integer) session.save(objNotificaciones);
            tx.commit();
        } catch (HibernateException he) {
            manejaException(log, he);
        } finally {
            session.close();
        }

        return id;
        
    }
}
