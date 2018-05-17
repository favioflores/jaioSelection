package jaio.selection.util;

import jaio.selection.bean.BateriaPersonalizadaBean;
import java.io.File;
import java.nio.charset.Charset;
import java.text.DateFormat;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "constantes")
@ApplicationScoped
public class Constantes {

    public static char CH_RANDOM_Z = 'Z';
    public static char CH_RANDOM_A = 'A';
    public static String UNDERLINE_COMMENT = "______________________________________________________________________________________________________________________________________________________________________________________________";
    public final static String STR_POINT = ".";
    public final static int INT_PARAMETER_ZERO_POS = 0;
    public final static int INT_PARAMETER_ONE_POS = 1;
    public static String ZERO = "0";
    public static Integer ZERO_INTEGER = 0;
    public String strApp = "Constantes";

    public static byte[] TRUE_BYTE = new byte[]{(byte) 1};
    public static byte[] FALSE_BYTE = new byte[]{(byte) 0};

    public static String STR_CSS = ".css";
    public static String STR_JPG = ".jpg";
    public static String STR_GIF = ".gif";
    public static String STR_PNG = ".png";
    public static String STR_JS = ".js";
    public static String STR_HTM = ".htm";
    public static String STR_SWF = ".swf";

    public static Integer INT_LONGITUD_CLAVE_DEFECTO_ACTIVO = 10;
    public static Integer INT_LONGITUD_ARCHIVOS = 20;

    public static Integer INT_ESTADO_ELEMENTO_ACTIVO = 1;
    public static Integer INT_ET_SENDER_DOMINIO = 58;
    public static Integer INT_ET_SENDER_PUERTO_ENVIO = 59;
    public static Integer INT_ET_SENDER_PUERTO_RECEPCION = 60;
    public static Integer INT_ET_SENDER_USUARIO = 61;
    public static Integer INT_ET_SENDER_CONTRASEÑA = 62;

    public static Integer INT_ET_TIPO_USUARIO_ADMINISTRADOR = 3;
    public static Integer INT_ET_TIPO_USUARIO_USUARIO_MAESTRO = 4;
    public static Integer INT_ET_TIPO_USUARIO_USUARIO = 5;

    public static Integer INT_ET_ESTADO_USUARIO_REGISTRADO = 12;
    public static Integer INT_ET_ESTADO_USUARIO_CONFIRMADO = 13;
    public static Integer INT_ET_ESTADO_USUARIO_BLOQUEADO = 14;

    public static Integer INT_ET_ESTADO_CONTRATO_REGISTRADO = 19;
    public static Integer INT_ET_ESTADO_CONTRATO_CONFIRMADO = 20;
    public static Integer INT_ET_ESTADO_CONTRATO_EXPIRADO = 21;
    public static Integer INT_ET_ESTADO_CONTRATO_BLOQUEADO = 22;

    public static Integer INT_ET_ESTADO_SELECCION_REGISTRADO = 64;
    public static Integer INT_ET_ESTADO_SELECCION_EN_EJECUCION = 65;

    public static Integer INT_ET_ESTADO_RELACION_REGISTRADO = 66;
    public static Integer INT_ET_ESTADO_RELACION_EN_EJECUCION = 67;

    public static Integer INT_ET_ESTADO_EVALUADO_REGISTRADO = 68;
    public static Integer INT_ET_ESTADO_EVALUADO_EN_PARAMETRIZACION = 72;
    public static Integer INT_ET_ESTADO_EVALUADO_EN_EJECUCION = 69;
    public static Integer INT_ET_ESTADO_EVALUADO_TERMINADO = 73;

    public static Integer INT_ET_TIPO_PARTICIPANTE_EVALUADOR = 38;
    public static Integer INT_ET_TIPO_PARTICIPANTE_EVALUADO = 39;

    public static Integer INT_ET_TIPO_COMPONENTE_CATEGORIA = 45;
    public static Integer INT_ET_TIPO_COMPONENTE_PREGUNTA_CERRADA = 46;
    public static Integer INT_ET_TIPO_COMPONENTE_COMENTARIO = 48;
    public static Integer INT_ET_TIPO_COMPONENTE_PREGUNTA_ABIERTA = 47;

    public static Integer INT_ET_TIPO_UBIGEO_PAIS = 15;
    public static Integer INT_ET_TIPO_UBIGEO_DEPARTAMENTO = 16;

    public static Integer INT_ET_TIPO_METRICA_ESCALA = 50;
    public static Integer INT_ET_TIPO_METRICA_RANGO = 51;

    public static Integer INT_ET_ESTADO_EVALUADOR_REGISTRADO = 70;
    public static Integer INT_ET_ESTADO_EVALUADOR_EN_PARAMETRIZACION = 75;
    public static Integer INT_ET_ESTADO_EVALUADOR_EN_EJECUCION = 71;
    public static Integer INT_ET_ESTADO_EVALUADOR_EN_PAUSA = 75;
    public static Integer INT_ET_ESTADO_EVALUADOR_TERMINADO = 76;

    public static Integer INT_ET_ESTADO_RELACION_EDO_EDOR_REGISTRADO = 79;
    public static Integer INT_ET_ESTADO_RELACION_EDO_EDOR_TERMINADO = 80;

    public static Integer INT_DT_ESTADOS_TARIFA = 9;
    public static Integer INT_DT_METODOLOGIAS = 11;
    public static Integer INT_DT_ESTADOS_PROYECTO = 12;
    public static Integer INT_DT_TIPO_DOCUMENTO = 3;
    public static Integer INT_DT_ESTADO_USUARIO = 5;
    public static Integer INT_DT_TIPO_CUENTA = 2;
    public static Integer INT_DT_ROLES = 27;
    public static Integer INT_DT_AREAS = 28;

    public static Integer INT_ET_ESTADO_TARIFA_REGISTRADO = 25;
    public static Integer INT_ET_ESTADO_TARIFA_CONFIRMADO = 26;
    public static Integer INT_ET_ESTADO_TARIFA_EXPIRADO = 27;

    public static Integer INT_ET_ESTADO_TIPO_PROYECTO_ESCALA = 30;
    public static Integer INT_ET_ESTADO_TIPO_PROYECTO_ELECCION = 31;

    public static Integer INT_ET_ESTADO_PROYECTO_REGISTRADO = 32;
    public static Integer INT_ET_ESTADO_PROYECTO_EN_PARAMETRIZACION = 33;
    public static Integer INT_ET_ESTADO_PROYECTO_EN_EJECUCION = 34;
    public static Integer INT_ET_ESTADO_PROYECTO_MODIFICADO = 35;
    public static Integer INT_ET_ESTADO_PROYECTO_PAUSADO = 36;
    public static Integer INT_ET_ESTADO_PROYECTO_TERMINADO = 37;

    public static Integer INT_ET_ESTADO_CUESTIONARIO_REGISTRADO = 43;
    public static Integer INT_ET_ESTADO_CUESTIONARIO_CONFIRMADO = 44;
    public static Integer INT_ET_ESTADO_CUESTIONARIO_EN_EJECUCION = 77;

    public static Integer INT_ET_ESTADO_CUES_EVA_REGISTRADO = 64;
    public static Integer INT_ET_ESTADO_CUES_EVA_EN_EJECUCION = 65;
    public static Integer INT_ET_ESTADO_CUES_EVA_TERMINADO = 65;

    public static Integer INT_ET_NOTIFICACION_CONVOCATORIA_RED = 81;
    public static Integer INT_ET_NOTIFICACION_CONVOCATORIA = 52;
    public static Integer INT_ET_NOTIFICACION_INSTRUCCIONES = 53;
    public static Integer INT_ET_NOTIFICACION_AGRADECIMIENTO = 54;

    public static String STR_ET_NOTIFICACION_AGRADECIMIENTO = "54";

    public static Integer INT_ET_MODELO_NOTIFICACION_CARGA_RED = 74;
    public static Integer INT_ET_MODELO_NOTIFICACION_CONVOCATORIA = 78;
    
    public static Integer INT_ET_TIPO_CORREO_CLAVE = 25;
    
    public static Integer INT_ET_ESTADO_NOTIFICACION_REGISTRADO = 30;
    public static Integer INT_ET_ESTADO_NOTIFICACION_ENVIADO = 31;
    public static Integer INT_ET_ESTADO_NOTIFICACION_FALLIDO = 32;

    public static String strEspacio = " ";
    public static String strDobleEspacio = "  ";
    public static String strVacio = "";
    public static String strPipe = "|";
    public static String STR_UNDERLINE = "_";
    public static String DDMMYYYY_HH24_MI_SS = "dd/MM/yyyy HH:mm:ss";
    public static String DDMMYYYYHH24MISS = "ddMMyyyyHHmmssS";
    public static String HH24_MI_DDMMYYYY = "HH:mm dd/MM/yyyy";
    public static String DDMMYYYY = "dd/MM/yyyy";
    public static String HH24 = "HH";

    public static String STR_EXTENSION_PDF = ".pdf";
    public static String STR_EXTENSION_PNG = ".png";
    public static String STR_EXTENSION_ZIP = ".zip";
    public static String STR_EXTENSION_XLS = ".xls";

    public static String PASO_0 = "0";
    public static String PASO_1 = "1";
    public static String PASO_2 = "2";
    public static String PASO_3 = "3";
    public static String PASO_4 = "4";
    public static String PASO_5 = "5";
    public static String PASO_6 = "6";
    public static String PASO_7 = "7";

    public static int FIRST_EQUAL_SECOND = 0;
    public static int FIRST_GREATER = 1;
    public static int SECOND_GREATER = -1;

    /**
     * *********
     */
    /* REPORTES */
    /**
     * *********
     */
    public static Integer INT_REPORTE_GRUPAL_CARATULA = 0;
    public static Integer INT_REPORTE_GRUPAL_RESPUESTAS = 8;
    public static Integer INT_REPORTE_GRUPAL_SUMARIO_X_CATEGORIA = 9;

    public static Integer INT_REPORTE_GRUPAL_NIVEL_DE_PARTICIPACION = 10;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_GENERAL_POR_COMPETENCIA = 11;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_GENERAL_POR_PREGUNTA = 12;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_POR_PREGUNTAS_ORDENADO_DESCENDENTE = 13;
    public static Integer INT_REPORTE_GRUPAL_PERSONAS_CON_MEJOR_PUNTJE_GENERAL = 14;
    public static Integer INT_REPORTE_GRUPAL_PERSONAS_CON_MENOR_PUNTAJE_GENERAL = 15;
    public static Integer INT_REPORTE_GRUPAL_RESUMEN_DE_PROMEDIO = 16;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_DE_COMPETENCIAS_POR_SEXO = 17;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_DE_COMPETENCIAS_POR_EDAD = 18;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_DE_COMPETENCIAS_POR_RELACIONES = 19;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_DE_COMPETENCIAS_POR_TIEMPO_EMPRESA = 20;
    public static Integer INT_REPORTE_GRUPAL_PROMEDIO_DE_COMPETENCIAS_POR_AREA = 21;
    public static Integer INT_REPORTE_GRUPAL_RESUMEN_DE_EVALUADOS_POR_RELACIONES = 22;

    public static Integer INT_REPORTE_INDIVIDUAL_CARATULA = 0;
    public static Integer INT_REPORTE_INDIVIDUAL_SUMARIO_X_CATEGORIA = 1;
    public static Integer INT_REPORTE_INDIVIDUAL_SUMARIO_X_CATEGORIA_MISMO = 2;
    public static Integer INT_REPORTE_INDIVIDUAL_CALIFICACION_X_ITEM_CATEGORIA = 3;
    public static Integer INT_REPORTE_INDIVIDUAL_CALIFICACION_X_ITEM_PROMEDIO = 4;
    public static Integer INT_REPORTE_INDIVIDUAL_ITEM_ALTA_CALIFICACION = 5;
    public static Integer INT_REPORTE_INDIVIDUAL_ITEM_BAJA_CALIFICACION = 6;
    public static Integer INT_REPORTE_INDIVIDUAL_PREGUNTAS_ABIERTAS = 7;

    public static String STR_INBOX_PRELIMINAR = File.separator + "var" + File.separator + "tmp" + File.separator + "InboxPreliminar";
    public static String STR_INBOX_DEFINITIVO = File.separator + "var" + File.separator + "tmp" + File.separator + "InboxDefinitivo";

    //public static String STR_INBOX_PRELIMINAR = File.separator + "InboxPreliminar";
    //public static String STR_INBOX_DEFINITIVO = File.separator + "InboxDefinitivo";
    public static int FORMAT_DATE_SHORT = DateFormat.SHORT;
    public static int FORMAT_DATE_MEDIUM = DateFormat.MEDIUM;
    public static int FORMAT_DATE_LONG = DateFormat.LONG;
    public static int FORMAT_DATE_FULL = DateFormat.FULL;

    public static String INT_PARAM_GRAF_MEDIDA = "graficoMedida";

    // Para contratos
    public static Integer INT_DT_ESTADO_CONTRATO = 7;
    public static Integer INT_DT_TIPO_CONTRATO = 8;

    public static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static Integer INTERVAL_SESSION = 30 * 60;


    /*IMPORTAR CUESTIONARIO*/
    public static String LINEA_CUESTIONARIO = "CUE";
    public static String LINEA_CATEGORIA = "CAT";
    public static String LINEA_PREGUNTA_CERRADA = "PRC";
    public static String LINEA_COMENTARIO = "COM";
    public static String LINEA_PREGUNTA_ABIERTA = "PRA";

    public static Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public static String ASTERISCO = "*";
    public static String TODO_TEXTO = "%%";

    public static Integer ET_DIAS_BUSQUEDAS = 100;

    public static String SESSION_USUARIO = "userSession";
    public static String SESSION_EMPRESA = "empresaSession";
    public static String SESSION_BATERIA = "bateriaSession";
    public static String SESSION_ID_BATERIA = "idBateriaSession";
    public static String SESSION_ID_PROCESO = "idProcesoSession";
    public static String SESSION_ID_RECLUTAMIENTO = "idReclutamiento";

    public static String MASCULINO = "6";
    public static String FEMENINO = "7";

    public static int EL_AREA_ESTADO_REGISTRADO = 8;
    public static int EL_AREA_ESTADO_ELIMINADO = 9;

    public static int EL_PERFIL_ESTADO_REGISTRADO = 10;
    public static int EL_PERFIL_ESTADO_ELIMINADO = 11;

    public static int EL_EMPRESA_ESTADO_REGISTRADO = 3;
    public static int EL_EMPRESA_ESTADO_ELIMINADO = 5;

    //TIPO DE DATOS
    public static String Tipo_dato_int = "int";
    public static String Tipo_dato_BigDecimal = "BigDecimal";
    public static String Tipo_dato_long = "long";
    public static String Tipo_dato_short = "short";
    public static String Tipo_dato_byte = "byte";
    public static String Tipo_dato_date = "date";
    
    //tabla definiciones
    public static int INT_ESTADO_USUARIO_VIGENTE = 1;
    public static int INT_ESTADO_USUARIO_CADUCADO = 2;
    public static int INT_ESTADO_EMPRESA_REGISTRADO = 3;
    public static int INT_ESTADO_EMPRESA_ELIMINADO = 5;
    public static int INT_TIPO_SEXO_MASCULINO = 6;
    public static int INT_TIPO_SEXO_FEMENINO = 7;
    public static int INT_ESTADO_AREA_REGISTRADO = 8;
    public static int INT_ESTADO_AREA_ELIMINADO = 9;
    public static int INT_ESTADO_EVALUACION_ACTIVO = 12;
    public static int INT_ESTADO_EVALUACION_INACTIVO = 13;
    public static int INT_ESTADO_PROCESO_REGISTRADO = 20;
    public static int INT_ESTADO_PROCESO_ELIMINADO = 21;
    
    
    //NUMEROS TIPO INT
    public static int Int_zero = 0;
    public static int Int_uno = 1;
    public static int Int_dos = 2;
    public static int Int_tres = 3;
    public static int Int_cuatro = 4;
    public static int Int_cinco = 5;
    public static int Int_seis = 6;
    

    public static int XLSX_FILA_EMPRESA = 0;
    public static int XLSX_COLUMNA_EMPRESA = 1;
    public static int XLSX_FILA_CABECERA_DATOS = 2;
    public static int XLSX_FILA_INICIO_DATOS = 3;
    public static int XLSX_COLUMNA_ID = 0;
    public static int XLSX_COLUMNA_TIPO_REGISTRO = 1;
    public static int XLSX_COLUMNA_NOMBRE_REGISTRO = 2;
    public static int XLSX_COLUMNA_DEPENDENCIA = 3;

    public static Integer TIPO_INTEGER = 0;
    public static Integer TIPO_STRING = 1;
    public static Integer TIPO_BIGDECIMAL = 3;
    public static Integer TIPO_DATE = 4;
    public static Integer TIPO_BOOLEAN = 5;

    public static String XLSX_ORG_MASIVO_COLUMNA_TIPO_AREA = "AREA";
    public static String XLSX_ORG_MASIVO_COLUMNA_TIPO_PERFIL = "PERFIL";

    public static String XLSX_ORG_MASIVO_CAB_ID = "ID";
    public static String XLSX_ORG_MASIVO_CAB_TIPO_REGISTRO = "TIPO DE REGISTRO";
    public static String XLSX_ORG_MASIVO_CAB_NOMBRE_REGISTRO = "NOMBRE DE REGISTRO";
    public static String XLSX_ORG_MASIVO_CAB_DEPENDENCIA = "DEPENDENCIA";
    
    //COLUMNAS DE RECLUTAMIENTO MASIVO
    public static String XLSX_RECL_CAB_TIPO_DE_INFORMACION = "TIPO DE INFORMACION";
    public static String XLSX_RECL_CAB_INFORMACION = "INFORMACION";
    
    public static String XLSX_RECL_CAB_INFO_CANDIDATO = "INFORMACION DE CANDIDATO";
    public static String XLSX_RECL_CAB_INFO_ACADEMICA = "INFORMACION ACADEMICA";
    public static String XLSX_RECL_CAB_INFO_CONOCIMIENTOS = "INFORMACION DE CONOCIMIENTOS";
    public static String XLSX_RECL_CAB_INFO_EXPERIENCIAS = "INFORMACION DE EXPERIENCIAS";
    public static String XLSX_RECL_CAB_INFO_REFERENCIAS = "INFORMACION DE REFERENCIAS";
    
    public static String XLSX_RECL_CAB_NOMBRES = "NOMBRES";
    public static String XLSX_RECL_CAB_APELLIDO_PATERNO = "APELLIDO PATERNO";
    public static String XLSX_RECL_CAB_APELLIDO_MATERNO = "APELLIDO MATERNO";
    public static String XLSX_RECL_CAB_TIPO_DOCUMENTO = "TIPO DE DOCUMENTO";
    public static String XLSX_RECL_CAB_NRO_DOCUMENTO = "NRO DE DOCUMENTO";
    public static String XLSX_RECL_CAB_FECHA_NACIMIENTO = "FECHA DE NACIMIENTO";
    public static String XLSX_RECL_CAB_DEPARTAMENTO = "DEPARTAMENTO";
    public static String XLSX_RECL_CAB_DISTRITO = "DISTRITO";
    public static String XLSX_RECL_CAB_DIRECCION = "DIRECCION";
    public static String XLSX_RECL_CAB_CORREO = "CORREO";
    public static String XLSX_RECL_CAB_CELULAR = "CELULAR";
    public static String XLSX_RECL_CAB_TELEFONO = "TELEFONO";
    
    public static String XLSX_RECL_CAB_NOMBRE_ACADEMICO = "NOMBRE ACADEMICO";
    public static String XLSX_RECL_CAB_ESPECIALIDAD = "ESPECIALIDAD";
    public static String XLSX_RECL_CAB_GRADO = "GRADO";
    public static String XLSX_RECL_CAB_FECHA_INICIO = "FECHA INICIO";
    public static String XLSX_RECL_CAB_FECHA_FIN = "FECHA FIN";
    public static String XLSX_RECL_CAB_LOGRO = "LOGRO";
    
    public static String XLSX_RECL_CAB_CONOCIMIENTO = "CONOCIMIENTO";
    public static String XLSX_RECL_CAB_NIVEL = "NIVEL";
    
    public static String XLSX_RECL_CAB_NOMBRE_DE_EMPRESA = "NOMBRE DE EMPRESA";
    public static String XLSX_RECL_CAB_CARGO = "CARGO";
    
    public static String XLSX_RECL_CAB_NOMBRE_DE_REFERIDO = "NOMBRE DE REFERIDO";
    
    public static int XLSX_RECL_COL_TIPO_INFORMACION = 0;
    public static int XLSX_RECL_COL_INFORMACION = 1;
    
    public static int XLSX_RECL_COL_INFO_CANDIDATO = 0;
    public static int XLSX_RECL_COL_INFO_ACADEMICA = 0;
    public static int XLSX_RECL_COL_INFO_CONOCIMIENTOS = 0;
    public static int XLSX_RECL_COL_INFO_EXPERIENCIAS = 0;
    public static int XLSX_RECL_COL_INFO_REFERENCIAS = 0;
    
    public static int XLSX_RECL_COL_NOMBRES = 1;
    public static int XLSX_RECL_COL_APELLIDO_PATERNO = 2;
    public static int XLSX_RECL_COL_APELLIDO_MATERNO = 3;
    public static int XLSX_RECL_COL_TIPO_DOCUMENTO = 4;
    public static int XLSX_RECL_COL_NRO_DOCUMENTO = 5;
    public static int XLSX_RECL_COL_FECHA_NACIMIENTO = 6;
    public static int XLSX_RECL_COL_DEPARTAMENTO = 7;
    public static int XLSX_RECL_COL_DISTRITO = 8;
    public static int XLSX_RECL_COL_DIRECCION = 9;
    public static int XLSX_RECL_COL_CORREO = 10;
    public static int XLSX_RECL_COL_CELULAR = 11;
    public static int XLSX_RECL_COL_TELEFONO = 12;
    
    public static int XLSX_RECL_COL_NOMBRE_ACADEMICO = 1;
    public static int XLSX_RECL_COL_ESPECIALIDAD = 2;
    public static int XLSX_RECL_COL_GRADO = 3;
    public static int XLSX_RECL_COL_FECHA_INICIO = 4;
    public static int XLSX_RECL_COL_FECHA_FIN = 5;
    public static int XLSX_RECL_COL_LOGRO = 6;
    
    public static int XLSX_RECL_COL_CONOCIMIENTO = 1;
    public static int XLSX_RECL_COL_NIVEL = 2;
    
    public static int XLSX_RECL_COL_NOMBRE_EMPRESA = 1;
    public static int XLSX_RECL_COL_CARGO = 2;
    public static int XLSX_RECL_COL_LOGRO_EXPERIENCIA = 3;
    
    
    public static int XLSX_RECL_COL_NOMBRE_REFERIDO = 1;
    public static int XLSX_RECL_COL_CELULAR_REFERENCIA = 3;
    public static int XLSX_RECL_COL_TELEFONO_REFERENCIA = 4;
}
