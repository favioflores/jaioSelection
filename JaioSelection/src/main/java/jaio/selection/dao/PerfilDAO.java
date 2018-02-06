package jaio.selection.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.orm.Perfil;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

public class PerfilDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(PerfilDAO.class);

    public List<Perfil> obtenerPerfiles(Integer estado) {

        List<Perfil> lstPerfiles = new ArrayList<Perfil>();

        iniciaSession();

        try {

            Query query = session.createQuery("select p, a FROM Perfil p join p.area a where p.estado = ? and p.empresa.id = ? ");

            query.setInteger(0, estado);
            query.setString(1, (String) Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA));

            lstPerfiles = query.list();

        } catch (Exception e) {
            log.error(e);
            manejaException(e);
        } finally {
            cerrarSession();
        }

        return lstPerfiles;
    }

    public List<Perfil> obtenerPerfilesXEmpresa(String id) {

        List<Perfil> lstPerfiles = new ArrayList<>();

        iniciaSession();

        try {

            Query query = session.createQuery("select p FROM Perfil p where p.empresa.id = :id order by p.nombre asc ");

            query.setString("id", id);

            lstPerfiles = query.list();

        } catch (Exception e) {
            log.error(e);
            manejaException(e);
        } finally {
            cerrarSession();
        }

        return lstPerfiles;
    }

    public List<Perfil> obtenerPerfilesXArea(String idArea) {

        List<Perfil> lstPerfiles = new ArrayList<>();

        iniciaSession();

        try {

            Query query = session.createQuery("select p FROM Perfil p where p.area.id = :id order by p.nombre asc ");

            query.setString("id", idArea);

            lstPerfiles = query.list();

        } catch (Exception e) {
            log.error(e);
            manejaException(e);
        } finally {
            cerrarSession();
        }

        return lstPerfiles;
    }

    public Integer grabar(Perfil perfil) {

        iniciaSession();

        try {

            Integer id = (Integer) session.save(perfil);

            guardarCambios();

            log.debug("Grago correctamente");

            return id;

        } catch (Exception e) {
            rollback(e);
        } finally {
            cerrarSession();
        }

        return null;
    }

    public boolean eliminaPerfil(String id) {

        iniciaSession();

        try {

            log.debug("Inicia con el borrado del área y la actualización de sus herarquias");

            String idEmpresa = Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA).toString();

            Query update = session.createSQLQuery(
                    " update perfil set estado = :estado where id = :id and empresa_id = :empresa ");

            update.setString("empresa", idEmpresa);
            update.setString("id", id);
            update.setInteger("estado", Constantes.EL_PERFIL_ESTADO_ELIMINADO);

            update.executeUpdate();

            guardarCambios();

            return true;

        } catch (RuntimeException re) {
            rollback(re);
        } finally {
            cerrarSession();
        }

        return false;

    }

    public boolean moverPerfil(String idPerfil, String idParentArea) {

        iniciaSession();

        try {

            log.debug("Inicia con la movida del perfil");

            String idEmpresa = Utilitarios.obtenerSession(Constantes.SESSION_EMPRESA).toString();

            Query update = session.createSQLQuery(
                    " update perfil set area_id = :parent where id = :current and estado = :estado and empresa_id = :empresa ");

            update.setString("parent", idParentArea);
            update.setString("current", idPerfil);
            update.setString("empresa", idEmpresa);
            update.setInteger("estado", Constantes.EL_PERFIL_ESTADO_REGISTRADO);

            update.executeUpdate();

            guardarCambios();

            return true;

        } catch (RuntimeException re) {
            rollback(re);
        } finally {
            cerrarSession();
        }

        return false;

    }
    public Perfil obtenerPerfil(String id) {
        iniciaSession();
        try {
            Query query = session.createQuery("From Perfil p where p.id = ? ");
            query.setString(0, id);
            Perfil perfil = (Perfil) query.uniqueResult();
            return perfil;
        } catch (Exception e) {
            manejaException(e);
        } finally {
            cerrarSession();
        }
        return null;
    }

}
