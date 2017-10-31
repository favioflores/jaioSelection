package jaio.selection.service;

import java.util.List;

import jaio.selection.entity2.Perfil;

public interface PerfilService {

	public abstract boolean crearPerfil(Perfil Perfil);
	public abstract boolean actualizaPerfil(Perfil Perfil);
	public abstract Perfil obtenerPerfil(Integer Id);
	public abstract boolean borrarPerfil(Perfil Perfil);
	public abstract List<Perfil> obtenerPerfils();
	
}
