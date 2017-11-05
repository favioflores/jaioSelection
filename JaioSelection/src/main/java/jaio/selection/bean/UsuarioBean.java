package jaio.selection.bean;

import java.io.Serializable;

import jaio.selection.entity.Usuario;

public class UsuarioBean implements Serializable{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer intUsuarioPk;
    private String strMail;
    private String strDescripcion;


    public UsuarioBean (Usuario usuario) {
		intUsuarioPk = usuario.getId();
		strMail = usuario.getCorreo();
		strDescripcion = usuario.getNombreCompleto();
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
