package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Definicion implements java.io.Serializable {

	private Integer id;
	private String descripcion;
	private Set elementos = new HashSet(0);

	public Definicion() {
	}

	public Definicion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Definicion(String descripcion, Set elementos) {
		this.descripcion = descripcion;
		this.elementos = elementos;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getElementos() {
		return this.elementos;
	}

	public void setElementos(Set elementos) {
		this.elementos = elementos;
	}

}
