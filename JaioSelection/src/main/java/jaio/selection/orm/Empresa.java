package jaio.selection.orm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Empresa implements java.io.Serializable {

    private Integer id;
    private Usuario usuario;
    private String nombre;
    private Date fechaRegistro;
    private int estado;
    private Integer orden;
    private byte[] imagen;
    private String tipo_imagen;
    private Set areas = new HashSet(0);
    private Set perfils = new HashSet(0);

    public Empresa() {
    }

    public Empresa(Usuario usuario, String nombre, Date fechaRegistro, int estado, Integer orden) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.orden = orden;
    }

    public Empresa(Usuario usuario, String nombre, Date fechaRegistro, int estado, Integer orden, byte[] imagen, String tipo_imagen, Set areas, Set perfils) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.orden = orden;
        this.imagen = imagen;
        this.tipo_imagen = tipo_imagen;
        this.areas = areas;
        this.perfils = perfils;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public String getTipo_imagen() {
        return tipo_imagen;
    }

    public void setTipo_imagen(String tipo_imagen) {
        this.tipo_imagen = tipo_imagen;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public byte[] getImagen() {
        return this.imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Set getAreas() {
        return this.areas;
    }

    public void setAreas(Set areas) {
        this.areas = areas;
    }

    public Set getPerfils() {
        return this.perfils;
    }

    public void setPerfils(Set perfils) {
        this.perfils = perfils;
    }

}
