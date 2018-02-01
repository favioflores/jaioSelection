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

@ManagedBean(name = "bateriaView")
@ViewScoped
public class BateriaView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(BateriaView.class);
    private static final long serialVersionUID = -1L;

    //lista para traer info al panel
    private List<BateriaBean> lstBaterias;
    
    
    @PostConstruct
    public void init() {
        limpiar();
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
   
}
