package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.ModeloComunicado;

public interface ModeloComunicadoService {

	public abstract boolean crearModeloComunicado(ModeloComunicado ModeloComunicado);
	public abstract boolean actualizaModeloComunicado(ModeloComunicado ModeloComunicado);
	public abstract ModeloComunicado obtenerModeloComunicado(Integer Id);
	public abstract boolean borrarModeloComunicado(ModeloComunicado ModeloComunicado);
	public abstract List<ModeloComunicado> obtenerModeloComunicados();
	
}
