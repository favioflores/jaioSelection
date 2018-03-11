package jaio.selection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModeloEvaluacionListCalBean implements Serializable {

    private List<ModeloEvaluacionCalBean> listModeloEvaluacionCalBean = new ArrayList<>();

    public List<ModeloEvaluacionCalBean> getListModeloEvaluacionCalBean() {
        return listModeloEvaluacionCalBean;
    }

    public void setListModeloEvaluacionCalBean(List<ModeloEvaluacionCalBean> listModeloEvaluacionCalBean) {
        this.listModeloEvaluacionCalBean = listModeloEvaluacionCalBean;
    }

}
