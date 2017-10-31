package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity2.ModeloCompetenciaSinonimo;

public interface ModeloCompetenciaSinonimoDAO {

	public abstract boolean crearModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo);
	public abstract boolean actualizaModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo);
	public abstract ModeloCompetenciaSinonimo obtenerModeloCompetenciaSinonimo(Integer Id);
	public abstract boolean borrarModeloCompetenciaSinonimo(ModeloCompetenciaSinonimo ModeloCompetenciaSinonimo);
	public abstract List<ModeloCompetenciaSinonimo> obtenerModeloCompetenciaSinonimos();
	
}
