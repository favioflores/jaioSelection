package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloCompetenciaSinonimoDAOImpl;
import jaio.selection.entity2.ModeloCompetenciaSinonimo;
import jaio.selection.service.ModeloCompetenciaSinonimoService;

public class ModeloCompetenciaSinonimoServiceImpl implements ModeloCompetenciaSinonimoService, Serializable {

	private ModeloCompetenciaSinonimoDAOImpl ModeloCompetenciaSinonimoDAO;

	public void setModeloCompetenciaSinonimoDAO(ModeloCompetenciaSinonimoDAOImpl ModeloCompetenciaSinonimoDAO) {
		this.ModeloCompetenciaSinonimoDAO = ModeloCompetenciaSinonimoDAO;
	}

	public boolean crearModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo) {
		return ModeloCompetenciaSinonimoDAO.crearModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo);
	}
	public boolean actualizaModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo) {
		return ModeloCompetenciaSinonimoDAO.actualizaModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo);
	}
	public ModeloCompetenciaSinonimo obtenerModeloCompetenciaSinonimo(Integer Id) {
		return ModeloCompetenciaSinonimoDAO.obtenerModeloCompetenciaSinonimo(Id);
	}
	public boolean borrarModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo) {
		return ModeloCompetenciaSinonimoDAO.borrarModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo);
	}
	public List<ModeloCompetenciaSinonimo> obtenerModeloCompetenciaSinonimos() {
		return ModeloCompetenciaSinonimoDAO.obtenerModeloCompetenciaSinonimos();
	}
	
}