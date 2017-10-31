package jaio.selection.entity2;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BateriaEvaluacionId implements java.io.Serializable {

	@Id
	private int modeloEvaluacionId;
	private int bateriaPersonalizadaId;

	public BateriaEvaluacionId() {
	}

	public BateriaEvaluacionId(int modeloEvaluacionId, int bateriaPersonalizadaId) {
		this.modeloEvaluacionId = modeloEvaluacionId;
		this.bateriaPersonalizadaId = bateriaPersonalizadaId;
	}

	public int getModeloEvaluacionId() {
		return this.modeloEvaluacionId;
	}

	public void setModeloEvaluacionId(int modeloEvaluacionId) {
		this.modeloEvaluacionId = modeloEvaluacionId;
	}

	public int getBateriaPersonalizadaId() {
		return this.bateriaPersonalizadaId;
	}

	public void setBateriaPersonalizadaId(int bateriaPersonalizadaId) {
		this.bateriaPersonalizadaId = bateriaPersonalizadaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BateriaEvaluacionId))
			return false;
		BateriaEvaluacionId castOther = (BateriaEvaluacionId) other;

		return (this.getModeloEvaluacionId() == castOther.getModeloEvaluacionId())
				&& (this.getBateriaPersonalizadaId() == castOther.getBateriaPersonalizadaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getModeloEvaluacionId();
		result = 37 * result + this.getBateriaPersonalizadaId();
		return result;
	}

}
