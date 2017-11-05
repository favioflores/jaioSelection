package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.EmpresaDAOImpl;
import jaio.selection.entity.Empresa;
import jaio.selection.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService, Serializable {

	private EmpresaDAOImpl EmpresaDAO;

	public void setEmpresaDAO(EmpresaDAOImpl EmpresaDAO) {
		this.EmpresaDAO = EmpresaDAO;
	}

	public boolean crearEmpresa(Empresa Empresa) {
		return EmpresaDAO.crearEmpresa(Empresa);
	}
	public boolean actualizaEmpresa(Empresa Empresa) {
		return EmpresaDAO.actualizaEmpresa(Empresa);
	}
	public Empresa obtenerEmpresa(Integer Id) {
		return EmpresaDAO.obtenerEmpresa(Id);
	}
	public boolean borrarEmpresa(Empresa Empresa) {
		return EmpresaDAO.borrarEmpresa(Empresa);
	}
	public List<Empresa> obtenerEmpresas() {
		return EmpresaDAO.obtenerEmpresas();
	}
	
}