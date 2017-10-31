package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity2.Usuario;

public interface UsuarioDAO {

	public abstract boolean crearUsuario(Usuario Usuario);
	public abstract boolean actualizaUsuario(Usuario Usuario);
	public abstract Usuario obtenerUsuario(Integer Id);
	public abstract boolean borrarUsuario(Usuario Usuario);
	public abstract List<Usuario> obtenerUsuarios();
	
}
