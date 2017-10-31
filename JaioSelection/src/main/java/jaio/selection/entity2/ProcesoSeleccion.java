package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class ProcesoSeleccion implements java.io.Serializable {

	private Integer id;
	private Perfil perfil;
	private Date fechaRegistro;
	private String descripcion;
	private int estado;
	private Set candidatos = new HashSet(0);
	private Set comunicadoProcesos = new HashSet(0);
	private Set notificacioneses = new HashSet(0);

	public ProcesoSeleccion() {
	}

	public ProcesoSeleccion(Perfil perfil, Date fechaRegistro, String descripcion, int estado) {
		this.perfil = perfil;
		this.fechaRegistro = fechaRegistro;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public ProcesoSeleccion(Perfil perfil, Date fechaRegistro, String descripcion, int estado, Set candidatos,
			Set comunicadoProcesos, Set notificacioneses) {
		this.perfil = perfil;
		this.fechaRegistro = fechaRegistro;
		this.descripcion = descripcion;
		this.estado = estado;
		this.candidatos = candidatos;
		this.comunicadoProcesos = comunicadoProcesos;
		this.notificacioneses = notificacioneses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Set getCandidatos() {
		return this.candidatos;
	}

	public void setCandidatos(Set candidatos) {
		this.candidatos = candidatos;
	}

	public Set getComunicadoProcesos() {
		return this.comunicadoProcesos;
	}

	public void setComunicadoProcesos(Set comunicadoProcesos) {
		this.comunicadoProcesos = comunicadoProcesos;
	}

	public Set getNotificacioneses() {
		return this.notificacioneses;
	}

	public void setNotificacioneses(Set notificacioneses) {
		this.notificacioneses = notificacioneses;
	}

}
