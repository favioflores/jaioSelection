package jaio.selection.view;

import jaio.selection.bean.AjusteEvaluacionBean;
import jaio.selection.bean.AreaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.bean.BateriaBean;
import jaio.selection.bean.ConvertirDatosBean;
import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.ModeloAjustesCalcBean;
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
import static jaio.selection.view.BaseView.ERROR;
import static jaio.selection.view.BaseView.FATAL;
import static jaio.selection.view.BaseView.INFO;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "crearBateriaView")
@ViewScoped
public class CrearBateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(CrearBateriaView.class);
    private static final long serialVersionUID = -1L;

    private List<BateriaBean> listaDeEvaluaciones;
    private List<BateriaBean> listaDeEvaluacionesRespaldo;

    private List<ModeloCompetenciaBean> lstCompetencia = new ArrayList<>();
    private String strCompSeleccionada;
    private LinkedHashMap<String, String> mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
    private List<BateriaBean> droppedBaterias;
    private List<ModeloCompetenciaBean> droppedCompetencias;
    private List<EmpresaBean> lstEmpresas;
    private List<AreaBean> lstArea;
    private List<PerfilBean> lstPerfil;
    private List<ModeloCompetencia> lstColors;
    private int minutosTotal = 0;
    private String nombreEvaluacion;
    private String strEmpresaSeleccionada;
    private String strAreaSeleccionada;
    private String strPerfilSeleccionado;
    private String empresa;
    private String competencia;

    private List<ModeloAjustesCalcBean> lstAjustesCalcBean;

    public List<ModeloAjustesCalcBean> getLstAjustesCalcBean() {
        return lstAjustesCalcBean;
    }

    public void setLstAjustesCalcBean(List<ModeloAjustesCalcBean> lstAjustesCalcBean) {
        this.lstAjustesCalcBean = lstAjustesCalcBean;
    }

    @PostConstruct
    public void init() {

        listaDeEvaluaciones = new ArrayList<>();
        listaDeEvaluacionesRespaldo = new ArrayList<>();
        cargarEvaluaciones();

        mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();

        droppedBaterias = new ArrayList<>();
        droppedCompetencias = new ArrayList<>();
        lstEmpresas = new ArrayList<>();
        lstArea = new ArrayList<>();
        lstPerfil = new ArrayList<>();
        lstColors = new ArrayList<>();
        lstCompetencia = new ArrayList<>();
        minutosTotal = 0;
        nombreEvaluacion = null;
        competencia = null;
        strCompSeleccionada = null;
        poblarEmpresas();

        cargarNombreDeCompetencias();
        if (Utilitarios.noEsNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_BATERIA))) {
            strEmpresaSeleccionada = (String) Utilitarios.obtenerSession(Constantes.SESSION_BATERIA);
            poblarArea();
        } else {
            strEmpresaSeleccionada = null;
            strAreaSeleccionada = null;
            strPerfilSeleccionado = null;
        }
    }

    public void limpiar() {

        cargarRespaldo();

        mapCompetenciasSeleccionadas.clear();
        droppedBaterias = new ArrayList<>();
        droppedCompetencias = new ArrayList<>();
        lstEmpresas = new ArrayList<>();
        lstArea = new ArrayList<>();
        lstPerfil = new ArrayList<>();
        lstColors = new ArrayList<>();
        lstCompetencia = new ArrayList<>();
        minutosTotal = 0;
        nombreEvaluacion = null;
        strEmpresaSeleccionada = null;
        strAreaSeleccionada = null;
        strPerfilSeleccionado = null;
        competencia = null;
        strCompSeleccionada = null;
        poblarEmpresas();
        cargarNombreDeCompetencias();
    }

    public List cargarEvaluaciones() {
        try {
            ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
            List<BateriaBean> lstEvaluaciones = new ArrayList<>();
            for (ModeloEvaluacion objModeloEvaluacion : objModelosDao.obtenerModelos()) {
                BateriaBean objBateriaBean = new BateriaBean();
                ConvertirDatosBean convertirDatos = new ConvertirDatosBean();
                objBateriaBean.setId(objModeloEvaluacion.getId().toString());
                objBateriaBean.setNombre(objModeloEvaluacion.getNombre());
                objBateriaBean.setValidez(objModeloEvaluacion.getValidez().toString());
                objBateriaBean.setConfiabilidad(objModeloEvaluacion.getConfiabilidad().toString());
                objBateriaBean.setMinutosEstimados(objModeloEvaluacion.getMinutosEstimados().toString());
                objBateriaBean.setLimiteTiempo(convertirDatos.convertirObjetAString(objModeloEvaluacion.getLimiteTiempo(), Constantes.Tipo_dato_byte));
                objBateriaBean.setLstCompetencias(obtenerCompetenciasPorEvaluacion(objBateriaBean));
                objBateriaBean.setLstAjustes(obtenerAjustesPorEvaluacion(objBateriaBean));
                objBateriaBean.setLstColores(lstColors);
                lstEvaluaciones.add(objBateriaBean);
            }
            listaDeEvaluacionesRespaldo.addAll(lstEvaluaciones);
            listaDeEvaluaciones.addAll(lstEvaluaciones);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return listaDeEvaluaciones;
    }

    public void cargarRespaldo() {
        try {
            listaDeEvaluaciones = new ArrayList<>();
            listaDeEvaluaciones.addAll(listaDeEvaluacionesRespaldo);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public List obtenerCompetenciasPorEvaluacion(BateriaBean objBateriaBean) {
        List contenedorCompetencias = new ArrayList<>();
        lstColors = new ArrayList<>();
        try {
            ModeloCompetenciaDAO objMCDao = new ModeloCompetenciaDAO();
            List listaCompetencias = objMCDao.obtenerCompetenciasXEvaluacion(objBateriaBean.getId());
            if (Utilitarios.noEsNuloOVacio(listaCompetencias)) {
                Iterator it = listaCompetencias.iterator();
                while (it.hasNext()) {
                    ModeloCompetencia objMC = new ModeloCompetencia();
                    Object obj[] = (Object[]) it.next();
                    contenedorCompetencias.add(obj[0].toString());
                    objMC.setColor(obj[2].toString());
                    lstColors.add(objMC);
                }
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return contenedorCompetencias;
    }

    public List obtenerAjustesPorEvaluacion(BateriaBean objBateriaBean) {
        List<ModeloAjustesCalcBean> listaDeAjustes = new ArrayList<>();
        try {
            ModeloEvaluacionDAO objMCDao = new ModeloEvaluacionDAO();
            List lstAjustes = objMCDao.traerAjustesXEvaluacion(objBateriaBean.getId());
            if (Utilitarios.noEsNuloOVacio(lstAjustes)) {
                Iterator it = lstAjustes.iterator();

                String tipo = "", tipoAnt = "";

                ModeloAjustesCalcBean objAjustesCalcBean = new ModeloAjustesCalcBean();

                while (it.hasNext()) {
                    Object obj[] = (Object[]) it.next();

                    tipo = obj[2].toString();

                    if (tipo.equals(tipoAnt)) {

                        AjusteEvaluacionBean objAjusteEvaluacionBean = new AjusteEvaluacionBean();

                        objAjusteEvaluacionBean.setConcepto(obj[1].toString());
                        objAjusteEvaluacionBean.setDato(obj[3].toString());

                        objAjustesCalcBean.getLstAjusteEvaluacionBean().add(objAjusteEvaluacionBean);

                        tipoAnt = tipo;

                    } else {

                        if (!tipoAnt.equals("")) {
                            listaDeAjustes.add(objAjustesCalcBean);
                        }

                        objAjustesCalcBean = new ModeloAjustesCalcBean();

                        objAjustesCalcBean.setTipo(obj[2].toString());
                        objAjustesCalcBean.setLstAjusteEvaluacionBean(new ArrayList<>());

                        AjusteEvaluacionBean objAjusteEvaluacionBean = new AjusteEvaluacionBean();

                        objAjusteEvaluacionBean.setConcepto(obj[1].toString());
                        objAjusteEvaluacionBean.setDato(obj[3].toString());

                        objAjustesCalcBean.getLstAjusteEvaluacionBean().add(objAjusteEvaluacionBean);

                        tipoAnt = tipo;

                    }

                }

                if (!lstAjustes.isEmpty()) {
                    listaDeAjustes.add(objAjustesCalcBean);
                }
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return listaDeAjustes;
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
                strEmpresaSeleccionada = null;
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
                mostrarAlerta(ERROR, "error.inesperado", null, null);
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
            if (Utilitarios.noEsNuloOVacio(strPerfilSeleccionado)) {
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
            pasarBaterias(objBateriaBean);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void pasarBaterias(BateriaBean objBateriaBean) {
        try {
            droppedBaterias.add(objBateriaBean);
            contarMinutos();
            cargarCompetencias(objBateriaBean.getId());
            listaDeEvaluaciones.remove(objBateriaBean);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void cargarCompetencias(String id) {
        try {
            ModeloCompetenciaDAO objMCDao = new ModeloCompetenciaDAO();
            List lstCompetencias = objMCDao.obtenerCompetenciasXEvaluacion(id);
            Iterator it = lstCompetencias.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                ModeloCompetenciaBean objModeloCompetencia = new ModeloCompetenciaBean();
                objModeloCompetencia.setNombre(obj[0].toString());
                objModeloCompetencia.setId(obj[1].toString());
                objModeloCompetencia.setColor(obj[2].toString());
                if (!mapCompetenciasSeleccionadas.containsKey(objModeloCompetencia.getId())) {
                    mapCompetenciasSeleccionadas.put(objModeloCompetencia.getId(), objModeloCompetencia.getNombre());
                    ModeloCompetenciaBean objCompetencia = new ModeloCompetenciaBean();
                    objCompetencia.setId(objModeloCompetencia.getId());
                    objCompetencia.setNombre(objModeloCompetencia.getNombre());
                    objCompetencia.setColor(objModeloCompetencia.getColor());
                    droppedCompetencias.add(objCompetencia);
                    Collections.sort(droppedCompetencias);
                }
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void quitarBaterias(BateriaBean objBateria) {
        try {
            listaDeEvaluaciones.add(objBateria);
            droppedBaterias.remove(objBateria);
            droppedCompetencias = new ArrayList<>();
            mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
            for (BateriaBean objBateriaBean1 : droppedBaterias) {
                cargarCompetencias(objBateriaBean1.getId());
            }
            contarMinutos();
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

    public void cargarNombreDeCompetencias() {
        try {
            ModeloEvaluacionDAO objM = new ModeloEvaluacionDAO();
            List listStringC = objM.traerNombresDeCompetencias();
            Iterator it = listStringC.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                ModeloCompetenciaBean objModelo = new ModeloCompetenciaBean();
                objModelo.setId(obj[0].toString());
                objModelo.setNombre(obj[1].toString());
                lstCompetencia.add(objModelo);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public List buscarEvaluacionXCompetencia() {
        try {
            ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
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
            if (Utilitarios.noEsNuloOVacio(strCompSeleccionada) && !strCompSeleccionada.equals("-1")) {
                List listaEvaluacionesBD = objModelosDao.obtenerModelosXCompetencia(strCompSeleccionada, sb);
                iterarEvaluacion(listaEvaluacionesBD);
            } else {
                List listaEvaluacionesBD = objModelosDao.obtenerModelosCompetencia(sb);
                iterarEvaluacion(listaEvaluacionesBD);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return listaDeEvaluaciones;
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
                objBateriaBean.setLstCompetencias(obtenerCompetenciasPorEvaluacion(objBateriaBean));
                objBateriaBean.setLstColores(lstColors);
                listaDeEvaluaciones.add(objBateriaBean);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void grabarBateria() {
        try {
            ModeloEvaluacionDAO objEvaluacionDAO = new ModeloEvaluacionDAO();
            if (Utilitarios.noEsNuloOVacio(droppedBaterias)) {
                if (Utilitarios.noEsNuloOVacio(droppedCompetencias)) {
                    BateriaPersonalizada objBateriaPersonalizada = new BateriaPersonalizada();
                    objBateriaPersonalizada.setNombre(nombreEvaluacion);
                    objBateriaPersonalizada.setFechaCreacion(new Date());
                    objBateriaPersonalizada.setEstado(Constantes.INT_ESTADO_PROCESO_REGISTRADO);
                    objBateriaPersonalizada.setResena(nombreEvaluacion);
                    objBateriaPersonalizada.setHorasEstimadasTotal(Constantes.Int_cinco);
                    objBateriaPersonalizada.setMinutosEstimadosTotal(minutosTotal);
                    objEvaluacionDAO.grabarBateriaPersonalizada(objBateriaPersonalizada, droppedBaterias, strPerfilSeleccionado);
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

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getMinutosTotal() {
        return minutosTotal;
    }

    public void setMinutosTotal(int minutosTotal) {
        this.minutosTotal = minutosTotal;
    }

    public List<ModeloCompetencia> getLstColors() {
        return lstColors;
    }

    public void setLstColors(List<ModeloCompetencia> lstColors) {
        this.lstColors = lstColors;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public List<ModeloCompetenciaBean> getLstCompetencia() {
        return lstCompetencia;
    }

    public void setLstCompetencia(List<ModeloCompetenciaBean> lstCompetencia) {
        this.lstCompetencia = lstCompetencia;
    }

    public String getStrCompSeleccionada() {
        return strCompSeleccionada;
    }

    public void setStrCompSeleccionada(String strCompSeleccionada) {
        this.strCompSeleccionada = strCompSeleccionada;
    }

    public void muestraAjuste(List<ModeloAjustesCalcBean> lstAjustesCalcBean) {

        this.lstAjustesCalcBean = lstAjustesCalcBean;

    }

    public List<BateriaBean> getListaDeEvaluacionesRespaldo() {
        return listaDeEvaluacionesRespaldo;
    }

    public void setListaDeEvaluacionesRespaldo(List<BateriaBean> listaDeEvaluacionesRespaldo) {
        this.listaDeEvaluacionesRespaldo = listaDeEvaluacionesRespaldo;
    }

}
