package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.InfoReferenciaDAOImpl;
import jaio.selection.entity2.InfoReferencia;
import jaio.selection.service.InfoReferenciaService;

public class InfoReferenciaServiceImpl implements InfoReferenciaService, Serializable {

	private InfoReferenciaDAOImpl InfoReferenciaDAO;

	public void setInfoReferenciaDAO(InfoReferenciaDAOImpl InfoReferenciaDAO) {
		this.InfoReferenciaDAO = InfoReferenciaDAO;
	}

	public boolean crearInfoReferencia(InfoReferencia InfoReferencia) {
		return InfoReferenciaDAO.crearInfoReferencia(InfoReferencia);
	}
	public boolean actualizaInfoReferencia(InfoReferencia InfoReferencia) {
		return InfoReferenciaDAO.actualizaInfoReferencia(InfoReferencia);
	}
	public InfoReferencia obtenerInfoReferencia(Integer Id) {
		return InfoReferenciaDAO.obtenerInfoReferencia(Id);
	}
	public boolean borrarInfoReferencia(InfoReferencia InfoReferencia) {
		return InfoReferenciaDAO.borrarInfoReferencia(InfoReferencia);
	}
	public List<InfoReferencia> obtenerInfoReferencias() {
		return InfoReferenciaDAO.obtenerInfoReferencias();
	}
	
}