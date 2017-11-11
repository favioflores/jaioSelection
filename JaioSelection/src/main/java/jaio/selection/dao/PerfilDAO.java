package jaio.selection.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.orm.Area;
import jaio.selection.orm.Perfil;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

public class PerfilDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PerfilDAO.class);


	public List<Perfil> obtenerPerfiles(Integer estado) {

		List<Perfil> lstPerfiles = new ArrayList<Perfil>();

		iniciaSession();

		try {

			Query query = session.createQuery("FROM Perfil p where p.estado = ? and p.empresa.id = ? ");

			query.setInteger(0, estado);
			query.setString(1, (String) Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA));

			lstPerfiles = query.list();

		} catch (Exception e) {
			log.error(e);
			manejaException(e);
		} finally {
			cerrarSession();
		}

		return lstPerfiles;
	}

    public Integer grabar(Perfil perfil) {

    	iniciaSession();

        try {

            Integer id = (Integer) session.save(perfil);

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

