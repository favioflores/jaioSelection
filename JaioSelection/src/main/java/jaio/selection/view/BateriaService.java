package jaio.selection.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import jaio.selection.orm.ModeloEvaluacion;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@ManagedBean(name = "bateriaService")
@ViewScoped
public class BateriaService extends BaseView implements Serializable {

    private String nombre;
    private int minutosEstimados;
    private BigDecimal validez;
    private BigDecimal confiabilidad;
    
    
    public List<ModeloEvaluacion> createBateria(int size) {
        List<ModeloEvaluacion> list = new ArrayList<ModeloEvaluacion>();
		for(int i = 0 ; i < size ; i++) {
                    list.add(new ModeloEvaluacion(getNombre(), getMinutosEstimados()
                            , getValidez(), getConfiabilidad()));
        }
        
        return list;
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
    
}
