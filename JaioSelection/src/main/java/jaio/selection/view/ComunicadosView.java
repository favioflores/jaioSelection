package jaio.selection.view;

import jaio.selection.util.Utilitarios;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Properties;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

@ManagedBean(name = "comunicadoView")
@ViewScoped
public class ComunicadosView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(ComunicadosView.class);

    private static final long serialVersionUID = -1L;

    private String strPreviewConvocatoriaTemplate;
    private String strPreviewRecordatorioTemplate;
    private String strPreviewFinalizacionTemplate;
    private String strPreviewAgredecimientoTemplate;
    private String strPreviewEntrevistaTemplate;

    private String strConvoTitu;

    public String getStrConvoTitu() {
        return strConvoTitu;
    }

    public void setStrConvoTitu(String strConvoTitu) {
        this.strConvoTitu = strConvoTitu;
    }

    public String getStrPreviewConvocatoriaTemplate() {
        return strPreviewConvocatoriaTemplate;
    }

    public void setStrPreviewConvocatoriaTemplate(String strPreviewConvocatoriaTemplate) {
        this.strPreviewConvocatoriaTemplate = strPreviewConvocatoriaTemplate;
    }

    public String getStrPreviewRecordatorioTemplate() {
        return strPreviewRecordatorioTemplate;
    }

    public void setStrPreviewRecordatorioTemplate(String strPreviewRecordatorioTemplate) {
        this.strPreviewRecordatorioTemplate = strPreviewRecordatorioTemplate;
    }

    public String getStrPreviewFinalizacionTemplate() {
        return strPreviewFinalizacionTemplate;
    }

    public void setStrPreviewFinalizacionTemplate(String strPreviewFinalizacionTemplate) {
        this.strPreviewFinalizacionTemplate = strPreviewFinalizacionTemplate;
    }

    public String getStrPreviewAgredecimientoTemplate() {
        return strPreviewAgredecimientoTemplate;
    }

    public void setStrPreviewAgredecimientoTemplate(String strPreviewAgredecimientoTemplate) {
        this.strPreviewAgredecimientoTemplate = strPreviewAgredecimientoTemplate;
    }

    public String getStrPreviewEntrevistaTemplate() {
        return strPreviewEntrevistaTemplate;
    }

    public void setStrPreviewEntrevistaTemplate(String strPreviewEntrevistaTemplate) {
        this.strPreviewEntrevistaTemplate = strPreviewEntrevistaTemplate;
    }

    @PostConstruct
    public void init() {

        limpiar();

        armarPreview();

    }

    private void armarPreview() {

        try {

            Properties props = new Properties();
            props.setProperty("resource.loader", "class");
            props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

            VelocityEngine ve = new VelocityEngine();

            ve.init(props);

            Template t = new Template();
            VelocityContext context;

            t = ve.getTemplate("templates/TemplateComunicadoModelo.vm");
            context = new VelocityContext();

            context.put("TITULO", msg("comunicados.modelo.titulo"));
            context.put("PARRAFO1", msg("comunicados.modelo.parrafo1"));

            StringWriter out = new StringWriter();
            t.merge(context, out);

            strPreviewConvocatoriaTemplate = out.toString();

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }

    }

    public void modificarTituloConvo() {

        try {

            if (Utilitarios.esNuloOVacio(strConvoTitu)) {
                Properties props = new Properties();
                props.setProperty("resource.loader", "class");
                props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

                VelocityEngine ve = new VelocityEngine();

                ve.init(props);

                Template t = new Template();
                VelocityContext context;

                t = ve.getTemplate("templates/TemplateComunicadoModelo.vm");
                context = new VelocityContext();

                context.put("TITULO", msg("comunicados.modelo.titulo"));
                context.put("PARRAFO1", msg("comunicados.modelo.parrafo1"));

                StringWriter out = new StringWriter();
                t.merge(context, out);

                strPreviewConvocatoriaTemplate = out.toString();
            } else {
                Properties props = new Properties();
                props.setProperty("resource.loader", "class");
                props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

                VelocityEngine ve = new VelocityEngine();

                ve.init(props);

                Template t = new Template();
                VelocityContext context;

                t = ve.getTemplate("templates/TemplateComunicadoModelo.vm");
                context = new VelocityContext();

                context.put("TITULO", strConvoTitu);
                context.put("PARRAFO1", msg("comunicados.modelo.parrafo1"));

                StringWriter out = new StringWriter();
                t.merge(context, out);

                strPreviewConvocatoriaTemplate = out.toString();
            }

        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);

        }
    }

    public void limpiar() {

        strPreviewAgredecimientoTemplate = "";
        strPreviewConvocatoriaTemplate = "";
        strPreviewEntrevistaTemplate = "";
        strPreviewFinalizacionTemplate = "";
        strPreviewRecordatorioTemplate = "";

    }

}
