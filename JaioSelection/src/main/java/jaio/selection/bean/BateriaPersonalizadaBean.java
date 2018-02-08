package jaio.selection.bean;

import java.io.Serializable;
import java.util.Date;

public class BateriaPersonalizadaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nombre;
    private String fechaCreacion;
    private String nombrePerfil;
    private int estado;
    private String resena;
    private int horasEstimadasTotal;
    private int minutosEstimadosTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public int getHorasEstimadasTotal() {
        return horasEstimadasTotal;
    }

    public void setHorasEstimadasTotal(int horasEstimadasTotal) {
        this.horasEstimadasTotal = horasEstimadasTotal;
    }

    public int getMinutosEstimadosTotal() {
        return minutosEstimadosTotal;
    }

    public void setMinutosEstimadosTotal(int minutosEstimadosTotal) {
        this.minutosEstimadosTotal = minutosEstimadosTotal;
    }
    
    
    
    
}
