package jaio.selection.entity2;
// Generated 21/10/2017 10:12:50 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Entity;

@Entity
public class EvaluacionPerfil implements java.io.Serializable {

	private EvaluacionPerfilId id;
	private BateriaPersonalizada bateriaPersonalizada;
	private Perfil perfil;

	public EvaluacionPerfil() {
	}

	public EvaluacionPerfil(EvaluacionPerfilId id, BateriaPersonalizada bateriaPersonalizada, Perfil perfil) {
		this.id = id;
		this.bateriaPersonalizada = bateriaPersonalizada;
		this.perfil = perfil;
	}

	public EvaluacionPerfilId getId() {
		return this.id;
	}

	public void setId(EvaluacionPerfilId id) {
		this.id = id;
	}

	public BateriaPersonalizada getBateriaPersonalizada() {
		return this.bateriaPersonalizada;
	}

	public void setBateriaPersonalizada(BateriaPersonalizada bateriaPersonalizada) {
		this.bateriaPersonalizada = bateriaPersonalizada;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
