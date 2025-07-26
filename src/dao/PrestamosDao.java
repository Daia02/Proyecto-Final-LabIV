package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.util.StringManager;

import java.util.ArrayList;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import Expeciones.MaximoPrestamos;
import Interfaces.IPrestamos;
import entidades.Cuenta;
import entidades.Nacionalidad;
import entidades.PagoPrestamo;
import entidades.Prestamo;
import entidades.SolicitudPrestamo;
import entidades.TipoCuenta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrestamosDao implements IPrestamos {
private static final String existeCbu="Select * from cuentas Where CBU = ? AND Estado = TRUE ";
private static final String query = 
"SELECT tipos_de_cuenta.Tipo_de_cuenta, tipos_de_cuenta.Nro_Tipo_cuenta ,cuentas.Nro_cuenta " +
"FROM usuarios " +
"INNER JOIN clientes ON usuarios.ID_Usuario = clientes.ID_Usuario " +
"INNER JOIN cuentas ON clientes.CUIL_cl = cuentas.CUIL_cu " +
"INNER JOIN tipos_de_cuenta ON tipos_de_cuenta.Nro_Tipo_cuenta = cuentas.Nro_Tipo_cuenta " +
"WHERE usuarios.Nombre_usuario = ?";
private static final String buscar_num_cuenta="Select cuentas.Nro_cuenta from cuentas where cuentas.CUIL_cu = ?" ;
	
public boolean ExisteCbu(String cbu){
	boolean existe=false;
	
	
	PreparedStatement statement;
	ResultSet resultSet;
	Conexion conexion=Conexion.getConexion();
	try {
		statement=conexion.getSQLConexion().prepareStatement(existeCbu);
		statement.setString(1, cbu);
		resultSet=statement.executeQuery();
		while (resultSet.next()) {
			return existe=true;
			
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	
	return existe;
}


@Override
public boolean AgregarPrestamos(Prestamo prestamo)throws MaximoPrestamos {
	boolean insertado =false;
	String INSERT_CLIENTE="{call InsertarSolicitudPrestamo(?,?,?,?,?,?)}";
	 try (Connection conexion = Conexion.getConexion().getSQLConexion();
             CallableStatement statement = conexion.prepareCall(INSERT_CLIENTE)) {

            parametros(statement, prestamo);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.commit();
                insertado= true;
            }
        } catch (SQLException e) {
        	
        	if (e.getErrorCode() ==45000) { 
        	throw new MaximoPrestamos("No puede solicitar más de tres préstamos activos");}
        	
        	else {
				
			
           e.printStackTrace(); 
             
            realizarRollback();
            return false;}
        }
        
        return insertado;
    }

private void parametros(PreparedStatement statement ,entidades.Prestamo prestamo) throws SQLException{
	statement.setLong(1,Long.parseLong(prestamo.getCliente().getCuilCl()));
	statement.setBigDecimal(2, prestamo.getImporte());
	statement.setInt(3, prestamo.getCoutas());
	statement.setInt(4, prestamo.getnumeroCuenta());
	statement.setBigDecimal(5, prestamo.getTotal());
	statement.setBigDecimal(6, prestamo.getInteres());
	
	
}
private void realizarRollback() {
    try (Connection conexion = Conexion.getConexion().getSQLConexion()) {
        if (conexion != null) {
            conexion.rollback();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


@Override
public List<Cuenta> ObtenerTipoCuentaUsuario(String usuario) {
	  List<Cuenta> tipo = new ArrayList<>();
      PreparedStatement statement = null;
      ResultSet resultSet = null;
      
      try {
          Conexion conexion = Conexion.getConexion(); // Obtener conexión
          statement = conexion.getSQLConexion().prepareStatement(query);
          statement.setString(1, usuario);
          resultSet = statement.executeQuery();

          while (resultSet.next()) {
        	  Cuenta cuenta=new Cuenta();
        	  entidades.TipoCuenta t=new TipoCuenta();
        	  t.setNroTipoCuenta(resultSet.getInt("Nro_Tipo_cuenta"));
        	  
        	  t.setTipoCuenta((resultSet.getString("Tipo_de_cuenta")));
        	  cuenta.setTipoCuenta(t);
        	  cuenta.setNroCuenta(resultSet.getInt("Nro_cuenta"));
            
             
              tipo.add(cuenta);
          }

      } catch (SQLException e) {
          e.printStackTrace();
      } finally {
          closeResources(statement, resultSet);
      }

      return tipo;
  }


private void closeResources(PreparedStatement statement, ResultSet resultSet) {
    try {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

///////Parte ADMIN
@Override
public List<PagoPrestamo> obtenerPrestamosYPagos(long cuil) {
 List<PagoPrestamo> prestamos = new ArrayList<>();
 String PROCEDURE_CALL = "{CALL obtenerPrestamosYPagos(?)}";
 
 try (Connection conexion = Conexion.getConexion().getSQLConexion();
      CallableStatement statement = conexion.prepareCall(PROCEDURE_CALL)) {

     statement.setLong(1, cuil);
     ResultSet resultSet = statement.executeQuery();

     while (resultSet.next()) {
         PagoPrestamo pagoPrestamo = new PagoPrestamo();
         pagoPrestamo.setNroPrestamo(resultSet.getInt("Nro_prestamo"));
         pagoPrestamo.setNroCuenta(resultSet.getInt("Nro_cuenta"));
         pagoPrestamo.setTipoCuenta(resultSet.getString("Tipo_de_cuenta")); // Tipo de cuenta
         pagoPrestamo.setImporteAprobado(resultSet.getBigDecimal("Importe_aprobado"));
         pagoPrestamo.setTotalAPagar(resultSet.getBigDecimal("Total_a_pagar"));
         pagoPrestamo.setPlazoDePago(resultSet.getInt("Plazo_de_pago"));
         pagoPrestamo.setInteresAplicado(resultSet.getBigDecimal("Interes_aplicado"));
         pagoPrestamo.setCantidadCuotas(resultSet.getInt("Cantidad_cuotas"));
         pagoPrestamo.setCuotasRestantes(resultSet.getInt("Cuotas_restantes"));
         pagoPrestamo.setFechaAprobacion(resultSet.getDate("Fecha_aprobacion"));
         pagoPrestamo.setFechaVencimiento(resultSet.getDate("Fecha_vencimiento"));
         pagoPrestamo.setMontoPorCuota(resultSet.getBigDecimal("Monto_por_cuota"));
         pagoPrestamo.setEstado(resultSet.getString("Estado"));
         
         prestamos.add(pagoPrestamo);
     }

 } catch (SQLException e) {
     e.printStackTrace();
 }

 return prestamos;
}

//Método para obtener préstamos y pagos de un cliente


@Override
public List<PagoPrestamo> obtenerPrestamosYPagosPorCuenta(long cuil, int nroCuenta) {
  List<PagoPrestamo> prestamos = new ArrayList<>();
  String PROCEDURE_CALL = "{CALL obtenerPrestamosYPagosPorCuenta(?, ?)}";
  
  try (Connection conexion = Conexion.getConexion().getSQLConexion();
       CallableStatement statement = conexion.prepareCall(PROCEDURE_CALL)) {

      statement.setLong(1, cuil);
      statement.setInt(2, nroCuenta);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
          PagoPrestamo pagoPrestamo = new PagoPrestamo();
          pagoPrestamo.setNroPrestamo(resultSet.getInt("Nro_prestamo"));
          pagoPrestamo.setNroCuenta(resultSet.getInt("Nro_cuenta"));
          pagoPrestamo.setTipoCuenta(resultSet.getString("Tipo_de_cuenta")); // Tipo de cuenta
          pagoPrestamo.setImporteAprobado(resultSet.getBigDecimal("Importe_aprobado"));
          pagoPrestamo.setTotalAPagar(resultSet.getBigDecimal("Total_a_pagar"));
          pagoPrestamo.setPlazoDePago(resultSet.getInt("Plazo_de_pago"));
          pagoPrestamo.setInteresAplicado(resultSet.getBigDecimal("Interes_aplicado"));
          pagoPrestamo.setCantidadCuotas(resultSet.getInt("Cantidad_cuotas"));
          pagoPrestamo.setCuotasRestantes(resultSet.getInt("Cuotas_restantes"));
          pagoPrestamo.setFechaAprobacion(resultSet.getDate("Fecha_aprobacion"));
          pagoPrestamo.setFechaVencimiento(resultSet.getDate("Fecha_vencimiento"));
          pagoPrestamo.setMontoPorCuota(resultSet.getBigDecimal("Monto_por_cuota"));
          pagoPrestamo.setEstado(resultSet.getString("Estado"));
          
          prestamos.add(pagoPrestamo);
      }

  } catch (SQLException e) {
      e.printStackTrace();
  }

  return prestamos;
}

@Override
public boolean pagarCuotaPrestamo(int nroPrestamo, int nroCuenta, Date fechaPago) {
  boolean pagoRealizado = false;
  String PROCEDURE_CALL = "{CALL pagarCuotaPrestamo(?, ?, ?)}";

  try (Connection conexion = Conexion.getConexion().getSQLConexion();
       CallableStatement statement = conexion.prepareCall(PROCEDURE_CALL)) {

      statement.setInt(1, nroPrestamo);
      statement.setInt(2, nroCuenta);
      statement.setDate(3, new java.sql.Date(fechaPago.getTime()));
      
      int filasAfectadas = statement.executeUpdate();
      if (filasAfectadas > 0) {
          conexion.commit();
          pagoRealizado = true;
      }

  } catch (SQLException e) {
      e.printStackTrace();
      try {
          Conexion.getConexion().getSQLConexion().rollback();
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
  }

  return pagoRealizado;
}

	
	@Override
	public List<Map<String, String>> obtenerCuentasPorCliente(long cuil) {
	    List<Map<String, String>> cuentas = new ArrayList<>();
	    Conexion conexion = Conexion.getConexion();
	    CallableStatement callableStatement = null;
	    ResultSet resultSet = null;
	    try {
	        callableStatement = (CallableStatement) conexion.getSQLConexion()
	                .prepareCall("{CALL obtenerCuentasPorCliente(?)}");
	        callableStatement.setLong(1, cuil);
	        resultSet = callableStatement.executeQuery();
	        while (resultSet.next()) {
	            Map<String, String> cuenta = new HashMap<>();
	            cuenta.put("Nro_cuenta", resultSet.getString("Nro_cuenta"));
	            cuenta.put("Tipo_de_cuenta", resultSet.getString("Tipo_de_cuenta"));
	            cuenta.put("Saldo", resultSet.getString("Saldo"));
	            cuentas.add(cuenta);

	            // Impresiones de depuración
	            System.out.println("Nro_cuenta: " + resultSet.getString("Nro_cuenta"));
	            System.out.println("Tipo_de_cuenta: " + resultSet.getString("Tipo_de_cuenta"));
	            System.out.println("Saldo: " + resultSet.getString("Saldo"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null)
	                resultSet.close();
	            if (callableStatement != null)
	                callableStatement.close();
	            conexion.cerrarConexion();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return cuentas;
	}

	@Override
	public List<SolicitudPrestamo> ObtenerClienteEstadoPrestamo(String usuario) {
		List<SolicitudPrestamo>devolverlist=new ArrayList<>();
		Conexion conexion=Conexion.getConexion();
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		
		try {
			callableStatement = (CallableStatement) conexion.getSQLConexion()
					.prepareCall("{CALL ObtenerClienteEstadoPrestamo (?)}");
			callableStatement.setString(1, usuario);
			resultSet=callableStatement.executeQuery();
			while(resultSet.next()) {
				SolicitudPrestamo solicitudPrestamo=new SolicitudPrestamo();
				Prestamo prestamos=new Prestamo();
				Cuenta cuenta=new Cuenta();
				
				solicitudPrestamo.setFecha_pedido(resultSet.getDate("Fecha"));
				solicitudPrestamo.setNroSolicitud(resultSet.getInt("NroSolicitud"));
				prestamos.setImporte(resultSet.getBigDecimal("ImportePedido"));
				prestamos.setInteres(resultSet.getBigDecimal("Interes"));
				prestamos.setCoutas(resultSet.getInt("PlazoPago"));
				prestamos.setTotal(resultSet.getBigDecimal("Total"));
				solicitudPrestamo.setEstado(resultSet.getString("EstadoSolicitud"));
				cuenta.setNroCuenta(resultSet.getInt("NumeroCuenta"));
				
				
				solicitudPrestamo.setPrestamos(prestamos);
				solicitudPrestamo.setCuenta(cuenta);
			devolverlist.add(solicitudPrestamo);
				
			}
					
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return devolverlist;
	}



}
