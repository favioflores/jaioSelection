package jaio.selection.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ComunicadoProcesoDAO;
import jaio.selection.entity2.ComunicadoProceso;

@Repository 
public class ComunicadoProcesoDAOImpl implements ComunicadoProcesoDAO {

	private static final Log log = LogFactory.getLog(ComunicadoProcesoDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public boolean crearComunicadoProceso(ComunicadoProceso ComunicadoProceso) {

		Session session = Session();

		session.beginTransaction();
		
		try {
			session.save(ComunicadoProceso);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaComunicadoProceso(ComunicadoProceso ComunicadoProceso) {

		Session session = Session();

		session.beginTransaction();

		try {
			session.update(ComunicadoProceso);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ComunicadoProceso obtenerComunicadoProceso(Integer id) {

		Session session = Session();
		ComunicadoProceso ComunicadoProceso = null;

		try {
			ComunicadoProceso = session.get(ComunicadoProceso.class, id);
			return ComunicadoProceso;
		} catch (Exception e) {
			log.error(e);
		}

		return ComunicadoProceso;
	}

	public boolean borrarComunicadoProceso(ComunicadoProceso ComunicadoProceso) {

		Session session = Session();

		try {
			session.delete(ComunicadoProceso);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ComunicadoProceso> obtenerComunicadoProcesos() {

		List<ComunicadoProceso> ComunicadoProcesos = null;

		Session session = Session();
		session.beginTransaction();

		try {

			Query query = Session().createQuery("FROM ComunicadoProceso");
			ComunicadoProcesos = query.list();

		} catch (Exception e) {
			log.error(e);
			Session().getTransaction().rollback();
		}
		return ComunicadoProcesos;
	}

	private Session Session() {
		
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			return session;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

}
