package jaio.selection.view;

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
import jaio.selection.bean.CompetenciaBean;
import jaio.selection.bean.ConvertirDatosBean;
import jaio.selection.bean.EmpresaBean;
import jaio.selection.bean.ModeloCompetenciaBean;
import jaio.selection.bean.PerfilBean;
import jaio.selection.dao.AreaDAO;
import jaio.selection.dao.EmpresaDAO;
import jaio.selection.dao.ModeloCompetenciaDAO;
import jaio.selection.util.Constantes;
import jaio.selection.dao.ModeloEvaluacionDAO;
import jaio.selection.dao.PerfilDAO;
import jaio.selection.orm.Area;
import jaio.selection.orm.BateriaEvaluacion;
import jaio.selection.orm.BateriaEvaluacionId;
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

    private LinkedHashMap<String, String> mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
    ConvertirDatosBean convertirDatos = new ConvertirDatosBean();
    private List<BateriaBean> lstBaterias;
    private List<BateriaBean> droppedBaterias;
    private List<ModeloCompetencia> listaCompetenciasPorModelo;
    private List<ModeloCompetenciaBean> droppedCompetencias;
    public List<EmpresaBean> lstEmpresas;
    public List<AreaBean> lstArea;
    public List<PerfilBean> lstPerfil;
    int minutosTotal = 0;
    String nombreEvaluacion;
    String strEmpresaSeleccionada;
    String strAreaSeleccionada;
    String strPerfilSeleccionado;
    String empresa;

    @PostConstruct
    public void init() {
        listaCompetenciasPorModelo = new ArrayList<>();
        mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
        lstBaterias = new ArrayList<>();
        droppedBaterias = new ArrayList<>();
        droppedCompetencias = new ArrayList<>();
        lstEmpresas = new ArrayList<>();
        lstArea = new ArrayList<>();
        lstPerfil = new ArrayList<>();
        nombreEvaluacion = null;
        poblarEmpresas();
        obtenerModelosDeEvaluaciones();
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
        listaCompetenciasPorModelo = new ArrayList<>();
        mapCompetenciasSeleccionadas = new LinkedHashMap<String, String>();
        lstBaterias = new ArrayList<>();
        droppedBaterias = new ArrayList<>();
        droppedCompetencias = new ArrayList<>();
        lstEmpresas = new ArrayList<>();
        lstArea = new ArrayList<>();
        lstPerfil = new ArrayList<>();
        nombreEvaluacion = null;
        strEmpresaSeleccionada = null;
        strAreaSeleccionada = null;
        strPerfilSeleccionado = null;
        poblarEmpresas();
        obtenerModelosDeEvaluaciones();
    }

    public List obtenerModelosDeEvaluaciones() {
        try {
            ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
            List<ModeloEvaluacion> listaEvaluacionesBD = objModelosDao.obtenerModelos();
            for (ModeloEvaluacion objModeloEvaluacion : listaEvaluacionesBD) {
                BateriaBean objBateriaBean = new BateriaBean();
                String minutosEstimados = convertirDatos.convertirObjetAString(objModeloEvaluacion.getMinutosEstimados(), Constantes.Tipo_dato_int);
                String limiteTiempo = convertirDatos.convertirObjetAString(objModeloEvaluacion.getLimiteTiempo(), Constantes.Tipo_dato_byte);
                objBateriaBean.setId(objModeloEvaluacion.getId().toString());
                objBateriaBean.setNombre(objModeloEvaluacion.getNombre());
                objBateriaBean.setValidez(objModeloEvaluacion.getValidez().toString());
                objBateriaBean.setConfiabilidad(objModeloEvaluacion.getConfiabilidad().toString());
                objBateriaBean.setMinutosEstimados(minutosEstimados);
                objBateriaBean.setLimiteTiempo(limiteTiempo);
                objBateriaBean.setLstCompetencias(obtenerCompetenciasPorEvaluacion(objBateriaBean));
                lstBaterias.add(objBateriaBean);
            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return lstBaterias;
    }

    public List<ModeloCompetencia> obtenerCompetenciasPorEvaluacion(BateriaBean objBateriaBean) {
        List<ModeloCompetencia> listaCompetencias = new ArrayList<>();
        ModeloCompetenciaDAO objMCDao = new ModeloCompetenciaDAO();
        try {
            listaCompetencias = objMCDao.obtenerCompetenciasXEvaluacion(objBateriaBean.getId());

            if (Utilitarios.noEsNuloOVacio(listaCompetencias)) {
                Iterator it = listaCompetencias.iterator();
                while (it.hasNext()) {
                    ModeloCompetencia objModeloCompetencia = new ModeloCompetencia();
                    Object obj[] = (Object[]) it.next();
                    objModeloCompetencia.setNombre(obj[0].toString());
                    listaCompetenciasPorModelo.add(objModeloCompetencia);
                }
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return listaCompetencias;
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
                if (!strAreaSeleccionada.equals("-1")) {
                    AreaDAO objAreaDAO = new AreaDAO();
                    Area objArea = objAreaDAO.obtenerArea(strAreaSeleccionada);
                    mostrarAlerta(INFO, "proceso.seleccion.area", null, null, objArea.getDescripcion());
                    poblarPerfil();
                }
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

            minutosTotal = 0;
            for (BateriaBean objBatBean : droppedBaterias) {
                int mEstimados = Integer.parseInt(objBatBean.getMinutosEstimados());
                minutosTotal += mEstimados;
            }
            ModeloCompetenciaDAO objMCDao = new ModeloCompetenciaDAO();
            List lstCompetencias = objMCDao.obtenerCompetenciasXEvaluacion(objBateriaBean.getId());
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

            lstBaterias.remove(objBateriaBean);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void quitarBaterias(BateriaBean objBateriaBean) {
        try {
            lstBaterias.add(objBateriaBean);
            droppedBaterias.remove(objBateriaBean);
            ModeloCompetenciaDAO objMCDao = new ModeloCompetenciaDAO();
            List lstCompetencias = objMCDao.obtenerCompetenciasXEvaluacion(objBateriaBean.getId());
            Iterator it = lstCompetencias.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                ModeloCompetenciaBean objModeloCompetencia = new ModeloCompetenciaBean();
                objModeloCompetencia.setNombre(obj[0].toString());
                objModeloCompetencia.setId(obj[1].toString());
                objModeloCompetencia.setColor(obj[2].toString());
                if (mapCompetenciasSeleccionadas.containsKey(objModeloCompetencia.getId())) {
                    mapCompetenciasSeleccionadas.remove(objModeloCompetencia.getId(), objModeloCompetencia.getNombre());
                    ModeloCompetenciaBean objCompetencia = new ModeloCompetenciaBean();
                    objCompetencia.setNombre(objModeloCompetencia.getNombre());
                    objCompetencia.setColor(objModeloCompetencia.getColor());
                    droppedCompetencias.remove(objCompetencia);
                }
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

    public List<BateriaBean> getLstBaterias() {
        return lstBaterias;
    }

    public void setLstBaterias(List<BateriaBean> lstBaterias) {
        this.lstBaterias = lstBaterias;
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

}
