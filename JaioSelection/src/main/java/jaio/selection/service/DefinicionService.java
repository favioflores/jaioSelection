package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.Definicion;

public interface DefinicionService {

	public abstract boolean crearDefinicion(Definicion Definicion);
	public abstract boolean actualizaDefinicion(Definicion Definicion);
	public abstract Definicion obtenerDefinicion(Integer Id);
	public abstract boolean borrarDefinicion(Definicion Definicion);
	public abstract List<Definicion> obtenerDefinicions();
	
}
