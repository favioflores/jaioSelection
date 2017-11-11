package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final


import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.Usuario;

public class UsuarioDAO extends HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(UsuarioDAO.class);

    public void persist(Usuario transientInstance) {
        log.debug("persisting Usuario instance");
        try {
            session.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(Usuario instance) {
        log.debug("attaching dirty Usuario instance");
        try {
            session.saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Usuario instance) {
        log.debug("attaching clean Usuario instance");
        try {
            session.lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(Usuario persistentInstance) {
        log.debug("deleting Usuario instance");
        try {
            session.delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }


    public Usuario merge(Usuario detachedInstance) {
        log.debug("merging Usuario instance");
        try {
            Usuario result = (Usuario) session.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Usuario findById( java.lang.Integer id) {
        log.debug("getting Usuario instance with id: " + id);
        try {
            Usuario instance = (Usuario) session.get("jaio.selection.orm.Usuario", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Usuario instance) {
        log.debug("finding Usuario instance by example");
        try {
            List results = session.createCriteria("jaio.selection.orm.Usuario")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }


	public Usuario obtenerUsuario(String correo, String contrasena) {

		Usuario usuario = null;

		iniciaSession();

		try {

			Query query = session.createQuery("FROM Usuario where correo = ? and contrasena = ?");

			query.setString(0, correo);
			query.setString(1, contrasena);

			usuario = (Usuario) query.uniqueResult();

		} catch (Exception e) {
			log.error(e);
			manejaException(e);
		} finally {
			cerrarSession();
		}

		return usuario;
	}


}

