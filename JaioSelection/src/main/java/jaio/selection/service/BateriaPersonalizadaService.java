package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.BateriaPersonalizada;

public interface BateriaPersonalizadaService {

	public abstract boolean crearBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada);
	public abstract boolean actualizaBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada);
	public abstract BateriaPersonalizada obtenerBateriaPersonalizada(Integer Id);
	public abstract boolean borrarBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada);
	public abstract List<BateriaPersonalizada> obtenerBateriaPersonalizadas();
	
}
