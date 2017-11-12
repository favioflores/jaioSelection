package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Area generated by hbm2java
 */
public class Area  implements java.io.Serializable {


     private Integer id;
     private Area area;
     private Empresa empresa;
     private String descripcion;
     private Date fechaRegistro;
     private int estado;
     private Set areas = new HashSet(0);
     private Set perfils = new HashSet(0);


    public Area() {
    }


    public Area(Empresa empresa, String descripcion, Date fechaRegistro, int estado) {
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }
    public Area(Area area, Empresa empresa, String descripcion, Date fechaRegistro, int estado, Set areas, Set perfils) {
       this.area = area;
       this.empresa = empresa;
       this.descripcion = descripcion;
       this.fechaRegistro = fechaRegistro;
       this.estado = estado;
       this.areas = areas;
       this.perfils = perfils;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
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

