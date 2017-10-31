package jaio.selection.service;

import java.util.List;

import jaio.selection.entity2.Empresa;

public interface EmpresaService {

	public abstract boolean crearEmpresa(Empresa Empresa);
	public abstract boolean actualizaEmpresa(Empresa Empresa);
	public abstract Empresa obtenerEmpresa(Integer Id);
	public abstract boolean borrarEmpresa(Empresa Empresa);
	public abstract List<Empresa> obtenerEmpresas();
	
}
