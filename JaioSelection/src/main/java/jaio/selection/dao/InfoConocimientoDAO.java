package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.InfoConocimiento;

/**
 * DAO object for domain model class InfoConocimiento.
 *
 * @see jaio.selection.orm.InfoConocimiento
 * @author Hibernate Tools
 */
public class InfoConocimientoDAO {

    private static final Log log = LogFactory.getLog(InfoConocimientoDAO.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(InfoConocimiento transientInstance) {
        log.debug("persisting InfoConocimiento instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(InfoConocimiento instance) {
        log.debug("attaching dirty InfoConocimiento instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(InfoConocimiento instance) {
        log.debug("attaching clean InfoConocimiento instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(InfoConocimiento persistentInstance) {
        log.debug("deleting InfoConocimiento instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public InfoConocimiento merge(InfoConocimiento detachedInstance) {
        log.debug("merging InfoConocimiento instance");
        try {
            InfoConocimiento result = (InfoConocimiento) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public InfoConocimiento findById(java.lang.Integer id) {
        log.debug("getting InfoConocimiento instance with id: " + id);
        try {
            InfoConocimiento instance = (InfoConocimiento) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.InfoConocimiento", id);
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

    public List findByExample(InfoConocimiento instance) {
        log.debug("finding InfoConocimiento instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.InfoConocimiento")
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
