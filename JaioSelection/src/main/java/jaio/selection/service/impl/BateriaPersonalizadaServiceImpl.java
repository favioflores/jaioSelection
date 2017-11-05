package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.BateriaPersonalizadaDAOImpl;
import jaio.selection.entity.BateriaPersonalizada;
import jaio.selection.service.BateriaPersonalizadaService;

public class BateriaPersonalizadaServiceImpl implements BateriaPersonalizadaService, Serializable {

	private BateriaPersonalizadaDAOImpl BateriaPersonalizadaDAO;

	public void setBateriaPersonalizadaDAO(BateriaPersonalizadaDAOImpl BateriaPersonalizadaDAO) {
		this.BateriaPersonalizadaDAO = BateriaPersonalizadaDAO;
	}

	public boolean crearBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada) {
		return BateriaPersonalizadaDAO.crearBateriaPersonalizada(BateriaPersonalizada);
	}
	public boolean actualizaBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada) {
		return BateriaPersonalizadaDAO.actualizaBateriaPersonalizada(BateriaPersonalizada);
	}
	public BateriaPersonalizada obtenerBateriaPersonalizada(Integer Id) {
		return BateriaPersonalizadaDAO.obtenerBateriaPersonalizada(Id);
	}
	public boolean borrarBateriaPersonalizada(BateriaPersonalizada BateriaPersonalizada) {
		return BateriaPersonalizadaDAO.borrarBateriaPersonalizada(BateriaPersonalizada);
	}
	public List<BateriaPersonalizada> obtenerBateriaPersonalizadas() {
		return BateriaPersonalizadaDAO.obtenerBateriaPersonalizadas();
	}
	
}