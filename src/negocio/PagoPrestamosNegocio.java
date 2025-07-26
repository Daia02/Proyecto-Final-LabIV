package negocio;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import dao.PrestamosDao;
import entidades.PagoPrestamo;
import Inegocio.IPagosPrestamosNeg;

public class PagoPrestamosNegocio implements IPagosPrestamosNeg{

    private PrestamosDao prestamosDao = new PrestamosDao();

    @Override
    public List<PagoPrestamo> obtenerPrestamosYPagos(long cuil) throws Exception {
        List<PagoPrestamo> prestamos = prestamosDao.obtenerPrestamosYPagos(cuil);

        if (prestamos == null) {
            throw new Exception("No se encontraron préstamos para el CUIL especificado.");
        }

        return prestamos;
    }

    @Override
    public List<PagoPrestamo> obtenerPrestamosYPagosPorCuenta(long cuil, int nroCuenta) throws Exception {
        List<PagoPrestamo> prestamos = prestamosDao.obtenerPrestamosYPagosPorCuenta(cuil, nroCuenta);

        if (prestamos == null) {
            throw new Exception("No se encontraron préstamos para el CUIL y número de cuenta especificados.");
        }

        return prestamos;
    }

    @Override
    public boolean pagarCuotaPrestamo(int nroPrestamo, int nroCuenta, Date fechaPago) throws Exception {
        boolean resultado = prestamosDao.pagarCuotaPrestamo(nroPrestamo, nroCuenta, fechaPago);

        if (!resultado) {
            throw new Exception("No se pudo realizar el pago de la cuota.");
        }

        return resultado;
    }
    
    @Override
    public List<Map<String, String>> obtenerCuentasPorCliente(long cuil) throws Exception {
        List<Map<String, String>> cuentas = null;
        try {
            cuentas = prestamosDao.obtenerCuentasPorCliente(cuil);
            
            if (cuentas == null || cuentas.isEmpty()) {
            	System.out.println("No se encontraron cuentas para el cliente con CUIL:");
                throw new Exception("No se encontraron cuentas para el cliente con CUIL: " + cuil);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al obtener las cuentas para el cliente con CUIL: " + cuil, e);
        }
        
        return cuentas;
    }

}
