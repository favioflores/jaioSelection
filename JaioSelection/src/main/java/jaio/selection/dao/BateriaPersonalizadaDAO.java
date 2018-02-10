package jaio.selection.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import jaio.selection.orm.BateriaPersonalizada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 * DAO object for domain model class BateriaPersonalizada.
 * @see jaio.selection.orm.BateriaPersonalizada
 * @author Hibernate Tools
 */
public class BateriaPersonalizadaDAO extends HibernateUtil implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(BateriaPersonalizadaDAO.class);
    
    /**
     * metodo para traer los procesos ya registrados
     * @param id
     * @return 
     */
     public List obtenerProcesosRegistrados(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select bp.nombre,bp.fecha_creacion,p.nombre as perfil,me.id " +
                " from bateria_evaluacion be " +
                " join modelo_evaluacion me on me.id = be.modelo_evaluacion_id " +
                " join bateria_personalizada bp on bp.id = be.bateria_personalizada_id " +
                " join evaluacion_perfil ep on ep.bateria_personalizada_id = bp.id " +
                " join proceso_seleccion ps on ps.id = ep.proceso_seleccion_id " +
                " join perfil p on p.id = ps.perfil_id " +
                " join empresa e on e.id = p.empresa_id " +
                " where e.id= :id");
            query.setString("id", id);
            return query.list();
        } catch (Exception e) {
            manejaException(e);
        } finally {
            cerrarSession();
        }
        return null;
    }
     

}
