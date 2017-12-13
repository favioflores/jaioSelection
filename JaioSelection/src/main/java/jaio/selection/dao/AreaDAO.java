package jaio.selection.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.bean.AreaOrganigramaBean;
import jaio.selection.bean.PerfilBean;
import jaio.selection.orm.Area;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

public class AreaDAO extends HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AreaDAO.class);

	public HashMap<String, AreaOrganigramaBean> obtenerAreasPerfilesRegistrados() throws Exception {

		HashMap<String, AreaOrganigramaBean> hAreas = new LinkedHashMap<String, AreaOrganigramaBean>();

		List lstAreas = new ArrayList();
		List lstPerfiles = new ArrayList();

		iniciaSession();

		try {

			String idEmpresa = Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA).toString();

			Query queryAreas = session.createSQLQuery(
					"select a.id, a.descripcion, a.area_herarquia_id from area a where a.empresa_id = :empresa and a.estado = :estado order by a.area_herarquia_id, a.id  ");
			queryAreas.setInteger("estado", Constantes.EL_AREA_ESTADO_REGISTRADO);
			queryAreas.setString("empresa", idEmpresa);

			Query queryPerfiles = session.createSQLQuery(
					"select p.id, p.nombre, p.area_id from perfil p where p.empresa_id = :empresa and p.estado = :estado order by p.area_id, p.id ");
			queryPerfiles.setInteger("estado", Constantes.EL_PERFIL_ESTADO_REGISTRADO);
			queryPerfiles.setString("empresa", idEmpresa);

			lstAreas = queryAreas.list();
			lstPerfiles = queryPerfiles.list();

		} catch (Exception e) {
			log.error(e);
			manejaException(e);
		} finally {
			cerrarSession();
		}

		for (Object objArea : lstAreas) {

			Object[] row = (Object[]) objArea;

			AreaOrganigramaBean objAreaOrganigramaBean = new AreaOrganigramaBean();

			objAreaOrganigramaBean.setId(row[0].toString());
			objAreaOrganigramaBean.setDescripcion(row[1].toString());
			objAreaOrganigramaBean.setLstPerfiles(new ArrayList<PerfilBean>());

			if (Utilitarios.noEsNuloOVacio(row[2])) {
				objAreaOrganigramaBean.setId_parent(row[2].toString());
			}

			hAreas.put(objAreaOrganigramaBean.getId(), objAreaOrganigramaBean);

		}

		for (Object objPerfil : lstPerfiles) {

			Object[] row = (Object[]) objPerfil;

			AreaOrganigramaBean objArea = hAreas.get(row[2].toString());

			PerfilBean objPerfilBean = new PerfilBean();
			objPerfilBean.setId(row[0].toString());
			objPerfilBean.setDescripcion(row[1].toString());

			objArea.getLstPerfiles().add(objPerfilBean);

		}

		return hAreas;

	}

	public boolean eliminaAreaActualizaHerarquias(String idCurrentArea, String idParentArea) {

		iniciaSession();

		try {
		
			log.debug("Inicia con el borrado del área y la actualización de sus herarquias");

			String idEmpresa = Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA).toString();

			Query update1 = session.createSQLQuery(
					" update area set area_herarquia_id = :parent where area_herarquia_id = :current and empresa_id = :empresa ");

			Query update2 = session.createSQLQuery(
					" update perfil set area_id = :parent where area_id = :current and estado = :estado and empresa_id = :empresa ");
							
			Query update3 = session.createSQLQuery(
					" update area set estado = :estado where id = :current and empresa_id = :empresa ");

			update1.setString("parent", idParentArea);
			update1.setString("current", idCurrentArea);
			update1.setString("empresa", idEmpresa);

			update2.setString("parent", idParentArea);
			update2.setString("current", idCurrentArea);
			update2.setString("empresa", idEmpresa);
			update2.setInteger("estado", Constantes.EL_PERFIL_ESTADO_REGISTRADO);
			
			update3.setInteger("estado", Constantes.EL_AREA_ESTADO_ELIMINADO);
			update3.setString("current", idCurrentArea);
			update3.setString("empresa", idEmpresa);
			
			update1.executeUpdate();
			update2.executeUpdate();
			update3.executeUpdate();
			
			guardarCambios();
			
			return true;
			
		} catch (RuntimeException re) {
			rollback(re);
		}finally {
			cerrarSession();
		}
		
		return false;
		
	}

	public boolean moverArea(String idArea, String idParentArea) {

		iniciaSession();

		try {
		
			log.debug("Inicia con la movida del area");

			String idEmpresa = Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA).toString();

			Query update = session.createSQLQuery(
					" update area set area_herarquia_id = :parent where id = :current and estado = :estado and empresa_id = :empresa ");
							
			update.setString("parent", idParentArea);
			update.setString("current", idArea);
			update.setString("empresa", idEmpresa);
			update.setInteger("estado", Constantes.EL_AREA_ESTADO_REGISTRADO);
			
			update.executeUpdate();
			
			guardarCambios();
			
			return true;
			
		} catch (RuntimeException re) {
			rollback(re);
		}finally {
			cerrarSession();
		}
		
		return false;
		
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
		} finally {
			cerrarSession();
		}

		return null;
	}

}
