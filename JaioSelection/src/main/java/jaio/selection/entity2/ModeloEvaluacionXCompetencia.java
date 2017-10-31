package jaio.selection.entity2;

import javax.persistence.Entity;

@Entity
public class ModeloEvaluacionXCompetencia implements java.io.Serializable {

	private ModeloEvaluacionXCompetenciaId id;
	private ModeloCompetencia modeloCompetencia;
	private ModeloEvaluacion modeloEvaluacion;

	public ModeloEvaluacionXCompetencia() {
	}

	public ModeloEvaluacionXCompetencia(ModeloEvaluacionXCompetenciaId id, ModeloCompetencia modeloCompetencia,
			ModeloEvaluacion modeloEvaluacion) {
		this.id = id;
		this.modeloCompetencia = modeloCompetencia;
		this.modeloEvaluacion = modeloEvaluacion;
	}

	public ModeloEvaluacionXCompetenciaId getId() {
		return this.id;
	}

	public void setId(ModeloEvaluacionXCompetenciaId id) {
		this.id = id;
	}

	public ModeloCompetencia getModeloCompetencia() {
		return this.modeloCompetencia;
	}

	public void setModeloCompetencia(ModeloCompetencia modeloCompetencia) {
		this.modeloCompetencia = modeloCompetencia;
	}

	public ModeloEvaluacion getModeloEvaluacion() {
		return this.modeloEvaluacion;
	}

	public void setModeloEvaluacion(ModeloEvaluacion modeloEvaluacion) {
		this.modeloEvaluacion = modeloEvaluacion;
	}

}
