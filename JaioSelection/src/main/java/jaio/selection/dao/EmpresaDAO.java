package jaio.selection.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import jaio.selection.orm.Empresa;
import jaio.selection.util.Utilitarios;

public class EmpresaDAO extends HibernateUtil implements Serializable{

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
        }finally {
        	cerrarSession();
		}
    }


    public List<Empresa> obtenerEmpresas(){

    	iniciaSession();
    	try{

    		Query query = session.createQuery("From Empresa e where e.usuario.id = ? ");

    		query.setParameter(0, Utilitarios.obtenerUsuarioSession().getIntUsuarioPk());

    		return query.list();

    	}catch (Exception e) {
			manejaException(e);
		}finally{
			cerrarSession();
		}

    	return new ArrayList<Empresa>();
    }

    public Empresa obtenerEmpresa(String id){

    	iniciaSession();
    	try{

    		Query query = session.createQuery("From Empresa e where e.id = ? ");

    		query.setString(0, id);

    		Empresa empresa = (Empresa) query.uniqueResult();

    		return empresa;

    	}catch (Exception e) {
			manejaException(e);
		}finally{
			cerrarSession();
		}

    	return null;
    }

}

