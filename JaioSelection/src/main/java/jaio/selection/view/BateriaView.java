package jaio.selection.view;

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
import jaio.selection.util.Constantes;
import jaio.selection.dao.ModeloEvaluacionDAO;
import jaio.selection.orm.ModeloEvaluacion;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "bateriaView")
@ViewScoped
public class BateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(BateriaView.class);
    private static final long serialVersionUID = -1L;

    private List<BateriaBean> lstBaterias;
    private List<ModeloEvaluacion> competencias;
    private List<BateriaBean> droppedBaterias;
    private Map mapCompetenciasSeleccionadas;
    
    @PostConstruct
    public void init() {
        limpiar();
        obtenerModelosEvaluaciones();
    }

    public void limpiar() {
        lstBaterias = new ArrayList<>();
        droppedBaterias = new ArrayList<BateriaBean>();
        competencias = new ArrayList<ModeloEvaluacion>();
        mapCompetenciasSeleccionadas = new HashMap();
        obtenerModelosEvaluaciones();
        CrearProcesoView processView = new CrearProcesoView();
        processView.limpiar();
    }
    
    public List obtenerModelosEvaluaciones() {
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
    
    public void cargarBilleteraBaterias(DragDropEvent ddEvent) {
        BateriaBean model = ((BateriaBean) ddEvent.getData());
        droppedBaterias.add(model);
        ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
        List<ModeloEvaluacion> lstCompBD = objModelosDao.obtenerCompetenciasXEvaluacion(model.getId());
        competencias = lstCompBD;
        
        for(CompetenciaBean objCompetenciaBean  : model.getLstCompetencias()){
            
            if (!mapCompetenciasSeleccionadas.containsKey(objCompetenciaBean.getNombre())){
                mapCompetenciasSeleccionadas.put("", "");
            }
        
        }
        
        lstBaterias.remove(model);
    }
    
    public void grabarBateria(String itemNombreE,String itemEmpresa, String itemArea, 
            String itemPerfil, List<BateriaBean> listBaterias){
        try {
            mostrarAlerta(INFO, "proceso.seleccion.perfil", null, null,itemEmpresa);
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

    public List<ModeloEvaluacion> getCompetencias() {
        return competencias;
    }
 
    public List<BateriaBean> getDroppedBaterias() {
        return droppedBaterias;
    }
   
}
