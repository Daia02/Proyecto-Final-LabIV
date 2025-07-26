package Inegocio;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import entidades.PagoPrestamo;

public interface IPagosPrestamosNeg {
	public List<PagoPrestamo> obtenerPrestamosYPagos(long cuil) throws Exception;
	public List<PagoPrestamo> obtenerPrestamosYPagosPorCuenta(long cuil, int nroCuenta) throws Exception;
	public boolean pagarCuotaPrestamo(int nroPrestamo, int nroCuenta, Date fechaPago) throws Exception;
	public List<Map<String, String>> obtenerCuentasPorCliente(long cuil) throws Exception;
}
