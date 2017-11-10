package jaio.selection.bean;

import java.io.Serializable;

import jaio.selection.orm.Usuario;

public class UsuarioBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer intUsuarioPk;
	private String strMail;
	private String strDescripcion;
	private String strSexo;

	public UsuarioBean(Usuario usuario) {
		intUsuarioPk = usuario.getId();
		strMail = usuario.getCorreo();
		strDescripcion = usuario.getNombreCompleto();
		strSexo = usuario.getSexo() + "";
	}

	public String getStrSexo() {
		return strSexo;
	}

	public void setStrSexo(String strSexo) {
		this.strSexo = strSexo;
	}

	public Integer getIntUsuarioPk() {
		return intUsuarioPk;
	}

	public void setIntUsuarioPk(Integer intUsuarioPk) {
		this.intUsuarioPk = intUsuarioPk;
	}

	public String getStrMail() {
		return strMail;
	}

	public void setStrMail(String strMail) {
		this.strMail = strMail;
	}

	public String getStrDescripcion() {
		return strDescripcion;
	}

	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}

}
