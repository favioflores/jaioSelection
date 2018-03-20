package jaio.selection.view;

import jaio.selection.bean.AcademicaBean;
import jaio.selection.bean.ConocimientoBean;
import jaio.selection.bean.ElementoBean;
import jaio.selection.bean.ExperienciaBean;
import jaio.selection.dao.ReclutamientoDAO;
import jaio.selection.orm.Candidato;
import jaio.selection.orm.InfoAcademica;
import jaio.selection.orm.InfoConocimiento;
import jaio.selection.orm.InfoExperiencia;
import jaio.selection.orm.ProcesoSeleccion;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "registrarReclutamientoView")
@ViewScoped
public class RegistrarReclutamientoView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(RegistrarReclutamientoView.class);
    private static final long serialVersionUID = -1L;

    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    private String idProcesoSelecccion;
    private String idReclutamiento;

    //Info Candidato
    private String nombreCandidato;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nroDocumento;
    private List<ElementoBean> listaTipoDocumento = new ArrayList<>();
    private String documentoSeleccionado;
    private String celular;
    private String telefono;
    private String direccion;
    private String fechaNacimiento;
    private String departamento;
    private String distrito;
    private String correo;

    //Info Academico
    private List<AcademicaBean> listAcademica = new ArrayList<>();
    private String nombreAcademico;
    private String especialidad;
    private String grado;
    private String fechaInicioAcademico;
    private String fechaFinAcademico;
    private String logro;

    //Info Conocimiento
    private List<ConocimientoBean> listConocimiento = new ArrayList<>();
    private String nombreConocimiento;
    private String nivelConocimiento;

    //Info Experiencia
    private List<ExperienciaBean> listExperiencia = new ArrayList<>();
    private String empresa;
    private String fechaInicioExperiencia;
    private String fechaFinExperiencia;
    private String logroExperiencia;
    private String cargo;

    @PostConstruct
    public void init() {

        poblarTipoDocumento();

        if (Utilitarios.noEsNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_ID_PROCESO))) {
            idProcesoSelecccion = (String) Utilitarios.obtenerSession(Constantes.SESSION_ID_PROCESO);
        }
        
        if (Utilitarios.noEsNuloOVacio(Utilitarios.obtenerSession(Constantes.SESSION_ID_RECLUTAMIENTO))) {
            idReclutamiento = (String) Utilitarios.obtenerSession(Constantes.SESSION_ID_RECLUTAMIENTO);
            cargaInformacionAcademica();
        } else {
           limpiarInfoCandidato();
        }
        
        limpiarInfoAcademica();
        limpiarInfoConocimiento();
        limpiarInfoExperiencia();

    }

    public void limpiarInfoCandidato() {
        nombreCandidato = null;
        apellidoPaterno = null;
        apellidoMaterno = null;
        nroDocumento = null;
        documentoSeleccionado = null;
        celular = null;
        telefono = null;
        direccion = null;
        fechaNacimiento = formato.format(new Date());
        departamento = null;
        distrito = null;
        correo = null;

    }

    public void cargaInformacionAcademica() {
        try {
            ReclutamientoDAO objReclutamientoDAO = new ReclutamientoDAO();
            Candidato objCandidatoDao = objReclutamientoDAO.cargarCandidato(idReclutamiento);
            nombreCandidato = objCandidatoDao.getNombre();
            apellidoPaterno = objCandidatoDao.getApellidoParterno();
            apellidoMaterno = objCandidatoDao.getApellidoMaterno();
            nroDocumento = objCandidatoDao.getNroDocumento();
            documentoSeleccionado = Integer.toString(objCandidatoDao.getTipoDocumento());
            celular = objCandidatoDao.getMovil();
            telefono = objCandidatoDao.getTelefono();
            direccion = objCandidatoDao.getDireccion();
            fechaNacimiento = formato.format(objCandidatoDao.getFechaNacimiento());
            distrito = Integer.toString(objCandidatoDao.getDistrito());
            correo = objCandidatoDao.getCorreo();

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void agregarInfoAcademica() {
        try {
            AcademicaBean objAcademica = new AcademicaBean();
            objAcademica.setNombre(nombreAcademico);
            objAcademica.setEspecialidad(especialidad);
            objAcademica.setGrado(grado);
            objAcademica.setFechaInicio(fechaInicioAcademico);
            objAcademica.setFechaFin(fechaFinAcademico);
            objAcademica.setLogro(logro);
            listAcademica.add(objAcademica);
            limpiarInfoAcademica();
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void quitarInfoAcademica(AcademicaBean objAcademica) {
        try {
            listAcademica.remove(objAcademica);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void limpiarInfoAcademica() {
        nombreAcademico = null;
        especialidad = null;
        grado = null;
        fechaInicioAcademico = formato.format(new Date());
        fechaFinAcademico = formato.format(new Date());
        logro = null;
    }

    public void agregarInfoConocimiento() {
        try {
            ConocimientoBean objConocimiento = new ConocimientoBean();
            objConocimiento.setNombre(nombreConocimiento);
            objConocimiento.setNivel(nivelConocimiento);
            listConocimiento.add(objConocimiento);
            limpiarInfoConocimiento();
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void quitarInfoConocimiento(ConocimientoBean objConocimiento) {
        try {
            listConocimiento.remove(objConocimiento);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void limpiarInfoConocimiento() {
        nombreConocimiento = null;
        nivelConocimiento = null;
    }

    public void agregarInfoExperiencia() {
        try {
            ExperienciaBean objExperiencia = new ExperienciaBean();
            objExperiencia.setEmpresa(empresa);
            objExperiencia.setFechaInicio(fechaInicioExperiencia);
            objExperiencia.setFechaFin(fechaFinExperiencia);
            objExperiencia.setCargo(cargo);
            objExperiencia.setLogro(logroExperiencia);
            listExperiencia.add(objExperiencia);
            limpiarInfoExperiencia();
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void quitarInfoExperiencia(ExperienciaBean objExperienciaBean) {
        try {
            listExperiencia.remove(objExperienciaBean);
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void limpiarInfoExperiencia() {
        empresa = null;
        fechaInicioExperiencia = formato.format(new Date());
        fechaFinExperiencia = formato.format(new Date());
        logroExperiencia = null;
        cargo = null;
    }

    public void poblarTipoDocumento() {
        try {
            listaTipoDocumento = new ArrayList<>();
            ReclutamientoDAO objReclutamientoDAO = new ReclutamientoDAO();
            for (Object o : objReclutamientoDAO.obtenerTipoDocumento()) {
                Object obj[] = (Object[]) o;
                ElementoBean el = new ElementoBean();
                el.setId(obj[0].toString());
                el.setDescripcion(obj[1].toString());
                listaTipoDocumento.add(el);
            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public void grabarInfoCandidato() {

        try {

            ProcesoSeleccion objProcesoSeleccion = new ProcesoSeleccion();
            if (Utilitarios.noEsNuloOVacio(idProcesoSelecccion)) {
                objProcesoSeleccion.setId(Integer.parseInt(idProcesoSelecccion));
            }

            Candidato objCandidato = new Candidato();
            objCandidato.setProcesoSeleccion(objProcesoSeleccion);
            objCandidato.setNombre(nombreCandidato);
            objCandidato.setApellidoParterno(apellidoPaterno);
            objCandidato.setApellidoMaterno(apellidoMaterno);
            objCandidato.setNroDocumento(nroDocumento);
            objCandidato.setTipoDocumento(Integer.parseInt(documentoSeleccionado));
            objCandidato.setMovil(celular);
            objCandidato.setTelefono(telefono);
            objCandidato.setDireccion(direccion);
            objCandidato.setFechaNacimiento(formato.parse(fechaNacimiento));
//            objCandidato.setDepartamento(departamento);
            objCandidato.setDistrito(1);
            objCandidato.setCorreo(correo);

            List<InfoAcademica> listaAcademica = new ArrayList<>();
            for (AcademicaBean academicaBean : listAcademica) {
                InfoAcademica academica = new InfoAcademica();
                academica.setNombre(academicaBean.getNombre());
                academica.setEspecialidad(academicaBean.getEspecialidad());
                academica.setGrado(academicaBean.getGrado());
                academica.setFechaInicio(formato.parse(academicaBean.getFechaInicio()));
                academica.setFechaFin(formato.parse(academicaBean.getFechaFin()));
                academica.setLogro(academicaBean.getLogro());
                listaAcademica.add(academica);
            }

            List<InfoConocimiento> listaConocimiento = new ArrayList<>();
            for (ConocimientoBean conocimientoBean : listConocimiento) {
                InfoConocimiento conocimiento = new InfoConocimiento();
                conocimiento.setNombre(conocimientoBean.getNombre());
                conocimiento.setNivel(conocimientoBean.getNivel());
                listaConocimiento.add(conocimiento);
            }

            List<InfoExperiencia> listaExperiencia = new ArrayList<>();
            for (ExperienciaBean experienciaBean : listExperiencia) {
                InfoExperiencia experiencia = new InfoExperiencia();
                experiencia.setEmpresa(experienciaBean.getEmpresa());
                experiencia.setFechaInicio(formato.parse(experienciaBean.getFechaInicio()));
                experiencia.setFechaFin(formato.parse(experienciaBean.getFechaFin()));
                experiencia.setCargo(experienciaBean.getCargo());
                experiencia.setLogro(experienciaBean.getLogro().getBytes());
                listaExperiencia.add(experiencia);
            }

            ReclutamientoDAO objReclutamientoDAO = new ReclutamientoDAO();
            objReclutamientoDAO.grabarInfoCandidato(objCandidato, listaAcademica, listaConocimiento, listaExperiencia);

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    public String getNombreCandidato() {
        return nombreCandidato;
    }

    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public List<ElementoBean> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<ElementoBean> tipoDocumento) {
        this.listaTipoDocumento = tipoDocumento;
    }

    public String getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionado(String documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreAcademico() {
        return nombreAcademico;
    }

    public void setNombreAcademico(String nombreAcademico) {
        this.nombreAcademico = nombreAcademico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getFechaInicioAcademico() {
        return fechaInicioAcademico;
    }

    public void setFechaInicioAcademico(String fechaInicioAcademico) {
        this.fechaInicioAcademico = fechaInicioAcademico;
    }

    public String getFechaFinAcademico() {
        return fechaFinAcademico;
    }

    public void setFechaFinAcademico(String fechaFinAcademico) {
        this.fechaFinAcademico = fechaFinAcademico;
    }

    public String getLogro() {
        return logro;
    }

    public void setLogro(String logro) {
        this.logro = logro;
    }

    public List<AcademicaBean> getListAcademica() {
        return listAcademica;
    }

    public void setListAcademica(List<AcademicaBean> listAcademica) {
        this.listAcademica = listAcademica;
    }

    public List<ConocimientoBean> getListConocimiento() {
        return listConocimiento;
    }

    public void setListConocimiento(List<ConocimientoBean> listConocimiento) {
        this.listConocimiento = listConocimiento;
    }

    public String getNombreConocimiento() {
        return nombreConocimiento;
    }

    public void setNombreConocimiento(String nombreConocimiento) {
        this.nombreConocimiento = nombreConocimiento;
    }

    public String getNivelConocimiento() {
        return nivelConocimiento;
    }

    public void setNivelConocimiento(String nivelConocimiento) {
        this.nivelConocimiento = nivelConocimiento;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFechaInicioExperiencia() {
        return fechaInicioExperiencia;
    }

    public void setFechaInicioExperiencia(String fechaInicioExperiencia) {
        this.fechaInicioExperiencia = fechaInicioExperiencia;
    }

    public String getFechaFinExperiencia() {
        return fechaFinExperiencia;
    }

    public void setFechaFinExperiencia(String fechaFinExperiencia) {
        this.fechaFinExperiencia = fechaFinExperiencia;
    }

    public String getLogroExperiencia() {
        return logroExperiencia;
    }

    public void setLogroExperiencia(String logroExperiencia) {
        this.logroExperiencia = logroExperiencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<ExperienciaBean> getListExperiencia() {
        return listExperiencia;
    }

    public void setListExperiencia(List<ExperienciaBean> listExperiencia) {
        this.listExperiencia = listExperiencia;
    }

}
