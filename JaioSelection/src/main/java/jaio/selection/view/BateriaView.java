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
import jaio.selection.bean.ConvertirDatosBean;
import jaio.selection.util.Constantes;
import jaio.selection.dao.ModeloEvaluacionDAO;
import jaio.selection.orm.ModeloEvaluacion;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "bateriaView")
@ViewScoped
public class BateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(BateriaView.class);
    private static final long serialVersionUID = -1L;

    //lista para evaluaciones de bd
    private List<BateriaBean> lstBaterias;
    
//    @ManagedProperty("#{bateriaService}")
//    private BateriaService bateriaService;
 
    private List<BateriaBean> baterias;
     
    private List<BateriaBean> droppedBaterias;
     
    private BateriaBean selectedBateria;
    
    
    @PostConstruct
    public void init() {
        obtenerModelosEvaluaciones();
        droppedBaterias = new ArrayList<BateriaBean>();
    }
    
    
    public List<BateriaBean> getLstBaterias() {
        return lstBaterias;
    }

    public void setLstBaterias(List<BateriaBean> lstBaterias) {
        this.lstBaterias = lstBaterias;
    }
    
    
    public void limpiar() {
        lstBaterias = new ArrayList<>();
    }
    
    public List obtenerModelosEvaluaciones() {
        try {
            lstBaterias = new ArrayList<>();
            ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
            ConvertirDatosBean convertirDatos = new ConvertirDatosBean();
            List<ModeloEvaluacion> listaEvaluacionesBD = objModelosDao.obtenerModelos();
            for(ModeloEvaluacion objModeloEvaluacion : listaEvaluacionesBD){
                BateriaBean objBateriaBean = new BateriaBean();
                String id = convertirDatos.convertirObjetAString(objModeloEvaluacion.getId(), Constantes.Tipo_dato_int);
                String validez = convertirDatos.convertirObjetAString(objModeloEvaluacion.getValidez(), Constantes.Tipo_dato_BigDecimal);
                String confiabilidad = convertirDatos.convertirObjetAString(objModeloEvaluacion.getConfiabilidad(), Constantes.Tipo_dato_BigDecimal);
                String minutosEstimados = convertirDatos.convertirObjetAString(objModeloEvaluacion.getMinutosEstimados(), Constantes.Tipo_dato_int);
                objBateriaBean.setId(id);
                objBateriaBean.setNombre(objModeloEvaluacion.getNombre());
                objBateriaBean.setValidez(validez);
                objBateriaBean.setConfiabilidad(confiabilidad);
                objBateriaBean.setMinutosEstimados(minutosEstimados);
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
        lstBaterias.remove(model);
    }
     
   
 
    public List<BateriaBean> getBaterias() {
        return baterias;
    }
 
    public List<BateriaBean> getDroppedBaterias() {
        return droppedBaterias;
    }    
 
    public BateriaBean getSelectedBateria() {
        return selectedBateria;
    }
 
    public void setSelectedBateria(BateriaBean selectedBateria) {
        this.selectedBateria = selectedBateria;
    }
   
}
