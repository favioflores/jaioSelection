package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.Candidato;

public interface CandidatoDAO {

	public abstract boolean crearCandidato(Candidato Candidato);
	public abstract boolean actualizaCandidato(Candidato Candidato);
	public abstract Candidato obtenerCandidato(Integer Id);
	public abstract boolean borrarCandidato(Candidato Candidato);
	public abstract List<Candidato> obtenerCandidatos();
	
}
