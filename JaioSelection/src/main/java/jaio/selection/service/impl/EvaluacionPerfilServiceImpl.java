package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.EvaluacionPerfilDAOImpl;
import jaio.selection.entity2.EvaluacionPerfil;
import jaio.selection.service.EvaluacionPerfilService;

public class EvaluacionPerfilServiceImpl implements EvaluacionPerfilService, Serializable {

	private EvaluacionPerfilDAOImpl EvaluacionPerfilDAO;

	public void setEvaluacionPerfilDAO(EvaluacionPerfilDAOImpl EvaluacionPerfilDAO) {
		this.EvaluacionPerfilDAO = EvaluacionPerfilDAO;
	}

	public boolean crearEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil) {
		return EvaluacionPerfilDAO.crearEvaluacionPerfil(EvaluacionPerfil);
	}
	public boolean actualizaEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil) {
		return EvaluacionPerfilDAO.actualizaEvaluacionPerfil(EvaluacionPerfil);
	}
	public EvaluacionPerfil obtenerEvaluacionPerfil(Integer Id) {
		return EvaluacionPerfilDAO.obtenerEvaluacionPerfil(Id);
	}
	public boolean borrarEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil) {
		return EvaluacionPerfilDAO.borrarEvaluacionPerfil(EvaluacionPerfil);
	}
	public List<EvaluacionPerfil> obtenerEvaluacionPerfils() {
		return EvaluacionPerfilDAO.obtenerEvaluacionPerfils();
	}
	
}