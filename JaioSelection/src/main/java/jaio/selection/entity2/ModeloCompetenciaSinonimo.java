package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Entity;

@Entity
public class ModeloCompetenciaSinonimo implements java.io.Serializable {

	private Integer id;
	private ModeloCompetencia modeloCompetencia;
	private String palabra;

	public ModeloCompetenciaSinonimo() {
	}

	public ModeloCompetenciaSinonimo(ModeloCompetencia modeloCompetencia, String palabra) {
		this.modeloCompetencia = modeloCompetencia;
		this.palabra = palabra;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModeloCompetencia getModeloCompetencia() {
		return this.modeloCompetencia;
	}

	public void setModeloCompetencia(ModeloCompetencia modeloCompetencia) {
		this.modeloCompetencia = modeloCompetencia;
	}

	public String getPalabra() {
		return this.palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

}
