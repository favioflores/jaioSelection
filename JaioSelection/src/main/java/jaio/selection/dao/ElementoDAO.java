package jaio.selection.dao;

import jaio.selection.orm.Elemento;
import jaio.selection.util.Constantes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;

public class ElementoDAO extends HibernateUtil implements Serializable {

    private static final Log log = LogFactory.getLog(ElementoDAO.class);

    public List<Elemento> obtenListaElemento() {
        
        List<Elemento> listaElemento = null;
        
        try {
            
            iniciaSession();
            Query query = session.createQuery("from Elemento el where el.activo = ? ");
            query.setInteger(0, Constantes.INT_ESTADO_ELEMENTO_ACTIVO);
            listaElemento = query.list();
            
        } catch (Exception e) {
            manejaException(log, e);
        }finally{
            cerrarSession();
        }

        return listaElemento;
    }
}
