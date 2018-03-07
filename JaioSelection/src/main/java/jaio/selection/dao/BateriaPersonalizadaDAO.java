package jaio.selection.dao;

import jaio.selection.bean.BateriaPersonalizadaBean;
import jaio.selection.bean.ConvertirDatosBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import jaio.selection.orm.BateriaPersonalizada;
import jaio.selection.util.Constantes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * DAO object for domain model class BateriaPersonalizada.
 *
 * @see jaio.selection.orm.BateriaPersonalizada
 * @author Hibernate Tools
 */
public class BateriaPersonalizadaDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(BateriaPersonalizadaDAO.class);

    /**
     * metodo para traer los procesos ya registrados
     *
     * @param id
     * @return
     */
    public List obtenerProcesosRegistrados(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select bp.id, bp.nombre, bp.fecha_creacion, p.nombre as nombrePerfil "
                    + " from bateria_personalizada bp "
                    + " join evaluacion_perfil ep on ep.bateria_personalizada_id = bp.id "
                    + " join proceso_seleccion ps on ps.id = ep.proceso_seleccion_id "
                    + " join perfil p on p.id = ps.perfil_id "
                    + " where p.empresa_id = :id "
                    + " and bp.estado = " + Constantes.INT_ESTADO_PROCESO_REGISTRADO
                    + " order by bp.id desc;");
            query.setString("id", id);
            return query.list();
        } catch (HibernateException e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public String obtenerNumeroEvaluaciones(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select count(modelo_evaluacion_id) "
                    + " from bateria_evaluacion where bateria_personalizada_id= " + id);
            
            String cantidad = query.uniqueResult().toString();
            return cantidad;
        } catch (HibernateException e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerCompetenciaParaResumen(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select distinct(mc.nombre),mc.id from modelo_competencia mc "
                    + " join modelo_evaluacion_x_competencia mec on mec.modelo_competencia_id = mc.id "
                    + " join bateria_evaluacion be on be.modelo_evaluacion_id = mec.modelo_evaluacion_id "
                    + " where be.bateria_personalizada_id=" + id);
            return query.list();
        } catch (HibernateException e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public boolean eliminarBateriaRegistrada(String id) {
        try {
            iniciaSession();
            Query update = session.createSQLQuery("update bateria_personalizada "
                    + "set estado = " + Constantes.INT_ESTADO_PROCESO_ELIMINADO + " where id =" + id);
            update.executeUpdate();
            guardarCambios();
        } catch (HibernateException e) {
            manejaException(log, e);
            return false;
        } finally {
            cerrarSession();
        }
        return true;
    }

}
