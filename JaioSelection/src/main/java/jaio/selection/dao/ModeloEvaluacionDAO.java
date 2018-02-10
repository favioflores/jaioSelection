package jaio.selection.dao;

import jaio.selection.orm.BateriaEvaluacion;
import jaio.selection.orm.BateriaPersonalizada;
import jaio.selection.orm.EvaluacionPerfil;
import jaio.selection.orm.EvaluacionPerfilId;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.ModeloEvaluacion;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.Query;

/**
 * DAO object for domain model class ModeloEvaluacion.
 * @see jaio.selection.orm.ModeloEvaluacion
 * @author Hibernate Tools
 */
public class ModeloEvaluacionDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ModeloEvaluacionDAO.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    public void persist(ModeloEvaluacion transientInstance) {
        log.debug("persisting ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ModeloEvaluacion instance) {
        log.debug("attaching dirty ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ModeloEvaluacion instance) {
        log.debug("attaching clean ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ModeloEvaluacion persistentInstance) {
        log.debug("deleting ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ModeloEvaluacion merge(ModeloEvaluacion detachedInstance) {
        log.debug("merging ModeloEvaluacion instance");
        try {
            ModeloEvaluacion result = (ModeloEvaluacion) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ModeloEvaluacion findById( java.lang.Integer id) {
        log.debug("getting ModeloEvaluacion instance with id: " + id);
        try {
            ModeloEvaluacion instance = (ModeloEvaluacion) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.ModeloEvaluacion", id);
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
    
    public List findByExample(ModeloEvaluacion instance) {
        log.debug("finding ModeloEvaluacion instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.ModeloEvaluacion")
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
    
    /**
     * metodo para traer lista de evaluaciones
     * @return 
     */
    public List<ModeloEvaluacion> obtenerModelos() {
        iniciaSession();
        try {
            Query query = session.createQuery("From ModeloEvaluacion");
            return query.list();
        } catch (Exception e) {
            manejaException(e);
        } finally {
            cerrarSession();
        }
        return null;
    }
    
    public boolean grabarBateria(BateriaPersonalizada bateriaPersonalizada, EvaluacionPerfilId evaluacionPerfilId,BateriaEvaluacion bateriaEvaluacion) {
        iniciaSession();
        boolean grabado = false;
        try {
            
            session.save(bateriaPersonalizada);
            
//            session.save(evaluacionPerfil);
//            session.save(bateriaEvaluacion);
            
            guardarCambios();
            log.debug("Grago correctamente");
        } catch (Exception e) {
            rollback(e);
        } finally {
            grabado = true;
            cerrarSession();
        }
        return grabado;
    }
    
}
