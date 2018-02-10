package jaio.selection.bean;

import java.io.Serializable;

public class ErrorExcelBean implements Serializable{

    private String strEtiqueta;
    private String strError;
    private String strFila;
    private String strColumna;
    private String strValor;

    public String getStrValor() {
        return strValor;
    }

    public void setStrValor(String strValor) {
        this.strValor = strValor;
    }

    public String getStrEtiqueta() {
        return strEtiqueta;
    }

    public void setStrEtiqueta(String strEtiqueta) {
        this.strEtiqueta = strEtiqueta;
    }

    public String getStrError() {
        return strError;
    }

    public void setStrError(String strError) {
        this.strError = strError;
    }

    public String getStrFila() {
        return strFila;
    }

    public void setStrFila(String strFila) {
        this.strFila = strFila;
    }

    public String getStrColumna() {
        return strColumna;
    }

    public void setStrColumna(String strColumna) {
        this.strColumna = strColumna;
    }

}


