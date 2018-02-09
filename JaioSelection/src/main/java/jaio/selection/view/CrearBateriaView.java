package jaio.selection.view;

import static com.sun.faces.util.CollectionsUtils.map;
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
import jaio.selection.bean.ModeloCompetenciaBean;
import jaio.selection.dao.ModeloCompetenciaDAO;
import jaio.selection.util.Constantes;
import jaio.selection.dao.ModeloEvaluacionDAO;
import jaio.selection.orm.ModeloCompetencia;
import jaio.selection.orm.ModeloEvaluacion;
import jaio.selection.util.Utilitarios;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "crearBateriaView")
@ViewScoped
public class CrearBateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(CrearBateriaView.class);
    private static final long serialVersionUID = -1L;

    private List<BateriaBean> lstBaterias;
    private List<BateriaBean> droppedBaterias;
    private List<ModeloCompetenciaBean> droppedCompetencias;
    private Map mapCompetenciasSeleccionadas = new HashMap();
    
    String nombreEvaluacion = new String();
    CrearProcesoView processView = new CrearProcesoView();
    
    @PostConstruct
    public void init() {
        limpiar();
        obtenerModelosDeEvaluaciones();
    }

    public void limpiar() {
        processView.limpiar();
        lstBaterias = new ArrayList<>();
        droppedBaterias = new ArrayList<>();
        droppedCompetencias = new ArrayList<>();
        mapCompetenciasSeleccionadas = new HashMap();
        nombreEvaluacion = new String();
        obtenerModelosDeEvaluaciones();
        
    }
    
    public List obtenerModelosDeEvaluaciones() {
        try {
            ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
            ConvertirDatosBean convertirDatos = new ConvertirDatosBean();
            List<ModeloEvaluacion> listaEvaluacionesBD = objModelosDao.obtenerModelos();
            for(ModeloEvaluacion objModeloEvaluacion : listaEvaluacionesBD){
                BateriaBean objBateriaBean = new BateriaBean();
                String id = convertirDatos.convertirObjetAString(objModeloEvaluacion.getId(), Constantes.Tipo_dato_int);
                String validez = convertirDatos.convertirObjetAString(objModeloEvaluacion.getValidez(), Constantes.Tipo_dato_BigDecimal);
                String confiabilidad = convertirDatos.convertirObjetAString(objModeloEvaluacion.getConfiabilidad(), Constantes.Tipo_dato_BigDecimal);
                String minutosEstimados = convertirDatos.convertirObjetAString(objModeloEvaluacion.getMinutosEstimados(), Constantes.Tipo_dato_int);
                String limiteTiempo = convertirDatos.convertirObjetAString(objModeloEvaluacion.getLimiteTiempo(), Constantes.Tipo_dato_byte);
                objBateriaBean.setId(id);
                objBateriaBean.setNombre(objModeloEvaluacion.getNombre());
                objBateriaBean.setValidez(validez);
                objBateriaBean.setConfiabilidad(confiabilidad);
                objBateriaBean.setMinutosEstimados(minutosEstimados);
                objBateriaBean.setLimiteTiempo(limiteTiempo);
                lstBaterias.add(objBateriaBean);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return lstBaterias;
    }
    
    public void cargarBilleteraDeBaterias(DragDropEvent ddEvent) {
        BateriaBean model = ((BateriaBean) ddEvent.getData());
        ModeloCompetenciaDAO objMCDao = new ModeloCompetenciaDAO();
        ConvertirDatosBean convertirDatos = new ConvertirDatosBean();
        droppedBaterias.add(model);
        List lstCompetencias = objMCDao.obtenerCompetenciasXEvaluacion(model.getId());
        Iterator it = lstCompetencias.iterator();
            while (it.hasNext()) {
                Object obj[] = (Object[]) it.next();
                ModeloCompetenciaBean objModeloCompetencia = new ModeloCompetenciaBean();
                objModeloCompetencia.setNombre(obj[0].toString());
                String id = convertirDatos.convertirObjetAString(obj[1], Constantes.Tipo_dato_int);
                objModeloCompetencia.setId(id);
                if (!mapCompetenciasSeleccionadas.containsKey(objModeloCompetencia.getId())){
                    mapCompetenciasSeleccionadas.put(objModeloCompetencia.getId(),objModeloCompetencia.getNombre());
                    ModeloCompetenciaBean objCompetencia = new ModeloCompetenciaBean();
                    objCompetencia.setNombre(objModeloCompetencia.getNombre());
                    droppedCompetencias.add(objCompetencia);
                }   
            }
        lstBaterias.remove(model);
    }
    
    public void vaciarBilleteraDeBaterias(DragDropEvent ddEvent){
        BateriaBean model = ((BateriaBean) ddEvent.getData());
        lstBaterias.add(model);
        droppedBaterias.remove(model);
    }
    
    
    public void grabarBateria(){
        try {
            if (Utilitarios.noEsNuloOVacio(nombreEvaluacion)){
                if (Utilitarios.noEsNuloOVacio(nombreEvaluacion)){
                    
                }else{
                    mostrarAlerta(INFO, "Seleccione Empresa", null, null);    
                }
            }else{
                mostrarAlerta(INFO, "Ingrese Nombre de Evaluación", null, null);
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
   
}
