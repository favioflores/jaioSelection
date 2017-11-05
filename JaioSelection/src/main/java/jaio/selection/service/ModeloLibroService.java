package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.ModeloLibro;

public interface ModeloLibroService {

	public abstract boolean crearModeloLibro(ModeloLibro ModeloLibro);
	public abstract boolean actualizaModeloLibro(ModeloLibro ModeloLibro);
	public abstract ModeloLibro obtenerModeloLibro(Integer Id);
	public abstract boolean borrarModeloLibro(ModeloLibro ModeloLibro);
	public abstract List<ModeloLibro> obtenerModeloLibros();
	
}
