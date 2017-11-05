package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloLibroDAOImpl;
import jaio.selection.entity.ModeloLibro;
import jaio.selection.service.ModeloLibroService;

public class ModeloLibroServiceImpl implements ModeloLibroService, Serializable {

	private ModeloLibroDAOImpl ModeloLibroDAO;

	public void setModeloLibroDAO(ModeloLibroDAOImpl ModeloLibroDAO) {
		this.ModeloLibroDAO = ModeloLibroDAO;
	}

	public boolean crearModeloLibro(ModeloLibro ModeloLibro) {
		return ModeloLibroDAO.crearModeloLibro(ModeloLibro);
	}
	public boolean actualizaModeloLibro(ModeloLibro ModeloLibro) {
		return ModeloLibroDAO.actualizaModeloLibro(ModeloLibro);
	}
	public ModeloLibro obtenerModeloLibro(Integer Id) {
		return ModeloLibroDAO.obtenerModeloLibro(Id);
	}
	public boolean borrarModeloLibro(ModeloLibro ModeloLibro) {
		return ModeloLibroDAO.borrarModeloLibro(ModeloLibro);
	}
	public List<ModeloLibro> obtenerModeloLibros() {
		return ModeloLibroDAO.obtenerModeloLibros();
	}
	
}