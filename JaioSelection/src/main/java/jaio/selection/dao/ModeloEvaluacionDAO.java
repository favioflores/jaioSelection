package jaio.selection.dao;

import jaio.selection.bean.BateriaBean;
import jaio.selection.orm.BateriaEvaluacion;
import jaio.selection.orm.BateriaEvaluacionId;
import jaio.selection.orm.BateriaPersonalizada;
import jaio.selection.orm.EvaluacionPerfil;
import jaio.selection.orm.EvaluacionPerfilId;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import jaio.selection.orm.ModeloEvaluacion;
import jaio.selection.orm.Perfil;
import jaio.selection.orm.ProcesoSeleccion;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;
import java.io.Serializable;
import java.util.Date;
import org.atmosphere.util.uri.UriComponent;
import org.hibernate.Query;

public class ModeloEvaluacionDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ModeloEvaluacionDAO.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    public void persist(ModeloEvaluacion transientInstance) {
        log.debug("persisting ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(ModeloEvaluacion instance) {
        log.debug("attaching dirty ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ModeloEvaluacion instance) {
        log.debug("attaching clean ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(ModeloEvaluacion persistentInstance) {
        log.debug("deleting ModeloEvaluacion instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ModeloEvaluacion merge(ModeloEvaluacion detachedInstance) {
        log.debug("merging ModeloEvaluacion instance");
        try {
            ModeloEvaluacion result = (ModeloEvaluacion) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ModeloEvaluacion findById(java.lang.Integer id) {
        log.debug("getting ModeloEvaluacion instance with id: " + id);
        try {
            ModeloEvaluacion instance = (ModeloEvaluacion) sessionFactory.getCurrentSession()
                    .get("jaio.selection.orm.ModeloEvaluacion", id);
            if (instance == null) {
                log.debug("get successful, no instance found");
            } else {
                log.debug("get successful, instance found");
            }
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(ModeloEvaluacion instance) {
        log.debug("finding ModeloEvaluacion instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("jaio.selection.orm.ModeloEvaluacion")
                    .add(Example.create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /**
     * metodo para traer lista de evaluaciones
     *
     * @return
     */
    public List<ModeloEvaluacion> obtenerModelos() {
        iniciaSession();
        try {
            Query query = session.createQuery("From ModeloEvaluacion order by 1 asc");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerModelosXCompetencia(String idCompetencia, StringBuilder sb) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select me.* from modelo_evaluacion me "
                    + " join modelo_evaluacion_x_competencia mec on mec.modelo_evaluacion_id = me.id "
                    + " join modelo_competencia mc on mec.modelo_competencia_id = mc.id "
                    + " where mc.id in((select id from (select id,nombre from modelo_competencia "
                    + "	union all "
                    + "	select modelo_competencia_id as id,palabra as nombre from modelo_competencia_sinonimo "
                    + "	) as T1 where nombre like '" + idCompetencia + "%' )) "
                    + "	"
                    + " and me.id not in (" + sb + ")");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerModelosCompetencia(StringBuilder sb) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select * from modelo_evaluacion where id not in (" + sb + ");");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List traerNombresDeCompetencias() {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select id,nombre from modelo_competencia "
                    + " union all select modelo_competencia_id as id,palabra as nombre from modelo_competencia_sinonimo");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List traerAjustesXEvaluacion(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select mac.id,mac.concepto,el.descripcion,mac.dato "
                    + " from modelo_ajustes_calc mac "
                    + " join elemento el on mac.tipo = el.id "
                    + " where mac.modelo_evaluacion_id=" + id + " order by el.descripcion, mac.id ");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerAjustesDeEvaluaciones() {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select mac.modelo_evaluacion_id as idE,mac.concepto,el.descripcion,mac.dato "
                    + "	from modelo_ajustes_calc mac "
                    + "	join elemento el on mac.tipo = el.id "
                    + "	order by mac.modelo_evaluacion_id, el.descripcion, mac.id ");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public void grabarBateriaPersonalizada(BateriaPersonalizada objBateriaPersonalizada, List<BateriaBean> droppedBaterias,
            String strPerfilSeleccionado) {
        try {
            iniciaSession();
            if (Utilitarios.noEsNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_ID_BATERIA))) {
                session.saveOrUpdate(objBateriaPersonalizada);
                Query del = session.createSQLQuery("delete from bateria_evaluacion "
                        + " where bateria_personalizada_id=" + objBateriaPersonalizada.getId());
                del.executeUpdate();
                
                Query del1 = session.createSQLQuery("delete from evaluacion_perfil "
                        + " where bateria_personalizada_id="+objBateriaPersonalizada.getId());
                del1.executeUpdate();
                
                Query del2 = session.createSQLQuery("delete from proceso_seleccion where id=(select proceso_seleccion_id"
                        + " from evaluacion_perfil where bateria_personalizada_id="+ objBateriaPersonalizada.getId() +")");
                del2.executeUpdate();
                
                
                
            } else {
                session.save(objBateriaPersonalizada);
            }

            for (BateriaBean droppedBateria : droppedBaterias) {
                ModeloEvaluacion objModeloEvaluacion = new ModeloEvaluacion();
                objModeloEvaluacion.setId(Integer.parseInt(droppedBateria.getId()));

                BateriaEvaluacionId objBateriaEvaluacionId = new BateriaEvaluacionId();
                objBateriaEvaluacionId.setBateriaPersonalizadaId(objBateriaPersonalizada.getId());
                objBateriaEvaluacionId.setModeloEvaluacionId(objModeloEvaluacion.getId());

                BateriaEvaluacion objBateriaEvaluacion = new BateriaEvaluacion();
                objBateriaEvaluacion.setBateriaPersonalizada(objBateriaPersonalizada);
                objBateriaEvaluacion.setModeloEvaluacion(objModeloEvaluacion);
                objBateriaEvaluacion.setId(objBateriaEvaluacionId);

                objModeloEvaluacion.getBateriaEvaluacion().add(objBateriaEvaluacion);
                objBateriaPersonalizada.getBateriaEvaluacion().add(objBateriaEvaluacion);

                session.save(objBateriaEvaluacion);

            }

            ProcesoSeleccion objProcesoSeleccion = new ProcesoSeleccion();
            EvaluacionPerfil objEvaluacionPerfil = new EvaluacionPerfil();

            Perfil objPerfil = new Perfil();
            objPerfil.setId(Integer.parseInt(strPerfilSeleccionado));

            objProcesoSeleccion.setFechaRegistro(new Date());
            objProcesoSeleccion.setDescripcion("descripcion de prueba");
            objProcesoSeleccion.setEstado(Constantes.INT_ESTADO_PROCESO_REGISTRADO);
            objProcesoSeleccion.setPerfil(objPerfil);

            objProcesoSeleccion.getEvaluacionPerfil().add(objEvaluacionPerfil);
            
            session.save(objProcesoSeleccion);

            EvaluacionPerfilId objEvaluacionPerfilId = new EvaluacionPerfilId();
            objEvaluacionPerfilId.setProcesoSeleccionId(objProcesoSeleccion.getId());
            objEvaluacionPerfilId.setBateriaPersonalizadaId(objBateriaPersonalizada.getId());
            objEvaluacionPerfilId.setEstado(Constantes.INT_ESTADO_EVALUACION_ACTIVO);

            objEvaluacionPerfil.setEstado(Constantes.INT_ESTADO_EVALUACION_ACTIVO);
            objEvaluacionPerfil.setBateriaPersonalizada(objBateriaPersonalizada);
            objEvaluacionPerfil.setProcesoSeleccion(objProcesoSeleccion);
            objEvaluacionPerfil.setId(objEvaluacionPerfilId);

            objBateriaPersonalizada.getEvaluacionPerfil().add(objEvaluacionPerfil);
            
            session.save(objEvaluacionPerfil);
            guardarCambios();
            log.debug("Grago correctamente");
        } catch (Exception e) {
            rollback(log, e);
        } finally {
            cerrarSession();
        }
    }

    public void updateRegister() {

    }

    public List obtenerEvaluacionesSeleccionadas(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select me.* from modelo_evaluacion me "
                    + " join bateria_evaluacion be on be.modelo_evaluacion_id = me.id "
                    + " where be.bateria_personalizada_id=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerInfoParaCampos(String id) {
        iniciaSession();
        try {
            Query query = session.createSQLQuery("select bp.nombre,p.empresa_id,p.area_id,p.id "
                    + " from bateria_personalizada bp "
                    + " join evaluacion_perfil ep on ep.bateria_personalizada_id = bp.id "
                    + " join proceso_seleccion ps on ps.id = ep.proceso_seleccion_id "
                    + " join perfil p on p.id = ps.perfil_id "
                    + " where bp.id=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public void actualizarBateria(BateriaPersonalizada objBateriaPersonalizada, List<BateriaBean> droppedBaterias, String strPerfilSeleccionado) {
        try {
            iniciaSession();

        } catch (Exception e) {
            rollback(log, e);
        } finally {
            cerrarSession();
        }
    }

}
