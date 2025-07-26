package negocio;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;
import dao.MovimientoDao;
import entidades.Movimiento;

public class NegocioMovimiento {
	private MovimientoDao movimientoDao;
	
	public NegocioMovimiento() {
		movimientoDao = new MovimientoDao();
	}
	
	
	/*public ArrayList<Movimiento> listarMovimientoUnaCuenta(String CUIL){
		return movimientoDao.obtenerTodosMovimientosUnCuil(CUIL);
	}
	
	public ArrayList<Movimiento> listarConFiltroImporte(String importe, String criterio){
		return movimientoDao.obtenerTodosMovimientosConImporte(importe, criterio);
	}*/
	
	// M�todo que obtiene un objeto Movimiento por n�mero de cuenta
	public List<Movimiento> obtenerMovimientoPorCuenta(int numeroCuenta) {
	    return movimientoDao.obtenerMovimientosPorCuenta(numeroCuenta);
	}

}
