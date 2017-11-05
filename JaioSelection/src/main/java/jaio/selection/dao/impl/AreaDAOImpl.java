package jaio.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jaio.selection.dao.AreaDAO;
import jaio.selection.entity.Area;

@Repository
public class AreaDAOImpl extends GenericDAO implements AreaDAO, Serializable {

	private static final Log log = LogFactory.getLog(AreaDAOImpl.class);

	public boolean crearArea(Area area) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.save(area);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public boolean actualizaArea(Area area) {

		Session session = getSession();

		session.beginTransaction();

		try {
			session.update(area);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public Area obtenerArea(Integer id) {

		Session session = getSession();
		Area area = null;

		try {
			area = session.get(Area.class, id);
			return area;
		} catch (Exception e) {
			log.error(e);
		}

		return area;
	}

	public boolean borrarArea(Area area) {

		Session session = getSession();

		try {
			session.delete(area);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			session.getTransaction().rollback();
		}

		return false;
	}

	public List<Area> obtenerAreas() {

		List<Area> areas = null;

		Session session = getSession();
		session.beginTransaction();

		try {

			Query query = getSession().createQuery("FROM Area");
			areas = query.list();

		} catch (Exception e) {
			log.error(e);
			getSession().getTransaction().rollback();
		}
		return areas;
	}

}
