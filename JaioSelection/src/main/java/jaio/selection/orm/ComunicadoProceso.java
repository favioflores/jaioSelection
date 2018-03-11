package jaio.selection.orm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ComunicadoProceso implements java.io.Serializable {

    private Integer id;
    private int tipo;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private int estado;
    private byte[] contenido;
    private ProcesoSeleccion procesoSeleccion;
    private Set comunicadoDetalles = new HashSet(0);

    public ComunicadoProceso() {
    }

    public ComunicadoProceso(int tipo, Date fechaRegistro, Date fechaActualizacion, int estado, byte[] contenido, ProcesoSeleccion procesoSeleccion, Set comunicadoDetalles) {
        this.tipo = tipo;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
        this.contenido = contenido;
        this.procesoSeleccion = procesoSeleccion;
        this.comunicadoDetalles = comunicadoDetalles;
    }

    public Integer getId() {
        return id;
    }

    public Set getComunicadoDetalles() {
        return comunicadoDetalles;
    }

    public void setComunicadoDetalles(Set comunicadoDetalles) {
        this.comunicadoDetalles = comunicadoDetalles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public ProcesoSeleccion getProcesoSeleccion() {
        return procesoSeleccion;
    }

    public void setProcesoSeleccion(ProcesoSeleccion procesoSeleccion) {
        this.procesoSeleccion = procesoSeleccion;
    }

}
