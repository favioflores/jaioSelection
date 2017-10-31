package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.InfoAcademicaDAOImpl;
import jaio.selection.entity2.InfoAcademica;
import jaio.selection.service.InfoAcademicaService;

public class InfoAcademicaServiceImpl implements InfoAcademicaService, Serializable {

	private InfoAcademicaDAOImpl InfoAcademicaDAO;

	public void setInfoAcademicaDAO(InfoAcademicaDAOImpl InfoAcademicaDAO) {
		this.InfoAcademicaDAO = InfoAcademicaDAO;
	}

	public boolean crearInfoAcademica(InfoAcademica InfoAcademica) {
		return InfoAcademicaDAO.crearInfoAcademica(InfoAcademica);
	}
	public boolean actualizaInfoAcademica(InfoAcademica InfoAcademica) {
		return InfoAcademicaDAO.actualizaInfoAcademica(InfoAcademica);
	}
	public InfoAcademica obtenerInfoAcademica(Integer Id) {
		return InfoAcademicaDAO.obtenerInfoAcademica(Id);
	}
	public boolean borrarInfoAcademica(InfoAcademica InfoAcademica) {
		return InfoAcademicaDAO.borrarInfoAcademica(InfoAcademica);
	}
	public List<InfoAcademica> obtenerInfoAcademicas() {
		return InfoAcademicaDAO.obtenerInfoAcademicas();
	}
	
}