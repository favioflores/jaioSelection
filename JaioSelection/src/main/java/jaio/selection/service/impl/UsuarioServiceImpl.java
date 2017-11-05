package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import jaio.selection.dao.impl.UsuarioDAOImpl;
import jaio.selection.entity.Usuario;
import jaio.selection.service.UsuarioService;

@Service ("usuarioService")
public class UsuarioServiceImpl implements UsuarioService, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioDAOImpl UsuarioDAO;

	@Transactional
	public boolean crearUsuario(Usuario Usuario) {
		return UsuarioDAO.crearUsuario(Usuario);
	}

	@Transactional
	public boolean actualizaUsuario(Usuario Usuario) {
		return UsuarioDAO.actualizaUsuario(Usuario);
	}

	@Transactional
	public Usuario obtenerUsuario(Integer Id) {
		return UsuarioDAO.obtenerUsuario(Id);
	}

	@Transactional
	public Usuario obtenerUsuario(String correo, String contrasena) {
		return UsuarioDAO.obtenerUsuario(correo, contrasena);
	}

	@Transactional
	public boolean borrarUsuario(Usuario Usuario) {
		return UsuarioDAO.borrarUsuario(Usuario);
	}

	@Transactional
	public List<Usuario> obtenerUsuarios() {
		return UsuarioDAO.obtenerUsuarios();
	}

}