package jaio.selection.dao;

import jaio.selection.orm.Candidato;
import jaio.selection.orm.InfoAcademica;
import jaio.selection.orm.InfoConocimiento;
import jaio.selection.orm.InfoExperiencia;
import jaio.selection.orm.ProcesoSeleccion;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jaio.selection.util.Utilitarios;
import java.io.Serializable;
import org.hibernate.Query;

public class ReclutamientoDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ReclutamientoDAO.class);

//    public List<ProcesoSeleccion> obtenerProcesosPorEmpresa
    
    
    public List obtenerTipoDocumento(){
        try {
            iniciaSession();
            Query query = session.createSQLQuery("select * from elemento where definicion_id=14 order by 1 asc");
            return query.list();
        } catch (Exception e) {
            manejaException(log, e);
        }finally{
            cerrarSession();
        }
        return null;
    }
    
    
    public void grabarInfoCandidato(Candidato candidato, List<InfoAcademica> listaAcademica,
            List<InfoConocimiento> listaConocimiento, List<InfoExperiencia> listaExperiencia) {
        try {
            iniciaSession();
            
            session.save(candidato);

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
