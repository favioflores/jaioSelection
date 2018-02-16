package jaio.selection.bean;

import jaio.selection.orm.ModeloCompetencia;
import java.io.Serializable;
import jaio.selection.orm.ModeloLibro;
import java.util.ArrayList;
import java.util.List;

public class BateriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private ModeloLibro modeloLibro;
    private String id;
    private String nombre;
    private String validez;
    private String confiabilidad;
    private String minutosEstimados;
    private String limiteTiempo;
    private String color;
    private List lstCompetencias;
    
    
    public BateriaBean(){
    }
    
    public BateriaBean(String nombre, String validez, String confiabilidad, String minutosEstimados, String limiteTiempo, List lstCompetencias){
        this.nombre = nombre;
        this.validez = validez;
        this.confiabilidad = confiabilidad;
        this.minutosEstimados = minutosEstimados;
        this.lstCompetencias = lstCompetencias;
    }

    public List getLstCompetencias() {
        return lstCompetencias;
    }

    public void setLstCompetencias(List lstCompetencias) {
        this.lstCompetencias = lstCompetencias;
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

    public String getMinutosEstimados() {
        return this.minutosEstimados;
    }

    public void setMinutosEstimados(String minutosEstimados) {
        this.minutosEstimados = minutosEstimados;
    }
    
    public String getLimiteTiempo() {
        return this.limiteTiempo;
    }

    public void setLimiteTiempo(String limiteTiempo) {
        this.limiteTiempo = limiteTiempo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
