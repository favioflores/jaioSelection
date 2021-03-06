package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Definicion generated by hbm2java
 */
public class Definicion implements java.io.Serializable {

    private Integer id;
    private String descripcion;
    private Set elementos = new HashSet(0);

    public Definicion() {
    }

    public Definicion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Definicion(String descripcion, Set elementos, Set elementos_1) {
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
