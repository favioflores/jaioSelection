package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloCompetenciaSinonimoDAO;
import jaio.selection.entity2.ModeloCompetenciaSinonimo;

@Repository 
public class ModeloCompetenciaSinonimoDAOImpl extends GenericDAO implements ModeloCompetenciaSinonimoDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloCompetenciaSinonimoDAOImpl.class);

	public boolean crearModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ModeloCompetenciaSinonimo);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloCompetenciaSinonimo);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloCompetenciaSinonimo obtenerModeloCompetenciaSinonimo(Integer id) {

		Session session = getSession();
		ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo = null;

		try {
			ModeloCompetenciaSinonimo = session.get(ModeloCompetenciaSinonimo.class, id);
			return ModeloCompetenciaSinonimo;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloCompetenciaSinonimo;
	}

	public boolean borrarModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo) {

		Session session = getSession();

		try {
			session.delete(ModeloCompetenciaSinonimo);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloCompetenciaSinonimo> obtenerModeloCompetenciaSinonimos() {

		List<ModeloCompetenciaSinonimo> ModeloCompetenciaSinonimos = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloCompetenciaSinonimo");
			ModeloCompetenciaSinonimos = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloCompetenciaSinonimos;
	}

}
