package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Perfil implements java.io.Serializable {

	private Integer id;
	private Area area;
	private Empresa empresa;
	private String nombre;
	private Date fechaRegistro;
	private int estado;
	private String descripcionFuncion;
	private Set evaluacionPerfils = new HashSet(0);
	private Set procesoSeleccions = new HashSet(0);

	public Perfil() {
	}

	public Perfil(Area area, Empresa empresa, String nombre, Date fechaRegistro, int estado) {
		this.area = area;
		this.empresa = empresa;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
	}

	public Perfil(Area area, Empresa empresa, String nombre, Date fechaRegistro, int estado, String descripcionFuncion,
			Set evaluacionPerfils, Set procesoSeleccions) {
		this.area = area;
		this.empresa = empresa;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.descripcionFuncion = descripcionFuncion;
		this.evaluacionPerfils = evaluacionPerfils;
		this.procesoSeleccions = procesoSeleccions;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getDescripcionFuncion() {
		return this.descripcionFuncion;
	}

	public void setDescripcionFuncion(String descripcionFuncion) {
		this.descripcionFuncion = descripcionFuncion;
	}

	public Set getEvaluacionPerfils() {
		return this.evaluacionPerfils;
	}

	public void setEvaluacionPerfils(Set evaluacionPerfils) {
		this.evaluacionPerfils = evaluacionPerfils;
	}

	public Set getProcesoSeleccions() {
		return this.procesoSeleccions;
	}

	public void setProcesoSeleccions(Set procesoSeleccions) {
		this.procesoSeleccions = procesoSeleccions;
	}

}
