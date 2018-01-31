package jaio.selection.dao;
// Generated 06/11/2017 06:08:05 PM by Hibernate Tools 5.1.4.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.ModeloEvaluacionXCompetencia;
import jaio.selection.orm.ModeloEvaluacionXCompetenciaId;

/**
 * DAO object for domain model class ModeloEvaluacionXCompetencia.
 *
 * @see jaio.selection.orm.ModeloEvaluacionXCompetencia
 * @author Hibernate Tools
 */
public class ModeloEvaluacionXCompetenciaDAO {

    private static final Log log = LogFactory.getLog(ModeloEvaluacionXCompetenciaDAO.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(ModeloEvaluacionXCompetencia transientInstance) {
        log.debug("persisting ModeloEvaluacionXCompetencia instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(ModeloEvaluacionXCompetencia instance) {
        log.debug("attaching dirty ModeloEvaluacionXCompetencia instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ModeloEvaluacionXCompetencia instance) {
        log.debug("attaching clean ModeloEvaluacionXCompetencia instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(ModeloEvaluacionXCompetencia persistentInstance) {
        log.debug("deleting ModeloEvaluacionXCompetencia instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ModeloEvaluacionXCompetencia merge(ModeloEvaluacionXCompetencia detachedInstance) {
        log.debug("merging ModeloEvaluacionXCompetencia instance");
        try {
            ModeloEvaluacionXCompetencia result = (ModeloEvaluacionXCompetencia) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ModeloEvaluacionXCompetencia findById(jaio.selection.orm.ModeloEvaluacionXCompetenciaId id) {
        log.debug("getting ModeloEvaluacionXCompetencia instance with id: " + id);
        try {
            ModeloEvaluacionXCompetencia instance = (ModeloEvaluacionXCompetencia) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.ModeloEvaluacionXCompetencia", id);
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

    public List findByExample(ModeloEvaluacionXCompetencia instance) {
        log.debug("finding ModeloEvaluacionXCompetencia instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.ModeloEvaluacionXCompetencia")
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
