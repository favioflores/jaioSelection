package jaio.selection.dao;

import jaio.selection.orm.Candidato;
import jaio.selection.orm.InfoAcademica;
import jaio.selection.orm.InfoConocimiento;
import jaio.selection.orm.InfoExperiencia;
import jaio.selection.orm.InfoReferencia;
import jaio.selection.util.Constantes;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.util.Utilitarios;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Query;

public class ReclutamientoDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ReclutamientoDAO.class);

    public List obtenerReclutados(String idEmpresa, String idProceso) {
        try {
            iniciaSession();

            StringBuilder builder = new StringBuilder();

            String sql1 = builder.append("select * from candidato c where c.proceso_seleccion_id in(select ps.id from proceso_seleccion ps ")
                    .append(" join perfil p on p.id = ps.perfil_id where p.empresa_id= ")
                    .append(idEmpresa).toString();
            if (Utilitarios.noEsNuloOVacio(idProceso)) {
                String sql2 = builder.append(" and c.proceso_seleccion_id= ")
                        .append(idProceso).toString();
            }
            String sql3 = builder.append(" ) ").toString();

            Query query = session.createSQLQuery(sql3);
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

    public List<InfoReferencia> cargarInfoReferencia(Integer id) {
        try {
            iniciaSession();
            Query query = session.createQuery("From InfoReferencia r where r.info_experiencia_id=" + id);
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
        return null;
    }

    public StringBuilder obtenerIds(List idList) {
        StringBuilder sb = new StringBuilder();
        if (Utilitarios.noEsNuloOVacio(idList) && idList.size() > Constantes.Int_zero) {
            for (int i = 0; i < idList.size(); i++) {
                sb.append("'").append(idList.get(i)).append("'").append(",");
            }
            sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            sb.append("''");
        }

        return sb;
    }

    public void grabarInfoCandidato(Candidato candidato, List<InfoAcademica> listaAcademica,
            List<InfoConocimiento> listaConocimiento, List<InfoExperiencia> listaExperiencia,
            List<InfoReferencia> listaReferencia) {
        try {
            iniciaSession();

            if (Utilitarios.noEsNuloOVacio(candidato.getId())) {
                session.saveOrUpdate(candidato);

                Query delAcademica = session.createSQLQuery("delete from info_academica where candidato_id=" + candidato.getId());
                delAcademica.executeUpdate();

                Query delConocimiento = session.createSQLQuery("delete from info_conocimiento where candidato_id=" + candidato.getId());
                delConocimiento.executeUpdate();

                Query list = session.createSQLQuery("select ir.id from info_referencia ir "
                        + "join info_experiencia ie on ie.id = ir.info_experiencia_id where ie.candidato_id=" + candidato.getId());

                Query delReferencia = session.createSQLQuery("delete from info_referencia where id in (" + obtenerIds(list.list()) + ")");
                delReferencia.executeUpdate();

                Query delExperiencia = session.createSQLQuery("delete from info_experiencia where candidato_id=" + candidato.getId());
                delExperiencia.executeUpdate();

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
                    if (Utilitarios.noEsNuloOVacio(listaReferencia)) {
                        for (InfoReferencia referencia : listaReferencia) {
                            if (referencia.getNombreEmpresa().equals(experiencia.getEmpresa())) {
                                referencia.setInfoExperiencia(experiencia);
                                session.save(referencia);
                            }
                        }
                    }
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

    public void eliminarReclutados(String id) {
        try {
            iniciaSession();

            Query delAcademica = session.createSQLQuery("delete from info_academica where candidato_id=" + id);
            delAcademica.executeUpdate();

            Query delConocimiento = session.createSQLQuery("delete from info_conocimiento where candidato_id=" + id);
            delConocimiento.executeUpdate();

            Query list = session.createSQLQuery("select ir.id from info_referencia ir "
                    + "join info_experiencia ie on ie.id = ir.info_experiencia_id where ie.candidato_id=" + id);

            Query delReferencia = session.createSQLQuery("delete from info_referencia where id in (" + obtenerIds(list.list()) + ")");
            delReferencia.executeUpdate();

            Query delExperiencia = session.createSQLQuery("delete from info_experiencia where candidato_id=" + id);
            delExperiencia.executeUpdate();

            Query delCandidato = session.createSQLQuery("delete from candidato where id=" + id);
            delCandidato.executeUpdate();
            log.debug("Se elimino el registro correctamente");
        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }
    }

}
