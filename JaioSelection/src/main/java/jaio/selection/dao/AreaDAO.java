package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity.Area;

public interface AreaDAO {

	public abstract boolean crearArea(Area Area);
	public abstract boolean actualizaArea(Area Area);
	public abstract Area obtenerArea(Integer Id);
	public abstract boolean borrarArea(Area Area);
	public abstract List<Area> obtenerAreas();

}
