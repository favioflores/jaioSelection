package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloEvaluacionXCompetenciaDAO;
import jaio.selection.entity.ModeloEvaluacionXCompetencia;

@Repository 
public class ModeloEvaluacionXCompetenciaDAOImpl extends GenericDAO implements ModeloEvaluacionXCompetenciaDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloEvaluacionXCompetenciaDAOImpl.class);

	public boolean crearModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ModeloEvaluacionXCompetencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloEvaluacionXCompetencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloEvaluacionXCompetencia obtenerModeloEvaluacionXCompetencia(Integer id) {

		Session session = getSession();
		ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia = null;

		try {
			ModeloEvaluacionXCompetencia = session.get(ModeloEvaluacionXCompetencia.class, id);
			return ModeloEvaluacionXCompetencia;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloEvaluacionXCompetencia;
	}

	public boolean borrarModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia) {

		Session session = getSession();

		try {
			session.delete(ModeloEvaluacionXCompetencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloEvaluacionXCompetencia> obtenerModeloEvaluacionXCompetencias() {

		List<ModeloEvaluacionXCompetencia> ModeloEvaluacionXCompetencias = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloEvaluacionXCompetencia");
			ModeloEvaluacionXCompetencias = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloEvaluacionXCompetencias;
	}

}
