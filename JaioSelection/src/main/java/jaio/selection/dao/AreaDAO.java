package jaio.selection.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.orm.Area;
import jaio.selection.orm.Empresa;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

public class AreaDAO extends HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AreaDAO.class);


	public List<Area> obtenerAreas(Integer estado) {

		List<Area> lstAreas = new ArrayList<Area>();

		iniciaSession();

		try {

			Query query = session.createQuery("select a FROM Area as a join a.perfil as p where a.estado = ? and a.empresa.id = ? ");

			query.setInteger(0, estado);
			query.setString(1, (String) Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA));

			lstAreas = query.list();

		} catch (Exception e) {
			log.error(e);
			manejaException(e);
		} finally {
			cerrarSession();
		}

		return lstAreas;
	}

    public Integer grabar(Area area) {

    	iniciaSession();

        try {

            Integer id = (Integer) session.save(area);

            guardarCambios();

            log.debug("Grago correctamente");

            return id;

        } catch (Exception e) {
            rollback(e);
        }finally {
        	cerrarSession();
		}

        return null;
    }

}

