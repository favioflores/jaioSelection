package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.InfoExperiencia;

public interface InfoExperienciaDAO {

	public abstract boolean crearInfoExperiencia(InfoExperiencia InfoExperiencia);
	public abstract boolean actualizaInfoExperiencia(InfoExperiencia InfoExperiencia);
	public abstract InfoExperiencia obtenerInfoExperiencia(Integer Id);
	public abstract boolean borrarInfoExperiencia(InfoExperiencia InfoExperiencia);
	public abstract List<InfoExperiencia> obtenerInfoExperiencias();
	
}
