package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.InfoReferenciaDAO;
import jaio.selection.entity.InfoReferencia;

@Repository 
public class InfoReferenciaDAOImpl extends GenericDAO implements InfoReferenciaDAO, Serializable {

	private static final Log log = LogFactory.getLog(InfoReferenciaDAOImpl.class);

	public boolean crearInfoReferencia(InfoReferencia InfoReferencia) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(InfoReferencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaInfoReferencia(InfoReferencia InfoReferencia) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(InfoReferencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public InfoReferencia obtenerInfoReferencia(Integer id) {

		Session session = getSession();
		InfoReferencia InfoReferencia = null;

		try {
			InfoReferencia = session.get(InfoReferencia.class, id);
			return InfoReferencia;
		} catch (Exception e) {
			log.error(e);
		}

		return InfoReferencia;
	}

	public boolean borrarInfoReferencia(InfoReferencia InfoReferencia) {

		Session session = getSession();

		try {
			session.delete(InfoReferencia);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<InfoReferencia> obtenerInfoReferencias() {

		List<InfoReferencia> InfoReferencias = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM InfoReferencia");
			InfoReferencias = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return InfoReferencias;
	}

}
