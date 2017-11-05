package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.CandidatoDAO;
import jaio.selection.entity.Candidato;

@Repository 
public class CandidatoDAOImpl extends GenericDAO implements CandidatoDAO, Serializable {

	private static final Log log = LogFactory.getLog(CandidatoDAOImpl.class);

	public boolean crearCandidato(Candidato Candidato) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(Candidato);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaCandidato(Candidato Candidato) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(Candidato);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public Candidato obtenerCandidato(Integer id) {

		Session session = getSession();
		Candidato Candidato = null;

		try {
			Candidato = session.get(Candidato.class, id);
			return Candidato;
		} catch (Exception e) {
			log.error(e);
		}

		return Candidato;
	}

	public boolean borrarCandidato(Candidato Candidato) {

		Session session = getSession();

		try {
			session.delete(Candidato);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Candidato> obtenerCandidatos() {

		List<Candidato> Candidatos = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Candidato");
			Candidatos = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return Candidatos;
	}

}
