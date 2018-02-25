package jaio.selection.dao;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log logParent = LogFactory.getLog(HibernateUtil.class);

    protected Session session;
    protected Transaction tx;

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(objServiceRegistry);
        } catch (Exception ex) {
            logParent.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void iniciaSession() {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        logParent.debug("Inicio transaccion");
    }

    public void obtenerSession() {
        session = getSessionFactory().getCurrentSession();
        logParent.debug("Abrió Session");
    }

    public void guardarCambios() {
        try {
            tx.commit();
            logParent.debug("Transaccion grabada");
        } catch (Exception e) {
            rollback(logParent, e);
        }
    }

    public void cerrarSession() {
        session.close();
    }

    public void rollback(Log log, Exception e) {
        tx.rollback();
        log.error(e);
    }

    public void manejaException(Log log, Exception e) {
        log.error(e);
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", e);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
