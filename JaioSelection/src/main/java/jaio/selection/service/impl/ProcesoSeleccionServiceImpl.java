package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ProcesoSeleccionDAOImpl;
import jaio.selection.entity2.ProcesoSeleccion;
import jaio.selection.service.ProcesoSeleccionService;

public class ProcesoSeleccionServiceImpl implements ProcesoSeleccionService, Serializable {

	private ProcesoSeleccionDAOImpl ProcesoSeleccionDAO;

	public void setProcesoSeleccionDAO(ProcesoSeleccionDAOImpl ProcesoSeleccionDAO) {
		this.ProcesoSeleccionDAO = ProcesoSeleccionDAO;
	}

	public boolean crearProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion) {
		return ProcesoSeleccionDAO.crearProcesoSeleccion(ProcesoSeleccion);
	}
	public boolean actualizaProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion) {
		return ProcesoSeleccionDAO.actualizaProcesoSeleccion(ProcesoSeleccion);
	}
	public ProcesoSeleccion obtenerProcesoSeleccion(Integer Id) {
		return ProcesoSeleccionDAO.obtenerProcesoSeleccion(Id);
	}
	public boolean borrarProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion) {
		return ProcesoSeleccionDAO.borrarProcesoSeleccion(ProcesoSeleccion);
	}
	public List<ProcesoSeleccion> obtenerProcesoSeleccions() {
		return ProcesoSeleccionDAO.obtenerProcesoSeleccions();
	}
	
}