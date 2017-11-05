package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloCompetenciaDAOImpl;
import jaio.selection.entity.ModeloCompetencia;
import jaio.selection.service.ModeloCompetenciaService;

public class ModeloCompetenciaServiceImpl implements ModeloCompetenciaService, Serializable {

	private ModeloCompetenciaDAOImpl ModeloCompetenciaDAO;

	public void setModeloCompetenciaDAO(ModeloCompetenciaDAOImpl ModeloCompetenciaDAO) {
		this.ModeloCompetenciaDAO = ModeloCompetenciaDAO;
	}

	public boolean crearModeloCompetencia(ModeloCompetencia ModeloCompetencia) {
		return ModeloCompetenciaDAO.crearModeloCompetencia(ModeloCompetencia);
	}
	public boolean actualizaModeloCompetencia(ModeloCompetencia ModeloCompetencia) {
		return ModeloCompetenciaDAO.actualizaModeloCompetencia(ModeloCompetencia);
	}
	public ModeloCompetencia obtenerModeloCompetencia(Integer Id) {
		return ModeloCompetenciaDAO.obtenerModeloCompetencia(Id);
	}
	public boolean borrarModeloCompetencia(ModeloCompetencia ModeloCompetencia) {
		return ModeloCompetenciaDAO.borrarModeloCompetencia(ModeloCompetencia);
	}
	public List<ModeloCompetencia> obtenerModeloCompetencias() {
		return ModeloCompetenciaDAO.obtenerModeloCompetencias();
	}
	
}