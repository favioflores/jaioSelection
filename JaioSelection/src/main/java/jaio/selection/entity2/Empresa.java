package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Empresa implements java.io.Serializable {

	private Integer id;
	private Usuario usuario;
	private String nombre;
	private Date fechaRegistro;
	private int estado;
	private byte predeterminado;
	private byte[] imagen;
	private Set areas = new HashSet(0);
	private Set perfils = new HashSet(0);

	public Empresa() {
	}

	public Empresa(Usuario usuario, String nombre, Date fechaRegistro, int estado, byte predeterminado) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.predeterminado = predeterminado;
	}

	public Empresa(Usuario usuario, String nombre, Date fechaRegistro, int estado, byte predeterminado, byte[] imagen,
			Set areas, Set perfils) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.predeterminado = predeterminado;
		this.imagen = imagen;
		this.areas = areas;
		this.perfils = perfils;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public byte getPredeterminado() {
		return this.predeterminado;
	}

	public void setPredeterminado(byte predeterminado) {
		this.predeterminado = predeterminado;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
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
