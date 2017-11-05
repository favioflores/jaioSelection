package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.EvaluacionPerfilDAO;
import jaio.selection.entity.EvaluacionPerfil;

@Repository 
public class EvaluacionPerfilDAOImpl extends GenericDAO implements EvaluacionPerfilDAO, Serializable {

	private static final Log log = LogFactory.getLog(EvaluacionPerfilDAOImpl.class);

	public boolean crearEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(EvaluacionPerfil);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(EvaluacionPerfil);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public EvaluacionPerfil obtenerEvaluacionPerfil(Integer id) {

		Session session = getSession();
		EvaluacionPerfil EvaluacionPerfil = null;

		try {
			EvaluacionPerfil = session.get(EvaluacionPerfil.class, id);
			return EvaluacionPerfil;
		} catch (Exception e) {
			log.error(e);
		}

		return EvaluacionPerfil;
	}

	public boolean borrarEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil) {

		Session session = getSession();

		try {
			session.delete(EvaluacionPerfil);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<EvaluacionPerfil> obtenerEvaluacionPerfils() {

		List<EvaluacionPerfil> EvaluacionPerfils = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM EvaluacionPerfil");
			EvaluacionPerfils = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return EvaluacionPerfils;
	}

}
