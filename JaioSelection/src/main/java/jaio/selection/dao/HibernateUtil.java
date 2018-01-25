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

public class HibernateUtil implements Serializable{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    protected Session session;
    protected Transaction tx;

    private static final SessionFactory sessionFactory;

    static {
        try {
        	Configuration configuration = new Configuration().configure();
        	ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(objServiceRegistry);
       } catch (Throwable ex) {
    	   log.error("Initial SessionFactory creation failed." + ex);
           throw new ExceptionInInitializerError(ex);
        }
    }

    public void iniciaSession(){
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        log.debug("Inicio transaccion");
    }

    public void obtenerSession(){
        session = getSessionFactory().getCurrentSession();
        log.debug("Abrió Session");
    }

    public void guardarCambios(){
    	try{
    		tx.commit();
    		log.debug("Transaccion grabada");
    	}catch (Exception e) {
			rollback(e);
		}
    }

    public void cerrarSession(){
    	session.close();
    }

    public void rollback(Exception e){
        tx.rollback();
        log.error(e);
    }

    public void manejaException(Exception e){
        log.error(e);
        throw new IllegalArgumentException(e);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
