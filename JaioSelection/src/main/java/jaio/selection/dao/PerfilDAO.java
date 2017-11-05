package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.Perfil;

public interface PerfilDAO {

	public abstract boolean crearPerfil(Perfil Perfil);
	public abstract boolean actualizaPerfil(Perfil Perfil);
	public abstract Perfil obtenerPerfil(Integer Id);
	public abstract boolean borrarPerfil(Perfil Perfil);
	public abstract List<Perfil> obtenerPerfils();
	
}
