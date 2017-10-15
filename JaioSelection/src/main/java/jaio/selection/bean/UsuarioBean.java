package jaio.selection.bean;

import java.io.Serializable;

public class UsuarioBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer intUsuarioPk;
    private String strEmail;
    private String strFechaAcceso;
    private String strDescripcion;
    
	public Integer getIntUsuarioPk() {
		return intUsuarioPk;
	}
	public void setIntUsuarioPk(Integer intUsuarioPk) {
		this.intUsuarioPk = intUsuarioPk;
	}
	public String getStrEmail() {
		return strEmail;
	}
	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	public String getStrFechaAcceso() {
		return strFechaAcceso;
	}
	public void setStrFechaAcceso(String strFechaAcceso) {
		this.strFechaAcceso = strFechaAcceso;
	}
	public String getStrDescripcion() {
		return strDescripcion;
	}
	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}

    
}
