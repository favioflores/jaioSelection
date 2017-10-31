package jaio.selection.entity2;

import javax.persistence.Entity;

@Entity
public class InfoReferencia implements java.io.Serializable {

	private Integer id;
	private InfoExperiencia infoExperiencia;
	private String nombreCompleto;
	private String cargo;
	private String telefono;
	private String movil;

	public InfoReferencia() {
	}

	public InfoReferencia(InfoExperiencia infoExperiencia, String nombreCompleto, String cargo) {
		this.infoExperiencia = infoExperiencia;
		this.nombreCompleto = nombreCompleto;
		this.cargo = cargo;
	}

	public InfoReferencia(InfoExperiencia infoExperiencia, String nombreCompleto, String cargo, String telefono,
			String movil) {
		this.infoExperiencia = infoExperiencia;
		this.nombreCompleto = nombreCompleto;
		this.cargo = cargo;
		this.telefono = telefono;
		this.movil = movil;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InfoExperiencia getInfoExperiencia() {
		return this.infoExperiencia;
	}

	public void setInfoExperiencia(InfoExperiencia infoExperiencia) {
		this.infoExperiencia = infoExperiencia;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return this.movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

}
