package jaio.selection.entity2;

import javax.persistence.Entity;

@Entity
public class ModeloRespuesta implements java.io.Serializable {

	private Integer id;
	private ModeloPregunta modeloPregunta;
	private String rptaTexto;
	private byte[] rptaImagen;
	private Integer rptaNumero;
	private Integer numeroOrden;
	private byte esCorrecta;

	public ModeloRespuesta() {
	}

	public ModeloRespuesta(ModeloPregunta modeloPregunta, byte esCorrecta) {
		this.modeloPregunta = modeloPregunta;
		this.esCorrecta = esCorrecta;
	}

	public ModeloRespuesta(ModeloPregunta modeloPregunta, String rptaTexto, byte[] rptaImagen, Integer rptaNumero,
			Integer numeroOrden, byte esCorrecta) {
		this.modeloPregunta = modeloPregunta;
		this.rptaTexto = rptaTexto;
		this.rptaImagen = rptaImagen;
		this.rptaNumero = rptaNumero;
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

	public String getRptaTexto() {
		return this.rptaTexto;
	}

	public void setRptaTexto(String rptaTexto) {
		this.rptaTexto = rptaTexto;
	}

	public byte[] getRptaImagen() {
		return this.rptaImagen;
	}

	public void setRptaImagen(byte[] rptaImagen) {
		this.rptaImagen = rptaImagen;
	}

	public Integer getRptaNumero() {
		return this.rptaNumero;
	}

	public void setRptaNumero(Integer rptaNumero) {
		this.rptaNumero = rptaNumero;
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

}
