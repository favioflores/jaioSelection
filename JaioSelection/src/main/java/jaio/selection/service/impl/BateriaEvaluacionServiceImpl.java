package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.BateriaEvaluacionDAOImpl;
import jaio.selection.entity2.BateriaEvaluacion;
import jaio.selection.service.BateriaEvaluacionService;

public class BateriaEvaluacionServiceImpl implements BateriaEvaluacionService, Serializable {

	private BateriaEvaluacionDAOImpl BateriaEvaluacionDAO;

	public void setBateriaEvaluacionDAO(BateriaEvaluacionDAOImpl BateriaEvaluacionDAO) {
		this.BateriaEvaluacionDAO = BateriaEvaluacionDAO;
	}

	public boolean crearBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion) {
		return BateriaEvaluacionDAO.crearBateriaEvaluacion(BateriaEvaluacion);
	}
	public boolean actualizaBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion) {
		return BateriaEvaluacionDAO.actualizaBateriaEvaluacion(BateriaEvaluacion);
	}
	public BateriaEvaluacion obtenerBateriaEvaluacion(Integer Id) {
		return BateriaEvaluacionDAO.obtenerBateriaEvaluacion(Id);
	}
	public boolean borrarBateriaEvaluacion(BateriaEvaluacion BateriaEvaluacion) {
		return BateriaEvaluacionDAO.borrarBateriaEvaluacion(BateriaEvaluacion);
	}
	public List<BateriaEvaluacion> obtenerBateriaEvaluacions() {
		return BateriaEvaluacionDAO.obtenerBateriaEvaluacions();
	}
	
}