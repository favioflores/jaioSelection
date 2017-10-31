package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Entity;

@Entity
public class ComunicadoProceso implements java.io.Serializable {

	private ComunicadoProcesoId id;
	private ModeloComunicado modeloComunicado;
	private ProcesoSeleccion procesoSeleccion;

	public ComunicadoProceso() {
	}

	public ComunicadoProceso(ComunicadoProcesoId id, ModeloComunicado modeloComunicado,
			ProcesoSeleccion procesoSeleccion) {
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
