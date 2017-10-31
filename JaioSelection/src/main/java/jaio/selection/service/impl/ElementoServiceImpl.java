package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ElementoDAOImpl;
import jaio.selection.entity2.Elemento;
import jaio.selection.service.ElementoService;

public class ElementoServiceImpl implements ElementoService, Serializable {

	private ElementoDAOImpl ElementoDAO;

	public void setElementoDAO(ElementoDAOImpl ElementoDAO) {
		this.ElementoDAO = ElementoDAO;
	}

	public boolean crearElemento(Elemento Elemento) {
		return ElementoDAO.crearElemento(Elemento);
	}
	public boolean actualizaElemento(Elemento Elemento) {
		return ElementoDAO.actualizaElemento(Elemento);
	}
	public Elemento obtenerElemento(Integer Id) {
		return ElementoDAO.obtenerElemento(Id);
	}
	public boolean borrarElemento(Elemento Elemento) {
		return ElementoDAO.borrarElemento(Elemento);
	}
	public List<Elemento> obtenerElementos() {
		return ElementoDAO.obtenerElementos();
	}
	
}