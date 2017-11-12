package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.ComunicadoProceso;
import jaio.selection.orm.ComunicadoProcesoId;

/**
 * DAO object for domain model class ComunicadoProceso.
 * @see jaio.selection.orm.ComunicadoProceso
 * @author Hibernate Tools
 */
public class ComunicadoProcesoDAO {

    private static final Log log = LogFactory.getLog(ComunicadoProcesoDAO.class);

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
    
    public void persist(ComunicadoProceso transientInstance) {
        log.debug("persisting ComunicadoProceso instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ComunicadoProceso instance) {
        log.debug("attaching dirty ComunicadoProceso instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ComunicadoProceso instance) {
        log.debug("attaching clean ComunicadoProceso instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ComunicadoProceso persistentInstance) {
        log.debug("deleting ComunicadoProceso instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ComunicadoProceso merge(ComunicadoProceso detachedInstance) {
        log.debug("merging ComunicadoProceso instance");
        try {
            ComunicadoProceso result = (ComunicadoProceso) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ComunicadoProceso findById( jaio.selection.orm.ComunicadoProcesoId id) {
        log.debug("getting ComunicadoProceso instance with id: " + id);
        try {
            ComunicadoProceso instance = (ComunicadoProceso) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.ComunicadoProceso", id);
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
    
    public List findByExample(ComunicadoProceso instance) {
        log.debug("finding ComunicadoProceso instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.ComunicadoProceso")
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
