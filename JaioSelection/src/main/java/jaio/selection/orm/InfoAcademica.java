package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final

import java.util.Date;

/**
 * InfoAcademica generated by hbm2java
 */
public class InfoAcademica implements java.io.Serializable {

    private Integer id;
    private Candidato candidato;
    private Date fechaInicio;
    private Date fechaFin;
    private String nombre;
    private String grado;
    private String logro;
    private String especialidad;

    public InfoAcademica() {
    }

    public InfoAcademica(Candidato candidato, Date fechaInicio, String nombre) {
        this.candidato = candidato;
        this.fechaInicio = fechaInicio;
        this.nombre = nombre;
    }

    public InfoAcademica(Candidato candidato, Date fechaInicio, Date fechaFin, String nombre, String grado, String logro, String especialidad) {
        this.candidato = candidato;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
        this.grado = grado;
        this.logro = logro;
        this.especialidad = especialidad;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return this.candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return this.grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getLogro() {
        return this.logro;
    }

    public void setLogro(String logro) {
        this.logro = logro;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
