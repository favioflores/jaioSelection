package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.NotificacionesDAO;
import jaio.selection.entity.Notificaciones;

@Repository 
public class NotificacionesDAOImpl extends GenericDAO implements NotificacionesDAO, Serializable {

	private static final Log log = LogFactory.getLog(NotificacionesDAOImpl.class);

	public boolean crearNotificaciones(Notificaciones Notificaciones) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(Notificaciones);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaNotificaciones(Notificaciones Notificaciones) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(Notificaciones);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public Notificaciones obtenerNotificaciones(Integer id) {

		Session session = getSession();
		Notificaciones Notificaciones = null;

		try {
			Notificaciones = session.get(Notificaciones.class, id);
			return Notificaciones;
		} catch (Exception e) {
			log.error(e);
		}

		return Notificaciones;
	}

	public boolean borrarNotificaciones(Notificaciones Notificaciones) {

		Session session = getSession();

		try {
			session.delete(Notificaciones);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Notificaciones> obtenerNotificacioness() {

		List<Notificaciones> Notificacioness = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Notificaciones");
			Notificacioness = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return Notificacioness;
	}

}
