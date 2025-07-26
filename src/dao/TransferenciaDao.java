package dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cuenta;;

public class TransferenciaDao {
	 private Conexion conexion;

	    public TransferenciaDao() {
	        this.conexion = Conexion.getConexion();
	    }

	    public void transferirDineroPorCBU(String cbuOrigen, String cbuDestino, double monto) throws SQLException {
	        String query = "{CALL TransferirDineroPorCBU(?, ?, ?)}";
	        try (Connection conn = conexion.getSQLConexion();
	             CallableStatement stmt = conn.prepareCall(query)) {

	            // Establecer los parámetros de entrada
	            stmt.setString(1, cbuOrigen);
	            stmt.setString(2, cbuDestino);
	            stmt.setDouble(3, monto);

	            // Ejecutar el procedimiento
	            stmt.execute();
	        } catch (SQLException e) {
	            // Lanza la excepción para manejarla en la capa de negocio
	            throw new SQLException("Error al realizar la transferencia: " + e.getMessage(), e);
	        }
	    }
	
	    
	    // Método para obtener las cuentas de un usuario (usando ID_Usuario)
	    public List<Cuenta> obtenerCuentasPorUsuario(int idUsuario) throws SQLException {
	        String query = "{CALL ObtenerCuentasPorUsuario(?)}"; // Llamada al SP
	        List<Cuenta> cuentas = new ArrayList<>();
	        
	        try (Connection conn = conexion.getSQLConexion();
	             CallableStatement stmt = conn.prepareCall(query)) { // Usar CallableStatement para SP

	            stmt.setInt(1, idUsuario);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                int nroCuenta = rs.getInt("Nro_cuenta");
	                double saldo = rs.getDouble("Saldo");

	                Cuenta cuenta = new Cuenta();
	                cuenta.setNroCuenta(nroCuenta);
	                cuenta.setSaldo(saldo);
	                cuentas.add(cuenta);
	            }
	        } catch (SQLException e) {
	            throw new SQLException("Error al obtener las cuentas: " + e.getMessage(), e);
	        }
	        
	        return cuentas;
	    }

	
	
}
