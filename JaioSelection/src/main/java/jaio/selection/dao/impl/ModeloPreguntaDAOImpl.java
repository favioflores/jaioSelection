package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloPreguntaDAO;
import jaio.selection.entity2.ModeloPregunta;

@Repository 
public class ModeloPreguntaDAOImpl extends GenericDAO implements ModeloPreguntaDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloPreguntaDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public boolean crearModeloPregunta(ModeloPregunta ModeloPregunta) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ModeloPregunta);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloPregunta(ModeloPregunta ModeloPregunta) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloPregunta);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloPregunta obtenerModeloPregunta(Integer id) {

		Session session = getSession();
		ModeloPregunta ModeloPregunta = null;

		try {
			ModeloPregunta = session.get(ModeloPregunta.class, id);
			return ModeloPregunta;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloPregunta;
	}

	public boolean borrarModeloPregunta(ModeloPregunta ModeloPregunta) {

		Session session = getSession();

		try {
			session.delete(ModeloPregunta);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloPregunta> obtenerModeloPreguntas() {

		List<ModeloPregunta> ModeloPreguntas = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloPregunta");
			ModeloPreguntas = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloPreguntas;
	}

}
