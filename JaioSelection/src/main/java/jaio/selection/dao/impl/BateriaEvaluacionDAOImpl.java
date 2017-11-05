package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.BateriaEvaluacionDAO;
import jaio.selection.entity.BateriaEvaluacion;

@Repository 
public class BateriaEvaluacionDAOImpl extends GenericDAO implements BateriaEvaluacionDAO, Serializable {

	private static final Log log = LogFactory.getLog(BateriaEvaluacionDAOImpl.class);

	public boolean crearBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(BateriaEvaluacion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(BateriaEvaluacion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public BateriaEvaluacion obtenerBateriaEvaluacion(Integer id) {

		Session session = getSession();
		BateriaEvaluacion BateriaEvaluacion = null;

		try {
			BateriaEvaluacion = session.get(BateriaEvaluacion.class, id);
			return BateriaEvaluacion;
		} catch (Exception e) {
			log.error(e);
		}

		return BateriaEvaluacion;
	}

	public boolean borrarBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion) {

		Session session = getSession();

		try {
			session.delete(BateriaEvaluacion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<BateriaEvaluacion> obtenerBateriaEvaluacions() {

		List<BateriaEvaluacion> BateriaEvaluacions = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM BateriaEvaluacion");
			BateriaEvaluacions = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return BateriaEvaluacions;
	}

}
