package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.InfoAcademica;

public interface InfoAcademicaDAO {

	public abstract boolean crearInfoAcademica(InfoAcademica InfoAcademica);
	public abstract boolean actualizaInfoAcademica(InfoAcademica InfoAcademica);
	public abstract InfoAcademica obtenerInfoAcademica(Integer Id);
	public abstract boolean borrarInfoAcademica(InfoAcademica InfoAcademica);
	public abstract List<InfoAcademica> obtenerInfoAcademicas();
	
}
