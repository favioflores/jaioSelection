package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.BateriaEvaluacion;
import jaio.selection.orm.BateriaEvaluacionId;

/**
 * DAO object for domain model class BateriaEvaluacion.
 * @see jaio.selection.orm.BateriaEvaluacion
 * @author Hibernate Tools
 */
public class BateriaEvaluacionDAO {

    private static final Log log = LogFactory.getLog(BateriaEvaluacionDAO.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(BateriaEvaluacion transientInstance) {
        log.debug("persisting BateriaEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(BateriaEvaluacion instance) {
        log.debug("attaching dirty BateriaEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BateriaEvaluacion instance) {
        log.debug("attaching clean BateriaEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(BateriaEvaluacion persistentInstance) {
        log.debug("deleting BateriaEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BateriaEvaluacion merge(BateriaEvaluacion detachedInstance) {
        log.debug("merging BateriaEvaluacion instance");
        try {
            BateriaEvaluacion result = (BateriaEvaluacion) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public BateriaEvaluacion findById( jaio.selection.orm.BateriaEvaluacionId id) {
        log.debug("getting BateriaEvaluacion instance with id: " + id);
        try {
            BateriaEvaluacion instance = (BateriaEvaluacion) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.BateriaEvaluacion", id);
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
    
    public List findByExample(BateriaEvaluacion instance) {
        log.debug("finding BateriaEvaluacion instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.BateriaEvaluacion")
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
}

