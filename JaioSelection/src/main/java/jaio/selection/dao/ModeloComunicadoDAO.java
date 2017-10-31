package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity2.ModeloComunicado;

public interface ModeloComunicadoDAO {

	public abstract boolean crearModeloComunicado(ModeloComunicado ModeloComunicado);
	public abstract boolean actualizaModeloComunicado(ModeloComunicado ModeloComunicado);
	public abstract ModeloComunicado obtenerModeloComunicado(Integer Id);
	public abstract boolean borrarModeloComunicado(ModeloComunicado ModeloComunicado);
	public abstract List<ModeloComunicado> obtenerModeloComunicados();
	
}
