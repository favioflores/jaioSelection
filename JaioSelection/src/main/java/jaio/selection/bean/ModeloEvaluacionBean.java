package jaio.selection.bean;

import jaio.selection.orm.ModeloCompetencia;
import java.io.Serializable;
import jaio.selection.orm.ModeloLibro;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ModeloEvaluacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String minutosEstimados;
    private String validez;
    private String confiabilidad;
    private String fechaCreacion;
    private String estado;
    private String limiteTiempo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMinutosEstimados() {
        return minutosEstimados;
    }

    public void setMinutosEstimados(String minutosEstimados) {
        this.minutosEstimados = minutosEstimados;
    }

    public String getValidez() {
        return validez;
    }

    public void setValidez(String validez) {
        this.validez = validez;
    }

    public String getConfiabilidad() {
        return confiabilidad;
    }

    public void setConfiabilidad(String confiabilidad) {
        this.confiabilidad = confiabilidad;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLimiteTiempo() {
        return limiteTiempo;
    }

    public void setLimiteTiempo(String limiteTiempo) {
        this.limiteTiempo = limiteTiempo;
    }
    
    
    
    
}
