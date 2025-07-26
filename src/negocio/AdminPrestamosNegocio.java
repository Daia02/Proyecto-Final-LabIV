package negocio;

import java.util.List;

import Inegocio.INegocioPrestamosAdmin;
import dao.AdminitradorDao;
import dao.SolicitudPrestamosDao;
import entidades.Administrador;
import entidades.Cliente;
import entidades.Prestamo;
import entidades.SolicitudPrestamo;

public class AdminPrestamosNegocio implements INegocioPrestamosAdmin {
SolicitudPrestamosDao daoPrestamos=new SolicitudPrestamosDao();
	@Override
	public List<SolicitudPrestamo> lista() {
		
		return daoPrestamos.ListaSolicitud();
	}
	@Override
	public SolicitudPrestamo DevueltePrestamosAtravezNumero(int numeroSolicitud) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean Actualizar_solicitud(int numero ,String admin) {
		SolicitudPrestamo solicitudPrestamo=daoPrestamos.DevueltePrestamosAtravezNumero(numero);
		dao.AdminitradorDao adminitradorDao=new AdminitradorDao();
		int idAdmin=adminitradorDao.BuscarID(admin);
		Administrador administrador=new Administrador();
		administrador.setIdUsuario(idAdmin);
		
		solicitudPrestamo.setAdministrador(administrador);
		
		return daoPrestamos.Actualizar_solicitud(solicitudPrestamo);
		
		
	}
	@Override
	public boolean Rechazar_Solicitud(int numero, String admin) {
		dao.AdminitradorDao adminitradorDao=new AdminitradorDao();
		int idAdmin=adminitradorDao.BuscarID(admin);
		System.out.println(idAdmin);
		return daoPrestamos.RechazarPrestamos(numero,idAdmin);
		
	
	}
	@Override
	public List<SolicitudPrestamo> obtenerHistorialPrestamos() {
		
		return daoPrestamos.obtenerHistorialPrestamos();
	}
	@Override
	public SolicitudPrestamo infoVerDetalle(String cuil, String admin) {
		negocio.NegocioCliente negocioCliente=new NegocioCliente();
		negocio.AdminitradorNegocio adminitradorNegocio=new AdminitradorNegocio();
		Cliente cliente =negocioCliente.buscarClienteUnico(cuil);
		Administrador administrador=adminitradorNegocio.DevolverInformacion(admin);
		entidades.Prestamo prestamo=new Prestamo();
		
		prestamo.setCliente(cliente);
		
		SolicitudPrestamo solicitudPrestamo=new SolicitudPrestamo();
		
		solicitudPrestamo.setAdministrador(administrador);
		solicitudPrestamo.setPrestamos(prestamo);
		
		
		
		return solicitudPrestamo;
	}

}
