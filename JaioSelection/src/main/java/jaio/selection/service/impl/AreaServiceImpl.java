package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jaio.selection.dao.impl.AreaDAOImpl;
import jaio.selection.entity.Area;
import jaio.selection.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService, Serializable {

	private AreaDAOImpl areaDAO;

	public void setAreaDAO(AreaDAOImpl areaDAO) {
		this.areaDAO = areaDAO;
	}

	@Transactional
	public boolean crearArea(Area Area) {
		return areaDAO.crearArea(Area);
	}
	
	@Transactional
	public boolean actualizaArea(Area Area) {
		return areaDAO.actualizaArea(Area);
	}
	
	@Transactional
	public Area obtenerArea(Integer Id) {
		return areaDAO.obtenerArea(Id);
	}
	
	@Transactional
	public boolean borrarArea(Area Area) {
		return areaDAO.borrarArea(Area);
	}
	
	@Transactional
	public List<Area> obtenerAreas() {
		return areaDAO.obtenerAreas();
	}
	
}