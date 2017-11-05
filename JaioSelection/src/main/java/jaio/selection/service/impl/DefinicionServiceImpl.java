package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.DefinicionDAOImpl;
import jaio.selection.entity.Definicion;
import jaio.selection.service.DefinicionService;

public class DefinicionServiceImpl implements DefinicionService, Serializable {

	private DefinicionDAOImpl DefinicionDAO;

	public void setDefinicionDAO(DefinicionDAOImpl DefinicionDAO) {
		this.DefinicionDAO = DefinicionDAO;
	}

	public boolean crearDefinicion(Definicion Definicion) {
		return DefinicionDAO.crearDefinicion(Definicion);
	}
	public boolean actualizaDefinicion(Definicion Definicion) {
		return DefinicionDAO.actualizaDefinicion(Definicion);
	}
	public Definicion obtenerDefinicion(Integer Id) {
		return DefinicionDAO.obtenerDefinicion(Id);
	}
	public boolean borrarDefinicion(Definicion Definicion) {
		return DefinicionDAO.borrarDefinicion(Definicion);
	}
	public List<Definicion> obtenerDefinicions() {
		return DefinicionDAO.obtenerDefinicions();
	}
	
}