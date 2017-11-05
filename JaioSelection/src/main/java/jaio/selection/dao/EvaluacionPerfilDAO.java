package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.EvaluacionPerfil;

public interface EvaluacionPerfilDAO {

	public abstract boolean crearEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil);
	public abstract boolean actualizaEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil);
	public abstract EvaluacionPerfil obtenerEvaluacionPerfil(Integer Id);
	public abstract boolean borrarEvaluacionPerfil(EvaluacionPerfil EvaluacionPerfil);
	public abstract List<EvaluacionPerfil> obtenerEvaluacionPerfils();
	
}
