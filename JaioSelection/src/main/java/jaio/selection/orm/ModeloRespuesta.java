package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final

/**
 * ModeloRespuesta generated by hbm2java
 */
public class ModeloRespuesta implements java.io.Serializable {

    private Integer id;
    private ModeloPregunta modeloPregunta;
    private String rpta;
    private String comentario;
    private Integer numeroOrden;
    private byte esCorrecta;

    public ModeloRespuesta() {
    }

    public ModeloRespuesta(ModeloPregunta modeloPregunta, byte esCorrecta) {
        this.modeloPregunta = modeloPregunta;
        this.esCorrecta = esCorrecta;
    }

    public ModeloRespuesta(ModeloPregunta modeloPregunta, String rpta, String comentario, Integer numeroOrden, byte esCorrecta) {
        this.modeloPregunta = modeloPregunta;
        this.rpta = rpta;
        this.comentario = comentario;
        this.numeroOrden = numeroOrden;
        this.esCorrecta = esCorrecta;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModeloPregunta getModeloPregunta() {
        return this.modeloPregunta;
    }

    public void setModeloPregunta(ModeloPregunta modeloPregunta) {
        this.modeloPregunta = modeloPregunta;
    }

    public Integer getNumeroOrden() {
        return this.numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public byte getEsCorrecta() {
        return this.esCorrecta;
    }

    public void setEsCorrecta(byte esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public String getRpta() {
        return rpta;
    }

    public void setRpta(String rpta) {
        this.rpta = rpta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
