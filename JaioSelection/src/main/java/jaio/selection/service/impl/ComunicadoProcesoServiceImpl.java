package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ComunicadoProcesoDAOImpl;
import jaio.selection.entity2.ComunicadoProceso;
import jaio.selection.service.ComunicadoProcesoService;

public class ComunicadoProcesoServiceImpl implements ComunicadoProcesoService, Serializable {

	private ComunicadoProcesoDAOImpl ComunicadoProcesoDAO;

	public void setComunicadoProcesoDAO(ComunicadoProcesoDAOImpl ComunicadoProcesoDAO) {
		this.ComunicadoProcesoDAO = ComunicadoProcesoDAO;
	}

	public boolean crearComunicadoProceso(ComunicadoProceso ComunicadoProceso) {
		return ComunicadoProcesoDAO.crearComunicadoProceso(ComunicadoProceso);
	}
	public boolean actualizaComunicadoProceso(ComunicadoProceso ComunicadoProceso) {
		return ComunicadoProcesoDAO.actualizaComunicadoProceso(ComunicadoProceso);
	}
	public ComunicadoProceso obtenerComunicadoProceso(Integer Id) {
		return ComunicadoProcesoDAO.obtenerComunicadoProceso(Id);
	}
	public boolean borrarComunicadoProceso(ComunicadoProceso ComunicadoProceso) {
		return ComunicadoProcesoDAO.borrarComunicadoProceso(ComunicadoProceso);
	}
	public List<ComunicadoProceso> obtenerComunicadoProcesos() {
		return ComunicadoProcesoDAO.obtenerComunicadoProcesos();
	}
	
}