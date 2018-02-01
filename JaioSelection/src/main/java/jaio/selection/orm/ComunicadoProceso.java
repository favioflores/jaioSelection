package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final

/**
 * ComunicadoProceso generated by hbm2java
 */
public class ComunicadoProceso implements java.io.Serializable {

    private ComunicadoProcesoId id;
    private ModeloComunicado modeloComunicado;
    private ProcesoSeleccion procesoSeleccion;

    public ComunicadoProceso() {
    }

    public ComunicadoProceso(ComunicadoProcesoId id, ModeloComunicado modeloComunicado, ProcesoSeleccion procesoSeleccion) {
        this.id = id;
        this.modeloComunicado = modeloComunicado;
        this.procesoSeleccion = procesoSeleccion;
    }

    public ComunicadoProcesoId getId() {
        return this.id;
    }

    public void setId(ComunicadoProcesoId id) {
        this.id = id;
    }

    public ModeloComunicado getModeloComunicado() {
        return this.modeloComunicado;
    }

    public void setModeloComunicado(ModeloComunicado modeloComunicado) {
        this.modeloComunicado = modeloComunicado;
    }

    public ProcesoSeleccion getProcesoSeleccion() {
        return this.procesoSeleccion;
    }

    public void setProcesoSeleccion(ProcesoSeleccion procesoSeleccion) {
        this.procesoSeleccion = procesoSeleccion;
    }

}
