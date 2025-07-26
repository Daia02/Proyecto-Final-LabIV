package Inegocio;

import java.math.BigDecimal;
import java.util.List;

import Expeciones.MaximoPrestamos;
import entidades.Cuenta;
import entidades.SolicitudPrestamo;
import entidades.TipoCuenta;

public interface INegocioPrestamos {
	public boolean existeCbu(String cbu);
	public List <Cuenta>ObtenerTipoCuentaUsuario (String usuario);
	public boolean agregarPrestamo(BigDecimal importe,int coutas,String usuario ,int tipo,BigDecimal total,BigDecimal interes ) throws MaximoPrestamos;
	
	
	
	
	
	
	public List<SolicitudPrestamo> ObtenerClienteEstadoPrestamo(String usuario);
}
