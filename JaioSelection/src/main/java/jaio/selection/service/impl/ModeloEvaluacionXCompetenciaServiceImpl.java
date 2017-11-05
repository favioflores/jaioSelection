package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloEvaluacionXCompetenciaDAOImpl;
import jaio.selection.entity.ModeloEvaluacionXCompetencia;
import jaio.selection.service.ModeloEvaluacionXCompetenciaService;

public class ModeloEvaluacionXCompetenciaServiceImpl implements ModeloEvaluacionXCompetenciaService, Serializable {

	private ModeloEvaluacionXCompetenciaDAOImpl ModeloEvaluacionXCompetenciaDAO;

	public void setModeloEvaluacionXCompetenciaDAO(ModeloEvaluacionXCompetenciaDAOImpl ModeloEvaluacionXCompetenciaDAO) {
		this.ModeloEvaluacionXCompetenciaDAO = ModeloEvaluacionXCompetenciaDAO;
	}

	public boolean crearModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia) {
		return ModeloEvaluacionXCompetenciaDAO.crearModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia);
	}
	public boolean actualizaModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia) {
		return ModeloEvaluacionXCompetenciaDAO.actualizaModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia);
	}
	public ModeloEvaluacionXCompetencia obtenerModeloEvaluacionXCompetencia(Integer Id) {
		return ModeloEvaluacionXCompetenciaDAO.obtenerModeloEvaluacionXCompetencia(Id);
	}
	public boolean borrarModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia) {
		return ModeloEvaluacionXCompetenciaDAO.borrarModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia);
	}
	public List<ModeloEvaluacionXCompetencia> obtenerModeloEvaluacionXCompetencias() {
		return ModeloEvaluacionXCompetenciaDAO.obtenerModeloEvaluacionXCompetencias();
	}
	
}