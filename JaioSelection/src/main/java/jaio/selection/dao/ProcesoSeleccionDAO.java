package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.ProcesoSeleccion;

public interface ProcesoSeleccionDAO {

	public abstract boolean crearProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion);
	public abstract boolean actualizaProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion);
	public abstract ProcesoSeleccion obtenerProcesoSeleccion(Integer Id);
	public abstract boolean borrarProcesoSeleccion(ProcesoSeleccion ProcesoSeleccion);
	public abstract List<ProcesoSeleccion> obtenerProcesoSeleccions();
	
}
