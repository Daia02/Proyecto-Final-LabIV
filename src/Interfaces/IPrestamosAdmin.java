package Interfaces;

import java.util.List;

import entidades.SolicitudPrestamo;

public interface IPrestamosAdmin {

	public boolean Actualizar_solicitud(SolicitudPrestamo estado);
	public List<SolicitudPrestamo>ListaSolicitud();
	public SolicitudPrestamo DevueltePrestamosAtravezNumero(int numeroSolicitud);
	public boolean RechazarPrestamos(int numeroSolicitud,int Idadmin);
	public List<SolicitudPrestamo>obtenerHistorialPrestamos();
	
}
