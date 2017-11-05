package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloCompetenciaDAO;
import jaio.selection.entity.ModeloCompetencia;

@Repository 
public class ModeloCompetenciaDAOImpl extends GenericDAO implements ModeloCompetenciaDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloCompetenciaDAOImpl.class);

	public boolean crearModeloCompetencia(ModeloCompetencia ModeloCompetencia) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ModeloCompetencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloCompetencia(ModeloCompetencia ModeloCompetencia) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloCompetencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloCompetencia obtenerModeloCompetencia(Integer id) {

		Session session = getSession();
		ModeloCompetencia ModeloCompetencia = null;

		try {
			ModeloCompetencia = session.get(ModeloCompetencia.class, id);
			return ModeloCompetencia;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloCompetencia;
	}

	public boolean borrarModeloCompetencia(ModeloCompetencia ModeloCompetencia) {

		Session session = getSession();

		try {
			session.delete(ModeloCompetencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloCompetencia> obtenerModeloCompetencias() {

		List<ModeloCompetencia> ModeloCompetencias = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloCompetencia");
			ModeloCompetencias = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloCompetencias;
	}

}
