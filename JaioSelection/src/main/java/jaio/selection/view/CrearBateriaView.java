package jaio.selection.view;

import static jaio.selection.view.BaseView.ERROR;
import static jaio.selection.view.BaseView.FATAL;
import static jaio.selection.view.BaseView.INFO;

import jaio.selection.bean.AjusteEvaluacionBean;
import jaio.selection.bean.BateriaBean;
import jaio.selection.bean.ConvertirDatosBean;
import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.ModeloAjustesCalcBean;
import jaio.selection.bean.ModeloAjustesListaBean;
import jaio.selection.bean.ModeloCompetenciaBean;
import jaio.selection.bean.PerfilBean;
import jaio.selection.dao.AreaDAO;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.ModeloCompetenciaDAO;
import jaio.selection.util.Constantes;
import jaio.selection.dao.ModeloEvaluacionDAO;
import jaio.selection.dao.PerfilDAO;
import jaio.selection.orm.Area;
import jaio.selection.orm.BateriaPersonalizada;
import jaio.selection.orm.Empresa;
import jaio.selection.orm.ModeloCompetencia;
import jaio.selection.orm.ModeloEvaluacion;
import jaio.selection.orm.Perfil;
import jaio.selection.util.Utilitarios;
import jaio.selection.bean.AreaBean;
import jaio.selection.bean.ModeloEvaluacionCalBean;
import jaio.selection.bean.ModeloEvaluacionListCalBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "crearBateriaView")
@ViewScoped
public class CrearBateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(CrearBateriaView.class);
    private static final long serialVersionUID = -1L;

    private String strNombreProceso;
    private List listaModeloCompetenciaTotalEvaluaciones = new ArrayList<>();
    private List listaNombresCompetenciasPorEvaluacion = new ArrayList<>();
    private List<ModeloCompetencia> listaColoresCompetenciasPorEvaluacion = new ArrayList<>();
    private List<ModeloAjustesCalcBean> listaAjustesPorEvaluacion;

    private List listaEvaluacionesPorCompetencia = new ArrayList<>();
    private LinkedHashMap<String, ModeloEvaluacionListCalBean> mapEvaComp = new LinkedHashMap();

    private List listaAjustesTotalEvaluaciones = new ArrayList<>();
    private List<ModeloAjustesCalcBean> listaAjustesPorEvaluacion1 = new ArrayList<>();
    private LinkedHashMap<String, ModeloAjustesListaBean> mhAjustes = new LinkedHashMap();

    private String strCompSeleccionada;
    private List<ModeloCompetenciaBean> listaCompetenciasTotal = new ArrayList<>();
    private List<BateriaBean> listaDeEvaluaciones = new ArrayList<>();
    private List<BateriaBean> listaDeEvaluacionesRespaldo = new ArrayList<>();
    private List<EmpresaBean> lstEmpresas;
    private List<AreaBean> lstArea;
    private List<PerfilBean> lstPerfil;
    private String strEmpresaSeleccionada;
    private String strAreaSeleccionada;
    private String strPerfilSeleccionado;
    private boolean lockEmpresa;
    private int minutosTotal = 0;
    private LinkedHashMap<String, String> mapCompetenciasSeleccionadas = new LinkedHashMap<>();
    private List<BateriaBean> droppedBaterias;
    private List<ModeloCompetenciaBean> droppedCompetencias;
    private String n;
    private String nm;

    private LinkedHashMap<String, String> mapGuiaEdit = new LinkedHashMap();

    @PostConstruct
    public void init() {
        cargarEvaluaciones();
        cargarListaCompetenciasTotal();
        llenarMapEvaComp();
        if (Utilitarios.noEsNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_ID_BATERIA))) {
            cargarEvaluacionesParaEditar((String) Utilitarios.obtenerSession(Constantes.SESSION_ID_BATERIA));
        } else {
            lockEmpresa = false;
            strNombreProceso = null;
            strCompSeleccionada = null;
            poblarEmpresas();
            lstArea = new ArrayList<>();
            lstPerfil = new ArrayList<>();
            minutosTotal = 0;
            mapCompetenciasSeleccionadas.clear();
            droppedBaterias = new ArrayList<>();
            droppedCompetencias = new ArrayList<>();
            if (Utilitarios.noEsNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_BATERIA))) {
                strEmpresaSeleccionada = (String) Utilitarios.obtenerSession(Constantes.SESSION_BATERIA);
                poblarArea();
            } else {
                strEmpresaSeleccionada = null;
                strAreaSeleccionada = null;
                strPerfilSeleccionado = null;
            }
        }
    }

    public void limpiar() {
        if (!Utilitarios.noEsNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_ID_BATERIA))) {
            strEmpresaSeleccionada = null;
            poblarEmpresas();
        }
        strNombreProceso = null;
        strCompSeleccionada = null;
        cargarRespaldo();
        lstArea = new ArrayList<>();
        lstPerfil = new ArrayList<>();
        strAreaSeleccionada = null;
        strPerfilSeleccionado = null;
        poblarArea();
        minutosTotal = 0;
        mapCompetenciasSeleccionadas.clear();
        droppedBaterias = new ArrayList<>();
        droppedCompetencias = new ArrayList<>();
    }

    public List cargarEvaluacionesParaEditar(String id) {
        try {
            ModeloEvaluacionDAO objEvaluacionDAO = new ModeloEvaluacionDAO();
            for (Object o : objEvaluacionDAO.obtenerInformacionDeCampos(id)) {
                Object c[] = (Object[]) o;
                strNombreProceso = c[0].toString();
                poblarEmpresas();
                strEmpresaSeleccionada = c[1].toString();
                poblarArea();
                strAreaSeleccionada = c[2].toString();
                poblarPerfil();
                strPerfilSeleccionado = c[3].toString();
            }
            droppedBaterias = new ArrayList<>();
            lockEmpresa = true;

            for (Object o : objEvaluacionDAO.obtenerEvaluacionesSeleccionadas(id)) {
                Object obj[] = (Object[]) o;
                if (mapGuiaEdit.containsKey(obj[0].toString())) {
                    BateriaBean bateria = listaDeEvaluacionesRespaldo.get(Integer.parseInt(mapGuiaEdit.get(obj[0].toString())));
                    droppedBaterias.add(bateria);
                    listaDeEvaluaciones.remove(bateria);
                }
            }
            contarMinutos();
            droppedCompetencias = new ArrayList<>();
            mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
            cargarCompetencias();
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return null;
    }

    public List cargarEvaluaciones() {
        try {

            ModeloCompetenciaDAO objModeloCompetenciaDAO = new ModeloCompetenciaDAO();
            listaModeloCompetenciaTotalEvaluaciones = new ArrayList<>();
            listaModeloCompetenciaTotalEvaluaciones = objModeloCompetenciaDAO.obtenerModeloCompetenciaDeEvaluaciones();

            ModeloEvaluacionDAO objEvaluacionDAO = new ModeloEvaluacionDAO();
            listaAjustesTotalEvaluaciones = new ArrayList<>();
            listaAjustesTotalEvaluaciones = objEvaluacionDAO.obtenerAjustesDeEvaluaciones();

            List<BateriaBean> lstEvaluaciones = new ArrayList<>();
            ConvertirDatosBean convertirDatos = new ConvertirDatosBean();
            int i = 0;
            for (ModeloEvaluacion objModeloEvaluacion : objEvaluacionDAO.obtenerModelos()) {
                BateriaBean objBateriaBean = new BateriaBean();
                objBateriaBean.setId(objModeloEvaluacion.getId().toString());
                objBateriaBean.setNombre(objModeloEvaluacion.getNombre());
                objBateriaBean.setValidez(objModeloEvaluacion.getValidez().toString());
                objBateriaBean.setConfiabilidad(objModeloEvaluacion.getConfiabilidad().toString());
                objBateriaBean.setMinutosEstimados(objModeloEvaluacion.getMinutosEstimados().toString());
                objBateriaBean.setLimiteTiempo(convertirDatos.convertirObjetAString(objModeloEvaluacion.getLimiteTiempo(), Constantes.Tipo_dato_byte));

                cargarModeloCompetenciaPorEvaluacion(objBateriaBean.getId());
                objBateriaBean.setLstCompetencias(listaNombresCompetenciasPorEvaluacion);
                objBateriaBean.setLstColores(listaColoresCompetenciasPorEvaluacion);

                cargarAjustePorEvaluacion();
                if (mhAjustes.containsKey(objBateriaBean.getId())) {
                    objBateriaBean.setLstAjustes(mhAjustes.get(objBateriaBean.getId()).getLstModeloAjustesCalcBeans());
                }

                lstEvaluaciones.add(objBateriaBean);
                mapGuiaEdit.put(objBateriaBean.getId(), Integer.toString(i));
                i++;
            }

            listaDeEvaluacionesRespaldo.addAll(lstEvaluaciones);
            listaDeEvaluaciones.addAll(lstEvaluaciones);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return listaDeEvaluaciones;
    }

    public void llenarMapEvaComp() {
        try {
            ModeloEvaluacionDAO objEvaluacionDAO = new ModeloEvaluacionDAO();
            listaEvaluacionesPorCompetencia = objEvaluacionDAO.obtenerListaDeEvaluacionesPorCompetencia();
            mapEvaComp.clear();

            ModeloEvaluacionCalBean objEvaluacionCalBean = new ModeloEvaluacionCalBean();
            ModeloEvaluacionListCalBean objEvaluacionListCalBean = new ModeloEvaluacionListCalBean();

            String idCAnt = "";
            Iterator it = listaEvaluacionesPorCompetencia.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                String idC = obj[0].toString();

                if (idC.equals(idCAnt) && !idCAnt.equals("")) {
                    objEvaluacionCalBean = new ModeloEvaluacionCalBean();
                    objEvaluacionCalBean.setId(obj[1].toString());
                    objEvaluacionCalBean.setNombre(obj[2].toString());
                    objEvaluacionListCalBean.setId(idCAnt);
                    objEvaluacionListCalBean.getListModeloEvaluacionCalBean().add(objEvaluacionCalBean);
                    idCAnt = idC;
                } else {

                    if (!idCAnt.equals("")) {
                        mapEvaComp.put(idCAnt, objEvaluacionListCalBean);
                    }
                    objEvaluacionListCalBean = new ModeloEvaluacionListCalBean();
                    objEvaluacionCalBean = new ModeloEvaluacionCalBean();
                    objEvaluacionCalBean.setId(obj[1].toString());
                    objEvaluacionCalBean.setNombre(obj[2].toString());

                    objEvaluacionListCalBean.setListModeloEvaluacionCalBean(new ArrayList<>());
                    objEvaluacionListCalBean.setId(new String());
                    objEvaluacionListCalBean.setId(idC);
                    objEvaluacionListCalBean.getListModeloEvaluacionCalBean().add(objEvaluacionCalBean);

                    idCAnt = idC;
                }
            }
            mapEvaComp.put(idCAnt, objEvaluacionListCalBean);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public List buscarEvaluacionXCompetencia() {
        try {
            ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();

            if (Utilitarios.noEsNuloOVacio(strCompSeleccionada) && !strCompSeleccionada.equals("-1")) {
                List listaEvaluacionesBD = objModelosDao.obtenerModelosXCompetencia(strCompSeleccionada, obtenerIdEvaluacionesSeleccionadas());
                iterarEvaluacion(listaEvaluacionesBD);
            } else {
                List listaEvaluacionesBD = objModelosDao.obtenerModelosCompetencia(obtenerIdEvaluacionesSeleccionadas());
                iterarEvaluacion(listaEvaluacionesBD);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return listaDeEvaluaciones;
    }

    public void verificarCompetenciaAlRetirar(BateriaBean objBateriaBean) {
        try {
            List listRetirado = new ArrayList<>();
            n = null;
            nm = null;
            LinkedHashMap<String, String> comp = new LinkedHashMap<>();
            
            LinkedHashMap<String, String> compE = new LinkedHashMap<>();
            
            for (Object o : objBateriaBean.getLstCompetencias()) {
                for (BateriaBean bat : droppedBaterias) {
                    comp = new LinkedHashMap<>();
                    Integer i = 0;
                    for (Object a : bat.getLstCompetencias()) {
                        comp.put(i.toString(), a.toString());
                        i++;
                    }
                    if (!comp.containsValue(o) && !compE.containsValue(o)) {
                        compE.put(o.toString(), o.toString());
                        listRetirado.add(o.toString());
                    }
                    if(droppedBaterias.size() == Constantes.Int_uno){
                        listRetirado.add(o.toString());
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < listRetirado.size(); i++) {
                sb.append("'").append(listRetirado.get(i)).append("'").append(",");
            }
            sb.deleteCharAt(sb.length() - 1).toString();
            nm = objBateriaBean.getNombre();
            n = sb.toString();
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public StringBuilder obtenerIdEvaluacionesSeleccionadas() {
        StringBuilder sb = new StringBuilder();
        if (Utilitarios.noEsNuloOVacio(droppedBaterias) && droppedBaterias.size() > Constantes.Int_zero) {
            List<String> lstId = new ArrayList<>();
            for (BateriaBean objBateriaBean : droppedBaterias) {
                lstId.add(objBateriaBean.getId());
            }
            for (int i = 0; i < lstId.size(); i++) {
                sb.append("'").append(lstId.get(i)).append("'").append(",");
            }
            sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            sb.append("''");
        }

        return sb;
    }

    public void cargarModeloCompetenciaPorEvaluacion(String id) {
        try {
            listaNombresCompetenciasPorEvaluacion = new ArrayList<>();
            listaColoresCompetenciasPorEvaluacion = new ArrayList<>();
            Iterator it = listaModeloCompetenciaTotalEvaluaciones.iterator();

            while (it.hasNext()) {
                ModeloCompetencia objCompetencia = new ModeloCompetencia();
                Object obj[] = (Object[]) it.next();

                if (id.equals(obj[0].toString())) {
                    objCompetencia.setColor(obj[2].toString());
                    listaNombresCompetenciasPorEvaluacion.add(obj[1].toString());
                    listaColoresCompetenciasPorEvaluacion.add(objCompetencia);
                }
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void cargarRespaldo() {
        try {
            listaDeEvaluaciones = new ArrayList<>();
            listaDeEvaluaciones.addAll(listaDeEvaluacionesRespaldo);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void muestraAjuste(List<ModeloAjustesCalcBean> listaAjustesPorEvaluacion1) {
        this.listaAjustesPorEvaluacion1 = listaAjustesPorEvaluacion1;

    }

    public void cargarAjustePorEvaluacion() {
        try {

            mhAjustes = new LinkedHashMap();

            listaAjustesPorEvaluacion1 = new ArrayList<>();

            Iterator it = listaAjustesTotalEvaluaciones.iterator();

            ModeloAjustesListaBean objModeloAjustesListaBean = new ModeloAjustesListaBean();
            ModeloAjustesCalcBean objAjustesCalcBean = new ModeloAjustesCalcBean();

            String Evaluacion = "", EvaluacionAnt = "";
            String Tipo = "", TipoAnt = "";
            String Concepto = "";
            String Dato = "";

            while (it.hasNext()) {

                Object obj[] = (Object[]) it.next();

                Evaluacion = obj[0].toString();
                Tipo = obj[2].toString();
                Concepto = obj[1].toString();
                Dato = obj[3].toString();

                if (Evaluacion.equals(EvaluacionAnt) && !EvaluacionAnt.equals("")) {

                    if (Tipo.equals(TipoAnt)) {

                        int a = objModeloAjustesListaBean.getLstModeloAjustesCalcBeans().indexOf(objAjustesCalcBean);
                        objAjustesCalcBean = objModeloAjustesListaBean.getLstModeloAjustesCalcBeans().get(a);
                        objAjustesCalcBean.getLstAjusteEvaluacionBean().add(new AjusteEvaluacionBean(Concepto, Dato));

                    } else {
                        objAjustesCalcBean = new ModeloAjustesCalcBean();
                        objAjustesCalcBean.setTipo(Tipo);
                        objAjustesCalcBean.setLstAjusteEvaluacionBean(new ArrayList<>());
                        objAjustesCalcBean.getLstAjusteEvaluacionBean().add(new AjusteEvaluacionBean(Concepto, Dato));
                        objModeloAjustesListaBean.getLstModeloAjustesCalcBeans().add(objAjustesCalcBean);
                    }

                    EvaluacionAnt = Evaluacion;
                    TipoAnt = Tipo;

                } else {

                    if (!Evaluacion.equals("")) {
                        mhAjustes.put(EvaluacionAnt, objModeloAjustesListaBean);
                    }

                    objModeloAjustesListaBean = new ModeloAjustesListaBean();

                    objAjustesCalcBean = new ModeloAjustesCalcBean();
                    objAjustesCalcBean.setTipo(Tipo);
                    objAjustesCalcBean.setLstAjusteEvaluacionBean(new ArrayList<>());
                    objAjustesCalcBean.getLstAjusteEvaluacionBean().add(new AjusteEvaluacionBean(Concepto, Dato));

                    objModeloAjustesListaBean.setLstModeloAjustesCalcBeans(new ArrayList<>());
                    objModeloAjustesListaBean.getLstModeloAjustesCalcBeans().add(objAjustesCalcBean);

                    EvaluacionAnt = Evaluacion;
                }
            }
            mhAjustes.put(EvaluacionAnt, objModeloAjustesListaBean);

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void poblarEmpresas() {
        try {
            lstEmpresas = new ArrayList<>();
            EmpresaDAO objEmpresaDAO = new EmpresaDAO();
            List<Empresa> lstEmpresa = objEmpresaDAO.obtenerEmpresasOrdenNombre();
            lstEmpresa.stream().map((objEmpresa) -> {
                EmpresaBean objEmpresaBean = new EmpresaBean();
                objEmpresaBean.setId(objEmpresa.getId().toString());
                objEmpresaBean.setDesc(objEmpresa.getNombre());
                return objEmpresaBean;
            }).forEachOrdered((objEmpresaBean) -> {
                lstEmpresas.add(objEmpresaBean);
            });
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void seleccionaEmpresa() {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada) && !strEmpresaSeleccionada.equals("-1")) {
                EmpresaDAO objEmpresaDAO = new EmpresaDAO();
                Empresa objEmpresa = objEmpresaDAO.obtenerEmpresa(strEmpresaSeleccionada);
                poblarArea();
                mostrarAlerta(INFO, "proceso.seleccion.empresa", null, null, objEmpresa.getNombre());
            } else {
                strAreaSeleccionada = null;
                strPerfilSeleccionado = null;
                lstArea = new ArrayList<>();
                lstPerfil = new ArrayList<>();
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void poblarArea() {
        try {
            if (Utilitarios.noEsNuloOVacio(strEmpresaSeleccionada) && !strEmpresaSeleccionada.equals("-1")) {
                lstArea = new ArrayList<>();
                AreaDAO objAreaDAO = new AreaDAO();
                List<Area> lstAreas = objAreaDAO.obtenerAreasXEmpresa(strEmpresaSeleccionada);
                for (Area objArea : lstAreas) {
                    AreaBean objAreaBean = new AreaBean();
                    objAreaBean.setId(objArea.getId().toString());
                    objAreaBean.setDescripcion(objArea.getDescripcion());
                    lstArea.add(objAreaBean);
                };
            } else {
                strAreaSeleccionada = null;
                strPerfilSeleccionado = null;
                lstArea = new ArrayList<>();
                lstPerfil = new ArrayList<>();
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void seleccionaArea() {
        try {
            if (Utilitarios.noEsNuloOVacio(strAreaSeleccionada) && !strAreaSeleccionada.equals("-1")) {
                AreaDAO objAreaDAO = new AreaDAO();
                Area objArea = objAreaDAO.obtenerArea(strAreaSeleccionada);
                mostrarAlerta(INFO, "proceso.seleccion.area", null, null, objArea.getDescripcion());
                poblarPerfil();
            } else {
                strPerfilSeleccionado = null;
                lstPerfil = new ArrayList<>();
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void poblarPerfil() {
        try {
            if (Utilitarios.noEsNuloOVacio(strAreaSeleccionada) && !strAreaSeleccionada.equals("-1")) {
                lstPerfil = new ArrayList<>();
                PerfilDAO objPerfilDAO = new PerfilDAO();
                List<Perfil> lstPerfiles = objPerfilDAO.obtenerPerfilesXArea(strAreaSeleccionada);
                for (Perfil objPerfil : lstPerfiles) {
                    PerfilBean objPerfilBean = new PerfilBean();
                    objPerfilBean.setId(objPerfil.getId().toString());
                    objPerfilBean.setDescripcion(objPerfil.getNombre());
                    lstPerfil.add(objPerfilBean);
                };
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void seleccionaPerfil() {
        try {
            if (Utilitarios.noEsNuloOVacio(strPerfilSeleccionado) && !strPerfilSeleccionado.equals("-1")) {
                PerfilDAO objPerfilDao = new PerfilDAO();
                Perfil objPerfil = objPerfilDao.obtenerPerfil(strPerfilSeleccionado);
                mostrarAlerta(INFO, "proceso.seleccion.perfil", null, null, objPerfil.getNombre());
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void cargarBilleteraDeBaterias(DragDropEvent ddEvent) {
        try {
            BateriaBean objBateriaBean = ((BateriaBean) ddEvent.getData());
            pasarBateria(objBateriaBean);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void cargarCompetencias() {
        try {
            ModeloCompetenciaDAO objMCDao = new ModeloCompetenciaDAO();
            List lstCompetencias = objMCDao.obtenerCompetenciasDeDroppBaterias(obtenerIdEvaluacionesSeleccionadas());
            Iterator it = lstCompetencias.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                String idComp = obj[1].toString();
                String nombre = obj[0].toString();
                String color = obj[2].toString();

                if (!mapCompetenciasSeleccionadas.containsKey(idComp)) {
                    mapCompetenciasSeleccionadas.put(idComp, nombre);
                    List listaN = new ArrayList();
                    ModeloCompetenciaBean objCompetencia = new ModeloCompetenciaBean();
                    objCompetencia.setId(idComp);
                    objCompetencia.setNombre(nombre);
                    objCompetencia.setColor(color);

                    if (!mapEvaComp.isEmpty()) {
                        ModeloEvaluacionListCalBean objMap = mapEvaComp.get(objCompetencia.getId());
                        for (ModeloEvaluacionCalBean objB : objMap.getListModeloEvaluacionCalBean()) {
                            String idEvaA = "";
                            for (Object o : lstCompetencias) {
                                Object e[] = (Object[]) o;
                                if (!idEvaA.equals(e[3].toString())) {
                                    if (mapEvaComp.containsKey(objCompetencia.getId())
                                            && e[3].toString().equals(objB.getId())) {
                                        listaN.add(objB.getNombre());
                                        idEvaA = e[3].toString();
                                    }
                                }
                            }
                        }
                    }

                    objCompetencia.setListaNombreEvaluaciones(listaN);
                    droppedCompetencias.add(objCompetencia);
                    Collections.sort(droppedCompetencias);
                }

            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void pasarBateria(BateriaBean objBateriaBean) {
        try {
            droppedBaterias.add(objBateriaBean);
            listaDeEvaluaciones.remove(objBateriaBean);
            contarMinutos();
            droppedCompetencias = new ArrayList<>();
            mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
            cargarCompetencias();
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void quitarBateria(BateriaBean objBateria) {
        try {
            listaDeEvaluaciones.add(objBateria);
            droppedBaterias.remove(objBateria);
            contarMinutos();
            droppedCompetencias = new ArrayList<>();
            mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
            cargarCompetencias();
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void contarMinutos() {
        minutosTotal = 0;
        for (BateriaBean objBatBean : droppedBaterias) {
            int mEstimados = Integer.parseInt(objBatBean.getMinutosEstimados());
            minutosTotal += mEstimados;
        }
    }

    public void cargarListaCompetenciasTotal() {
        try {
            listaCompetenciasTotal = new ArrayList<>();
            ModeloEvaluacionDAO objM = new ModeloEvaluacionDAO();
            List listStringC = objM.traerNombresDeCompetencias();
            Iterator it = listStringC.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                ModeloCompetenciaBean objModelo = new ModeloCompetenciaBean();
                objModelo.setId(obj[0].toString());
                objModelo.setNombre(obj[1].toString());
                listaCompetenciasTotal.add(objModelo);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void iterarEvaluacion(List listaEvaluacionesBD) {
        try {
            Iterator it = listaEvaluacionesBD.iterator();
            listaDeEvaluaciones = new ArrayList<>();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                BateriaBean objBateriaBean = new BateriaBean();
                objBateriaBean.setId(obj[0].toString());
                objBateriaBean.setNombre(obj[1].toString());
                objBateriaBean.setMinutosEstimados(obj[2].toString());
                objBateriaBean.setValidez(obj[3].toString());
                objBateriaBean.setConfiabilidad(obj[4].toString());
                objBateriaBean.setLimiteTiempo(obj[7].toString());

                cargarModeloCompetenciaPorEvaluacion(obj[0].toString());
                objBateriaBean.setLstCompetencias(listaNombresCompetenciasPorEvaluacion);
                objBateriaBean.setLstColores(listaColoresCompetenciasPorEvaluacion);

                cargarAjustePorEvaluacion();
                if (mhAjustes.containsKey(objBateriaBean.getId())) {
                    objBateriaBean.setLstAjustes(mhAjustes.get(objBateriaBean.getId()).getLstModeloAjustesCalcBeans());
                }

                listaDeEvaluaciones.add(objBateriaBean);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void grabarBateria() {
        try {
            ModeloEvaluacionDAO objEvaluacionDAO = new ModeloEvaluacionDAO();
            String id = (String) Utilitarios.obtenerSession(Constantes.SESSION_ID_BATERIA);
            if (Utilitarios.noEsNuloOVacio(droppedBaterias)) {
                if (Utilitarios.noEsNuloOVacio(droppedCompetencias)) {
                    BateriaPersonalizada objBateriaPersonalizada = new BateriaPersonalizada();
                    if (Utilitarios.noEsNuloOVacio(id)) {
                        objBateriaPersonalizada.setId(Integer.parseInt(id));
                    }
                    objBateriaPersonalizada.setNombre(strNombreProceso);
                    objBateriaPersonalizada.setFechaCreacion(new Date());
                    objBateriaPersonalizada.setEstado(Constantes.INT_ESTADO_PROCESO_REGISTRADO);
                    objBateriaPersonalizada.setResena(strNombreProceso);
                    objBateriaPersonalizada.setHorasEstimadasTotal(Constantes.Int_cinco);
                    objBateriaPersonalizada.setMinutosEstimadosTotal(minutosTotal);
                    objEvaluacionDAO.grabarBateriaPersonalizada(objBateriaPersonalizada, droppedBaterias, strPerfilSeleccionado);
                    if (Utilitarios.noEsNuloOVacio(id)) {
                        Utilitarios.ponerSession(null, Constantes.SESSION_ID_BATERIA);
                        lockEmpresa = false;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("crearProceso.jsf");
                    }
                    limpiar();
                    mostrarAlerta(INFO, "bateria.bateriaGuardada", null, null);
                } else {
                    mostrarAlerta(ERROR, "bateria.noSeleccionoCompetencias", null, null);
                }
            } else {
                mostrarAlerta(ERROR, "bateria.noSeleccionoEvaluaciones", null, null);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public List<BateriaBean> getListaDeEvaluaciones() {
        return listaDeEvaluaciones;
    }

    public void setListaDeEvaluaciones(List<BateriaBean> listaDeEvaluaciones) {
        this.listaDeEvaluaciones = listaDeEvaluaciones;
    }

    public List<BateriaBean> getDroppedBaterias() {
        return droppedBaterias;
    }

    public void setDroppedBaterias(List<BateriaBean> droppedBaterias) {
        this.droppedBaterias = droppedBaterias;
    }

    public List<ModeloCompetenciaBean> getDroppedCompetencias() {
        return droppedCompetencias;
    }

    public void setDroppedCompetencias(List<ModeloCompetenciaBean> droppedCompetencias) {
        this.droppedCompetencias = droppedCompetencias;
    }

    public String getStrNombreProceso() {
        return strNombreProceso;
    }

    public void setStrNombreProceso(String strNombreProceso) {
        this.strNombreProceso = strNombreProceso;
    }

    public String getStrEmpresaSeleccionada() {
        return strEmpresaSeleccionada;
    }

    public void setStrEmpresaSeleccionada(String strEmpresaSeleccionada) {
        this.strEmpresaSeleccionada = strEmpresaSeleccionada;
    }

    public String getStrPerfilSeleccionado() {
        return strPerfilSeleccionado;
    }

    public void setStrPerfilSeleccionado(String strPerfilSeleccionado) {
        this.strPerfilSeleccionado = strPerfilSeleccionado;
    }

    public String getStrAreaSeleccionada() {
        return strAreaSeleccionada;
    }

    public void setStrAreaSeleccionada(String strAreaSeleccionada) {
        this.strAreaSeleccionada = strAreaSeleccionada;
    }

    public List<EmpresaBean> getLstEmpresas() {
        return lstEmpresas;
    }

    public void setLstEmpresas(List<EmpresaBean> lstEmpresas) {
        this.lstEmpresas = lstEmpresas;
    }

    public List<PerfilBean> getLstPerfil() {
        return lstPerfil;
    }

    public void setLstPerfil(List<PerfilBean> lstPerfil) {
        this.lstPerfil = lstPerfil;
    }

    public List<AreaBean> getLstArea() {
        return lstArea;
    }

    public void setLstArea(List<AreaBean> lstArea) {
        this.lstArea = lstArea;
    }

    public int getMinutosTotal() {
        return minutosTotal;
    }

    public void setMinutosTotal(int minutosTotal) {
        this.minutosTotal = minutosTotal;
    }

    public List<ModeloCompetenciaBean> getListaCompetenciasTotal() {
        return listaCompetenciasTotal;
    }

    public void setListaCompetenciasTotal(List<ModeloCompetenciaBean> listaCompetenciasTotal) {
        this.listaCompetenciasTotal = listaCompetenciasTotal;
    }

    public String getStrCompSeleccionada() {
        return strCompSeleccionada;
    }

    public void setStrCompSeleccionada(String strCompSeleccionada) {
        this.strCompSeleccionada = strCompSeleccionada;
    }

    public List<BateriaBean> getListaDeEvaluacionesRespaldo() {
        return listaDeEvaluacionesRespaldo;
    }

    public void setListaDeEvaluacionesRespaldo(List<BateriaBean> listaDeEvaluacionesRespaldo) {
        this.listaDeEvaluacionesRespaldo = listaDeEvaluacionesRespaldo;
    }

    public List<ModeloAjustesCalcBean> getListaAjustesPorEvaluacion() {
        return listaAjustesPorEvaluacion;
    }

    public void setListaAjustesPorEvaluacion(List<ModeloAjustesCalcBean> listaAjustesPorEvaluacion) {
        this.listaAjustesPorEvaluacion = listaAjustesPorEvaluacion;
    }

    public List<ModeloAjustesCalcBean> getListaAjustesPorEvaluacion1() {
        return listaAjustesPorEvaluacion1;
    }

    public void setListaAjustesPorEvaluacion1(List<ModeloAjustesCalcBean> listaAjustesPorEvaluacion1) {
        this.listaAjustesPorEvaluacion1 = listaAjustesPorEvaluacion1;
    }

    public boolean isLockEmpresa() {
        return lockEmpresa;
    }

    public void setLockEmpresa(boolean lockEmpresa) {
        this.lockEmpresa = lockEmpresa;
    }

    public List getListaEvaluacionesPorCompetencia() {
        return listaEvaluacionesPorCompetencia;
    }

    public void setListaEvaluacionesPorCompetencia(List listaEvaluacionesPorCompetencia) {
        this.listaEvaluacionesPorCompetencia = listaEvaluacionesPorCompetencia;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

}
