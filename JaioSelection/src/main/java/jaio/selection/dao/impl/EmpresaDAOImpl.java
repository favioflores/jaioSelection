package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.EmpresaDAO;
import jaio.selection.entity2.Empresa;

@Repository 
public class EmpresaDAOImpl extends GenericDAO implements EmpresaDAO, Serializable {

	private static final Log log = LogFactory.getLog(EmpresaDAOImpl.class);

	public boolean crearEmpresa(Empresa Empresa) {

		Session session = getSession();

		session.beginTransaction();
		
		try {
			session.save(Empresa);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaEmpresa(Empresa Empresa) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(Empresa);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public Empresa obtenerEmpresa(Integer id) {

		Session session = getSession();
		Empresa Empresa = null;

		try {
			Empresa = session.get(Empresa.class, id);
			return Empresa;
		} catch (Exception e) {
			log.error(e);
		}

		return Empresa;
	}

	public boolean borrarEmpresa(Empresa Empresa) {

		Session session = getSession();

		try {
			session.delete(Empresa);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Empresa> obtenerEmpresas() {

		List<Empresa> Empresas = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Empresa");
			Empresas = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return Empresas;
	}

}
