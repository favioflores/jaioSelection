package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.ModeloCompetenciaSinonimo;

public interface ModeloCompetenciaSinonimoService {

	public abstract boolean crearModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo);
	public abstract boolean actualizaModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo);
	public abstract ModeloCompetenciaSinonimo obtenerModeloCompetenciaSinonimo(Integer Id);
	public abstract boolean borrarModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo);
	public abstract List<ModeloCompetenciaSinonimo> obtenerModeloCompetenciaSinonimos();
	
}
