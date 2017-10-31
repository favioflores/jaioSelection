package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.InfoExperienciaDAO;
import jaio.selection.entity2.InfoExperiencia;

@Repository 
public class InfoExperienciaDAOImpl extends GenericDAO implements InfoExperienciaDAO, Serializable {

	private static final Log log = LogFactory.getLog(InfoExperienciaDAOImpl.class);

	public boolean crearInfoExperiencia(InfoExperiencia InfoExperiencia) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(InfoExperiencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaInfoExperiencia(InfoExperiencia InfoExperiencia) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(InfoExperiencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public InfoExperiencia obtenerInfoExperiencia(Integer id) {

		Session session = getSession();
		InfoExperiencia InfoExperiencia = null;

		try {
			InfoExperiencia = session.get(InfoExperiencia.class, id);
			return InfoExperiencia;
		} catch (Exception e) {
			log.error(e);
		}

		return InfoExperiencia;
	}

	public boolean borrarInfoExperiencia(InfoExperiencia InfoExperiencia) {

		Session session = getSession();

		try {
			session.delete(InfoExperiencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<InfoExperiencia> obtenerInfoExperiencias() {

		List<InfoExperiencia> InfoExperiencias = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM InfoExperiencia");
			InfoExperiencias = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return InfoExperiencias;
	}

}
