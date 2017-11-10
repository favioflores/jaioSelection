package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ModeloEvaluacion generated by hbm2java
 */
public class ModeloEvaluacion  implements java.io.Serializable {


     private Integer id;
     private ModeloLibro modeloLibro;
     private String nombre;
     private int horasEstimadas;
     private int minutosEstimados;
     private BigDecimal validez;
     private BigDecimal confiabilidad;
     private Date fechaCreacion;
     private int estado;
     private byte limiteTiempo;
     private Set modeloEvaluacionXCompetencias = new HashSet(0);
     private Set bateriaEvaluacions = new HashSet(0);
     private Set modeloEvaluacionXCompetencias_1 = new HashSet(0);
     private Set modeloPreguntas = new HashSet(0);
     private Set bateriaEvaluacions_1 = new HashSet(0);
     private Set modeloPreguntas_1 = new HashSet(0);

    public ModeloEvaluacion() {
    }

	
    public ModeloEvaluacion(ModeloLibro modeloLibro, String nombre, int horasEstimadas, int minutosEstimados, BigDecimal validez, BigDecimal confiabilidad, Date fechaCreacion, int estado, byte limiteTiempo) {
        this.modeloLibro = modeloLibro;
        this.nombre = nombre;
        this.horasEstimadas = horasEstimadas;
        this.minutosEstimados = minutosEstimados;
        this.validez = validez;
        this.confiabilidad = confiabilidad;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.limiteTiempo = limiteTiempo;
    }
    public ModeloEvaluacion(ModeloLibro modeloLibro, String nombre, int horasEstimadas, int minutosEstimados, BigDecimal validez, BigDecimal confiabilidad, Date fechaCreacion, int estado, byte limiteTiempo, Set modeloEvaluacionXCompetencias, Set bateriaEvaluacions, Set modeloEvaluacionXCompetencias_1, Set modeloPreguntas, Set bateriaEvaluacions_1, Set modeloPreguntas_1) {
       this.modeloLibro = modeloLibro;
       this.nombre = nombre;
       this.horasEstimadas = horasEstimadas;
       this.minutosEstimados = minutosEstimados;
       this.validez = validez;
       this.confiabilidad = confiabilidad;
       this.fechaCreacion = fechaCreacion;
       this.estado = estado;
       this.limiteTiempo = limiteTiempo;
       this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
       this.bateriaEvaluacions = bateriaEvaluacions;
       this.modeloEvaluacionXCompetencias_1 = modeloEvaluacionXCompetencias_1;
       this.modeloPreguntas = modeloPreguntas;
       this.bateriaEvaluacions_1 = bateriaEvaluacions_1;
       this.modeloPreguntas_1 = modeloPreguntas_1;
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
    public Set getBateriaEvaluacions() {
        return this.bateriaEvaluacions;
    }
    
    public void setBateriaEvaluacions(Set bateriaEvaluacions) {
        this.bateriaEvaluacions = bateriaEvaluacions;
    }
    public Set getModeloEvaluacionXCompetencias_1() {
        return this.modeloEvaluacionXCompetencias_1;
    }
    
    public void setModeloEvaluacionXCompetencias_1(Set modeloEvaluacionXCompetencias_1) {
        this.modeloEvaluacionXCompetencias_1 = modeloEvaluacionXCompetencias_1;
    }
    public Set getModeloPreguntas() {
        return this.modeloPreguntas;
    }
    
    public void setModeloPreguntas(Set modeloPreguntas) {
        this.modeloPreguntas = modeloPreguntas;
    }
    public Set getBateriaEvaluacions_1() {
        return this.bateriaEvaluacions_1;
    }
    
    public void setBateriaEvaluacions_1(Set bateriaEvaluacions_1) {
        this.bateriaEvaluacions_1 = bateriaEvaluacions_1;
    }
    public Set getModeloPreguntas_1() {
        return this.modeloPreguntas_1;
    }
    
    public void setModeloPreguntas_1(Set modeloPreguntas_1) {
        this.modeloPreguntas_1 = modeloPreguntas_1;
    }




}


