package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Usuario implements java.io.Serializable {

	private Integer id;
	private String correo;
	private String nombreCompleto;
	private Date fechaRegistro;
	private int estado;
	private String contrasena;
	private Set empresas = new HashSet(0);

	public Usuario() {
	}

	public Usuario(String correo, String nombreCompleto, Date fechaRegistro, int estado, String contrasena) {
		this.correo = correo;
		this.nombreCompleto = nombreCompleto;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.contrasena = contrasena;
	}

	public Usuario(String correo, String nombreCompleto, Date fechaRegistro, int estado, String contrasena,
			Set empresas) {
		this.correo = correo;
		this.nombreCompleto = nombreCompleto;
		this.fechaRegistro = fechaRegistro;
		this.estado = estado;
		this.contrasena = contrasena;
		this.empresas = empresas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
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

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Set getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set empresas) {
		this.empresas = empresas;
	}

}
