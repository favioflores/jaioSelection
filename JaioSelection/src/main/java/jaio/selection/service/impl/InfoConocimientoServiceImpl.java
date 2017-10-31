package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.InfoConocimientoDAOImpl;
import jaio.selection.entity2.InfoConocimiento;
import jaio.selection.service.InfoConocimientoService;

public class InfoConocimientoServiceImpl implements InfoConocimientoService, Serializable {

	private InfoConocimientoDAOImpl InfoConocimientoDAO;

	public void setInfoConocimientoDAO(InfoConocimientoDAOImpl InfoConocimientoDAO) {
		this.InfoConocimientoDAO = InfoConocimientoDAO;
	}

	public boolean crearInfoConocimiento(InfoConocimiento InfoConocimiento) {
		return InfoConocimientoDAO.crearInfoConocimiento(InfoConocimiento);
	}
	public boolean actualizaInfoConocimiento(InfoConocimiento InfoConocimiento) {
		return InfoConocimientoDAO.actualizaInfoConocimiento(InfoConocimiento);
	}
	public InfoConocimiento obtenerInfoConocimiento(Integer Id) {
		return InfoConocimientoDAO.obtenerInfoConocimiento(Id);
	}
	public boolean borrarInfoConocimiento(InfoConocimiento InfoConocimiento) {
		return InfoConocimientoDAO.borrarInfoConocimiento(InfoConocimiento);
	}
	public List<InfoConocimiento> obtenerInfoConocimientos() {
		return InfoConocimientoDAO.obtenerInfoConocimientos();
	}
	
}