package jaio.selection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModeloEvaluacionListCalBean implements Serializable {

    String id;
    private List<ModeloEvaluacionCalBean> listModeloEvaluacionCalBean = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ModeloEvaluacionCalBean> getListModeloEvaluacionCalBean() {
        return listModeloEvaluacionCalBean;
    }

    public void setListModeloEvaluacionCalBean(List<ModeloEvaluacionCalBean> listModeloEvaluacionCalBean) {
        this.listModeloEvaluacionCalBean = listModeloEvaluacionCalBean;
    }

}
