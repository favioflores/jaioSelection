package jaio.selection.bean;

import java.io.Serializable;
import jaio.selection.orm.ModeloLibro;
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
    private List lstCompetencias;
    private List lstColores;
    private List<ModeloAjustesCalcBean> lstAjustes;
    
    
    public BateriaBean(){
    }
    
    public BateriaBean(String nombre, String validez, String confiabilidad, String minutosEstimados, String limiteTiempo, List lstCompetencias, List lstColores){
        this.nombre = nombre;
        this.validez = validez;
        this.confiabilidad = confiabilidad;
        this.minutosEstimados = minutosEstimados;
        this.lstCompetencias = lstCompetencias;
        this.lstColores = lstColores;
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

    public List getLstColores() {
        return lstColores;
    }

    public void setLstColores(List lstColores) {
        this.lstColores = lstColores;
    }

    public List<ModeloAjustesCalcBean> getLstAjustes() {
        return lstAjustes;
    }

    public void setLstAjustes(List<ModeloAjustesCalcBean> lstAjustes) {
        this.lstAjustes = lstAjustes;
    }

}
