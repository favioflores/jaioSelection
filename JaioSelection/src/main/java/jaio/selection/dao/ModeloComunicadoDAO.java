package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.ModeloComunicado;

/**
 * DAO object for domain model class ModeloComunicado.
 *
 * @see jaio.selection.orm.ModeloComunicado
 * @author Hibernate Tools
 */
public class ModeloComunicadoDAO {

    private static final Log log = LogFactory.getLog(ModeloComunicadoDAO.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(ModeloComunicado transientInstance) {
        log.debug("persisting ModeloComunicado instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(ModeloComunicado instance) {
        log.debug("attaching dirty ModeloComunicado instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ModeloComunicado instance) {
        log.debug("attaching clean ModeloComunicado instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(ModeloComunicado persistentInstance) {
        log.debug("deleting ModeloComunicado instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ModeloComunicado merge(ModeloComunicado detachedInstance) {
        log.debug("merging ModeloComunicado instance");
        try {
            ModeloComunicado result = (ModeloComunicado) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ModeloComunicado findById(java.lang.Integer id) {
        log.debug("getting ModeloComunicado instance with id: " + id);
        try {
            ModeloComunicado instance = (ModeloComunicado) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.ModeloComunicado", id);
            if (instance == null) {
                log.debug("get successful, no instance found");
            } else {
                log.debug("get successful, instance found");
            }
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(ModeloComunicado instance) {
        log.debug("finding ModeloComunicado instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.ModeloComunicado")
                    .add(Example.create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
