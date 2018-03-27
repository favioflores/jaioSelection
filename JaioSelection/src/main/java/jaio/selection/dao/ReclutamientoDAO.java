package jaio.selection.dao;

import jaio.selection.orm.Candidato;
import jaio.selection.orm.InfoAcademica;
import jaio.selection.orm.InfoConocimiento;
import jaio.selection.orm.InfoExperiencia;
import jaio.selection.orm.InfoReferencia;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.util.Utilitarios;
import java.io.Serializable;
import org.hibernate.Query;

public class ReclutamientoDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ReclutamientoDAO.class);

    public List<Candidato> obtenerReclutados(String id) {
        try {
            iniciaSession();
            Query query = session.createQuery("From Candidato c where proceso_seleccion_id=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerProcesos(String id) {
        try {
            iniciaSession();
            Query query = session.createSQLQuery("select ps.id,ps.descripcion from proceso_seleccion ps "
                    + "join perfil p on p.id=ps.perfil_id "
                    + "where p.empresa_id=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List obtenerTipoDocumento() {
        try {
            iniciaSession();
            Query query = session.createSQLQuery("select * from elemento where definicion_id=14 order by 1 asc");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public Candidato cargarCandidato(String id) {
        try {
            iniciaSession();
            Query query = session.createQuery("From Candidato c where c.id =" + id);
            Candidato candidato = (Candidato) query.uniqueResult();
            return candidato;
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List<InfoAcademica> cargarInfoAcademica(Integer id) {
        try {
            iniciaSession();
            Query query = session.createQuery("From InfoAcademica a where a.candidato=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List<InfoConocimiento> cargarInfoConocimiento(Integer id) {
        try {
            iniciaSession();
            Query query = session.createQuery("From InfoConocimiento c where c.candidato=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public List<InfoExperiencia> cargarInfoExperiencia(Integer id) {
        try {
            iniciaSession();
            Query query = session.createQuery("From InfoExperiencia e where e.candidato=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public void grabarInfoCandidato(Candidato candidato, List<InfoAcademica> listaAcademica,
            List<InfoConocimiento> listaConocimiento, List<InfoExperiencia> listaExperiencia, List<InfoReferencia> listaReferencia) {
        try {
            iniciaSession();

            if (Utilitarios.noEsNuloOVacio(candidato.getId())) {
                session.saveOrUpdate(candidato);

                Query del1 = session.createSQLQuery("delete from info_academica where candidato_id=" + candidato.getId());
                del1.executeUpdate();

                Query del2 = session.createSQLQuery("delete from info_conocimiento where candidato_id=" + candidato.getId());
                del2.executeUpdate();

                Query del3 = session.createSQLQuery("delete from info_referencia where info_experiencia_id=" + candidato.getId());
                del3.executeUpdate();

                Query del4 = session.createSQLQuery("delete from info_referencia where id=(select ir.id from info_referencia ir "
                        + "join info_experiencia ie on ie.id = ir.info_experiencia_id "
                        + "where ie.candidato_id= " + candidato.getId() + ")");
                del4.executeUpdate();

            } else {
                session.save(candidato);
            }

            if (Utilitarios.noEsNuloOVacio(listaAcademica)) {
                for (InfoAcademica academica : listaAcademica) {
                    academica.setCandidato(candidato);
                    session.save(academica);
                }
            }

            if (Utilitarios.noEsNuloOVacio(listaConocimiento)) {
                for (InfoConocimiento conocimiento : listaConocimiento) {
                    conocimiento.setCandidato(candidato);
                    session.save(conocimiento);
                }
            }

            if (Utilitarios.noEsNuloOVacio(listaExperiencia)) {
                for (InfoExperiencia experiencia : listaExperiencia) {
                    experiencia.setCandidato(candidato);
                    session.save(experiencia);
                }
            }

            guardarCambios();
            log.debug("Grago correctamente");
        } catch (Exception e) {
            rollback(log, e);
        } finally {
            cerrarSession();
        }
    }

}
