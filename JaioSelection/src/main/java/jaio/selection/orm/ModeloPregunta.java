package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final


import java.util.HashSet;
import java.util.Set;

/**
 * ModeloPregunta generated by hbm2java
 */
public class ModeloPregunta  implements java.io.Serializable {


     private Integer id;
     private ModeloEvaluacion modeloEvaluacion;
     private int tipo;
     private String nombre;
     private int orden;
     private int horasEstimadas;
     private int minutosEstimados;
     private byte[] imagen;
     private String comentario;
     private byte ejemplo;
     private Set modeloRespuestas = new HashSet(0);
     private Set modeloRespuestas_1 = new HashSet(0);

    public ModeloPregunta() {
    }

	
    public ModeloPregunta(ModeloEvaluacion modeloEvaluacion, int tipo, String nombre, int orden, int horasEstimadas, int minutosEstimados, String comentario, byte ejemplo) {
        this.modeloEvaluacion = modeloEvaluacion;
        this.tipo = tipo;
        this.nombre = nombre;
        this.orden = orden;
        this.horasEstimadas = horasEstimadas;
        this.minutosEstimados = minutosEstimados;
       this.comentario = comentario;
       this.ejemplo = ejemplo;
    }
    public ModeloPregunta(ModeloEvaluacion modeloEvaluacion, int tipo, String nombre, int orden, int horasEstimadas, int minutosEstimados, byte[] imagen, String comentario, byte ejemplo, Set modeloRespuestas, Set modeloRespuestas_1) {
       this.modeloEvaluacion = modeloEvaluacion;
       this.tipo = tipo;
       this.nombre = nombre;
       this.orden = orden;
       this.horasEstimadas = horasEstimadas;
       this.minutosEstimados = minutosEstimados;
       this.imagen = imagen;
       this.comentario = comentario;
       this.ejemplo = ejemplo;
       this.modeloRespuestas = modeloRespuestas;
       this.modeloRespuestas_1 = modeloRespuestas_1;
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

    public Set getModeloRespuestas() {
        return this.modeloRespuestas;
    }
    
    public void setModeloRespuestas(Set modeloRespuestas) {
        this.modeloRespuestas = modeloRespuestas;
    }
    public Set getModeloRespuestas_1() {
        return this.modeloRespuestas_1;
    }
    
    public void setModeloRespuestas_1(Set modeloRespuestas_1) {
        this.modeloRespuestas_1 = modeloRespuestas_1;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public byte getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(byte ejemplo) {
        this.ejemplo = ejemplo;
    }

}


