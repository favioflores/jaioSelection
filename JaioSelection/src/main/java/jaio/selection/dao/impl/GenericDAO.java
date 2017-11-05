package jaio.selection.dao.impl;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDAO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	private static final Log log = LogFactory.getLog(GenericDAO.class);

	public Session getSession() {

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
