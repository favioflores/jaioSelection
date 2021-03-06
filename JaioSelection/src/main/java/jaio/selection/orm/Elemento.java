package jaio.selection.orm;

public class Elemento implements java.io.Serializable {

    private Integer id;
    private Definicion definicion;
    private String descripcion;
    private String cadena;
    private int orden;
    private byte activo;

    public Elemento() {
    }

    public Elemento(Definicion definicion, String descripcion, int orden, byte activo) {
        this.definicion = definicion;
        this.descripcion = descripcion;
        this.orden = orden;
        this.activo = activo;
    }

    public Integer getId() {
        return this.id;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Definicion getDefinicion() {
        return this.definicion;
    }

    public void setDefinicion(Definicion definicion) {
        this.definicion = definicion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return this.orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public byte getActivo() {
        return this.activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

}
