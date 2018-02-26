package jaio.selection.orm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProcesoSeleccion implements java.io.Serializable {

    private Integer id;
    private Perfil perfil;
    private Date fechaRegistro;
    private String descripcion;
    private int estado;
    private Set candidatos = new HashSet(0);
    private Set comunicadoProcesos = new HashSet(0);
    private Set evaluacionPerfil = new HashSet(0);

    public ProcesoSeleccion() {
    }

    public ProcesoSeleccion(Perfil perfil, Date fechaRegistro, String descripcion, int estado) {
        this.perfil = perfil;
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public ProcesoSeleccion(Perfil perfil, Date fechaRegistro, String descripcion, int estado, Set candidatos, Set comunicadoProcesos, Set evaluacionPerfil) {
        this.perfil = perfil;
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.estado = estado;
        this.candidatos = candidatos;
        this.comunicadoProcesos = comunicadoProcesos;
        this.evaluacionPerfil = evaluacionPerfil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return this.perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Set getCandidatos() {
        return this.candidatos;
    }

    public void setCandidatos(Set candidatos) {
        this.candidatos = candidatos;
    }

    public Set getComunicadoProcesos() {
        return this.comunicadoProcesos;
    }

    public void setComunicadoProcesos(Set comunicadoProcesos) {
        this.comunicadoProcesos = comunicadoProcesos;
    }

    public Set getEvaluacionPerfil() {
        return evaluacionPerfil;
    }

    public void setEvaluacionPerfil(Set evaluacionPerfil) {
        this.evaluacionPerfil = evaluacionPerfil;
    }

}
