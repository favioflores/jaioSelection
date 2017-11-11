package jaio.selection.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.event.organigram.OrganigramNodeCollapseEvent;
import org.primefaces.event.organigram.OrganigramNodeDragDropEvent;
import org.primefaces.event.organigram.OrganigramNodeExpandEvent;
import org.primefaces.event.organigram.OrganigramNodeSelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

import jaio.selection.dao.EmpresaDAO;
import jaio.selection.orm.Empresa;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

@ManagedBean(name = "organigramaView")
@ViewScoped
public class OrganigramaView extends BaseView implements Serializable {

	private static Log log = LogFactory.getLog(OrganigramaView.class);

	private static final long serialVersionUID = -1L;

	private OrganigramNode rootNode;
	private OrganigramNode selection;
	private String area;
	private String perfil;

	public OrganigramNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(OrganigramNode rootNode) {
		this.rootNode = rootNode;
	}

	public OrganigramNode getSelection() {
		return selection;
	}

	public void setSelection(OrganigramNode selection) {
		this.selection = selection;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@PostConstruct
	public void init() {

		try {

			mostrarAlerta(INFO, "organigrama.modificando");

			if (Utilitarios.esNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA))) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("crearEmpresa.jsf");
			} else {

				String idEmpresa = (String) Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA);

				EmpresaDAO objEmpresaDAO = new EmpresaDAO();

				Empresa objEmpresa = objEmpresaDAO.obtenerEmpresa(idEmpresa);

				rootNode = new DefaultOrganigramNode("root", objEmpresa.getNombre(), null);
				rootNode.setCollapsible(false);
				rootNode.setDroppable(false);
				rootNode.setDraggable(false);
				rootNode.setSelectable(true);
				rootNode.setType("root");

				OrganigramNode ejemploArea = addNode(rootNode, "area", "Ejemplo de Area");
				
				addNode(ejemploArea, "perfil", "Ylene Sanchez Sotelo");				

			}
			
		} catch (Exception e) {
			log.error(e);
		}
	}

	protected OrganigramNode addNode(OrganigramNode parent, String tipo, String name) {
		
		OrganigramNode node = new DefaultOrganigramNode(tipo, name, parent);
		node.setDroppable(true);
		node.setDraggable(true);
		node.setSelectable(true);
		
		if(tipo.equals("area")) {
			node.setCollapsible(true);	
		}else if(tipo.equals("perfil")) {
			node.setCollapsible(false);
		}
		
		
		return node;
	}
	
	public void nodeDragDropListener(OrganigramNodeDragDropEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSummary("Node '" + event.getOrganigramNode().getData() + "' moved from "
				+ event.getSourceOrganigramNode().getData() + " to '" + event.getTargetOrganigramNode().getData()
				+ "'");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void nodeSelectListener(OrganigramNodeSelectEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSummary("Node '" + event.getOrganigramNode().getData() + "' selected.");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void nodeCollapseListener(OrganigramNodeCollapseEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSummary("Node '" + event.getOrganigramNode().getData() + "' collapsed.");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void nodeExpandListener(OrganigramNodeExpandEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSummary("Node '" + event.getOrganigramNode().getData() + "' expanded.");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void removeDivision() {
		// re-evaluate selection - might be a differenct object instance if viewstate
		// serialization is enabled
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
	}

	public void removeEmployee() {
		// re-evaluate selection - might be a differenct object instance if viewstate
		// serialization is enabled
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		currentSelection.getParent().getChildren().remove(currentSelection);
	}

	public void agregarArea() {
		
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		addNode(currentSelection, "area", area);
		area = "";

	}

	public void agregarPerfil() {
		
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		addNode(currentSelection, "perfil", perfil);
		perfil = "";
	}


	public void verHistorial() {
		try {

		} catch (Exception e) {
			log.error(e);
		}
	}

}
