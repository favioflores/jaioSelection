package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Candidato implements java.io.Serializable {

	private Integer id;
	private ProcesoSeleccion procesoSeleccion;
	private String nombre;
	private String apellidoParterno;
	private String apellidoMaterno;
	private String nroDocumento;
	private int tipoDocumento;
	private String movil;
	private String telefono;
	private String direccion;
	private Date fechaNacimiento;
	private int distrito;
	private String correo;
	private int estado;
	private int prioridad;
	private byte esApto;
	private byte esFinalista;
	private Set infoAcademicas = new HashSet(0);
	private Set infoConocimientos = new HashSet(0);
	private Set infoExperiencias = new HashSet(0);

	public Candidato() {
	}

	public Candidato(ProcesoSeleccion procesoSeleccion, String nombre, String apellidoParterno, String nroDocumento,
			int tipoDocumento, int distrito, String correo, int estado, int prioridad, byte esApto, byte esFinalista) {
		this.procesoSeleccion = procesoSeleccion;
		this.nombre = nombre;
		this.apellidoParterno = apellidoParterno;
		this.nroDocumento = nroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.distrito = distrito;
		this.correo = correo;
		this.estado = estado;
		this.prioridad = prioridad;
		this.esApto = esApto;
		this.esFinalista = esFinalista;
	}

	public Candidato(ProcesoSeleccion procesoSeleccion, String nombre, String apellidoParterno, String apellidoMaterno,
			String nroDocumento, int tipoDocumento, String movil, String telefono, String direccion,
			Date fechaNacimiento, int distrito, String correo, int estado, int prioridad, byte esApto, byte esFinalista,
			Set infoAcademicas, Set infoConocimientos, Set infoExperiencias) {
		this.procesoSeleccion = procesoSeleccion;
		this.nombre = nombre;
		this.apellidoParterno = apellidoParterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nroDocumento = nroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.movil = movil;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.distrito = distrito;
		this.correo = correo;
		this.estado = estado;
		this.prioridad = prioridad;
		this.esApto = esApto;
		this.esFinalista = esFinalista;
		this.infoAcademicas = infoAcademicas;
		this.infoConocimientos = infoConocimientos;
		this.infoExperiencias = infoExperiencias;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoParterno() {
		return this.apellidoParterno;
	}

	public void setApellidoParterno(String apellidoParterno) {
		this.apellidoParterno = apellidoParterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNroDocumento() {
		return this.nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public int getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getMovil() {
		return this.movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getDistrito() {
		return this.distrito;
	}

	public void setDistrito(int distrito) {
		this.distrito = distrito;
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

	public int getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public byte getEsApto() {
		return this.esApto;
	}

	public void setEsApto(byte esApto) {
		this.esApto = esApto;
	}

	public byte getEsFinalista() {
		return this.esFinalista;
	}

	public void setEsFinalista(byte esFinalista) {
		this.esFinalista = esFinalista;
	}

	public Set getInfoAcademicas() {
		return this.infoAcademicas;
	}

	public void setInfoAcademicas(Set infoAcademicas) {
		this.infoAcademicas = infoAcademicas;
	}

	public Set getInfoConocimientos() {
		return this.infoConocimientos;
	}

	public void setInfoConocimientos(Set infoConocimientos) {
		this.infoConocimientos = infoConocimientos;
	}

	public Set getInfoExperiencias() {
		return this.infoExperiencias;
	}

	public void setInfoExperiencias(Set infoExperiencias) {
		this.infoExperiencias = infoExperiencias;
	}

}
