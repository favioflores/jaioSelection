package jaio.selection.view;

import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "registrarReclutamientoMasivoView")
@ViewScoped
public class RegistrarReclutamientoMasivoView extends BaseView implements Serializable {

    private static Log log = LogFactory.getLog(RegistrarReclutamientoMasivoView.class);
    private static final long serialVersionUID = -1L;
    
    private StreamedContent fileImport;
    private StreamedContent fileExport;
    private UploadedFile inputFile;
    
    public StreamedContent getFileExport() {
        downloadFile();
        return fileExport;
    }
    
    public void downloadFile() {
        try {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/FormatoDeReclutamientoMasivo.xlsx");
            fileExport = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "FormatoDeReclutamientoMasivo.xlsx");
        } catch (Exception e) {
            mostrarAlerta(FATAL, "error.inesperado", log, e);
        }
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
    
    

}
