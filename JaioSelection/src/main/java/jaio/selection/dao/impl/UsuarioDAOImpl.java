package jaio.selection.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.UsuarioDAO;
import jaio.selection.entity.Usuario;


@Repository
public class UsuarioDAOImpl extends GenericDAO implements UsuarioDAO {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(UsuarioDAOImpl.class);

	public boolean crearUsuario(Usuario Usuario) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.save(Usuario);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaUsuario(Usuario Usuario) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(Usuario);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	@Override
	public Usuario obtenerUsuario(Integer id) {

		Session session = getSession();
		Usuario Usuario = null;

		try {
			Usuario = session.get(Usuario.class, id);
			return Usuario;
		} catch (Exception e) {
			log.error(e);
		}

		return Usuario;
	}

	@Override
	public Usuario obtenerUsuario(String correo, String contrasena) {

		Session session = getSession();
		Usuario usuario = null;

		try {
			Query query = session.createQuery("FROM Usuario where correo = ? and contrasena = ?");

			query.setString(0, correo);
			query.setString(1, contrasena);

			usuario = (Usuario) query.uniqueResult();

		} catch (Exception e) {
			log.error(e);
		}

		return usuario;
	}

	public boolean borrarUsuario(Usuario Usuario) {

		Session session = getSession();

		try {
			session.delete(Usuario);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Usuario> obtenerUsuarios() {

		List<Usuario> Usuarios = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Usuario");
			Usuarios = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return Usuarios;
	}

}