package negocio;

import dao.CuentaDao;
import entidades.Cuenta;
import java.util.ArrayList;

public class NegocioCuenta {
	private CuentaDao cuenta;
	
	public NegocioCuenta() {
		cuenta = new CuentaDao();
	}
	
	public boolean asignarCuenta(Cuenta agregar) {
		return cuenta.agregarCuenta(agregar);
	}
	
	public int cuentaUltimoId() {
		return cuenta.ultimoIDCuenta();
	}
	
	public long cuentasTotales(long l) {
	    return cuenta.totalCuentas(l);
	}
	
	 public boolean eliminarCuenta(int nroCuenta){
		 return cuenta.eliminarCuenta(nroCuenta);
	 }
	 
	 // Nuevo método para obtener todas las cuentas
	    public ArrayList<Cuenta> obtenerCuentas() {
	        return cuenta.obtenerCuentas();
	    }
	    
	    //  método para modificar cuenta llamando al DAO
	    public boolean modificarCuenta(Cuenta cuentaModificada) {
	        return cuenta.modificarCuenta(cuentaModificada);
	    }
	    
	    public Cuenta obtenerCuentaPorNumero(int nroCuenta) {
	        return cuenta.obtenerCuentaPorNumero(nroCuenta);
	    }
	    
	    public boolean verificarCuilRepetido(long cuil) {
	        return cuenta.verificarCuilRepetido( cuil);
	    }
	    
	    public boolean verificarCbuRepetido(String cbu) {
	        return cuenta.verificarCbuRepetido( cbu);
	    }
	    
	    public String  obtenerCbuPorNumeroCuenta(int nroCuenta) {
	    return cuenta.obtenerCbuPorNumeroCuenta(nroCuenta);
	    }
	
}
