package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.BateriaPersonalizadaDAO;
import jaio.selection.entity2.BateriaPersonalizada;

@Repository 
public class BateriaPersonalizadaDAOImpl extends GenericDAO implements BateriaPersonalizadaDAO, Serializable {

	private static final Log log = LogFactory.getLog(BateriaPersonalizadaDAOImpl.class);

	public boolean crearBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(BateriaPersonalizada);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(BateriaPersonalizada);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public BateriaPersonalizada obtenerBateriaPersonalizada(Integer id) {

		Session session = getSession();
		BateriaPersonalizada BateriaPersonalizada = null;

		try {
			BateriaPersonalizada = session.get(BateriaPersonalizada.class, id);
			return BateriaPersonalizada;
		} catch (Exception e) {
			log.error(e);
		}

		return BateriaPersonalizada;
	}

	public boolean borrarBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada) {

		Session session = getSession();

		try {
			session.delete(BateriaPersonalizada);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<BateriaPersonalizada> obtenerBateriaPersonalizadas() {

		List<BateriaPersonalizada> BateriaPersonalizadas = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM BateriaPersonalizada");
			BateriaPersonalizadas = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return BateriaPersonalizadas;
	}

}
