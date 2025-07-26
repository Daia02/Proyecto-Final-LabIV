package entidades;

public class Administrador {
	private String cuit;
    private int idUsuario;  // Relacionado con Usuario
    private String dni;
    private String nombre;
    private String apellido;
    private String contraseña;
    private String Ad_Usuario;
    
	public String getAd_Usuario() {
		return Ad_Usuario;
	}

	public void setAd_Usuario(String ad_Usuario) {
		Ad_Usuario = ad_Usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Administrador(String cuit, int idUsuario, String dni, String nombre, String apellido) {
		this.cuit = cuit;
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Administrador() {
	}

	public String getCuit() {
		return cuit;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Administrador [cuit=" + cuit + ", idUsuario=" + idUsuario + ", dni=" + dni + ", nombre=" + nombre
				+ ", apellido=" + apellido + "]";
	}
    
	
    
    
}
