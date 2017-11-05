package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.InfoConocimiento;

public interface InfoConocimientoService {

	public abstract boolean crearInfoConocimiento(InfoConocimiento InfoConocimiento);
	public abstract boolean actualizaInfoConocimiento(InfoConocimiento InfoConocimiento);
	public abstract InfoConocimiento obtenerInfoConocimiento(Integer Id);
	public abstract boolean borrarInfoConocimiento(InfoConocimiento InfoConocimiento);
	public abstract List<InfoConocimiento> obtenerInfoConocimientos();
	
}
