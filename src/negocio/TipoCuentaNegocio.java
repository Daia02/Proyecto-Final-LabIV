package negocio;

import java.util.ArrayList;

import dao.TipoCuentaDao;
import entidades.TipoCuenta;


public class TipoCuentaNegocio {
	private TipoCuentaDao tipoCuenta;
	
	public TipoCuentaNegocio() {
		tipoCuenta = new TipoCuentaDao();
	}
	
	public ArrayList<TipoCuenta> listarTodoTipoCuenta(){
		return tipoCuenta.obtenerTodosTipoCuenta();
	}
}
