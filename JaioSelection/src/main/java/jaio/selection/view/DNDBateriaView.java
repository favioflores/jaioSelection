package jaio.selection.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import jaio.selection.orm.ModeloEvaluacion;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "dNDBateriaView")
@ViewScoped
public class DNDBateriaView extends BaseView implements Serializable {

    @ManagedProperty("#{bateriaService}")
    private BateriaService bateriaService;
 
    private List<ModeloEvaluacion> baterias;
     
    private List<ModeloEvaluacion> droppedBaterias;
     
    private ModeloEvaluacion selectedBateria;
     
    @PostConstruct
    public void init() {
        baterias = bateriaService.createBateria(9);
        droppedBaterias = new ArrayList<ModeloEvaluacion>();
    }
     
    public void onBateriaDrop(DragDropEvent ddEvent) {
        ModeloEvaluacion model = ((ModeloEvaluacion) ddEvent.getData());
        droppedBaterias.add(model);
        baterias.remove(model);
    }
     
    public void setBateriaService(BateriaService bateriaService) {
        this.bateriaService = bateriaService;
    }
 
    public List<ModeloEvaluacion> getBaterias() {
        return baterias;
    }
 
    public List<ModeloEvaluacion> getDroppedBaterias() {
        return droppedBaterias;
    }    
 
    public ModeloEvaluacion getSelectedBateria() {
        return selectedBateria;
    }
 
    public void setSelectedBateria(ModeloEvaluacion selectedCar) {
        this.selectedBateria = selectedCar;
    }
   
}
