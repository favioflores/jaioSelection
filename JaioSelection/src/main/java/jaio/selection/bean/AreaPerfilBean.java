package jaio.selection.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.orm.Area;
import jaio.selection.orm.Perfil;

public class AreaPerfilBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(AreaPerfilBean.class);

	private Area area;
	private Area parentArea;
	private List<Perfil> lstPerfiles;
	
	public Area getParentArea() {
		return parentArea;
	}
	public void setParentArea(Area parentArea) {
		this.parentArea = parentArea;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public List<Perfil> getLstPerfiles() {
		return lstPerfiles;
	}
	public void setLstPerfiles(List<Perfil> lstPerfiles) {
		this.lstPerfiles = lstPerfiles;
	}
	
}
