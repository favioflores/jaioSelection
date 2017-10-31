package jaio.selection.service;

import java.util.List;

import jaio.selection.entity2.InfoExperiencia;

public interface InfoExperienciaService {

	public abstract boolean crearInfoExperiencia(InfoExperiencia InfoExperiencia);
	public abstract boolean actualizaInfoExperiencia(InfoExperiencia InfoExperiencia);
	public abstract InfoExperiencia obtenerInfoExperiencia(Integer Id);
	public abstract boolean borrarInfoExperiencia(InfoExperiencia InfoExperiencia);
	public abstract List<InfoExperiencia> obtenerInfoExperiencias();
	
}
