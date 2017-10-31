package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloPreguntaDAOImpl;
import jaio.selection.entity2.ModeloPregunta;
import jaio.selection.service.ModeloPreguntaService;

public class ModeloPreguntaServiceImpl implements ModeloPreguntaService, Serializable {

	private ModeloPreguntaDAOImpl ModeloPreguntaDAO;

	public void setModeloPreguntaDAO(ModeloPreguntaDAOImpl ModeloPreguntaDAO) {
		this.ModeloPreguntaDAO = ModeloPreguntaDAO;
	}

	public boolean crearModeloPregunta(ModeloPregunta ModeloPregunta) {
		return ModeloPreguntaDAO.crearModeloPregunta(ModeloPregunta);
	}
	public boolean actualizaModeloPregunta(ModeloPregunta ModeloPregunta) {
		return ModeloPreguntaDAO.actualizaModeloPregunta(ModeloPregunta);
	}
	public ModeloPregunta obtenerModeloPregunta(Integer Id) {
		return ModeloPreguntaDAO.obtenerModeloPregunta(Id);
	}
	public boolean borrarModeloPregunta(ModeloPregunta ModeloPregunta) {
		return ModeloPreguntaDAO.borrarModeloPregunta(ModeloPregunta);
	}
	public List<ModeloPregunta> obtenerModeloPreguntas() {
		return ModeloPreguntaDAO.obtenerModeloPreguntas();
	}
	
}