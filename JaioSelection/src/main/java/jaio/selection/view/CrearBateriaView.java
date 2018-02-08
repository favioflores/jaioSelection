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

@ManagedBean(name = "crarBateriaView")
@ViewScoped
public class CrearBateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(CrearBateriaView.class);
    private static final long serialVersionUID = -1L;

    private List<BateriaBean> lstBaterias;
//    private List<ModeloEvaluacion> droppedCompetencias;
    private List<BateriaBean> droppedCompetencias;
    private List<BateriaBean> droppedBaterias;
    private Map mapCompetenciasSeleccionadas;
    
    @PostConstruct
    public void init() {
        limpiar();
        obtenerModelosDeEvaluaciones();
    }

    public void limpiar() {
        lstBaterias = new ArrayList<>();
        droppedBaterias = new ArrayList<>();
        droppedCompetencias = new ArrayList<>();
        mapCompetenciasSeleccionadas = new HashMap();
        obtenerModelosDeEvaluaciones();
        CrearProcesoView processView = new CrearProcesoView();
        processView.limpiar();
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
        droppedBaterias.add(model);
        droppedCompetencias.add(model);
//        ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
//        List<ModeloEvaluacion> lstCompBD = objModelosDao.obtenerCompetenciasXEvaluacion(model.getId());
//        droppedCompetencias = lstCompBD;
//        for(CompetenciaBean objCompetenciaBean  : model.getLstCompetencias()){
//            if (!mapCompetenciasSeleccionadas.containsKey(objCompetenciaBean.getNombre())){
//                mapCompetenciasSeleccionadas.put("", "");
//            }
//        }
        lstBaterias.remove(model);
    }
    
    public void vaciarBilleteraDeBaterias(DragDropEvent ddEvent){
        BateriaBean model = ((BateriaBean) ddEvent.getData());
        lstBaterias.add(model);
        droppedBaterias.remove(model);
        droppedCompetencias.remove(model);
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

    public List<BateriaBean> getCompetencias() {
        return droppedCompetencias;
    }
 
    public List<BateriaBean> getDroppedBaterias() {
        return droppedBaterias;
    }
   
}
