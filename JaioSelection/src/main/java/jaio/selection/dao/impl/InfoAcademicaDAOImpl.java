package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.InfoAcademicaDAO;
import jaio.selection.entity2.InfoAcademica;

@Repository 
public class InfoAcademicaDAOImpl extends GenericDAO implements InfoAcademicaDAO, Serializable {

	private static final Log log = LogFactory.getLog(InfoAcademicaDAOImpl.class);

	public boolean crearInfoAcademica(InfoAcademica InfoAcademica) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(InfoAcademica);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaInfoAcademica(InfoAcademica InfoAcademica) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(InfoAcademica);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public InfoAcademica obtenerInfoAcademica(Integer id) {

		Session session = getSession();
		InfoAcademica InfoAcademica = null;

		try {
			InfoAcademica = session.get(InfoAcademica.class, id);
			return InfoAcademica;
		} catch (Exception e) {
			log.error(e);
		}

		return InfoAcademica;
	}

	public boolean borrarInfoAcademica(InfoAcademica InfoAcademica) {

		Session session = getSession();

		try {
			session.delete(InfoAcademica);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<InfoAcademica> obtenerInfoAcademicas() {

		List<InfoAcademica> InfoAcademicas = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM InfoAcademica");
			InfoAcademicas = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return InfoAcademicas;
	}

}
