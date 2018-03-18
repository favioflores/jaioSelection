package jaio.selection.bean;

import java.io.Serializable;

public class RegistrarReclutamientoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nroDocumento;
    private String documentoSeleccionado;
    private String celular;
    private String telefono;
    private String direccion;
    private String fechaNacimiento;
    private String departamento;
    private String distrito;
    private String correo;
    private String estado;
    private String prioridad;
    private String esApto;
    private String esFinalista;
    private String procesosSeleccionId;

    public RegistrarReclutamientoBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEsApto() {
        return esApto;
    }

    public void setEsApto(String esApto) {
        this.esApto = esApto;
    }

    public String getEsFinalista() {
        return esFinalista;
    }

    public void setEsFinalista(String esFinalista) {
        this.esFinalista = esFinalista;
    }

    public String getProcesosSeleccionId() {
        return procesosSeleccionId;
    }

    public void setProcesosSeleccionId(String procesosSeleccionId) {
        this.procesosSeleccionId = procesosSeleccionId;
    }

}
