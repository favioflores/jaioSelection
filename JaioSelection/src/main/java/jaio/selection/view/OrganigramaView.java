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
	private String employeeName;
	
	

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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
				rootNode.setDroppable(true);
				rootNode.setDraggable(false);
				
				/*
				 * OrganigramNode softwareDevelopment = addDivision(rootNode,
				 * "Software Development", "Ridvan Agar");
				 * 
				 * OrganigramNode teamJavaEE = addDivision(softwareDevelopment, "Team JavaEE");
				 * addDivision(teamJavaEE, "JSF", "Thomas Andraschko"); addDivision(teamJavaEE,
				 * "Backend", "Marie Louise");
				 * 
				 * OrganigramNode teamMobile = addDivision(softwareDevelopment, "Team Mobile");
				 * addDivision(teamMobile, "Android", "Andy Ruby"); addDivision(teamMobile,
				 * "iOS", "Stevan Jobs");
				 * 
				 * addDivision(rootNode, "Managed Services", "Thorsten Schultze",
				 * "Sandra Becker");
				 * 
				 * OrganigramNode marketing = addDivision(rootNode, "Marketing");
				 * addDivision(marketing, "Social Media", "Ali Mente", "Susanne Muster");
				 * addDivision(marketing, "Press", "Manfred Mustermann", "Hans Peter");
				 * 
				 * addDivision(rootNode, "Management", "Hassan El Manfalouty");
				 */

			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	protected OrganigramNode addDivision(OrganigramNode parent, String name, String... employees) {
		OrganigramNode divisionNode = new DefaultOrganigramNode("division", name, parent);
		divisionNode.setDroppable(true);
		divisionNode.setDraggable(true);
		divisionNode.setSelectable(true);

		if (employees != null) {
			for (String employee : employees) {
				OrganigramNode employeeNode = new DefaultOrganigramNode("employee", employee, divisionNode);
				employeeNode.setDraggable(true);
				employeeNode.setSelectable(true);
			}
		}

		return divisionNode;
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
		setMessage(currentSelection.getData() + " will entfernt werden.", FacesMessage.SEVERITY_INFO);
	}

	public void removeEmployee() {
		// re-evaluate selection - might be a differenct object instance if viewstate
		// serialization is enabled
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		currentSelection.getParent().getChildren().remove(currentSelection);
	}

	public void addEmployee() {
		// re-evaluate selection - might be a differenct object instance if viewstate
		// serialization is enabled
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);

		OrganigramNode employee = new DefaultOrganigramNode("employee", employeeName, currentSelection);
		employee.setDraggable(true);
		employee.setSelectable(true);
	}

	private void setMessage(String msg, FacesMessage.Severity severity) {
		FacesMessage message = new FacesMessage();
		message.setSummary(msg);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void verHistorial() {
		try {

		} catch (Exception e) {
			log.error(e);
		}
	}

}
