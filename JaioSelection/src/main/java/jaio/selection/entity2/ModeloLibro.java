package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class ModeloLibro implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String autor;
	private String adaptacion;
	private String resena;
	private Integer horasEstimadas;
	private Integer minutosEstimados;
	private Set modeloEvaluacions = new HashSet(0);

	public ModeloLibro() {
	}

	public ModeloLibro(String nombre) {
		this.nombre = nombre;
	}

	public ModeloLibro(String nombre, String autor, String adaptacion, String resena, Integer horasEstimadas,
			Integer minutosEstimados, Set modeloEvaluacions) {
		this.nombre = nombre;
		this.autor = autor;
		this.adaptacion = adaptacion;
		this.resena = resena;
		this.horasEstimadas = horasEstimadas;
		this.minutosEstimados = minutosEstimados;
		this.modeloEvaluacions = modeloEvaluacions;
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

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAdaptacion() {
		return this.adaptacion;
	}

	public void setAdaptacion(String adaptacion) {
		this.adaptacion = adaptacion;
	}

	public String getResena() {
		return this.resena;
	}

	public void setResena(String resena) {
		this.resena = resena;
	}

	public Integer getHorasEstimadas() {
		return this.horasEstimadas;
	}

	public void setHorasEstimadas(Integer horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public Integer getMinutosEstimados() {
		return this.minutosEstimados;
	}

	public void setMinutosEstimados(Integer minutosEstimados) {
		this.minutosEstimados = minutosEstimados;
	}

	public Set getModeloEvaluacions() {
		return this.modeloEvaluacions;
	}

	public void setModeloEvaluacions(Set modeloEvaluacions) {
		this.modeloEvaluacions = modeloEvaluacions;
	}

}
