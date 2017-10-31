package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class ModeloCompetencia implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String resena;
	private Set modeloCompetenciaSinonimos = new HashSet(0);
	private Set modeloEvaluacionXCompetencias = new HashSet(0);

	public ModeloCompetencia() {
	}

	public ModeloCompetencia(String nombre) {
		this.nombre = nombre;
	}

	public ModeloCompetencia(String nombre, String resena, Set modeloCompetenciaSinonimos,
			Set modeloEvaluacionXCompetencias) {
		this.nombre = nombre;
		this.resena = resena;
		this.modeloCompetenciaSinonimos = modeloCompetenciaSinonimos;
		this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResena() {
		return this.resena;
	}

	public void setResena(String resena) {
		this.resena = resena;
	}

	public Set getModeloCompetenciaSinonimos() {
		return this.modeloCompetenciaSinonimos;
	}

	public void setModeloCompetenciaSinonimos(Set modeloCompetenciaSinonimos) {
		this.modeloCompetenciaSinonimos = modeloCompetenciaSinonimos;
	}

	public Set getModeloEvaluacionXCompetencias() {
		return this.modeloEvaluacionXCompetencias;
	}

	public void setModeloEvaluacionXCompetencias(Set modeloEvaluacionXCompetencias) {
		this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
	}

}
