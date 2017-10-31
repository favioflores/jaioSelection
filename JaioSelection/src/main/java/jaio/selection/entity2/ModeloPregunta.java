package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class ModeloPregunta implements java.io.Serializable {

	private Integer id;
	private ModeloEvaluacion modeloEvaluacion;
	private int tipo;
	private String nombre;
	private int orden;
	private int horasEstimadas;
	private int minutosEstimados;
	private byte[] imagen;
	private int tipoRespuesta;
	private Set modeloRespuestas = new HashSet(0);

	public ModeloPregunta() {
	}

	public ModeloPregunta(ModeloEvaluacion modeloEvaluacion, int tipo, String nombre, int orden, int horasEstimadas,
			int minutosEstimados, int tipoRespuesta) {
		this.modeloEvaluacion = modeloEvaluacion;
		this.tipo = tipo;
		this.nombre = nombre;
		this.orden = orden;
		this.horasEstimadas = horasEstimadas;
		this.minutosEstimados = minutosEstimados;
		this.tipoRespuesta = tipoRespuesta;
	}

	public ModeloPregunta(ModeloEvaluacion modeloEvaluacion, int tipo, String nombre, int orden, int horasEstimadas,
			int minutosEstimados, byte[] imagen, int tipoRespuesta, Set modeloRespuestas) {
		this.modeloEvaluacion = modeloEvaluacion;
		this.tipo = tipo;
		this.nombre = nombre;
		this.orden = orden;
		this.horasEstimadas = horasEstimadas;
		this.minutosEstimados = minutosEstimados;
		this.imagen = imagen;
		this.tipoRespuesta = tipoRespuesta;
		this.modeloRespuestas = modeloRespuestas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModeloEvaluacion getModeloEvaluacion() {
		return this.modeloEvaluacion;
	}

	public void setModeloEvaluacion(ModeloEvaluacion modeloEvaluacion) {
		this.modeloEvaluacion = modeloEvaluacion;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
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

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public int getTipoRespuesta() {
		return this.tipoRespuesta;
	}

	public void setTipoRespuesta(int tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public Set getModeloRespuestas() {
		return this.modeloRespuestas;
	}

	public void setModeloRespuestas(Set modeloRespuestas) {
		this.modeloRespuestas = modeloRespuestas;
	}

}
