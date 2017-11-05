package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.ComunicadoProceso;

public interface ComunicadoProcesoDAO {

	public abstract boolean crearComunicadoProceso(ComunicadoProceso ComunicadoProceso);
	public abstract boolean actualizaComunicadoProceso(ComunicadoProceso ComunicadoProceso);
	public abstract ComunicadoProceso obtenerComunicadoProceso(Integer Id);
	public abstract boolean borrarComunicadoProceso(ComunicadoProceso ComunicadoProceso);
	public abstract List<ComunicadoProceso> obtenerComunicadoProcesos();
	
}
