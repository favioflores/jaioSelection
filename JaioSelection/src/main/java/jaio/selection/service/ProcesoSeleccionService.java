package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.ProcesoSeleccion;

public interface ProcesoSeleccionService {

	public abstract boolean crearProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion);
	public abstract boolean actualizaProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion);
	public abstract ProcesoSeleccion obtenerProcesoSeleccion(Integer Id);
	public abstract boolean borrarProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion);
	public abstract List<ProcesoSeleccion> obtenerProcesoSeleccions();
	
}
