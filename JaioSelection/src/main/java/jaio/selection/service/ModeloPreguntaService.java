package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.ModeloPregunta;

public interface ModeloPreguntaService {

	public abstract boolean crearModeloPregunta(ModeloPregunta ModeloPregunta);
	public abstract boolean actualizaModeloPregunta(ModeloPregunta ModeloPregunta);
	public abstract ModeloPregunta obtenerModeloPregunta(Integer Id);
	public abstract boolean borrarModeloPregunta(ModeloPregunta ModeloPregunta);
	public abstract List<ModeloPregunta> obtenerModeloPreguntas();
	
}
