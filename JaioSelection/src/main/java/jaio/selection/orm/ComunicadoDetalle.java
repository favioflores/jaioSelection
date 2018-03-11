package jaio.selection.orm;

public class ComunicadoDetalle implements java.io.Serializable {

    private Integer id;
    private String parametro;
    private byte[] contenido;
    private ComunicadoProceso comunicadoProceso;

    public ComunicadoDetalle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ComunicadoProceso getComunicadoProceso() {
        return comunicadoProceso;
    }

    public void setComunicadoProceso(ComunicadoProceso comunicadoProceso) {
        this.comunicadoProceso = comunicadoProceso;
    }

}
