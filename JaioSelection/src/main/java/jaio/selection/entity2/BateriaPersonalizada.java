package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BateriaPersonalizada implements java.io.Serializable {
	
	@Id
	private Integer id;
	private String nombre;
	private Date fechaCreacion;
	private int estado;
	private String resena;
	private int horasEstimadasTotal;
	private int minutosEstimadosTotal;
	private Set bateriaEvaluacions = new HashSet(0);
	private Set evaluacionPerfils = new HashSet(0);

	public BateriaPersonalizada() {
	}

	public BateriaPersonalizada(String nombre, Date fechaCreacion, int estado, int horasEstimadasTotal,
			int minutosEstimadosTotal) {
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.horasEstimadasTotal = horasEstimadasTotal;
		this.minutosEstimadosTotal = minutosEstimadosTotal;
	}

	public BateriaPersonalizada(String nombre, Date fechaCreacion, int estado, String resena, int horasEstimadasTotal,
			int minutosEstimadosTotal, Set bateriaEvaluacions, Set evaluacionPerfils) {
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.resena = resena;
		this.horasEstimadasTotal = horasEstimadasTotal;
		this.minutosEstimadosTotal = minutosEstimadosTotal;
		this.bateriaEvaluacions = bateriaEvaluacions;
		this.evaluacionPerfils = evaluacionPerfils;
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

	public String getResena() {
		return this.resena;
	}

	public void setResena(String resena) {
		this.resena = resena;
	}

	public int getHorasEstimadasTotal() {
		return this.horasEstimadasTotal;
	}

	public void setHorasEstimadasTotal(int horasEstimadasTotal) {
		this.horasEstimadasTotal = horasEstimadasTotal;
	}

	public int getMinutosEstimadosTotal() {
		return this.minutosEstimadosTotal;
	}

	public void setMinutosEstimadosTotal(int minutosEstimadosTotal) {
		this.minutosEstimadosTotal = minutosEstimadosTotal;
	}

	public Set getBateriaEvaluacions() {
		return this.bateriaEvaluacions;
	}

	public void setBateriaEvaluacions(Set bateriaEvaluacions) {
		this.bateriaEvaluacions = bateriaEvaluacions;
	}

	public Set getEvaluacionPerfils() {
		return this.evaluacionPerfils;
	}

	public void setEvaluacionPerfils(Set evaluacionPerfils) {
		this.evaluacionPerfils = evaluacionPerfils;
	}

}
