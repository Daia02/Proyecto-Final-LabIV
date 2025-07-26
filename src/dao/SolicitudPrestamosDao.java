package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IPrestamosAdmin;
import entidades.Administrador;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import entidades.SolicitudPrestamo;
import entidades.TipoCuenta;
import negocio.PrestamosNegocio;

public class SolicitudPrestamosDao implements IPrestamosAdmin{
	private static final String lista="SELECT * FROM solicitudes_prestamos WHERE solicitudes_prestamos.Estado = 'Pendiente' ORDER BY solicitudes_prestamos.Fecha_solicitud ASC;";
	private static final String devolver="select * from solicitudes_prestamos where solicitudes_prestamos.Nro_solicitud=? ";
	
	
	public boolean RechazarPrestamos(int numeroSolicitud,int IDAdmin) {
		boolean insertado =false;
		String InsertePrestamosActualizado="{call RechazarPrestamo(? ,? )}";
		try {Connection conexion=Conexion.getConexion().getSQLConexion();
		CallableStatement statement=conexion.prepareCall(InsertePrestamosActualizado);
		parametosRechazo(statement,numeroSolicitud,IDAdmin);
			
		int filasAfectadas=statement.executeUpdate();
		if(filasAfectadas>0) {
			conexion.commit();
			insertado=true;
		}
		}catch(SQLException e){
			e.printStackTrace();
			realizarRollback();
		}
		
		
		return insertado;
	}
		private void parametosRechazo(PreparedStatement statement, int numero,int IdAdmin) throws SQLException {
			
			statement.setInt(1, numero);
			statement.setInt(2, IdAdmin);
		}
	
	
	@Override
	public boolean Actualizar_solicitud(SolicitudPrestamo solicitudPrestamo) {
		boolean insertado =false;
		String InsertePrestamosActualizado="{call AprobarPrestamo(? ,? ,? ,? ,? ,? ,?)}";
		try {Connection conexion=Conexion.getConexion().getSQLConexion();
		CallableStatement statement=conexion.prepareCall(InsertePrestamosActualizado);
		parametros(statement,solicitudPrestamo);
			
		int filasAfectadas=statement.executeUpdate();
		if(filasAfectadas>0) {
			conexion.commit();
			insertado=true;
		}
		}catch(SQLException e){
			e.printStackTrace();
			realizarRollback();
		}
		
		
		return insertado;
	}
	private void realizarRollback() {
	    try (Connection conexion = Conexion.getConexion().getSQLConexion()) {
	        if (conexion != null) {
	            conexion.rollback();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }}
	
	private void parametros(PreparedStatement statement,entidades.SolicitudPrestamo solicitudPrestamo) throws SQLException {
		statement.setInt(1, solicitudPrestamo.getNroSolicitud());
		statement.setInt(2, solicitudPrestamo.getAdministrador().getIdUsuario());
		statement.setBigDecimal(3,solicitudPrestamo.getPrestamos().getImporte() );
		statement.setBigDecimal(4, solicitudPrestamo.getPrestamos().getTotal());
		statement.setInt(5, solicitudPrestamo.getPrestamos().getCoutas());
		statement.setBigDecimal(6, solicitudPrestamo.getPrestamos().getInteres());
		statement.setInt(7, solicitudPrestamo.getPrestamos().getCoutas());
		
		
	}
	@Override
	public List<SolicitudPrestamo> ListaSolicitud() {
		List<SolicitudPrestamo> tipo = new ArrayList<>();
	      PreparedStatement statement = null;
	      ResultSet resultSet = null;
	      
	      try {
	          Conexion conexion = Conexion.getConexion(); // Obtener conexión
	          statement = conexion.getSQLConexion().prepareStatement(lista);
	         
	          resultSet = statement.executeQuery();

	          while (resultSet.next()) {
	        	  SolicitudPrestamo solicitudPrestamo=new SolicitudPrestamo();
	        	  Cliente cliente=new Cliente();
	        		Prestamo prestamo=new Prestamo();	 
	        		Cuenta cuenta=new Cuenta();
	             solicitudPrestamo.setNroSolicitud(resultSet.getInt("Nro_solicitud"));
	           cliente.setCuilCl(resultSet.getString("CUIL_pr"));
	           prestamo.setImporte(resultSet.getBigDecimal("Importe_pedido"));
	           prestamo.setInteres(resultSet.getBigDecimal("Interes"));
	           prestamo.setCoutas(resultSet.getInt("Plazo_de_pago"));
	           prestamo.setTotal(resultSet.getBigDecimal("Total_a_pagar"));
	           solicitudPrestamo.setEstado(resultSet.getString("Estado"));
	           solicitudPrestamo.setFecha_pedido(resultSet.getDate("Fecha_Solicitud"));
	           cuenta.setNroCuenta(resultSet.getInt("Nro_cuenta"));
	           
	           		prestamo.setCliente(cliente);
	           		solicitudPrestamo.setCuenta(cuenta);
	           		solicitudPrestamo.setPrestamos(prestamo);
	           		
	        	  
	              tipo.add(solicitudPrestamo);
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
	@Override
	public SolicitudPrestamo DevueltePrestamosAtravezNumero(int numeroSolicitud) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion=Conexion.getConexion();
		SolicitudPrestamo solicitudPrestamo=new SolicitudPrestamo();
		try {
			statement=conexion.getSQLConexion().prepareStatement(devolver);
			statement.setInt(1, numeroSolicitud);
			resultSet=statement.executeQuery();
		while(resultSet.next()) {
			
      	  Cliente cliente=new Cliente();
      		Prestamo prestamo=new Prestamo();	 
      		Cuenta cuenta=new Cuenta();
           solicitudPrestamo.setNroSolicitud(resultSet.getInt("Nro_solicitud"));
         cliente.setCuilCl(resultSet.getString("CUIL_pr"));
         prestamo.setImporte(resultSet.getBigDecimal("Importe_pedido"));
         prestamo.setInteres(resultSet.getBigDecimal("Interes"));
         prestamo.setCoutas(resultSet.getInt("Plazo_de_pago"));
         prestamo.setTotal(resultSet.getBigDecimal("Total_a_pagar"));
         solicitudPrestamo.setEstado(resultSet.getString("Estado"));
         solicitudPrestamo.setFecha_pedido(resultSet.getDate("Fecha_Solicitud"));
         cuenta.setNroCuenta(resultSet.getInt("Nro_cuenta"));
         
         		prestamo.setCliente(cliente);
         		solicitudPrestamo.setCuenta(cuenta);
         		solicitudPrestamo.setPrestamos(prestamo);
		}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return solicitudPrestamo;
	}
	
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
	@Override
	public List<SolicitudPrestamo> obtenerHistorialPrestamos() {
		List<SolicitudPrestamo>devolverlist=new ArrayList<>();
		Conexion conexion=Conexion.getConexion();
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		
		try {
			callableStatement = (CallableStatement) conexion.getSQLConexion()
					.prepareCall("{CALL ObtenerHistorialPrestamos()}");
			
			resultSet=callableStatement.executeQuery();
			while(resultSet.next()) {
				SolicitudPrestamo solicitudPrestamo=new SolicitudPrestamo();
				Prestamo prestamos=new Prestamo();
				Cuenta cuenta=new Cuenta();
				Cliente cliente=new Cliente();
				Administrador administrador=new Administrador();
				
				solicitudPrestamo.setNroSolicitud(resultSet.getInt("Nro_solicitud"));
				solicitudPrestamo.setFecha_pedido(resultSet.getDate("Fecha_solicitud"));
				
				cliente.setCuilCl(resultSet.getString("CUIL_pr"));
				cuenta.setNroCuenta(resultSet.getInt("Nro_cuenta"));
				
				prestamos.setImporte(resultSet.getBigDecimal("Importe_pedido"));
				prestamos.setCoutas(resultSet.getInt("Plazo_de_pago"));
				prestamos.setInteres(resultSet.getBigDecimal("Interes"));
				prestamos.setTotal(resultSet.getBigDecimal("Total_a_pagar"));
				
				solicitudPrestamo.setEstado(resultSet.getString("Estado_final"));
				
				administrador.setAd_Usuario(resultSet.getString("Nombre_Admin"));
				prestamos.setCliente(cliente);
				
				
				solicitudPrestamo.setPrestamos(prestamos);
				solicitudPrestamo.setCuenta(cuenta);
				solicitudPrestamo.setAdministrador(administrador);
			devolverlist.add(solicitudPrestamo);
				
			}
					
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return devolverlist;

	}

	
	
}
