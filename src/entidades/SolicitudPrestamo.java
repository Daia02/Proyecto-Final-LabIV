package entidades;

import java.sql.Date;

public class SolicitudPrestamo {
	private int nroSolicitud; //Primary key
	private entidades.Prestamo prestamos; // Relacionado con usuario
	private Cuenta cuenta;
	private int idAdmin; // Relacionado con admin
	private Date fecha_pedido;
	private String estado;
	private Administrador administrador;

	
	public Administrador getAdministrador() {
	return administrador;
}

public void setAdministrador(Administrador administrador) {
	this.administrador = administrador;
}


	public int getNroSolicitud() {
		return nroSolicitud;
	}

	public void setNroSolicitud(int nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}

	public entidades.Prestamo getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(entidades.Prestamo prestamos) {
		this.prestamos = prestamos;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public Date getFecha_pedido() {
		return fecha_pedido;
	}

	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
