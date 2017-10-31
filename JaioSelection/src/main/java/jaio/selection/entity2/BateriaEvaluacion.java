package jaio.selection.entity2;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bateriaEvaluacion")
public class BateriaEvaluacion implements java.io.Serializable {

	@Id
	private BateriaEvaluacionId id;
	private BateriaPersonalizada bateriaPersonalizada;
	private ModeloEvaluacion modeloEvaluacion;

	public BateriaEvaluacion() {
	}

	public BateriaEvaluacion(BateriaEvaluacionId id, BateriaPersonalizada bateriaPersonalizada,
			ModeloEvaluacion modeloEvaluacion) {
		this.id = id;
		this.bateriaPersonalizada = bateriaPersonalizada;
		this.modeloEvaluacion = modeloEvaluacion;
	}

	public BateriaEvaluacionId getId() {
		return this.id;
	}

	public void setId(BateriaEvaluacionId id) {
		this.id = id;
	}

	public BateriaPersonalizada getBateriaPersonalizada() {
		return this.bateriaPersonalizada;
	}

	public void setBateriaPersonalizada(BateriaPersonalizada bateriaPersonalizada) {
		this.bateriaPersonalizada = bateriaPersonalizada;
	}

	public ModeloEvaluacion getModeloEvaluacion() {
		return this.modeloEvaluacion;
	}

	public void setModeloEvaluacion(ModeloEvaluacion modeloEvaluacion) {
		this.modeloEvaluacion = modeloEvaluacion;
	}

}
