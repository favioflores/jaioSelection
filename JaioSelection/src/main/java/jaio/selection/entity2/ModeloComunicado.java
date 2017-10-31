package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class ModeloComunicado implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String asunto;
	private byte[] contenido;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private int estado;
	private Set comunicadoProcesos = new HashSet(0);

	public ModeloComunicado() {
	}

	public ModeloComunicado(String nombre, String asunto, byte[] contenido, Date fechaRegistro, Date fechaActualizacion,
			int estado) {
		this.nombre = nombre;
		this.asunto = asunto;
		this.contenido = contenido;
		this.fechaRegistro = fechaRegistro;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}

	public ModeloComunicado(String nombre, String asunto, byte[] contenido, Date fechaRegistro, Date fechaActualizacion,
			int estado, Set comunicadoProcesos) {
		this.nombre = nombre;
		this.asunto = asunto;
		this.contenido = contenido;
		this.fechaRegistro = fechaRegistro;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.comunicadoProcesos = comunicadoProcesos;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public byte[] getContenido() {
		return this.contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Set getComunicadoProcesos() {
		return this.comunicadoProcesos;
	}

	public void setComunicadoProcesos(Set comunicadoProcesos) {
		this.comunicadoProcesos = comunicadoProcesos;
	}

}
