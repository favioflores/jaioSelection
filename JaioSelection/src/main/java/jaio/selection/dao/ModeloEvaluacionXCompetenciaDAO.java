package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.ModeloEvaluacionXCompetencia;

public interface ModeloEvaluacionXCompetenciaDAO {

	public abstract boolean crearModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia);
	public abstract boolean actualizaModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia);
	public abstract ModeloEvaluacionXCompetencia obtenerModeloEvaluacionXCompetencia(Integer Id);
	public abstract boolean borrarModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetencia ModeloEvaluacionXCompetencia);
	public abstract List<ModeloEvaluacionXCompetencia> obtenerModeloEvaluacionXCompetencias();
	
}
