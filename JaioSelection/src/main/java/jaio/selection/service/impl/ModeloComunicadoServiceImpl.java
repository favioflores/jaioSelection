package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.ModeloComunicadoDAOImpl;
import jaio.selection.entity2.ModeloComunicado;
import jaio.selection.service.ModeloComunicadoService;

public class ModeloComunicadoServiceImpl implements ModeloComunicadoService, Serializable {

	private ModeloComunicadoDAOImpl ModeloComunicadoDAO;

	public void setModeloComunicadoDAO(ModeloComunicadoDAOImpl ModeloComunicadoDAO) {
		this.ModeloComunicadoDAO = ModeloComunicadoDAO;
	}

	public boolean crearModeloComunicado(ModeloComunicado ModeloComunicado) {
		return ModeloComunicadoDAO.crearModeloComunicado(ModeloComunicado);
	}
	public boolean actualizaModeloComunicado(ModeloComunicado ModeloComunicado) {
		return ModeloComunicadoDAO.actualizaModeloComunicado(ModeloComunicado);
	}
	public ModeloComunicado obtenerModeloComunicado(Integer Id) {
		return ModeloComunicadoDAO.obtenerModeloComunicado(Id);
	}
	public boolean borrarModeloComunicado(ModeloComunicado ModeloComunicado) {
		return ModeloComunicadoDAO.borrarModeloComunicado(ModeloComunicado);
	}
	public List<ModeloComunicado> obtenerModeloComunicados() {
		return ModeloComunicadoDAO.obtenerModeloComunicados();
	}
	
}