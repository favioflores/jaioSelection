package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ElementoDAO;
import jaio.selection.entity.Elemento;

@Repository 
public class ElementoDAOImpl extends GenericDAO implements ElementoDAO, Serializable {

	private static final Log log = LogFactory.getLog(ElementoDAOImpl.class);

	public boolean crearElemento(Elemento Elemento) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(Elemento);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaElemento(Elemento Elemento) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(Elemento);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public Elemento obtenerElemento(Integer id) {

		Session session = getSession();
		Elemento Elemento = null;

		try {
			Elemento = session.get(Elemento.class, id);
			return Elemento;
		} catch (Exception e) {
			log.error(e);
		}

		return Elemento;
	}

	public boolean borrarElemento(Elemento Elemento) {

		Session session = getSession();

		try {
			session.delete(Elemento);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Elemento> obtenerElementos() {

		List<Elemento> Elementos = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Elemento");
			Elementos = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return Elementos;
	}

}
