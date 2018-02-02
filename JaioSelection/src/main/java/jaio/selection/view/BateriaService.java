package jaio.selection.view;

import jaio.selection.bean.BateriaBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "bateriaService")
@ViewScoped
public class BateriaService extends BaseView implements Serializable {

    private String nombre;
    private int minutosEstimados;
    private String validez;
    private String confiabilidad;
    
    
    public List<BateriaBean> createBateria(int size) {
        List<BateriaBean> list = new ArrayList<BateriaBean>();
		for(int i = 0 ; i < size ; i++) {
                    list.add(new BateriaBean(getNombre(), getMinutosEstimados()
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
