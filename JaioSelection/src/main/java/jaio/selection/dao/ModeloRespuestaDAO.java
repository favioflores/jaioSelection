package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity2.ModeloRespuesta;

public interface ModeloRespuestaDAO {

	public abstract boolean crearModeloRespuesta(ModeloRespuesta ModeloRespuesta);
	public abstract boolean actualizaModeloRespuesta(ModeloRespuesta ModeloRespuesta);
	public abstract ModeloRespuesta obtenerModeloRespuesta(Integer Id);
	public abstract boolean borrarModeloRespuesta(ModeloRespuesta ModeloRespuesta);
	public abstract List<ModeloRespuesta> obtenerModeloRespuestas();
	
}
