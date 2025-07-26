package entidades;

public class Usuario {
	private int idUsuario;
	private String nombre;
	private String contraseña;
	private String tipoUsuario;
	private boolean estado;

	
    public Usuario(int idUsuario, String nombre, String contraseña, String tipoUsuario, boolean estado) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.tipoUsuario = tipoUsuario;
		this.estado = estado;
	}





	public String getTipoUsuario() {
		return tipoUsuario;
	}





	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}





	public boolean isEstado() {
		return estado;
	}





	public void setEstado(boolean estado) {
		this.estado = estado;
	}





	public Usuario() {
		
	}
    
	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getContraseña() {
		return contraseña;
	}



	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}



	public int getIdUsuario() {
		return idUsuario;
	}

	

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", tipoUsuario=" +"]";
	}
    
}