package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ProcesoSeleccionDAO;
import jaio.selection.entity2.ProcesoSeleccion;

@Repository 
public class ProcesoSeleccionDAOImpl extends GenericDAO implements ProcesoSeleccionDAO, Serializable {

	private static final Log log = LogFactory.getLog(ProcesoSeleccionDAOImpl.class);

	public boolean crearProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ProcesoSeleccion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ProcesoSeleccion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ProcesoSeleccion obtenerProcesoSeleccion(Integer id) {

		Session session = getSession();
		ProcesoSeleccion ProcesoSeleccion = null;

		try {
			ProcesoSeleccion = session.get(ProcesoSeleccion.class, id);
			return ProcesoSeleccion;
		} catch (Exception e) {
			log.error(e);
		}

		return ProcesoSeleccion;
	}

	public boolean borrarProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion) {

		Session session = getSession();

		try {
			session.delete(ProcesoSeleccion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ProcesoSeleccion> obtenerProcesoSeleccions() {

		List<ProcesoSeleccion> ProcesoSeleccions = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ProcesoSeleccion");
			ProcesoSeleccions = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ProcesoSeleccions;
	}

}