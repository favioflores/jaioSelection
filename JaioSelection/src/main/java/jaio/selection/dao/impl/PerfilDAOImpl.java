package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.PerfilDAO;
import jaio.selection.entity2.Perfil;

@Repository 
public class PerfilDAOImpl extends GenericDAO implements PerfilDAO, Serializable{

	private static final Log log = LogFactory.getLog(PerfilDAOImpl.class);

	public boolean crearPerfil(Perfil Perfil) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.save(Perfil);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaPerfil(Perfil Perfil) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(Perfil);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public Perfil obtenerPerfil(Integer id) {

		Session session = getSession();
		Perfil Perfil = null;

		try {
			Perfil = session.get(Perfil.class, id);
			return Perfil;
		} catch (Exception e) {
			log.error(e);
		}

		return Perfil;
	}

	public boolean borrarPerfil(Perfil Perfil) {

		Session session = getSession();

		try {
			session.delete(Perfil);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Perfil> obtenerPerfils() {

		List<Perfil> Perfils = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Perfil");
			Perfils = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return Perfils;
	}

}
