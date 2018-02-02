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
import jaio.selection.dao.ModeloEvaluacionDAO;
import jaio.selection.orm.ModeloEvaluacion;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "bateriaView")
@ViewScoped
public class BateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(BateriaView.class);
    private static final long serialVersionUID = -1L;

    //lista para traer info al panel
    private List<BateriaBean> lstBaterias;
    
    @ManagedProperty("#{bateriaService}")
    private BateriaService bateriaService;
 
    private List<BateriaBean> baterias;
     
    private List<BateriaBean> droppedBaterias;
     
    private BateriaBean selectedBateria;
    
    
    @PostConstruct
    public void init() {
        limpiar();
        baterias = bateriaService.createBateria(9);
        obtenerModelosEvaluaciones();
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
    
    public void obtenerModelosEvaluaciones() {
        try {
            lstBaterias = new ArrayList<>();
            ModeloEvaluacionDAO objModelosDao = new ModeloEvaluacionDAO();
            for(ModeloEvaluacion objModeloEvaluacion : objModelosDao.obtenerModelos()){
                BateriaBean objBateriaBean = new BateriaBean();
                objBateriaBean.setId(objModeloEvaluacion.getId());
                objBateriaBean.setNombre(objModeloEvaluacion.getNombre());
                objBateriaBean.setValidez(objModeloEvaluacion.getValidez());
                objBateriaBean.setConfiabilidad(objModeloEvaluacion.getConfiabilidad());
                objBateriaBean.setMinutosEstimados(objModeloEvaluacion.getMinutosEstimados());
                lstBaterias.add(objBateriaBean);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }
    public void onBateriaDrop(DragDropEvent ddEvent) {
        BateriaBean model = ((BateriaBean) ddEvent.getData());
        droppedBaterias.add(model);
        baterias.remove(model);
    }
     
    public void setBateriaService(BateriaService bateriaService) {
        this.bateriaService = bateriaService;
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
 
    public void setSelectedBateria(BateriaBean selectedCar) {
        this.selectedBateria = selectedCar;
    }
   
}
