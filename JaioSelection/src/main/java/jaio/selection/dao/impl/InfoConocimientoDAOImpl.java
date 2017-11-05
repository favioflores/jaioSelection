package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.InfoConocimientoDAO;
import jaio.selection.entity.InfoConocimiento;

@Repository 
public class InfoConocimientoDAOImpl extends GenericDAO implements InfoConocimientoDAO, Serializable {

	private static final Log log = LogFactory.getLog(InfoConocimientoDAOImpl.class);

	public boolean crearInfoConocimiento(InfoConocimiento InfoConocimiento) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(InfoConocimiento);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaInfoConocimiento(InfoConocimiento InfoConocimiento) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(InfoConocimiento);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public InfoConocimiento obtenerInfoConocimiento(Integer id) {

		Session session = getSession();
		InfoConocimiento InfoConocimiento = null;

		try {
			InfoConocimiento = session.get(InfoConocimiento.class, id);
			return InfoConocimiento;
		} catch (Exception e) {
			log.error(e);
		}

		return InfoConocimiento;
	}

	public boolean borrarInfoConocimiento(InfoConocimiento InfoConocimiento) {

		Session session = getSession();

		try {
			session.delete(InfoConocimiento);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<InfoConocimiento> obtenerInfoConocimientos() {

		List<InfoConocimiento> InfoConocimientos = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM InfoConocimiento");
			InfoConocimientos = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return InfoConocimientos;
	}

}
