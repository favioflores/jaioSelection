package jaio.selection.view;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import jaio.selection.bean.AreaPerfilBean;
import jaio.selection.dao.AreaDAO;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.PerfilDAO;
import jaio.selection.orm.Area;
import jaio.selection.orm.Empresa;
import jaio.selection.orm.Perfil;
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

	private AreaDAO objAreaDAO = new AreaDAO();
	private PerfilDAO objPerfilDAO = new PerfilDAO();

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

				List<AreaPerfilBean> lstAreaPerfilBean = objAreaDAO.obtenerAreasRegistradas();

				if (lstAreaPerfilBean.isEmpty()) {
					grabarPrimeraVez(idEmpresa);
					lstAreaPerfilBean = objAreaDAO.obtenerAreasRegistradas();
				} 
				
				armarMapaBD(lstAreaPerfilBean);
				armarOrganigramaBD(idEmpresa, lstAreaPerfilBean);
				

			}

		} catch (Exception e) {
			log.error(e);
		}
	}

	protected void armarMapaBD(List<AreaPerfilBean> lstAreaPerfilBean) {
		

		for(AreaPerfilBean objAreaPerfilBean: lstAreaPerfilBean){
			
			Area objArea = objAreaPerfilBean.getArea();
			
			System.out.println("Area : " + objArea.getDescripcion());
			
			System.out.println("Area padre : " + objArea.getArea().getDescripcion());
			
			List<Perfil> lstPerfiles = objAreaPerfilBean.getLstPerfiles();
			
			for(Perfil objPerfil : lstPerfiles) {
				System.out.println("Perfil : " + objPerfil.getNombre());
			}
			
		}
		
				
	}
	
	protected void armarOrganigramaBD(String idEmpresa, List<AreaPerfilBean> lstAreas) {

		try {

			EmpresaDAO objEmpresaDAO = new EmpresaDAO();

			Empresa objEmpresa = objEmpresaDAO.obtenerEmpresa(idEmpresa);

			rootNode = new DefaultOrganigramNode("root", objEmpresa.getNombre(), null);
			rootNode.setCollapsible(false);
			rootNode.setDroppable(false);
			rootNode.setDraggable(false);
			rootNode.setSelectable(true);
			rootNode.setType("root");

		} catch (Exception e) {
			log.error(e);
		}
	}

	protected void grabarPrimeraVez(String idEmpresa) {

		try {

			EmpresaDAO objEmpresaDAO = new EmpresaDAO();

			Empresa objEmpresa = objEmpresaDAO.obtenerEmpresa(idEmpresa);

			Area objArea1 = new Area();
			objArea1.setEmpresa(objEmpresa);
			objArea1.setDescripcion(msg("organizacion.ejemplo.area1"));
			objArea1.setEstado(Constantes.EL_AREA_ESTADO_REGISTRADO);
			objArea1.setFechaRegistro(new Date());

			Perfil objPerfil1 = new Perfil();

			objPerfil1.setArea(objArea1);
			objPerfil1.setNombre(msg("organizacion.ejemplo.perfil1"));
			objPerfil1.setFechaRegistro(new Date());
			objPerfil1.setEstado(Constantes.EL_PERFIL_ESTADO_REGISTRADO);
			objPerfil1.setEmpresa(objEmpresa);

			Perfil objPerfil2 = new Perfil();

			objPerfil2.setArea(objArea1);
			objPerfil2.setNombre(msg("organizacion.ejemplo.perfil2"));
			objPerfil2.setFechaRegistro(new Date());
			objPerfil2.setEstado(Constantes.EL_PERFIL_ESTADO_REGISTRADO);
			objPerfil2.setEmpresa(objEmpresa);
			
			objArea1.getPerfils().add(objPerfil1);
			objArea1.getPerfils().add(objPerfil2);

			objAreaDAO.grabar(objArea1);

			Area objArea2 = new Area();
			objArea2.setEmpresa(objEmpresa);
			objArea2.setDescripcion(msg("organizacion.ejemplo.area2"));
			objArea2.setEstado(Constantes.EL_AREA_ESTADO_REGISTRADO);
			objArea2.setFechaRegistro(new Date());

			Perfil objPerfil3 = new Perfil();

			objPerfil3.setArea(objArea2);
			objPerfil3.setNombre(msg("organizacion.ejemplo.perfil3"));
			objPerfil3.setFechaRegistro(new Date());
			objPerfil3.setEstado(Constantes.EL_PERFIL_ESTADO_REGISTRADO);
			objPerfil3.setEmpresa(objEmpresa);
			
			objArea2.getPerfils().add(objPerfil3);

			objAreaDAO.grabar(objArea2);

		} catch (Exception e) {
			log.error(e);
		}
	}

	protected OrganigramNode addNode(OrganigramNode parent, String tipo, String name, String key) {

		OrganigramNode node = new DefaultOrganigramNode(tipo, name, parent);
		node.setDroppable(true);
		node.setDraggable(true);
		node.setSelectable(true);
		node.setRowKey(key);

		if (tipo.equals("area")) {
			node.setCollapsible(true);
		} else if (tipo.equals("perfil")) {
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

	public void quitarArea() {
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		currentSelection.getParent().getChildren().remove(currentSelection);
	}

	public void quitarPerfil() {
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		currentSelection.getParent().getChildren().remove(currentSelection);
	}

	public synchronized void agregarArea() {

		synchronized (this) {
			OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
			addNode(currentSelection, "area", area,null);
			area = "";
		}

	}

	public synchronized void agregarPerfil() {

		synchronized (this) {
			OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
			addNode(currentSelection, "perfil", perfil,null);
			perfil = "";
		}
	}

	public void verHistorial() {
		try {

		} catch (Exception e) {
			log.error(e);
		}
	}

}
