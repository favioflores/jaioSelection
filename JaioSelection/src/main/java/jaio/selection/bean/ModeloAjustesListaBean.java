package jaio.selection.bean;

import java.util.List;

public class ModeloAjustesListaBean implements java.io.Serializable {

    private List<ModeloAjustesCalcBean> lstModeloAjustesCalcBeans;

    public List<ModeloAjustesCalcBean> getLstModeloAjustesCalcBeans() {
        return lstModeloAjustesCalcBeans;
    }

    public void setLstModeloAjustesCalcBeans(List<ModeloAjustesCalcBean> lstModeloAjustesCalcBeans) {
        this.lstModeloAjustesCalcBeans = lstModeloAjustesCalcBeans;
    }

}
