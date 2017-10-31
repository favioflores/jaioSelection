package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloEvaluacionDAO;
import jaio.selection.entity2.ModeloEvaluacion;

@Repository 
public class ModeloEvaluacionDAOImpl extends GenericDAO implements ModeloEvaluacionDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloEvaluacionDAOImpl.class);

	public boolean crearModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ModeloEvaluacion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloEvaluacion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloEvaluacion obtenerModeloEvaluacion(Integer id) {

		Session session = getSession();
		ModeloEvaluacion ModeloEvaluacion = null;

		try {
			ModeloEvaluacion = session.get(ModeloEvaluacion.class, id);
			return ModeloEvaluacion;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloEvaluacion;
	}

	public boolean borrarModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion) {

		Session session = getSession();

		try {
			session.delete(ModeloEvaluacion);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloEvaluacion> obtenerModeloEvaluacions() {

		List<ModeloEvaluacion> ModeloEvaluacions = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloEvaluacion");
			ModeloEvaluacions = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloEvaluacions;
	}

}
