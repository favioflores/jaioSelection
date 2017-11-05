package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloRespuestaDAOImpl;
import jaio.selection.entity.ModeloRespuesta;
import jaio.selection.service.ModeloRespuestaService;

public class ModeloRespuestaServiceImpl implements ModeloRespuestaService, Serializable {

	private ModeloRespuestaDAOImpl ModeloRespuestaDAO;

	public void setModeloRespuestaDAO(ModeloRespuestaDAOImpl ModeloRespuestaDAO) {
		this.ModeloRespuestaDAO = ModeloRespuestaDAO;
	}

	public boolean crearModeloRespuesta(ModeloRespuesta ModeloRespuesta) {
		return ModeloRespuestaDAO.crearModeloRespuesta(ModeloRespuesta);
	}
	public boolean actualizaModeloRespuesta(ModeloRespuesta ModeloRespuesta) {
		return ModeloRespuestaDAO.actualizaModeloRespuesta(ModeloRespuesta);
	}
	public ModeloRespuesta obtenerModeloRespuesta(Integer Id) {
		return ModeloRespuestaDAO.obtenerModeloRespuesta(Id);
	}
	public boolean borrarModeloRespuesta(ModeloRespuesta ModeloRespuesta) {
		return ModeloRespuestaDAO.borrarModeloRespuesta(ModeloRespuesta);
	}
	public List<ModeloRespuesta> obtenerModeloRespuestas() {
		return ModeloRespuestaDAO.obtenerModeloRespuestas();
	}
	
}