package jaio.selection.orm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Notificaciones implements java.io.Serializable {

    private Integer id;
    private ProcesoSeleccion procesoSeleccion;
    private String asunto;
    private byte[] contenido;
    private String correo;
    private int estado;
    private Date fechaRegistro;
    private Date fechaEnvio;
    private Set destinatarioss = new HashSet(0);

    public Notificaciones() {
    }

    public Notificaciones(ProcesoSeleccion procesoSeleccion, String asunto, byte[] contenido, String correo, int estado, Date fechaRegistro) {
        this.procesoSeleccion = procesoSeleccion;
        this.asunto = asunto;
        this.contenido = contenido;
        this.correo = correo;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Notificaciones(ProcesoSeleccion procesoSeleccion, String asunto, byte[] contenido, String correo, int estado, Date fechaRegistro, Date fechaEnvio) {
        this.procesoSeleccion = procesoSeleccion;
        this.asunto = asunto;
        this.contenido = contenido;
        this.correo = correo;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaEnvio = fechaEnvio;
    }

    public Notificaciones(ProcesoSeleccion procesoSeleccion, String asunto, byte[] contenido, String correo, int estado, Date fechaRegistro, Date fechaEnvio, Set destinatarioss) {
        this.procesoSeleccion = procesoSeleccion;
        this.asunto = asunto;
        this.contenido = contenido;
        this.correo = correo;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaEnvio = fechaEnvio;
        this.destinatarioss = destinatarioss;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcesoSeleccion getProcesoSeleccion() {
        return this.procesoSeleccion;
    }

    public void setProcesoSeleccion(ProcesoSeleccion procesoSeleccion) {
        this.procesoSeleccion = procesoSeleccion;
    }

    public String getAsunto() {
        return this.asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public byte[] getContenido() {
        return this.contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaEnvio() {
        return this.fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Set getDestinatarioss() {
        return destinatarioss;
    }

    public void setDestinatarioss(Set destinatarioss) {
        this.destinatarioss = destinatarioss;
    }

}
