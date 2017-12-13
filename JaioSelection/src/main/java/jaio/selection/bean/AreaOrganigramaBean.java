package jaio.selection.bean;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.OrganigramNode;

public class AreaOrganigramaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String descripcion;
	private String id_parent;
	private OrganigramNode node;
	private List<PerfilBean> lstPerfiles;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getId_parent() {
		return id_parent;
	}
	public void setId_parent(String id_parent) {
		this.id_parent = id_parent;
	}
	public OrganigramNode getNode() {
		return node;
	}
	public void setNode(OrganigramNode node) {
		this.node = node;
	}
	public List<PerfilBean> getLstPerfiles() {
		return lstPerfiles;
	}
	public void setLstPerfiles(List<PerfilBean> lstPerfiles) {
		this.lstPerfiles = lstPerfiles;
	}

}