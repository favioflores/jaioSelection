package jaio.selection.bean;

import jaio.selection.orm.ModeloCompetencia;
import java.io.Serializable;
import jaio.selection.orm.ModeloLibro;
import java.util.List;

public class ModeloCompetenciaBean implements Serializable, Comparable<ModeloCompetenciaBean> {

    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String resena;
    private String color;

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

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int compareTo(ModeloCompetenciaBean o) {
         return this.getNombre().compareTo(o.getNombre());
    }

    
}
