package jaio.selection.bean;

import jaio.selection.util.Utilitarios;
import jaio.selection.util.Constantes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertirDatosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public String convertirObjetAString(Object dato, String tipo) {
        String nuevoDato = "";
        if(Utilitarios.noEsNuloOVacio(dato)){
            if(Constantes.Tipo_dato_int.equals(tipo)){
                int valor = (int)dato;
                nuevoDato = Integer.toString(valor);
            }else if(Constantes.Tipo_dato_BigDecimal.equals(tipo)){
                BigDecimal valor = (BigDecimal)dato;
                nuevoDato = valor.toString();
            }else if(Constantes.Tipo_dato_long.equals(tipo)){
                Long valor = (Long)dato;
                nuevoDato = Long.toString(valor);
            }else if(Constantes.Tipo_dato_short.equals(tipo)){
                Short valor = (Short)dato;
                nuevoDato = Short.toString(valor);
            }else if(Constantes.Tipo_dato_byte.equals(tipo)){
                byte valor = (byte)dato;
                nuevoDato = Byte.toString(valor);
            }else if(Constantes.Tipo_dato_date.equals(tipo)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date valor = (Date)dato;
                nuevoDato = sdf.format(valor);
            }
                
                
        }
        return nuevoDato;
    }
    
    public Object convertirStringAObject(String dato, String tipo) {
        Object nuevoDato=null;
        if(Utilitarios.noEsNuloOVacio(dato)){
            if(Constantes.Tipo_dato_int.equals(tipo)){
                nuevoDato = Integer.parseInt(dato);
            }else if(Constantes.Tipo_dato_BigDecimal.equals(tipo)){
                nuevoDato = new BigDecimal(dato);
            }else if(Constantes.Tipo_dato_long.equals(tipo)){
                nuevoDato = Long.parseLong(dato);
            }else if(Constantes.Tipo_dato_short.equals(tipo)){
                nuevoDato = Short.parseShort(dato);
            }
        }
        return nuevoDato;
    }
    
}