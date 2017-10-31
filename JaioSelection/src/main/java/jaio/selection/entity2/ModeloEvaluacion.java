package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class ModeloEvaluacion implements java.io.Serializable {

	private Integer id;
	private ModeloLibro modeloLibro;
	private String nombre;
	private int horasEstimadas;
	private int minutosEstimados;
	private BigDecimal validez;
	private BigDecimal confiabilidad;
	private Date fechaCreacion;
	private int estado;
	private byte limiteTiempo;
	private Set modeloEvaluacionXCompetencias = new HashSet(0);
	private Set modeloPreguntas = new HashSet(0);
	private Set bateriaEvaluacions = new HashSet(0);

	public ModeloEvaluacion() {
	}

	public ModeloEvaluacion(ModeloLibro modeloLibro, String nombre, int horasEstimadas, int minutosEstimados,
			BigDecimal validez, BigDecimal confiabilidad, Date fechaCreacion, int estado, byte limiteTiempo) {
		this.modeloLibro = modeloLibro;
		this.nombre = nombre;
		this.horasEstimadas = horasEstimadas;
		this.minutosEstimados = minutosEstimados;
		this.validez = validez;
		this.confiabilidad = confiabilidad;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.limiteTiempo = limiteTiempo;
	}

	public ModeloEvaluacion(ModeloLibro modeloLibro, String nombre, int horasEstimadas, int minutosEstimados,
			BigDecimal validez, BigDecimal confiabilidad, Date fechaCreacion, int estado, byte limiteTiempo,
			Set modeloEvaluacionXCompetencias, Set modeloPreguntas, Set bateriaEvaluacions) {
		this.modeloLibro = modeloLibro;
		this.nombre = nombre;
		this.horasEstimadas = horasEstimadas;
		this.minutosEstimados = minutosEstimados;
		this.validez = validez;
		this.confiabilidad = confiabilidad;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.limiteTiempo = limiteTiempo;
		this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
		this.modeloPreguntas = modeloPreguntas;
		this.bateriaEvaluacions = bateriaEvaluacions;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModeloLibro getModeloLibro() {
		return this.modeloLibro;
	}

	public void setModeloLibro(ModeloLibro modeloLibro) {
		this.modeloLibro = modeloLibro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHorasEstimadas() {
		return this.horasEstimadas;
	}

	public void setHorasEstimadas(int horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public int getMinutosEstimados() {
		return this.minutosEstimados;
	}

	public void setMinutosEstimados(int minutosEstimados) {
		this.minutosEstimados = minutosEstimados;
	}

	public BigDecimal getValidez() {
		return this.validez;
	}

	public void setValidez(BigDecimal validez) {
		this.validez = validez;
	}

	public BigDecimal getConfiabilidad() {
		return this.confiabilidad;
	}

	public void setConfiabilidad(BigDecimal confiabilidad) {
		this.confiabilidad = confiabilidad;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public byte getLimiteTiempo() {
		return this.limiteTiempo;
	}

	public void setLimiteTiempo(byte limiteTiempo) {
		this.limiteTiempo = limiteTiempo;
	}

	public Set getModeloEvaluacionXCompetencias() {
		return this.modeloEvaluacionXCompetencias;
	}

	public void setModeloEvaluacionXCompetencias(Set modeloEvaluacionXCompetencias) {
		this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
	}

	public Set getModeloPreguntas() {
		return this.modeloPreguntas;
	}

	public void setModeloPreguntas(Set modeloPreguntas) {
		this.modeloPreguntas = modeloPreguntas;
	}

	public Set getBateriaEvaluacions() {
		return this.bateriaEvaluacions;
	}

	public void setBateriaEvaluacions(Set bateriaEvaluacions) {
		this.bateriaEvaluacions = bateriaEvaluacions;
	}

}
