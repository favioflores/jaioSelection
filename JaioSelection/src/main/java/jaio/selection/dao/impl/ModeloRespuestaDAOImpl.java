package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloRespuestaDAO;
import jaio.selection.entity2.ModeloRespuesta;

@Repository 
public class ModeloRespuestaDAOImpl extends GenericDAO implements ModeloRespuestaDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloRespuestaDAOImpl.class);

	public boolean crearModeloRespuesta(ModeloRespuesta ModeloRespuesta) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.save(ModeloRespuesta);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloRespuesta(ModeloRespuesta ModeloRespuesta) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloRespuesta);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloRespuesta obtenerModeloRespuesta(Integer id) {

		Session session = getSession();
		ModeloRespuesta ModeloRespuesta = null;

		try {
			ModeloRespuesta = session.get(ModeloRespuesta.class, id);
			return ModeloRespuesta;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloRespuesta;
	}

	public boolean borrarModeloRespuesta(ModeloRespuesta ModeloRespuesta) {

		Session session = getSession();

		try {
			session.delete(ModeloRespuesta);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloRespuesta> obtenerModeloRespuestas() {

		List<ModeloRespuesta> ModeloRespuestas = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloRespuesta");
			ModeloRespuestas = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloRespuestas;
	}

}
