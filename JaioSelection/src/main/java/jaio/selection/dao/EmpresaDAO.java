package jaio.selection.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.orm.Empresa;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;

public class EmpresaDAO extends HibernateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(EmpresaDAO.class);

    public void grabar(Empresa empresa) {

        iniciaSession();

        try {

            session.save(empresa);

            guardarCambios();

            log.debug("Grago correctamente");

        } catch (Exception e) {
            rollback(e);
        } finally {
            cerrarSession();
        }
    }

    public List<Empresa> obtenerEmpresas() {

        iniciaSession();
        try {

            Query query = session.createQuery("From Empresa e where e.usuario.id = ? and e.estado = ? order by e.orden desc, e.nombre ");

            query.setInteger(0, Utilitarios.obtenerUsuarioSession().getIntUsuarioPk());
            query.setInteger(1, Constantes.EL_EMPRESA_ESTADO_REGISTRADO);

            return query.list();

        } catch (Exception e) {
            manejaException(e);
        } finally {
            cerrarSession();
        }

        return new ArrayList<Empresa>();
    }

    public List<Empresa> obtenerEmpresasOrdenNombre() {

        iniciaSession();
        try {

            Query query = session.createQuery("From Empresa e where e.usuario.id = ? and e.estado = ? order by e.orden desc, e.nombre ");

            query.setInteger(0, Utilitarios.obtenerUsuarioSession().getIntUsuarioPk());
            query.setInteger(1, Constantes.EL_EMPRESA_ESTADO_REGISTRADO);

            return query.list();

        } catch (Exception e) {
            manejaException(e);
        } finally {
            cerrarSession();
        }

        return new ArrayList<Empresa>();
    }

    public Empresa obtenerEmpresa(String id) {

        iniciaSession();
        try {

            Query query = session.createQuery("From Empresa e where e.id = ? ");

            query.setString(0, id);

            Empresa empresa = (Empresa) query.uniqueResult();

            return empresa;

        } catch (Exception e) {
            manejaException(e);
        } finally {
            cerrarSession();
        }

        return null;
    }

    public boolean eliminaEmpresa(String id) {

        iniciaSession();

        try {

            log.debug("Inicia con el borrado de la empresa");

            Query update = session.createSQLQuery(
                    " update empresa set estado = :estado where id = :id ");

            update.setString("id", id);
            update.setInteger("estado", Constantes.EL_EMPRESA_ESTADO_ELIMINADO);

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

    public boolean actualizaPrioridad(String id, Integer prioridad) {

        iniciaSession();

        try {

            log.debug("Inicia actualizaPrioridad");

            Query update = session.createSQLQuery(
                    " update empresa set orden = :orden where id = :id ");

            update.setParameter("orden", prioridad);
            update.setString("id", id);

            update.executeUpdate();

            guardarCambios();

            log.debug("Fin actualizaPrioridad");

            return true;

        } catch (RuntimeException re) {
            rollback(re);
        } finally {
            cerrarSession();
        }

        return false;

    }

}
