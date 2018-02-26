package jaio.selection.orm;

public class NotificacionDetalle implements java.io.Serializable {

    private Integer id;
    private Notificaciones notificaciones;
    private String parametro;
    private byte[] contenido;

    public NotificacionDetalle() {
    }

    public NotificacionDetalle(Notificaciones notificaciones, String parametro, byte[] contenido) {
        this.notificaciones = notificaciones;
        this.parametro = parametro;
        this.contenido = contenido;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Notificaciones getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Notificaciones notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

}
