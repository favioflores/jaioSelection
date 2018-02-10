package jaio.selection.view;

import jaio.selection.bean.AreaBean;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.event.organigram.OrganigramNodeDragDropEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

import jaio.selection.bean.AreaOrganigramaBean;
import jaio.selection.bean.ErrorExcelBean;
import jaio.selection.bean.PerfilBean;
import jaio.selection.dao.AreaDAO;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.PerfilDAO;
import jaio.selection.orm.Area;
import jaio.selection.orm.Empresa;
import jaio.selection.orm.Perfil;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "organigramaView")
@ViewScoped
public class OrganigramaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(OrganigramaView.class);

    private static final long serialVersionUID = -1L;

    private List<ErrorExcelBean> lstErrores;
    private OrganigramNode rootNode;
    private OrganigramNode selection;
    private String area;
    private String perfil;
    private HashMap<String, AreaOrganigramaBean> hOrganigrama = new LinkedHashMap<String, AreaOrganigramaBean>();
    private AreaDAO objAreaDAO = new AreaDAO();
    private PerfilDAO objPerfilDAO = new PerfilDAO();
    private String idEmpresa;
    private StreamedContent fileImport;
    private UploadedFile inputFile;

    public List<ErrorExcelBean> getLstErrores() {
        return lstErrores;
    }

    public void setLstErrores(List<ErrorExcelBean> lstErrores) {
        this.lstErrores = lstErrores;
    }

    public UploadedFile getInputFile() {
        return inputFile;
    }

    public void setInputFile(UploadedFile inputFile) {
        this.inputFile = inputFile;
    }

    public StreamedContent getFileImport() {
        return fileImport;
    }

    public void setFileImport(StreamedContent fileImport) {
        this.fileImport = fileImport;
    }

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
        if (area != null) {
            this.area = area.trim();
        }
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        if (perfil != null) {
            this.perfil = perfil.trim();
        }
    }

    @PostConstruct
    public void init() {

        try {

            limpiar();
            mostrarAlerta(INFO, "organigrama.modificando", null, null);

            if (Utilitarios.esNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA))) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("crearEmpresa.jsf");
            } else {

                idEmpresa = (String) Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA);

                hOrganigrama = objAreaDAO.obtenerAreasPerfilesRegistrados();

                if (hOrganigrama.isEmpty()) {
                    grabarPrimeraVez(idEmpresa);
                }

                armarMapaBD(idEmpresa);

            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void limpiar() {
        inputFile = null;
        fileImport = null;
        lstErrores = new ArrayList<>();
    }

    protected void armarMapaBD(String idEmpresa) {

        try {

            hOrganigrama.clear();

            hOrganigrama = objAreaDAO.obtenerAreasPerfilesRegistrados();

            EmpresaDAO objEmpresaDAO = new EmpresaDAO();

            Empresa objEmpresa = objEmpresaDAO.obtenerEmpresa(idEmpresa);

            rootNode = new DefaultOrganigramNode("root", objEmpresa.getNombre(), null);
            rootNode.setCollapsible(false);
            rootNode.setDroppable(true);
            rootNode.setDraggable(false);
            rootNode.setSelectable(true);
            rootNode.setType("root");

            Iterator it = hOrganigrama.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry pair = (Map.Entry) it.next();

                AreaOrganigramaBean objAreaOrganigramaBean = (AreaOrganigramaBean) pair.getValue();

                // ESTE CÓDIGO FUNCIONA PORQUE EL QUERY ORDENA LOS OBJETOS
                if (Utilitarios.esNuloOVacio(objAreaOrganigramaBean.getId_parent())) {
                    objAreaOrganigramaBean.setNode(addNode(rootNode, "area", objAreaOrganigramaBean.getDescripcion(),
                            "area" + objAreaOrganigramaBean.getId()));
                } else {
                    objAreaOrganigramaBean.setNode(addNode(
                            hOrganigrama.get(objAreaOrganigramaBean.getId_parent().toString()).getNode(), "area",
                            objAreaOrganigramaBean.getDescripcion(), "area" + objAreaOrganigramaBean.getId()));
                }

                for (PerfilBean objPerfilBean : objAreaOrganigramaBean.getLstPerfiles()) {
                    objPerfilBean.setNode(addNode(objAreaOrganigramaBean.getNode(), "perfil",
                            objPerfilBean.getDescripcion(), "perfil" + objPerfilBean.getId()));
                }

            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
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
            objArea2.setArea(objArea1);

            Perfil objPerfil3 = new Perfil();

            objPerfil3.setArea(objArea2);
            objPerfil3.setNombre(msg("organizacion.ejemplo.perfil3"));
            objPerfil3.setFechaRegistro(new Date());
            objPerfil3.setEstado(Constantes.EL_PERFIL_ESTADO_REGISTRADO);
            objPerfil3.setEmpresa(objEmpresa);

            objArea2.getPerfils().add(objPerfil3);

            objAreaDAO.grabar(objArea2);

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
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

    public void moverNodo(OrganigramNodeDragDropEvent event) {

        try {

            armarMapaBD(idEmpresa);

            if (event.getOrganigramNode().getType().equals("perfil")) {

                if (!event.getTargetOrganigramNode().getType().equals("area")) {
                    mostrarAlerta(ERROR, "organizacion.nopuede.mover.perfil", null, null,
                            event.getOrganigramNode().getData(), event.getTargetOrganigramNode().getData());
                } else {

                    Iterator it = hOrganigrama.entrySet().iterator();

                    while (it.hasNext()) {

                        Map.Entry pair = (Map.Entry) it.next();

                        AreaOrganigramaBean objAreaOrganigramaBean = (AreaOrganigramaBean) pair.getValue();

                        if (objAreaOrganigramaBean.getDescripcion().equals(event.getTargetOrganigramNode().getData())) {
                            event.getTargetOrganigramNode().setRowKey(objAreaOrganigramaBean.getId());
                        }

                        for (PerfilBean objPerfilBean : objAreaOrganigramaBean.getLstPerfiles()) {

                            if (objPerfilBean.getDescripcion().equals(event.getOrganigramNode().getData())) {
                                event.getOrganigramNode().setRowKey(objPerfilBean.getId());
                            }

                        }

                    }

                    if (Utilitarios.noEsNuloOVacio(event.getOrganigramNode().getRowKey())
                            && Utilitarios.noEsNuloOVacio(event.getTargetOrganigramNode().getRowKey())) {

                        boolean flag = objPerfilDAO.moverPerfil(event.getOrganigramNode().getRowKey(),
                                event.getTargetOrganigramNode().getRowKey());

                        if (flag) {
                            mostrarAlerta(INFO, "organizacion.movio.perfil", null, null,
                                    event.getOrganigramNode().getData(), event.getTargetOrganigramNode().getData());
                        }
                    }

                }

            } else if (event.getOrganigramNode().getType().equals("area")) {

                if (event.getTargetOrganigramNode().getType().equals("perfil")) {
                    mostrarAlerta(ERROR, "organizacion.nopuede.mover.area", null, null,
                            event.getOrganigramNode().getData(), event.getTargetOrganigramNode().getData());
                } else {

                    Iterator it = hOrganigrama.entrySet().iterator();

                    while (it.hasNext()) {

                        Map.Entry pair = (Map.Entry) it.next();

                        AreaOrganigramaBean objAreaOrganigramaBean = (AreaOrganigramaBean) pair.getValue();

                        if (objAreaOrganigramaBean.getDescripcion().equals(event.getTargetOrganigramNode().getData())) {
                            event.getTargetOrganigramNode().setRowKey(objAreaOrganigramaBean.getId());
                        }

                        if (objAreaOrganigramaBean.getDescripcion().equals(event.getOrganigramNode().getData())) {
                            event.getOrganigramNode().setRowKey(objAreaOrganigramaBean.getId());
                        }

                        for (PerfilBean objPerfilBean : objAreaOrganigramaBean.getLstPerfiles()) {

                            if (objPerfilBean.getDescripcion().equals(event.getOrganigramNode().getData())) {
                                event.getOrganigramNode().setRowKey(objPerfilBean.getId());
                            }

                        }

                    }

                    String parent;
                    if (event.getTargetOrganigramNode().getType().equals("root")) {
                        parent = null;
                    } else {
                        parent = event.getTargetOrganigramNode().getRowKey();
                    }

                    if (Utilitarios.noEsNuloOVacio(event.getOrganigramNode().getRowKey())
                            && Utilitarios.noEsNuloOVacio(event.getTargetOrganigramNode().getRowKey())) {

                        boolean flag = objAreaDAO.moverArea(event.getOrganigramNode().getRowKey(), parent);

                        if (flag) {
                            mostrarAlerta(INFO, "organizacion.movio.area", null, null,
                                    event.getOrganigramNode().getData(), event.getTargetOrganigramNode().getData());
                        }
                    }

                }

            }

            armarMapaBD(idEmpresa);

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void quitarArea() {

        try {

            armarMapaBD(idEmpresa);

            OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);

            Iterator it = hOrganigrama.entrySet().iterator();

            String parent = null;

            int countAreas = 0;

            while (it.hasNext()) {

                Map.Entry pair = (Map.Entry) it.next();

                AreaOrganigramaBean objAreaOrganigramaBean = (AreaOrganigramaBean) pair.getValue();

                if (objAreaOrganigramaBean.getDescripcion().equals(currentSelection.getData().toString())) {
                    currentSelection.setRowKey(objAreaOrganigramaBean.getId());

                    if (Utilitarios.noEsNuloOVacio(objAreaOrganigramaBean.getId_parent())) {
                        parent = objAreaOrganigramaBean.getId_parent();
                    }
                }

                countAreas++;

            }

            // ENCUENTRA A LOS HIJOS Y LOS ACTUALIZA POR EL NUEVO PADRE
            if (Utilitarios.esNuloOVacio(parent) && !currentSelection.getChildren().isEmpty()) {
                mostrarAlerta(ERROR, "organizacion.ultima.area.con.perfil", null, null);
            } else if (Utilitarios.esNuloOVacio(parent) && countAreas == 1) {
                mostrarAlerta(ERROR, "organizacion.ultima.area", null, null);
            } else {
                boolean flag = objAreaDAO.eliminaAreaActualizaHerarquias(currentSelection.getRowKey(), parent);
                if (flag) {
                    mostrarAlerta(INFO, "organizacion.area.eliminada", null, null);
                } else {
                    mostrarAlerta(ERROR, "error.inesperado", null, null);
                }
            }

            armarMapaBD(idEmpresa);

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void quitarPerfil() {

        try {

            armarMapaBD(idEmpresa);

            OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);

            Iterator it = hOrganigrama.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry pair = (Map.Entry) it.next();

                AreaOrganigramaBean objAreaOrganigramaBean = (AreaOrganigramaBean) pair.getValue();

                for (PerfilBean objPerfilBean : objAreaOrganigramaBean.getLstPerfiles()) {

                    // VALIDA SI EXISTE EL MISMO NODO
                    if (objPerfilBean.getDescripcion().equals(currentSelection.getData())) {
                        currentSelection.setRowKey(objPerfilBean.getId());
                        break;
                    }

                }

            }

            boolean flag = objPerfilDAO.eliminaPerfil(currentSelection.getRowKey());

            if (flag) {
                mostrarAlerta(INFO, "organizacion.perfil.eliminado", null, null);
            } else {
                mostrarAlerta(FATAL, "error.inesperado", null, null);
            }

            armarMapaBD(idEmpresa);

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public synchronized void agregarArea() {

        synchronized (this) {

            try {

                armarMapaBD(idEmpresa);

                OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);

                if (validaNuevaArea(currentSelection)) {

                    Empresa objEmpresa = new Empresa();

                    objEmpresa.setId(Integer.parseInt(idEmpresa));

                    Area objArea = new Area();
                    objArea.setDescripcion(area);
                    objArea.setEmpresa(objEmpresa);
                    objArea.setEstado(Constantes.EL_AREA_ESTADO_REGISTRADO);
                    objArea.setFechaRegistro(new Date());

                    if (!currentSelection.getType().equals("root")) {

                        String[] idArea = currentSelection.getRowKey().split("area");
                        Area objAreaParent = new Area();
                        objAreaParent.setId(Integer.parseInt(idArea[0]));
                        objArea.setArea(objAreaParent);

                    }

                    objAreaDAO.grabar(objArea);

                    area = "";

                    mostrarAlerta(INFO, "organizacion.area.agregada", null, null);

                    armarMapaBD(idEmpresa);

                }

            } catch (Exception e) {
                mostrarAlerta(FATAL, "error.inesperado", log, e);
            }
        }

    }

    private boolean validaNuevaArea(OrganigramNode currentSelection) {

        Iterator it = hOrganigrama.entrySet().iterator();

        boolean nuevoPermitido = true;

        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry) it.next();

            AreaOrganigramaBean objAreaOrganigramaBean = (AreaOrganigramaBean) pair.getValue();

            if (objAreaOrganigramaBean.getDescripcion().equals(currentSelection.getData().toString())) {
                currentSelection.setRowKey(objAreaOrganigramaBean.getId());
            }

            // VALIDA SI EXISTE EL MISMO NODO
            if (objAreaOrganigramaBean.getDescripcion().equals(area)) {
                nuevoPermitido = false;
                mostrarAlerta(ERROR, "organizacion.existe.area", null, null);
            }
        }

        return nuevoPermitido;
    }

    private boolean validaNuevoPerfil(OrganigramNode currentSelection) {

        Iterator it = hOrganigrama.entrySet().iterator();

        boolean nuevoPermitido = true;

        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry) it.next();

            AreaOrganigramaBean objAreaOrganigramaBean = (AreaOrganigramaBean) pair.getValue();

            if (objAreaOrganigramaBean.getDescripcion().equals(currentSelection.getData().toString())) {
                currentSelection.setRowKey(objAreaOrganigramaBean.getId());
            }

            for (PerfilBean objPerfilBean : objAreaOrganigramaBean.getLstPerfiles()) {

                // VALIDA SI EXISTE EL MISMO NODO
                if (objPerfilBean.getDescripcion().equals(perfil)) {
                    nuevoPermitido = false;
                    mostrarAlerta(ERROR, "organizacion.existe.perfil", null, null);
                }

            }

        }

        return nuevoPermitido;
    }

    public synchronized void agregarPerfil() {

        synchronized (this) {

            try {

                armarMapaBD(idEmpresa);

                OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);

                if (validaNuevoPerfil(currentSelection)) {

                    Empresa objEmpresa = new Empresa();

                    objEmpresa.setId(Integer.parseInt(idEmpresa));

                    Perfil objPerfil = new Perfil();
                    objPerfil.setNombre(perfil);
                    objPerfil.setEmpresa(objEmpresa);
                    objPerfil.setEstado(Constantes.EL_PERFIL_ESTADO_REGISTRADO);
                    objPerfil.setFechaRegistro(new Date());

                    if (!currentSelection.getType().equals("root")) {

                        String[] idArea = currentSelection.getRowKey().split("area");
                        Area objAreaParent = new Area();
                        objAreaParent.setId(Integer.parseInt(idArea[0]));
                        objPerfil.setArea(objAreaParent);

                    }

                    objPerfilDAO.grabar(objPerfil);

                    perfil = "";

                    mostrarAlerta(INFO, "organizacion.perfil.agregado", null, null);

                    armarMapaBD(idEmpresa);

                }

            } catch (Exception e) {
                mostrarAlerta(FATAL, "error.inesperado", log, e);
            }
        }

    }

    public void verHistorial() {
        try {

        } catch (Exception e) {
            log.error(e);
        }
    }

    public void fileExport() {

    }

    public void cargaOrganigramaMasivo(FileUploadEvent event) {

        List<ErrorExcelBean> lstErrorExcelBeans = new ArrayList<>();

        if (event.getFile() == null) {
            mostrarAlerta(ERROR, "organigrama.masivo.archivo.vacio", null, null);
        } else {

            XSSFWorkbook xlsxMasivo;

            try {
                xlsxMasivo = new XSSFWorkbook(event.getFile().getInputstream());

                /**
                 * VALIDA LA CABECERA
                 */
                validaCabeceraMasivo(xlsxMasivo, lstErrorExcelBeans);

                /**
                 * VALIDA EL CONTENIDO
                 */
                if (lstErrorExcelBeans.isEmpty()) {
                    validaOrganigramaMasivo(xlsxMasivo, lstErrorExcelBeans);
                } else {
                    lstErrores = lstErrorExcelBeans;
                    mostrarAlerta(WARN, "organigrama.masivo.archivo.procesoConErrores", null, null);
                    return;
                }

                /**
                 * PROCESA LSO REGISTROS
                 */
                if (lstErrorExcelBeans.isEmpty()) {
                    procesaOrganigramaMasivo(xlsxMasivo, lstErrorExcelBeans);
                } else {
                    lstErrores = lstErrorExcelBeans;
                    mostrarAlerta(WARN, "organigrama.masivo.archivo.procesoConErrores", null, null);
                    return;
                }

                if (!lstErrorExcelBeans.isEmpty()) {
                    lstErrores = lstErrorExcelBeans;
                    mostrarAlerta(WARN, "organigrama.masivo.archivo.procesoConErrores", null, null);
                    return;
                } else {
                    mostrarAlerta(INFO, "organigrama.masivo.archivo.procesoOk", null, null);
                }

                inputFile = null;
                fileImport = null;

            } catch (Exception ex) {
                mostrarAlerta(FATAL, "error.inesperado", log, ex);
            }

        }

    }

    private List<ErrorExcelBean> procesaOrganigramaMasivo(XSSFWorkbook xlsMasivo, List<ErrorExcelBean> lstErrores) {

        try {

            XSSFSheet hoja = xlsMasivo.getSheetAt(0);
            Iterator<Row> filas = hoja.iterator();

            filas.next();
            filas.next();

            Map<String, String> mpArea = new HashMap();
            Map<String, String> mpPerfil = new HashMap();

            // RECORRE AREAS Y VALIDA SOLO AUTENTICAS
            while (filas.hasNext()) {

                Row row = filas.next();

                String strId = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_ID);
                String strRegistro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_TIPO_REGISTRO);
                String strNombre = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO);

                ErrorExcelBean objErrorExcelBean = new ErrorExcelBean();
                objErrorExcelBean.setStrFila((row.getRowNum() + 1) + "");

                if (strRegistro.toUpperCase().equals(Constantes.XLSX_ORG_MASIVO_COLUMNA_TIPO_AREA)) {

                    boolean esError = false;

                    if (mpArea.containsKey(strId)) {
                        objErrorExcelBean.setStrValor(strId);
                        objErrorExcelBean.setStrEtiqueta(msg("organigrama.masivo.columna.id"));
                        objErrorExcelBean.setStrColumna(CellReference.convertNumToColString(Constantes.XLSX_COLUMNA_ID));
                        objErrorExcelBean.setStrError(msg("organigrama.masivo.error.area.id.duplicado"));
                        esError = true;
                        lstErrores.add(objErrorExcelBean);
                    }

                    if (mpArea.containsValue(strNombre)) {
                        objErrorExcelBean.setStrValor(strNombre);
                        objErrorExcelBean.setStrEtiqueta(msg("organigrama.masivo.columna.nombreRegistro"));
                        objErrorExcelBean.setStrColumna(CellReference.convertNumToColString(Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO));
                        objErrorExcelBean.setStrError(msg("organigrama.masivo.error.area.nombre.duplicado"));
                        esError = true;
                        lstErrores.add(objErrorExcelBean);
                    }

                    if (!esError) {
                        mpArea.put(strId, strNombre);
                    }

                }

            }

            // RECORRE PERFILES Y VALIDA SOLO AUTENTICAS
            filas = hoja.iterator();
            filas.next();
            filas.next();

            while (filas.hasNext()) {

                Row row = filas.next();

                String strId = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_ID);
                String strRegistro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_TIPO_REGISTRO);
                String strNombre = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO);
                String strDependencia = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_DEPENDENCIA);

                ErrorExcelBean objErrorExcelBean = new ErrorExcelBean();
                objErrorExcelBean.setStrFila((row.getRowNum() + 1) + "");

                if (strRegistro.toUpperCase().equals(Constantes.XLSX_ORG_MASIVO_COLUMNA_TIPO_PERFIL)) {

                    boolean esError = false;

                    if (mpPerfil.containsKey(strId)) {
                        objErrorExcelBean.setStrValor(strId);
                        objErrorExcelBean.setStrEtiqueta(msg("organigrama.masivo.columna.id"));
                        objErrorExcelBean.setStrColumna(CellReference.convertNumToColString(Constantes.XLSX_COLUMNA_ID));
                        objErrorExcelBean.setStrError(msg("organigrama.masivo.error.perfil.id.duplicado"));
                        esError = true;
                        lstErrores.add(objErrorExcelBean);
                    }

                    if (mpPerfil.containsValue(strNombre)) {
                        objErrorExcelBean.setStrValor(strNombre);
                        objErrorExcelBean.setStrEtiqueta(msg("organigrama.masivo.columna.nombreRegistro"));
                        objErrorExcelBean.setStrColumna(CellReference.convertNumToColString(Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO));
                        objErrorExcelBean.setStrError(msg("organigrama.masivo.error.perfil.nombre.duplicado"));
                        esError = true;
                        lstErrores.add(objErrorExcelBean);
                    }

                    if (!mpArea.containsKey(strDependencia)) {
                        objErrorExcelBean.setStrValor(strDependencia);
                        objErrorExcelBean.setStrEtiqueta(msg("organigrama.masivo.columna.dependencia"));
                        objErrorExcelBean.setStrColumna(CellReference.convertNumToColString(Constantes.XLSX_COLUMNA_DEPENDENCIA));
                        objErrorExcelBean.setStrError(msg("organigrama.masivo.error.perfil.dependencia.noexiste"));

                        esError = true;
                        lstErrores.add(objErrorExcelBean);
                    }

                    if (!esError) {
                        mpPerfil.put(strId, strNombre);
                    }

                }

            }

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);
        }

        return lstErrores;

    }

    private List<ErrorExcelBean> validaCabeceraMasivo(XSSFWorkbook xlsMasivo, List<ErrorExcelBean> lstErrores) {

        try {

            XSSFSheet hoja = xlsMasivo.getSheetAt(0);
            Iterator<Row> filas = hoja.iterator();

            filas.next();

            Row row = filas.next();

            String strId = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_ID);
            String strRegistro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_TIPO_REGISTRO);
            String strNombre = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO);
            String strDependencia = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_DEPENDENCIA);

            Utilitarios.validaValorCeldaXLSX(strId, Constantes.TIPO_STRING, msg("organigrama.masivo.columna.id"), lstErrores, row, Constantes.XLSX_COLUMNA_ID, true,
                    Constantes.XLSX_ORG_MASIVO_CAB_ID);
            Utilitarios.validaValorCeldaXLSX(strRegistro, Constantes.TIPO_STRING, msg("organigrama.masivo.columna.tipoRegistro"), lstErrores, row, Constantes.XLSX_COLUMNA_TIPO_REGISTRO, true,
                    Constantes.XLSX_ORG_MASIVO_CAB_TIPO_REGISTRO);
            Utilitarios.validaValorCeldaXLSX(strNombre, Constantes.TIPO_STRING, msg("organigrama.masivo.columna.nombreRegistro"), lstErrores, row, Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO, true,
                    Constantes.XLSX_ORG_MASIVO_CAB_NOMBRE_REGISTRO);
            Utilitarios.validaValorCeldaXLSX(strDependencia, Constantes.TIPO_STRING, msg("organigrama.masivo.columna.dependencia"), lstErrores, row, Constantes.XLSX_COLUMNA_DEPENDENCIA, true,
                    Constantes.XLSX_ORG_MASIVO_CAB_DEPENDENCIA);

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);
        }

        return lstErrores;
    }

    private List<ErrorExcelBean> validaOrganigramaMasivo(XSSFWorkbook xlsMasivo, List<ErrorExcelBean> lstErrores) {

        try {

            XSSFSheet hoja = xlsMasivo.getSheetAt(0);
            Iterator<Row> filas = hoja.iterator();
            filas.next();
            filas.next();

            while (filas.hasNext()) {

                Row row = filas.next();

                String strId = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_ID);
                String strRegistro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_TIPO_REGISTRO);
                String strNombre = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO);
                String strDependencia = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_COLUMNA_DEPENDENCIA);

                Utilitarios.validaValorCeldaXLSX(strId, Constantes.TIPO_INTEGER, msg("organigrama.masivo.columna.id"), lstErrores, row, Constantes.XLSX_COLUMNA_ID, true);
                Utilitarios.validaValorCeldaXLSX(strRegistro, Constantes.TIPO_STRING, msg("organigrama.masivo.columna.tipoRegistro"), lstErrores, row, Constantes.XLSX_COLUMNA_TIPO_REGISTRO, true,
                        Constantes.XLSX_ORG_MASIVO_COLUMNA_TIPO_AREA,
                        Constantes.XLSX_ORG_MASIVO_COLUMNA_TIPO_PERFIL);
                Utilitarios.validaValorCeldaXLSX(strNombre, Constantes.TIPO_STRING, msg("organigrama.masivo.columna.nombreRegistro"), lstErrores, row, Constantes.XLSX_COLUMNA_NOMBRE_REGISTRO, true);
                Utilitarios.validaValorCeldaXLSX(strDependencia, Constantes.TIPO_INTEGER, msg("organigrama.masivo.columna.dependencia"), lstErrores, row, Constantes.XLSX_COLUMNA_DEPENDENCIA, false);

            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

        return lstErrores;

    }

}
