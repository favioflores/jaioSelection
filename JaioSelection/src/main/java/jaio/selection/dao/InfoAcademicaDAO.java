package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.InfoAcademica;

/**
 * DAO object for domain model class InfoAcademica.
 *
 * @see jaio.selection.orm.InfoAcademica
 * @author Hibernate Tools
 */
public class InfoAcademicaDAO {

    private static final Log log = LogFactory.getLog(InfoAcademicaDAO.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(InfoAcademica transientInstance) {
        log.debug("persisting InfoAcademica instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(InfoAcademica instance) {
        log.debug("attaching dirty InfoAcademica instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(InfoAcademica instance) {
        log.debug("attaching clean InfoAcademica instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(InfoAcademica persistentInstance) {
        log.debug("deleting InfoAcademica instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public InfoAcademica merge(InfoAcademica detachedInstance) {
        log.debug("merging InfoAcademica instance");
        try {
            InfoAcademica result = (InfoAcademica) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public InfoAcademica findById(java.lang.Integer id) {
        log.debug("getting InfoAcademica instance with id: " + id);
        try {
            InfoAcademica instance = (InfoAcademica) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.InfoAcademica", id);
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

    public List findByExample(InfoAcademica instance) {
        log.debug("finding InfoAcademica instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.InfoAcademica")
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
