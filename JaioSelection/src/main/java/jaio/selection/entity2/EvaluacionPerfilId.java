package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Entity;

@Entity
public class EvaluacionPerfilId implements java.io.Serializable {

	private int perfilId;
	private int bateriaPersonalizadaId;
	private int estado;

	public EvaluacionPerfilId() {
	}

	public EvaluacionPerfilId(int perfilId, int bateriaPersonalizadaId, int estado) {
		this.perfilId = perfilId;
		this.bateriaPersonalizadaId = bateriaPersonalizadaId;
		this.estado = estado;
	}

	public int getPerfilId() {
		return this.perfilId;
	}

	public void setPerfilId(int perfilId) {
		this.perfilId = perfilId;
	}

	public int getBateriaPersonalizadaId() {
		return this.bateriaPersonalizadaId;
	}

	public void setBateriaPersonalizadaId(int bateriaPersonalizadaId) {
		this.bateriaPersonalizadaId = bateriaPersonalizadaId;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EvaluacionPerfilId))
			return false;
		EvaluacionPerfilId castOther = (EvaluacionPerfilId) other;

		return (this.getPerfilId() == castOther.getPerfilId())
				&& (this.getBateriaPersonalizadaId() == castOther.getBateriaPersonalizadaId())
				&& (this.getEstado() == castOther.getEstado());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPerfilId();
		result = 37 * result + this.getBateriaPersonalizadaId();
		result = 37 * result + this.getEstado();
		return result;
	}

}
