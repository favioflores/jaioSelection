package jaio.selection.orm;

public class Destinatarios implements java.io.Serializable {

    private Integer id;
    private Notificaciones notificaciones;
    private String correo;

    public Destinatarios() {
    }

    public Destinatarios(Notificaciones notificaciones, String correo) {
        this.notificaciones = notificaciones;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
