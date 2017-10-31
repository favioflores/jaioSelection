package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "area")
public class Area implements java.io.Serializable {

	@Id
	private Integer id;
	private Area area;
	private Empresa empresa;
	private String descripcion;
	private Date fechaRegistro;
	private int estado;
	private Set areas = new HashSet(0);
	private Set perfils = new HashSet(0);

	public Area() {
	}

	public Area(Empresa empresa, String descripcion, Date fechaRegistro, int estado) {
		this.empresa = empresa;
		this.descripcion = descripcion;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
	}

	public Area(Area area, Empresa empresa, String descripcion, Date fechaRegistro, int estado, Set areas,
			Set perfils) {
		this.area = area;
		this.empresa = empresa;
		this.descripcion = descripcion;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.areas = areas;
		this.perfils = perfils;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Set getAreas() {
		return this.areas;
	}

	public void setAreas(Set areas) {
		this.areas = areas;
	}

	public Set getPerfils() {
		return this.perfils;
	}

	public void setPerfils(Set perfils) {
		this.perfils = perfils;
	}

}
