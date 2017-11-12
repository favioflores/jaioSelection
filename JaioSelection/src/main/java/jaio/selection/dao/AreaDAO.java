package jaio.selection.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.bean.AreaPerfilBean;
import jaio.selection.orm.Area;
import jaio.selection.orm.Perfil;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

public class AreaDAO extends HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AreaDAO.class);


	public List<AreaPerfilBean> obtenerAreasRegistradas() {

		List<AreaPerfilBean> lstAreaPerfil = new ArrayList<AreaPerfilBean>();

		iniciaSession();

		try {

			Query query = session.createQuery("select a FROM Area as a where a.estado = ? and a.empresa.id = ? order by a.areas.id desc ");

			query.setInteger(0, Constantes.EL_AREA_ESTADO_REGISTRADO);
			query.setString(1, (String) Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA));

			List<Area> lstArea = query.list();
			
			for(Area objArea : lstArea) {
				
				AreaPerfilBean objAreaPerfilBean = new AreaPerfilBean();
				
				objAreaPerfilBean.setArea(objArea);
				
				Iterator itPerfiles = objArea.getPerfils().iterator();
					
				while(itPerfiles.hasNext()) {
					
					Perfil objPerfil = (Perfil) itPerfiles.next();
					
					if(objPerfil.getEstado()==Constantes.EL_PERFIL_ESTADO_REGISTRADO) {
						objAreaPerfilBean.getLstPerfiles().add(objPerfil);
					}
					
				}
				
				lstAreaPerfil.add(objAreaPerfilBean);
				
			}

		} catch (Exception e) {
			log.error(e);
			manejaException(e);
		} finally {
			cerrarSession();
		}

		return lstAreaPerfil;
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

