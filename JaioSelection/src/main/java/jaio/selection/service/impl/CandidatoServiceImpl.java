package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.CandidatoDAOImpl;
import jaio.selection.entity.Candidato;
import jaio.selection.service.CandidatoService;

public class CandidatoServiceImpl implements CandidatoService, Serializable {

	private CandidatoDAOImpl CandidatoDAO;

	public void setCandidatoDAO(CandidatoDAOImpl CandidatoDAO) {
		this.CandidatoDAO = CandidatoDAO;
	}

	public boolean crearCandidato(Candidato Candidato) {
		return CandidatoDAO.crearCandidato(Candidato);
	}
	public boolean actualizaCandidato(Candidato Candidato) {
		return CandidatoDAO.actualizaCandidato(Candidato);
	}
	public Candidato obtenerCandidato(Integer Id) {
		return CandidatoDAO.obtenerCandidato(Id);
	}
	public boolean borrarCandidato(Candidato Candidato) {
		return CandidatoDAO.borrarCandidato(Candidato);
	}
	public List<Candidato> obtenerCandidatos() {
		return CandidatoDAO.obtenerCandidatos();
	}
	
}