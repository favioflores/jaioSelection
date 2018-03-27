package jaio.selection.bean;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final

import jaio.selection.orm.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Candidato generated by hbm2java
 */
public class CandidatoBean implements java.io.Serializable {

    private String id;
    private String nombre;
    private String apellidoParterno;
    private String apellidoMaterno;
    private String nroDocumento;
    private String tipoDocumento;
    private String movil;
    private String telefono;
    private String direccion;
    private Date fechaNacimiento;
    private String distrito;
    private String correo;
    private String estado;
    private String prioridad;
    private String esApto;
    private String esFinalista;

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

    public String getApellidoParterno() {
        return apellidoParterno;
    }

    public void setApellidoParterno(String apellidoParterno) {
        this.apellidoParterno = apellidoParterno;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

}
