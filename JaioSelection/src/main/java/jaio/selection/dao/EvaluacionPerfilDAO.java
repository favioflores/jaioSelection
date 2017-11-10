package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.EvaluacionPerfil;
import jaio.selection.orm.EvaluacionPerfilId;

/**
 * DAO object for domain model class EvaluacionPerfil.
 * @see jaio.selection.orm.EvaluacionPerfil
 * @author Hibernate Tools
 */
public class EvaluacionPerfilDAO {

    private static final Log log = LogFactory.getLog(EvaluacionPerfilDAO.class);

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
    
    public void persist(EvaluacionPerfil transientInstance) {
        log.debug("persisting EvaluacionPerfil instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(EvaluacionPerfil instance) {
        log.debug("attaching dirty EvaluacionPerfil instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(EvaluacionPerfil instance) {
        log.debug("attaching clean EvaluacionPerfil instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(EvaluacionPerfil persistentInstance) {
        log.debug("deleting EvaluacionPerfil instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public EvaluacionPerfil merge(EvaluacionPerfil detachedInstance) {
        log.debug("merging EvaluacionPerfil instance");
        try {
            EvaluacionPerfil result = (EvaluacionPerfil) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public EvaluacionPerfil findById( jaio.selection.orm.EvaluacionPerfilId id) {
        log.debug("getting EvaluacionPerfil instance with id: " + id);
        try {
            EvaluacionPerfil instance = (EvaluacionPerfil) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.EvaluacionPerfil", id);
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
    
    public List findByExample(EvaluacionPerfil instance) {
        log.debug("finding EvaluacionPerfil instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.EvaluacionPerfil")
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

