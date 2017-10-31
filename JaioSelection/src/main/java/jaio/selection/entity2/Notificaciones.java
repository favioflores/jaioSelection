package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Notificaciones implements java.io.Serializable {

	private Integer id;
	private ProcesoSeleccion procesoSeleccion;
	private String asunto;
	private byte[] contenido;
	private String correo;
	private int estado;
	private Date fechaRegistro;
	private Date fechaEnvio;

	public Notificaciones() {
	}

	public Notificaciones(ProcesoSeleccion procesoSeleccion, String asunto, byte[] contenido, String correo, int estado,
			Date fechaRegistro) {
		this.procesoSeleccion = procesoSeleccion;
		this.asunto = asunto;
		this.contenido = contenido;
		this.correo = correo;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
	}

	public Notificaciones(ProcesoSeleccion procesoSeleccion, String asunto, byte[] contenido, String correo, int estado,
			Date fechaRegistro, Date fechaEnvio) {
		this.procesoSeleccion = procesoSeleccion;
		this.asunto = asunto;
		this.contenido = contenido;
		this.correo = correo;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
		this.fechaEnvio = fechaEnvio;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProcesoSeleccion getProcesoSeleccion() {
		return this.procesoSeleccion;
	}

	public void setProcesoSeleccion(ProcesoSeleccion procesoSeleccion) {
		this.procesoSeleccion = procesoSeleccion;
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

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

}
