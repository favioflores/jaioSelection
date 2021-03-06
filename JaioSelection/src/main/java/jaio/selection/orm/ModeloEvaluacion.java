package jaio.selection.orm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ModeloEvaluacion implements java.io.Serializable {

    private Integer id;
    private ModeloLibro modeloLibro;
    private String nombre;
    private Integer minutosEstimados;
    private BigDecimal validez;
    private BigDecimal confiabilidad;
    private Date fechaCreacion;
    private Integer estado;
    private byte limiteTiempo;
    private byte[] instructivo;
    private Set modeloEvaluacionXCompetencias = new HashSet(0);
    private Set bateriaEvaluacion = new HashSet(0);
    private Set modeloPreguntas = new HashSet(0);
    private Set modeloAjustesCalc = new HashSet(0);

    public ModeloEvaluacion() {
    }

    public ModeloEvaluacion(ModeloLibro modeloLibro, String nombre, int horasEstimadas, Integer minutosEstimados, BigDecimal validez, BigDecimal confiabilidad, Date fechaCreacion, Integer estado, byte limiteTiempo, byte[] instructivo) {
        this.modeloLibro = modeloLibro;
        this.nombre = nombre;
        this.minutosEstimados = minutosEstimados;
        this.validez = validez;
        this.confiabilidad = confiabilidad;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.limiteTiempo = limiteTiempo;
        this.instructivo = instructivo;
    }

    public ModeloEvaluacion(ModeloLibro modeloLibro, String nombre, int horasEstimadas, Integer minutosEstimados, BigDecimal validez, BigDecimal confiabilidad, Date fechaCreacion, Integer estado, byte limiteTiempo, byte[] instructivo, Set modeloEvaluacionXCompetencias, Set bateriaEvaluacion, Set modeloPreguntas, Set modeloAjustesCalc) {
        this.modeloLibro = modeloLibro;
        this.nombre = nombre;
        this.minutosEstimados = minutosEstimados;
        this.validez = validez;
        this.confiabilidad = confiabilidad;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.limiteTiempo = limiteTiempo;
        this.instructivo = instructivo;
        this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
        this.bateriaEvaluacion = bateriaEvaluacion;
        this.modeloPreguntas = modeloPreguntas;
        this.modeloAjustesCalc = modeloAjustesCalc;
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

    public Integer getMinutosEstimados() {
        return this.minutosEstimados;
    }

    public void setMinutosEstimados(Integer minutosEstimados) {
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

    public Set getBateriaEvaluacion() {
        return this.bateriaEvaluacion;
    }

    public void setBateriaEvaluacion(Set bateriaEvaluacion) {
        this.bateriaEvaluacion = bateriaEvaluacion;
    }

    public Set getModeloPreguntas() {
        return this.modeloPreguntas;
    }

    public void setModeloPreguntas(Set modeloPreguntas) {
        this.modeloPreguntas = modeloPreguntas;
    }

    public Set getModeloAjustesCalc() {
        return modeloAjustesCalc;
    }

    public void setModeloAjustesCalc(Set modeloAjustesCalc) {
        this.modeloAjustesCalc = modeloAjustesCalc;
    }

    public byte[] getInstructivo() {
        return instructivo;
    }

    public void setInstructivo(byte[] instructivo) {
        this.instructivo = instructivo;
    }

}
