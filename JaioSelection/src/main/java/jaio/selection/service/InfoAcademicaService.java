package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.InfoAcademica;

public interface InfoAcademicaService {

	public abstract boolean crearInfoAcademica(InfoAcademica InfoAcademica);
	public abstract boolean actualizaInfoAcademica(InfoAcademica InfoAcademica);
	public abstract InfoAcademica obtenerInfoAcademica(Integer Id);
	public abstract boolean borrarInfoAcademica(InfoAcademica InfoAcademica);
	public abstract List<InfoAcademica> obtenerInfoAcademicas();
	
}
