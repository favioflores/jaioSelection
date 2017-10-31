package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.PerfilDAOImpl;
import jaio.selection.entity2.Perfil;
import jaio.selection.service.PerfilService;

public class PerfilServiceImpl implements PerfilService, Serializable {

	private PerfilDAOImpl PerfilDAO;

	public void setPerfilDAO(PerfilDAOImpl PerfilDAO) {
		this.PerfilDAO = PerfilDAO;
	}

	public boolean crearPerfil(Perfil Perfil) {
		return PerfilDAO.crearPerfil(Perfil);
	}
	public boolean actualizaPerfil(Perfil Perfil) {
		return PerfilDAO.actualizaPerfil(Perfil);
	}
	public Perfil obtenerPerfil(Integer Id) {
		return PerfilDAO.obtenerPerfil(Id);
	}
	public boolean borrarPerfil(Perfil Perfil) {
		return PerfilDAO.borrarPerfil(Perfil);
	}
	public List<Perfil> obtenerPerfils() {
		return PerfilDAO.obtenerPerfils();
	}
	
}