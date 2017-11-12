package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.ModeloCompetencia;

/**
 * DAO object for domain model class ModeloCompetencia.
 * @see jaio.selection.orm.ModeloCompetencia
 * @author Hibernate Tools
 */
public class ModeloCompetenciaDAO {

    private static final Log log = LogFactory.getLog(ModeloCompetenciaDAO.class);

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
    
    public void persist(ModeloCompetencia transientInstance) {
        log.debug("persisting ModeloCompetencia instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ModeloCompetencia instance) {
        log.debug("attaching dirty ModeloCompetencia instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ModeloCompetencia instance) {
        log.debug("attaching clean ModeloCompetencia instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ModeloCompetencia persistentInstance) {
        log.debug("deleting ModeloCompetencia instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ModeloCompetencia merge(ModeloCompetencia detachedInstance) {
        log.debug("merging ModeloCompetencia instance");
        try {
            ModeloCompetencia result = (ModeloCompetencia) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ModeloCompetencia findById( java.lang.Integer id) {
        log.debug("getting ModeloCompetencia instance with id: " + id);
        try {
            ModeloCompetencia instance = (ModeloCompetencia) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.ModeloCompetencia", id);
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
    
    public List findByExample(ModeloCompetencia instance) {
        log.debug("finding ModeloCompetencia instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.ModeloCompetencia")
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
