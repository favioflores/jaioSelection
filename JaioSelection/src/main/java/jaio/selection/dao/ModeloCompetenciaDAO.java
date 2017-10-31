package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity2.ModeloCompetencia;

public interface ModeloCompetenciaDAO {

	public abstract boolean crearModeloCompetencia(ModeloCompetencia ModeloCompetencia);
	public abstract boolean actualizaModeloCompetencia(ModeloCompetencia ModeloCompetencia);
	public abstract ModeloCompetencia obtenerModeloCompetencia(Integer Id);
	public abstract boolean borrarModeloCompetencia(ModeloCompetencia ModeloCompetencia);
	public abstract List<ModeloCompetencia> obtenerModeloCompetencias();
	
}
