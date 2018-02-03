package jaio.selection.bean;

import java.io.Serializable;
import jaio.selection.orm.ModeloLibro;
import java.math.BigDecimal;

public class BateriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private ModeloLibro modeloLibro;
    private String id;
    private String nombre;
    private String minutosEstimados;
    private String validez;
    private String confiabilidad;
    
    
    public BateriaBean(){
    }
    
    public BateriaBean(String nombre ,String minutosEstimados){
        this.nombre = nombre;
        this.minutosEstimados = minutosEstimados;
    }
    
    public BateriaBean(String nombre ,String minutosEstimados ,String validez ,String confiabilidad){
        this.nombre = nombre;
        this.minutosEstimados = minutosEstimados;
        this.validez = validez;
        this.confiabilidad = confiabilidad;
    }
    
    public ModeloLibro getModeloLibro() {
        return this.modeloLibro;
    }

    public void setModeloLibro(ModeloLibro modeloLibro) {
        this.modeloLibro = modeloLibro;
    }
    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMinutosEstimados() {
        return this.minutosEstimados;
    }

    public void setMinutosEstimados(String minutosEstimados) {
        this.minutosEstimados = minutosEstimados;
    }

    public String getValidez() {
        return this.validez;
    }

    public void setValidez(String validez) {
        this.validez = validez;
    }

    public String getConfiabilidad() {
        return this.confiabilidad;
    }

    public void setConfiabilidad(String confiabilidad) {
        this.confiabilidad = confiabilidad;
    }

    
}
