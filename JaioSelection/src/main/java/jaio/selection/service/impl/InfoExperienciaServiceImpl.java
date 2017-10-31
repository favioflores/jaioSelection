package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.InfoExperienciaDAOImpl;
import jaio.selection.entity2.InfoExperiencia;
import jaio.selection.service.InfoExperienciaService;

public class InfoExperienciaServiceImpl implements InfoExperienciaService, Serializable {

	private InfoExperienciaDAOImpl InfoExperienciaDAO;

	public void setInfoExperienciaDAO(InfoExperienciaDAOImpl InfoExperienciaDAO) {
		this.InfoExperienciaDAO = InfoExperienciaDAO;
	}

	public boolean crearInfoExperiencia(InfoExperiencia InfoExperiencia) {
		return InfoExperienciaDAO.crearInfoExperiencia(InfoExperiencia);
	}
	public boolean actualizaInfoExperiencia(InfoExperiencia InfoExperiencia) {
		return InfoExperienciaDAO.actualizaInfoExperiencia(InfoExperiencia);
	}
	public InfoExperiencia obtenerInfoExperiencia(Integer Id) {
		return InfoExperienciaDAO.obtenerInfoExperiencia(Id);
	}
	public boolean borrarInfoExperiencia(InfoExperiencia InfoExperiencia) {
		return InfoExperienciaDAO.borrarInfoExperiencia(InfoExperiencia);
	}
	public List<InfoExperiencia> obtenerInfoExperiencias() {
		return InfoExperienciaDAO.obtenerInfoExperiencias();
	}
	
}