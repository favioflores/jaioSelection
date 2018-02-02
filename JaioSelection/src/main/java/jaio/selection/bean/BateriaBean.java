package jaio.selection.bean;

import java.io.Serializable;
import jaio.selection.orm.ModeloLibro;
import java.math.BigDecimal;

public class BateriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private ModeloLibro modeloLibro;
    private String nombre;
    private int minutosEstimados;
    private String validez;
    private String confiabilidad;
    
    public BateriaBean(){}
    
    public BateriaBean(String nombre ,int minutosEstimados ,String validez ,String confiabilidad){
        this.nombre = nombre;
        this.minutosEstimados = minutosEstimados;
        this.validez = validez;
        this.confiabilidad = confiabilidad;
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

    public int getMinutosEstimados() {
        return this.minutosEstimados;
    }

    public void setMinutosEstimados(int minutosEstimados) {
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
