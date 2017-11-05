package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.ModeloLibro;

public interface ModeloLibroDAO {

	public abstract boolean crearModeloLibro(ModeloLibro ModeloLibro);
	public abstract boolean actualizaModeloLibro(ModeloLibro ModeloLibro);
	public abstract ModeloLibro obtenerModeloLibro(Integer Id);
	public abstract boolean borrarModeloLibro(ModeloLibro ModeloLibro);
	public abstract List<ModeloLibro> obtenerModeloLibros();
	
}
