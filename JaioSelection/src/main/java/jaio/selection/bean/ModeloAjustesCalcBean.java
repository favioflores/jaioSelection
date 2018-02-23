package jaio.selection.bean;

import java.util.List;

public class ModeloAjustesCalcBean implements java.io.Serializable {

    private String tipo;
    private List<AjusteEvaluacionBean> lstAjusteEvaluacionBean;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<AjusteEvaluacionBean> getLstAjusteEvaluacionBean() {
        return lstAjusteEvaluacionBean;
    }

    public void setLstAjusteEvaluacionBean(List<AjusteEvaluacionBean> lstAjusteEvaluacionBean) {
        this.lstAjusteEvaluacionBean = lstAjusteEvaluacionBean;
    }
 
}
