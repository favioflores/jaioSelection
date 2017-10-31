package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Entity;

@Entity
public class ComunicadoProcesoId implements java.io.Serializable {

	private int procesoSeleccionId;
	private int modeloComunicadoId;

	public ComunicadoProcesoId() {
	}

	public ComunicadoProcesoId(int procesoSeleccionId, int modeloComunicadoId) {
		this.procesoSeleccionId = procesoSeleccionId;
		this.modeloComunicadoId = modeloComunicadoId;
	}

	public int getProcesoSeleccionId() {
		return this.procesoSeleccionId;
	}

	public void setProcesoSeleccionId(int procesoSeleccionId) {
		this.procesoSeleccionId = procesoSeleccionId;
	}

	public int getModeloComunicadoId() {
		return this.modeloComunicadoId;
	}

	public void setModeloComunicadoId(int modeloComunicadoId) {
		this.modeloComunicadoId = modeloComunicadoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ComunicadoProcesoId))
			return false;
		ComunicadoProcesoId castOther = (ComunicadoProcesoId) other;

		return (this.getProcesoSeleccionId() == castOther.getProcesoSeleccionId())
				&& (this.getModeloComunicadoId() == castOther.getModeloComunicadoId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProcesoSeleccionId();
		result = 37 * result + this.getModeloComunicadoId();
		return result;
	}

}
