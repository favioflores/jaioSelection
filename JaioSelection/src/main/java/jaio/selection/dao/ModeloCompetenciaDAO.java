package jaio.selection.dao;

import jaio.selection.orm.ModeloCompetencia;
import jaio.selection.orm.ModeloEvaluacion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;

public class ModeloCompetenciaDAO extends HibernateUtil implements Serializable {

    private static final Log log = LogFactory.getLog(ModeloCompetenciaDAO.class);

    public List<ModeloCompetencia> obtenerModelos() {
        iniciaSession();
        try {
            Query query = session.createQuery("From ModeloCompetencia");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerCompetenciasXEvaluacion(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select distinct(mc.nombre),mc.id, mc.color from modelo_evaluacion_x_competencia mec "
                    + " join modelo_evaluacion me on me.id = mec.modelo_evaluacion_id "
                    + " join modelo_competencia mc on mc.id = mec.modelo_competencia_id "
                    + " where mec.modelo_evaluacion_id = :id");
            query.setString("id", id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerModeloCompetenciaDeEvaluaciones() {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select me.id, mc.nombre, mc.color, mc.id as idmc from modelo_evaluacion_x_competencia mec "
                    + " join modelo_evaluacion me on me.id = mec.modelo_evaluacion_id "
                    + " join modelo_competencia mc on mc.id = mec.modelo_competencia_id order by 1 asc");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerNombresEvaluacionPorCompetencia(String id, StringBuilder sb) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select me.nombre from modelo_evaluacion_x_competencia mec "
                    + " join modelo_evaluacion me on me.id = mec.modelo_evaluacion_id "
                    + "	join modelo_competencia mc on mc.id = mec.modelo_competencia_id "
                    + " where mc.id=" + id + " and me.id in (" + sb + ")");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

}
