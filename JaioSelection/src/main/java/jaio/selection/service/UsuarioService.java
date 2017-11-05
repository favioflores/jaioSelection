package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.Usuario;

public interface UsuarioService {

	public abstract boolean crearUsuario(Usuario Usuario);
	public abstract boolean actualizaUsuario(Usuario Usuario);
	public abstract Usuario obtenerUsuario(Integer Id);
	public abstract Usuario obtenerUsuario(String correo, String contrasena);
	public abstract boolean borrarUsuario(Usuario Usuario);
	public abstract List<Usuario> obtenerUsuarios();

}
