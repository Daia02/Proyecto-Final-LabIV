package entidades;

import java.sql.Date;



public class Cliente {
    private String cuilCl;
    private String idUsuario;
    private String nroLocalidad;
    private String nroProvincia;
    private String nroNacionalidad;
    private String dni;
    private String nombre;
    private String apellido;
    private String sexo;
    private Date fechaNacimiento; // Tipo Date
    private String direccion;
    private String correoElectronico;
    private String telefono;
    private boolean activo;

    // Constructor con los cambios necesarios
    public Cliente(String cuilCl, String idUsuario, String nroLocalidad, String nroProvincia, String nroNacionalidad,
                   String dni, String nombre, String apellido, String sexo, Date fechaNacimiento, String direccion,
                   String correoElectronico, String telefono) {
        this.cuilCl = cuilCl;
        this.idUsuario = idUsuario;
        this.nroLocalidad = nroLocalidad;
        this.nroProvincia = nroProvincia;
        this.nroNacionalidad = nroNacionalidad;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.activo = true;
    }
    
    public Cliente() {
		// TODO Auto-generated constructor stub
	}

	// Getters y Setters
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

	public String getCuilCl() {
        return cuilCl;
    }

    public void setCuilCl(String string) {
        this.cuilCl = string;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNroLocalidad() {
        return nroLocalidad;
    }

    public void setNroLocalidad(String nroLocalidad) {
        this.nroLocalidad = nroLocalidad;
    }

    public String getNroProvincia() {
        return nroProvincia;
    }

    public void setNroProvincia(String nroProvincia) {
        this.nroProvincia = nroProvincia;
    }

    public String getNroNacionalidad() {
        return nroNacionalidad;
    }

    public void setNroNacionalidad(String nroNacionalidad) {
        this.nroNacionalidad = nroNacionalidad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
