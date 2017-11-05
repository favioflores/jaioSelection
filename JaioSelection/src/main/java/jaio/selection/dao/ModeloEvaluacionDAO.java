package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.ModeloEvaluacion;

public interface ModeloEvaluacionDAO {

	public abstract boolean crearModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion);
	public abstract boolean actualizaModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion);
	public abstract ModeloEvaluacion obtenerModeloEvaluacion(Integer Id);
	public abstract boolean borrarModeloEvaluacion(ModeloEvaluacion ModeloEvaluacion);
	public abstract List<ModeloEvaluacion> obtenerModeloEvaluacions();
	
}
