package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Entity;

@Entity
public class InfoConocimiento implements java.io.Serializable {

	private Integer id;
	private Candidato candidato;
	private String nombre;
	private String nivel;

	public InfoConocimiento() {
	}

	public InfoConocimiento(Candidato candidato, String nombre) {
		this.candidato = candidato;
		this.nombre = nombre;
	}

	public InfoConocimiento(Candidato candidato, String nombre, String nivel) {
		this.candidato = candidato;
		this.nombre = nombre;
		this.nivel = nivel;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
