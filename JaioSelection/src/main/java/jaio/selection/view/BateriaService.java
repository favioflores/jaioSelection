package jaio.selection.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import jaio.selection.orm.ModeloEvaluacion;
import java.util.Arrays;
import java.util.UUID;

@ManagedBean(name = "bateriaService")
@ViewScoped
public class BateriaService extends BaseView implements Serializable {

    
//    public List<ModeloEvaluacion> createBateria(int size) {
//        List<ModeloEvaluacion> list = new ArrayList<ModeloEvaluacion>();
////		for(int i = 0 ; i < size ; i++) {
//////			list.add(new ModeloEvaluacion(getRandomId()));
////        }
//        
//        return list;
//    }
    
    private String getRandomId() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
    
}
