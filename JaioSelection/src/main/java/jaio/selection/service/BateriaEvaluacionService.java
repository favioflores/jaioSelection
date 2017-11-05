package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.BateriaEvaluacion;

public interface BateriaEvaluacionService {

	public abstract boolean crearBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion);
	public abstract boolean actualizaBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion);
	public abstract BateriaEvaluacion obtenerBateriaEvaluacion(Integer Id);
	public abstract boolean borrarBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion);
	public abstract List<BateriaEvaluacion> obtenerBateriaEvaluacions();
	
}
