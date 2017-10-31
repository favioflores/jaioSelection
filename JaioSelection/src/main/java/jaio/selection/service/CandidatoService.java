package jaio.selection.service;

import java.util.List;

import jaio.selection.entity2.Candidato;

public interface CandidatoService {

	public abstract boolean crearCandidato(Candidato Candidato);
	public abstract boolean actualizaCandidato(Candidato Candidato);
	public abstract Candidato obtenerCandidato(Integer Id);
	public abstract boolean borrarCandidato(Candidato Candidato);
	public abstract List<Candidato> obtenerCandidatos();
	
}
