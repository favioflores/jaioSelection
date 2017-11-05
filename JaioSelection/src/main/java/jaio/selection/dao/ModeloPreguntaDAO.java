package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.ModeloPregunta;

public interface ModeloPreguntaDAO {

	public abstract boolean crearModeloPregunta(ModeloPregunta ModeloPregunta);
	public abstract boolean actualizaModeloPregunta(ModeloPregunta ModeloPregunta);
	public abstract ModeloPregunta obtenerModeloPregunta(Integer Id);
	public abstract boolean borrarModeloPregunta(ModeloPregunta ModeloPregunta);
	public abstract List<ModeloPregunta> obtenerModeloPreguntas();
	
}
