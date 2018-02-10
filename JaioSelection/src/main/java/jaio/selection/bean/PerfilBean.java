package jaio.selection.bean;

import java.io.Serializable;

import org.primefaces.model.OrganigramNode;

public class PerfilBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String descripcion;
    private OrganigramNode node;
    private String dependencia;

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public OrganigramNode getNode() {
        return node;
    }

    public void setNode(OrganigramNode node) {
        this.node = node;
    }

}
