package jaio.selection.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.orm.Usuario;

public class UsuarioDAO extends HibernateUtil implements Serializable {

    private static final Log log = LogFactory.getLog(UsuarioDAO.class);

    public Usuario obtenerUsuario(String correo, String contrasena) {

        Usuario usuario = null;

        iniciaSession();

        try {

            Query query = session.createSQLQuery("select id from usuario where binary correo = ? and binary contrasena = ? ");

            query.setString(0, correo);
            query.setString(1, contrasena);

            List lst = query.list();

            if (lst.isEmpty()) {
                return null;
            } else {
                Query quer1 = session.createQuery("From Usuario where id = ? ");
                quer1.setString(0, lst.get(0).toString());
                usuario = (Usuario) quer1.uniqueResult();
            }

        } catch (Exception e) {
            manejaException(log, e);
        } finally {
            cerrarSession();
        }

        return usuario;
    }

}
