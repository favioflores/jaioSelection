package jaio.selection.bean;

import java.io.Serializable;

public class AjusteEvaluacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String concepto;
    private String dato;

    
    public AjusteEvaluacionBean (String concepto, String dato){
       this.concepto = concepto;
       this.dato = dato;
    }
    
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

}
