package Inegocio;

import java.util.List;

import entidades.SolicitudPrestamo;

public interface INegocioPrestamosAdmin {

public List<SolicitudPrestamo> lista();	
public SolicitudPrestamo DevueltePrestamosAtravezNumero(int numeroSolicitud) ;
public boolean Actualizar_solicitud(int numero , String admin);
public boolean Rechazar_Solicitud(int numero,String admin);
public List<SolicitudPrestamo>obtenerHistorialPrestamos();
public SolicitudPrestamo infoVerDetalle(String cuil , String admin);
	
}
