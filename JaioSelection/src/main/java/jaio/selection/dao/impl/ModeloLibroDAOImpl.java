package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloLibroDAO;
import jaio.selection.entity.ModeloLibro;

@Repository 
public class ModeloLibroDAOImpl extends GenericDAO implements ModeloLibroDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloLibroDAOImpl.class);

	public boolean crearModeloLibro(ModeloLibro ModeloLibro) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ModeloLibro);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloLibro(ModeloLibro ModeloLibro) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloLibro);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloLibro obtenerModeloLibro(Integer id) {

		Session session = getSession();
		ModeloLibro ModeloLibro = null;

		try {
			ModeloLibro = session.get(ModeloLibro.class, id);
			return ModeloLibro;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloLibro;
	}

	public boolean borrarModeloLibro(ModeloLibro ModeloLibro) {

		Session session = getSession();

		try {
			session.delete(ModeloLibro);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloLibro> obtenerModeloLibros() {

		List<ModeloLibro> ModeloLibros = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloLibro");
			ModeloLibros = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloLibros;
	}

}
