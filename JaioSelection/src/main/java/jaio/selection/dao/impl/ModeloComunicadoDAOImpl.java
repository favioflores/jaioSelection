package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.ModeloComunicadoDAO;
import jaio.selection.entity.ModeloComunicado;

@Repository 
public class ModeloComunicadoDAOImpl extends GenericDAO implements ModeloComunicadoDAO, Serializable {

	private static final Log log = LogFactory.getLog(ModeloComunicadoDAOImpl.class);

	public boolean crearModeloComunicado(ModeloComunicado ModeloComunicado) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(ModeloComunicado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaModeloComunicado(ModeloComunicado ModeloComunicado) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(ModeloComunicado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public ModeloComunicado obtenerModeloComunicado(Integer id) {

		Session session = getSession();
		ModeloComunicado ModeloComunicado = null;

		try {
			ModeloComunicado = session.get(ModeloComunicado.class, id);
			return ModeloComunicado;
		} catch (Exception e) {
			log.error(e);
		}

		return ModeloComunicado;
	}

	public boolean borrarModeloComunicado(ModeloComunicado ModeloComunicado) {

		Session session = getSession();

		try {
			session.delete(ModeloComunicado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<ModeloComunicado> obtenerModeloComunicados() {

		List<ModeloComunicado> ModeloComunicados = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM ModeloComunicado");
			ModeloComunicados = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return ModeloComunicados;
	}

}
