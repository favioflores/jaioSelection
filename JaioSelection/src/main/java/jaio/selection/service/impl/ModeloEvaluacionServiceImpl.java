package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloEvaluacionDAOImpl;
import jaio.selection.entity2.ModeloEvaluacion;
import jaio.selection.service.ModeloEvaluacionService;

public class ModeloEvaluacionServiceImpl implements ModeloEvaluacionService, Serializable {

	private ModeloEvaluacionDAOImpl ModeloEvaluacionDAO;

	public void setModeloEvaluacionDAO(ModeloEvaluacionDAOImpl ModeloEvaluacionDAO) {
		this.ModeloEvaluacionDAO = ModeloEvaluacionDAO;
	}

	public boolean crearModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion) {
		return ModeloEvaluacionDAO.crearModeloEvaluacion(ModeloEvaluacion);
	}
	public boolean actualizaModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion) {
		return ModeloEvaluacionDAO.actualizaModeloEvaluacion(ModeloEvaluacion);
	}
	public ModeloEvaluacion obtenerModeloEvaluacion(Integer Id) {
		return ModeloEvaluacionDAO.obtenerModeloEvaluacion(Id);
	}
	public boolean borrarModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion) {
		return ModeloEvaluacionDAO.borrarModeloEvaluacion(ModeloEvaluacion);
	}
	public List<ModeloEvaluacion> obtenerModeloEvaluacions() {
		return ModeloEvaluacionDAO.obtenerModeloEvaluacions();
	}
	
}