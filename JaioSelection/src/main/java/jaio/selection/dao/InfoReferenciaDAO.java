package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity2.InfoReferencia;

public interface InfoReferenciaDAO {

	public abstract boolean crearInfoReferencia(InfoReferencia InfoReferencia);
	public abstract boolean actualizaInfoReferencia(InfoReferencia InfoReferencia);
	public abstract InfoReferencia obtenerInfoReferencia(Integer Id);
	public abstract boolean borrarInfoReferencia(InfoReferencia InfoReferencia);
	public abstract List<InfoReferencia> obtenerInfoReferencias();
	
}
