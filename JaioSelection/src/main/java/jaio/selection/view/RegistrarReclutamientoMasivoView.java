package jaio.selection.view;

import jaio.selection.bean.CandidatoBean;
import jaio.selection.bean.ContInfoCandidatoListBean;
import jaio.selection.bean.ErrorExcelBean;
import jaio.selection.util.Constantes;
import jaio.selection.util.Utilitarios;
import static jaio.selection.view.BaseView.ERROR;
import static jaio.selection.view.BaseView.FATAL;
import static jaio.selection.view.BaseView.INFO;
import static jaio.selection.view.BaseView.WARN;
import static jaio.selection.view.BaseView.msg;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "registrarReclutamientoMasivoView")
@ViewScoped
public class RegistrarReclutamientoMasivoView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(RegistrarReclutamientoMasivoView.class);
    private static final long serialVersionUID = -1L;

    private List<ErrorExcelBean> lstErrores;

    private StreamedContent fileImport;
    private StreamedContent fileExport;
    private UploadedFile inputFile;

    public void limpiar() {
        inputFile = null;
        fileImport = null;
        lstErrores = new ArrayList<>();

    }

    public StreamedContent getFileExport() {
        downloadFile();
        return fileExport;
    }

    //descarga de archivo
    public void setFileExport(StreamedContent fileExport) {
        this.fileExport = fileExport;
    }

    public void downloadFile() {
        try {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/FormatoDeReclutamientoMasivo.xlsx");
            fileExport = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "FormatoDeReclutamientoMasivo.xlsx");
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
    }

    //carga de archivo
    public void cargaRegistroMasivo(FileUploadEvent event) {

        List<ErrorExcelBean> lstErrorExcelBeans = new ArrayList<>();

        if (event.getFile() == null) {
            mostrarAlerta(ERROR, "organigrama.masivo.archivo.vacio", null, null);
        } else {

            XSSFWorkbook xlsxMasivo;

            try {
                xlsxMasivo = new XSSFWorkbook(event.getFile().getInputstream());

                //validar cabecera
                validarCabeceraMasivo(xlsxMasivo, lstErrorExcelBeans);

                //validar contenido
                if (lstErrorExcelBeans.isEmpty()) {
                    validarReclutamientoMasivo(xlsxMasivo, lstErrorExcelBeans);
                } else {
                    lstErrores = lstErrorExcelBeans;
                    mostrarAlerta(WARN, "organigrama.masivo.archivo.procesoConErrores", null, null);
                    inputFile = null;
                    fileImport = null;
                    return;
                }

                //procesar registros
                if (lstErrorExcelBeans.isEmpty()) {
                    procesarReclutamientoMasivo(xlsxMasivo, lstErrorExcelBeans);
                } else {
                    lstErrores = lstErrorExcelBeans;
                    mostrarAlerta(WARN, "organigrama.masivo.archivo.procesoConErrores", null, null);
                    inputFile = null;
                    fileImport = null;
                    return;
                }

                if (!lstErrorExcelBeans.isEmpty()) {
                    lstErrores = lstErrorExcelBeans;
                    mostrarAlerta(WARN, "organigrama.masivo.archivo.procesoConErrores", null, null);
                    inputFile = null;
                    fileImport = null;
                    return;
                }

                inputFile = null;
                fileImport = null;

            } catch (Exception ex) {
                mostrarAlerta(FATAL, "error.inesperado", log, ex);
            }

        }

    }

    private List<ErrorExcelBean> validarCabeceraMasivo(XSSFWorkbook xlsMasivo, List<ErrorExcelBean> lstErrores) {

        try {

            XSSFSheet hoja = xlsMasivo.getSheetAt(0);
            Iterator<Row> filas = hoja.iterator();

            filas.next();

            Row row = filas.next();

            //informacion de candidato
            String strInfoCandidato = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_INFO_CANDIDATO);
            String strNombres = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRES);
            String strApellidoPaterno = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_PATERNO);
            String strApellidoMaterno = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_MATERNO);
            String strTipoDocumento = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TIPO_DOCUMENTO);
            String strNroDocumento = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NRO_DOCUMENTO);
            String strFechaNacimiento = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_NACIMIENTO);
            String strDepartamento = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DEPARTAMENTO);
            String strDistrito = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DISTRITO);
            String strDireccion = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DIRECCION);
            String strCorreo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CORREO);
            String strCelular = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CELULAR);
            String strTelefono = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TELEFONO);

            Utilitarios.validaValorCeldaXLSX(strInfoCandidato, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.infoCandidato"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_INFO_CANDIDATO, true, Constantes.XLSX_RECL_MASIVO_CAB_INFO_CANDIDATO);
            Utilitarios.validaValorCeldaXLSX(strNombres, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombres"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRES, true, Constantes.XLSX_RECL_MASIVO_CAB_NOMBRES);
            Utilitarios.validaValorCeldaXLSX(strApellidoPaterno, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.apellidoPaterno"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_APELLIDO_PATERNO, true, Constantes.XLSX_RECL_MASIVO_CAB_APELLIDO_PATERNO);
            Utilitarios.validaValorCeldaXLSX(strApellidoMaterno, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.apellidoMaterno"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_APELLIDO_MATERNO, true, Constantes.XLSX_RECL_MASIVO_CAB_APELLIDO_MATERNO);
            Utilitarios.validaValorCeldaXLSX(strTipoDocumento, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.tipoDeDocumento"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_TIPO_DOCUMENTO, true, Constantes.XLSX_RECL_MASIVO_CAB_TIPO_DOCUMENTO);
            Utilitarios.validaValorCeldaXLSX(strNroDocumento, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nroDeDocumento"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_NRO_DOCUMENTO, true, Constantes.XLSX_RECL_MASIVO_CAB_NRO_DOCUMENTO);
            Utilitarios.validaValorCeldaXLSX(strFechaNacimiento, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaDeNacimiento"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_NACIMIENTO, true, Constantes.XLSX_RECL_MASIVO_CAB_FECHA_NACIMIENTO);
            Utilitarios.validaValorCeldaXLSX(strDepartamento, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.departamento"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_DEPARTAMENTO, true, Constantes.XLSX_RECL_MASIVO_CAB_DEPARTAMENTO);
            Utilitarios.validaValorCeldaXLSX(strDistrito, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.distrito"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_DISTRITO, true, Constantes.XLSX_RECL_MASIVO_CAB_DISTRITO);
            Utilitarios.validaValorCeldaXLSX(strDireccion, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.direccion"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_DIRECCION, true, Constantes.XLSX_RECL_MASIVO_CAB_DIRECCION);
            Utilitarios.validaValorCeldaXLSX(strCorreo, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.correo"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_CORREO, true, Constantes.XLSX_RECL_MASIVO_CAB_CORREO);
            Utilitarios.validaValorCeldaXLSX(strCelular, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.celular"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_CELULAR, true, Constantes.XLSX_RECL_MASIVO_CAB_CELULAR);
            Utilitarios.validaValorCeldaXLSX(strTelefono, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.telefono"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_TELEFONO, true, Constantes.XLSX_RECL_MASIVO_CAB_TELEFONO);

            //informacion academica
            row = filas.next();
            String strInfoAcademica = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_INFO_ACADEMICA);
            String strNombreAcademico = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_ACADEMICO);
            String strEspecialidad = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_ESPECIALIDAD);
            String strGrado = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_GRADO);
            String strFechaInicio = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_INICIO);
            String strFechaFin = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_FIN);
            String strLogro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_LOGRO);

            Utilitarios.validaValorCeldaXLSX(strInfoAcademica, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.infoAcademica"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_INFO_ACADEMICA, true, Constantes.XLSX_RECL_MASIVO_CAB_INFO_ACADEMICA);
            Utilitarios.validaValorCeldaXLSX(strNombreAcademico, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombreAcademico"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRE_ACADEMICO, true, Constantes.XLSX_RECL_MASIVO_CAB_NOMBRE_ACADEMICO);
            Utilitarios.validaValorCeldaXLSX(strEspecialidad, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.especialidad"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_ESPECIALIDAD, true, Constantes.XLSX_RECL_MASIVO_CAB_ESPECIALIDAD);
            Utilitarios.validaValorCeldaXLSX(strGrado, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.grado"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_GRADO, true, Constantes.XLSX_RECL_MASIVO_CAB_GRADO);
            Utilitarios.validaValorCeldaXLSX(strFechaInicio, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaInicio"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_INICIO, true, Constantes.XLSX_RECL_MASIVO_CAB_FECHA_INICIO);
            Utilitarios.validaValorCeldaXLSX(strFechaFin, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaFin"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_FIN, true, Constantes.XLSX_RECL_MASIVO_CAB_FECHA_FIN);
            Utilitarios.validaValorCeldaXLSX(strLogro, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.logro"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_LOGRO, true, Constantes.XLSX_RECL_MASIVO_CAB_LOGRO);

            //informacion de conocimientos
            row = filas.next();
            String strInfoConocimientos = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_INFO_CONOCIMIENTOS);
            String strConocimiento = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CONOCIMIENTO);
            String strNivel = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NIVEL);

            Utilitarios.validaValorCeldaXLSX(strInfoConocimientos, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.infoConocimientos"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_INFO_CONOCIMIENTOS, true, Constantes.XLSX_RECL_MASIVO_CAB_INFO_CONOCIMIENTOS);
            Utilitarios.validaValorCeldaXLSX(strConocimiento, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.conocimiento"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_CONOCIMIENTO, true, Constantes.XLSX_RECL_MASIVO_CAB_CONOCIMIENTO);
            Utilitarios.validaValorCeldaXLSX(strNivel, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nivel"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_NIVEL, true, Constantes.XLSX_RECL_MASIVO_CAB_NIVEL);

            //informacion de experiencias
            row = filas.next();
            String strInfoExperiencias = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_INFO_EXPERIENCIAS);
            String strNombreEmpresa = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_EMPRESA);
            String strCargoEmpresa = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CARGO);
            String strLogroEmpresa = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_LOGRO_EXPERIENCIA);
            String strFechaInicioEmpresa = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_INICIO);
            String strFechaFinEmpresa = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_FIN);

            Utilitarios.validaValorCeldaXLSX(strInfoExperiencias, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.infoExperiencias"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_INFO_EXPERIENCIAS, true, Constantes.XLSX_RECL_MASIVO_CAB_INFO_EXPERIENCIAS);
            Utilitarios.validaValorCeldaXLSX(strNombreEmpresa, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombreEmpresa"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRE_EMPRESA, true, Constantes.XLSX_RECL_MASIVO_CAB_NOMBRE_DE_EMPRESA);
            Utilitarios.validaValorCeldaXLSX(strCargoEmpresa, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.cargo"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_CARGO, true, Constantes.XLSX_RECL_MASIVO_CAB_CARGO);
            Utilitarios.validaValorCeldaXLSX(strLogroEmpresa, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.logro"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_LOGRO_EXPERIENCIA, true, Constantes.XLSX_RECL_MASIVO_CAB_LOGRO);
            Utilitarios.validaValorCeldaXLSX(strFechaInicioEmpresa, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaInicio"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_INICIO, true, Constantes.XLSX_RECL_MASIVO_CAB_FECHA_INICIO);
            Utilitarios.validaValorCeldaXLSX(strFechaFinEmpresa, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaFin"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_FIN, true, Constantes.XLSX_RECL_MASIVO_CAB_FECHA_FIN);

            //informacion de referencias
            row = filas.next();
            String strInfoReferencias = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_INFO_REFERENCIAS);
            String strNombreReferido = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_REFERIDO);
            String strCargoReferido = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CARGO);
            String strCelularReferido = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CELULAR_REFERENCIA);
            String strTelefonoReferido = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TELEFONO_REFERENCIA);

            Utilitarios.validaValorCeldaXLSX(strInfoReferencias, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.infoReferencias"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_INFO_REFERENCIAS, true, Constantes.XLSX_RECL_MASIVO_CAB_INFO_REFERENCIAS);
            Utilitarios.validaValorCeldaXLSX(strNombreReferido, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombreDeReferido"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRE_REFERIDO, true, Constantes.XLSX_RECL_MASIVO_CAB_NOMBRE_DE_REFERIDO);
            Utilitarios.validaValorCeldaXLSX(strCargoReferido, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.cargo"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_CARGO, true, Constantes.XLSX_RECL_MASIVO_CAB_CARGO);
            Utilitarios.validaValorCeldaXLSX(strCelularReferido, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.celular"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_CELULAR_REFERENCIA, true, Constantes.XLSX_RECL_MASIVO_CAB_CELULAR);
            Utilitarios.validaValorCeldaXLSX(strTelefonoReferido, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.telefono"),
                    lstErrores, row, Constantes.XLSX_RECL_COL_TELEFONO_REFERENCIA, true, Constantes.XLSX_RECL_MASIVO_CAB_TELEFONO);

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);
        }

        return lstErrores;
    }

    private List<ErrorExcelBean> validarReclutamientoMasivo(XSSFWorkbook xlsMasivo, List<ErrorExcelBean> lstErrores) {
        try {

            XSSFSheet hoja = xlsMasivo.getSheetAt(0);
            Iterator<Row> filas = hoja.iterator();
            filas.next();
            filas.next();
            filas.next();
            filas.next();
            filas.next();
            filas.next();

            while (filas.hasNext()) {
                Row row = filas.next();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                String strTipoInfo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TIPO_INFORMACION);
                if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_CANDIDATO)) {

                    String nombres = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRES);
                    String apePater = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_PATERNO);
                    String apeMater = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_MATERNO);
                    String tipoDoc = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TIPO_DOCUMENTO);
                    Integer nroDoc = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NRO_DOCUMENTO));
                    String fechaNac = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_NACIMIENTO);
                    String depart = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DEPARTAMENTO);
                    String distr = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DISTRITO);
                    String dir = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DIRECCION);
                    String email = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CORREO);
                    Integer cel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CELULAR));
                    Integer tel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TELEFONO));

                    Utilitarios.validaValorCeldaXLSX(nombres, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombres"), lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRES, true);
                    Utilitarios.validaValorCeldaXLSX(apePater, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.apellidoPaterno"), lstErrores, row, Constantes.XLSX_RECL_COL_APELLIDO_PATERNO, true);
                    Utilitarios.validaValorCeldaXLSX(apeMater, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.apellidoMaterno"), lstErrores, row, Constantes.XLSX_RECL_COL_APELLIDO_MATERNO, true);
                    Utilitarios.validaValorCeldaXLSX(tipoDoc, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.tipoDeDocumento"), lstErrores, row, Constantes.XLSX_RECL_COL_TIPO_DOCUMENTO, true);
                    Utilitarios.validaValorCeldaXLSX(nroDoc, Constantes.TIPO_INTEGER, msg("reclutamiento.masivo.columna.nroDeDocumento"), lstErrores, row, Constantes.XLSX_RECL_COL_NRO_DOCUMENTO, true);
                    Utilitarios.validaValorCeldaXLSX(fechaNac, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaDeNacimiento"), lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_NACIMIENTO, true);
                    Utilitarios.validaValorCeldaXLSX(depart, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.departamento"), lstErrores, row, Constantes.XLSX_RECL_COL_DEPARTAMENTO, true);
                    Utilitarios.validaValorCeldaXLSX(distr, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.distrito"), lstErrores, row, Constantes.XLSX_RECL_COL_DISTRITO, true);
                    Utilitarios.validaValorCeldaXLSX(dir, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.direccion"), lstErrores, row, Constantes.XLSX_RECL_COL_DIRECCION, true);
                    Utilitarios.validaValorCeldaXLSX(email, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.correo"), lstErrores, row, Constantes.XLSX_RECL_COL_CORREO, true);
                    Utilitarios.validaValorCeldaXLSX(cel, Constantes.TIPO_INTEGER, msg("reclutamiento.masivo.columna.celular"), lstErrores, row, Constantes.XLSX_RECL_COL_CELULAR, true);
                    Utilitarios.validaValorCeldaXLSX(tel, Constantes.TIPO_INTEGER, msg("reclutamiento.masivo.columna.telefono"), lstErrores, row, Constantes.XLSX_RECL_COL_TELEFONO, true);

                } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_ACADEMICA)) {

                    String nomAcad = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_ACADEMICO);
                    String espec = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_ESPECIALIDAD);
                    String grado = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_GRADO);
                    String fechaIni = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_INICIO);
                    String fechaFin = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_FIN);
                    String logro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_LOGRO);

                    Utilitarios.validaValorCeldaXLSX(nomAcad, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombreAcademico"), lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRE_ACADEMICO, true);
                    Utilitarios.validaValorCeldaXLSX(espec, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.especialidad"), lstErrores, row, Constantes.XLSX_RECL_COL_ESPECIALIDAD, true);
                    Utilitarios.validaValorCeldaXLSX(grado, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.grado"), lstErrores, row, Constantes.XLSX_RECL_COL_GRADO, true);
                    Utilitarios.validaValorCeldaXLSX(fechaIni, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaInicio"), lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_INICIO, true);
                    Utilitarios.validaValorCeldaXLSX(fechaFin, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaFin"), lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_FIN, true);
                    Utilitarios.validaValorCeldaXLSX(logro, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.logro"), lstErrores, row, Constantes.XLSX_RECL_COL_LOGRO, true);

                } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_CONOCIMIENTOS)) {

                    String con = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CONOCIMIENTO);
                    String niv = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NIVEL);

                    Utilitarios.validaValorCeldaXLSX(con, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.conocimiento"), lstErrores, row, Constantes.XLSX_RECL_COL_CONOCIMIENTO, true);
                    Utilitarios.validaValorCeldaXLSX(niv, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nivel"), lstErrores, row, Constantes.XLSX_RECL_COL_NIVEL, true);

                } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_EXPERIENCIAS)) {

                    String nomEm = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_EMPRESA);
                    String cargo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CARGO);
                    String logro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_LOGRO_EXPERIENCIA);
                    String fechaIni = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_INICIO);
                    String fechaFin = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_FIN);

                    Utilitarios.validaValorCeldaXLSX(nomEm, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombreEmpresa"), lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRE_EMPRESA, true);
                    Utilitarios.validaValorCeldaXLSX(cargo, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.cargo"), lstErrores, row, Constantes.XLSX_RECL_COL_CARGO, true);
                    Utilitarios.validaValorCeldaXLSX(logro, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.logro"), lstErrores, row, Constantes.XLSX_RECL_COL_LOGRO_EXPERIENCIA, true);
                    Utilitarios.validaValorCeldaXLSX(fechaIni, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaInicio"), lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_INICIO, true);
                    Utilitarios.validaValorCeldaXLSX(fechaFin, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.fechaFin"), lstErrores, row, Constantes.XLSX_RECL_COL_FECHA_FIN, true);

                } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_REFERENCIAS)) {

                    String nombRef = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_REFERIDO);
                    String cargo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CARGO);
                    Integer cel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CELULAR_REFERENCIA));
                    Integer tel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TELEFONO_REFERENCIA));

                    Utilitarios.validaValorCeldaXLSX(nombRef, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.nombreDeReferido"), lstErrores, row, Constantes.XLSX_RECL_COL_NOMBRE_REFERIDO, true);
                    Utilitarios.validaValorCeldaXLSX(cargo, Constantes.TIPO_STRING, msg("reclutamiento.masivo.columna.cargo"), lstErrores, row, Constantes.XLSX_RECL_COL_CARGO, true);
                    Utilitarios.validaValorCeldaXLSX(cel, Constantes.TIPO_INTEGER, msg("reclutamiento.masivo.columna.celular"), lstErrores, row, Constantes.XLSX_RECL_COL_CELULAR_REFERENCIA, true);
                    Utilitarios.validaValorCeldaXLSX(tel, Constantes.TIPO_INTEGER, msg("reclutamiento.masivo.columna.telefono"), lstErrores, row, Constantes.XLSX_RECL_COL_TELEFONO_REFERENCIA, true);
                }

            }
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
        return lstErrores;
    }

    private List<ErrorExcelBean> procesarReclutamientoMasivo(XSSFWorkbook xlsMasivo, List<ErrorExcelBean> lstErrores) {
        try {

            XSSFSheet hoja = xlsMasivo.getSheetAt(0);
            Iterator<Row> filas = hoja.iterator();

            filas.next();
            filas.next();
            filas.next();
            filas.next();
            filas.next();

            Map<String, ContInfoCandidatoListBean> mpRegCandidatos = new HashMap();
            ContInfoCandidatoListBean objContenedorCand = new ContInfoCandidatoListBean();

            Integer idCandidato = 0;
            Integer idCandidatoAnt = null;

            while (filas.hasNext()) {

                Row row = filas.next();

                String strTipoInfo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TIPO_INFORMACION);

                if (Utilitarios.noEsNuloOVacio(strTipoInfo)) {
                    if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_CANDIDATO)) {
                        String nombres = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRES);
                        String apePater = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_PATERNO);
                        String apeMater = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_MATERNO);
                        String tipoDoc = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TIPO_DOCUMENTO);
                        Integer nroDoc = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NRO_DOCUMENTO));
                        String fechaNac = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_NACIMIENTO);
                        String depart = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DEPARTAMENTO);
                        String distr = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DISTRITO);
                        String dir = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DIRECCION);
                        String email = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CORREO);
                        Integer cel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CELULAR));
                        Integer tel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TELEFONO));

                    } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_ACADEMICA)) {
                        String nomAcad = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_ACADEMICO);
                        String espec = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_ESPECIALIDAD);
                        String grado = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_GRADO);
                        String fechaIni = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_INICIO);
                        String fechaFin = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_FIN);
                        String logro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_LOGRO);

                    } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_CONOCIMIENTOS)) {
                        String con = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CONOCIMIENTO);
                        String niv = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NIVEL);

                    } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_EXPERIENCIAS)) {
                        String nomEm = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_EMPRESA);
                        String cargo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CARGO);
                        String logro = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_LOGRO_EXPERIENCIA);
                        String fechaIni = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_INICIO);
                        String fechaFin = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_FIN);

                    } else if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_REFERENCIAS)) {
                        String nombRef = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRE_REFERIDO);
                        String cargo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CARGO);
                        Integer cel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CELULAR_REFERENCIA));
                        Integer tel = Integer.parseInt(Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TELEFONO_REFERENCIA));

                    }
                }

                ErrorExcelBean objErrorExcelBean = new ErrorExcelBean();
                objErrorExcelBean.setStrFila((row.getRowNum() + 1) + "");

            }

            if (lstErrores.isEmpty()) {
                // generaPreviewMasivo(xlsMasivo);
                mostrarAlerta(INFO, "organigrama.masivo.proceso.ok", null, null);
                mostrarAlerta(WARN, "organigrama.masivo.proceso.preliminar", null, null);
            }

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);
        }

        return lstErrores;

    }

    private boolean generaPreviewMasivo(XSSFWorkbook xlsMasivo) {
        try {

            boolean terminoOk = true;

            XSSFSheet hoja = xlsMasivo.getSheetAt(0);
            Iterator<Row> filas = hoja.iterator();

            filas.next();
            filas.next();
            filas.next();
            filas.next();
            filas.next();
            
            CandidatoBean objCandidatoBean = new CandidatoBean();
            
            while (filas.hasNext()) {
                Row row = filas.next();
                String strTipoInfo = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TIPO_INFORMACION);

                if (Utilitarios.noEsNuloOVacio(strTipoInfo)) {
                    if (strTipoInfo.equals(Constantes.XLSX_RECL_MASIVO_CAB_INFO_CANDIDATO)) {
                        String strNombres = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NOMBRES);
                        String strApePater = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_PATERNO);
                        String strApeMater = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_APELLIDO_MATERNO);
                        String strTipoDoc = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TIPO_DOCUMENTO);
                        String strNroDoc = (Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_NRO_DOCUMENTO));
                        String strFechaNac = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_FECHA_NACIMIENTO);
                        String strDepart = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DEPARTAMENTO);
                        String strDistr = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DISTRITO);
                        String strDir = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_DIRECCION);
                        String strEmail = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CORREO);
                        String strCel = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_CELULAR);
                        String strTel = Utilitarios.obtieneDatoCelda(row, Constantes.XLSX_RECL_COL_TELEFONO);
                        
                        objCandidatoBean.setNombre(strNombres);
                        objCandidatoBean.setApellidoParterno(strApePater);
                        objCandidatoBean.setApellidoMaterno(strApeMater);
                        objCandidatoBean.setTipoDocumento(strTipoDoc);
                        objCandidatoBean.setNroDocumento(strNroDoc);
                        objCandidatoBean.setFechaNacimiento(strFechaNac);
                        objCandidatoBean.setDistrito(strDistr);
                        objCandidatoBean.setDireccion(strDir);
                        objCandidatoBean.setCorreo(strEmail);
                        objCandidatoBean.setMovil(strCel);
                        objCandidatoBean.setTelefono(strTel);
                        
                        

                    }
                }
            }

        } catch (Exception ex) {
            mostrarAlerta(FATAL, "error.inesperado", log, ex);
        }

        return true;
    }

    public StreamedContent getFileImport() {
        return fileImport;
    }

    public void setFileImport(StreamedContent fileImport) {
        this.fileImport = fileImport;
    }

    public UploadedFile getInputFile() {
        return inputFile;
    }

    public void setInputFile(UploadedFile inputFile) {
        this.inputFile = inputFile;
    }

    public List<ErrorExcelBean> getLstErrores() {
        return lstErrores;
    }

    public void setLstErrores(List<ErrorExcelBean> lstErrores) {
        this.lstErrores = lstErrores;
    }

}
