package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class InfoExperiencia implements java.io.Serializable {

	private Integer id;
	private Candidato candidato;
	private String empresa;
	private Date fechaInicio;
	private Date fechaFin;
	private byte[] logro;
	private String cargo;
	private Set infoReferencias = new HashSet(0);

	public InfoExperiencia() {
	}

	public InfoExperiencia(Candidato candidato, String empresa, Date fechaInicio, String cargo) {
		this.candidato = candidato;
		this.empresa = empresa;
		this.fechaInicio = fechaInicio;
		this.cargo = cargo;
	}

	public InfoExperiencia(Candidato candidato, String empresa, Date fechaInicio, Date fechaFin, byte[] logro,
			String cargo, Set infoReferencias) {
		this.candidato = candidato;
		this.empresa = empresa;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.logro = logro;
		this.cargo = cargo;
		this.infoReferencias = infoReferencias;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Candidato getCandidato() {
		return this.candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public byte[] getLogro() {
		return this.logro;
	}

	public void setLogro(byte[] logro) {
		this.logro = logro;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Set getInfoReferencias() {
		return this.infoReferencias;
	}

	public void setInfoReferencias(Set infoReferencias) {
		this.infoReferencias = infoReferencias;
	}

}
