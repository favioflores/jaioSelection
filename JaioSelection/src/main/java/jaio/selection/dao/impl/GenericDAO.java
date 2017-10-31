package jaio.selection.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class GenericDAO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	private static final Log log = LogFactory.getLog(GenericDAO.class);
	
	protected Session getSession() {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			return session;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
}
