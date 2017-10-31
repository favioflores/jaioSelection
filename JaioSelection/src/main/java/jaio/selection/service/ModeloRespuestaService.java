package jaio.selection.service;

import java.util.List;

import jaio.selection.entity2.ModeloRespuesta;

public interface ModeloRespuestaService {

	public abstract boolean crearModeloRespuesta(ModeloRespuesta ModeloRespuesta);
	public abstract boolean actualizaModeloRespuesta(ModeloRespuesta ModeloRespuesta);
	public abstract ModeloRespuesta obtenerModeloRespuesta(Integer Id);
	public abstract boolean borrarModeloRespuesta(ModeloRespuesta ModeloRespuesta);
	public abstract List<ModeloRespuesta> obtenerModeloRespuestas();
	
}
