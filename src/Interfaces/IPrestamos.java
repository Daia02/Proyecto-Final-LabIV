package Interfaces;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import Expeciones.MaximoPrestamos;
import entidades.Cuenta;
import entidades.Prestamo;
import entidades.SolicitudPrestamo;
import entidades.PagoPrestamo;

public interface IPrestamos {
	public boolean ExisteCbu(String cbu);
	public boolean AgregarPrestamos(Prestamo prestamo) throws MaximoPrestamos;
	public List<Cuenta> ObtenerTipoCuentaUsuario(String usuario);
	
	public java.util.List<PagoPrestamo> obtenerPrestamosYPagos(long cuil);
	public List<PagoPrestamo> obtenerPrestamosYPagosPorCuenta(long cuil, int nroCuenta);
	public boolean pagarCuotaPrestamo(int nroPrestamo, int nroCuenta, Date fechaPago);
	public List<Map<String, String>> obtenerCuentasPorCliente(long cuil);
	
	public List<SolicitudPrestamo> ObtenerClienteEstadoPrestamo(String usuario);

}
