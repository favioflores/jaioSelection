package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.DefinicionDAO;
import jaio.selection.entity2.Definicion;

@Repository 
public class DefinicionDAOImpl extends GenericDAO implements DefinicionDAO, Serializable {

	private static final Log log = LogFactory.getLog(DefinicionDAOImpl.class);

	public boolean crearDefinicion(Definicion Definicion) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(Definicion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaDefinicion(Definicion Definicion) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(Definicion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public Definicion obtenerDefinicion(Integer id) {

		Session session = getSession();
		Definicion Definicion = null;

		try {
			Definicion = session.get(Definicion.class, id);
			return Definicion;
		} catch (Exception e) {
			log.error(e);
		}

		return Definicion;
	}

	public boolean borrarDefinicion(Definicion Definicion) {

		Session session = getSession();

		try {
			session.delete(Definicion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Definicion> obtenerDefinicions() {

		List<Definicion> Definicions = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Definicion");
			Definicions = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return Definicions;
	}

}
