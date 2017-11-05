package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.Elemento;

public interface ElementoService {

	public abstract boolean crearElemento(Elemento Elemento);
	public abstract boolean actualizaElemento(Elemento Elemento);
	public abstract Elemento obtenerElemento(Integer Id);
	public abstract boolean borrarElemento(Elemento Elemento);
	public abstract List<Elemento> obtenerElementos();
	
}
