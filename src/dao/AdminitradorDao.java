package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interfaces.IAdminitradores;
import entidades.Administrador;



public class AdminitradorDao implements IAdminitradores {
private static final String buscar="Select * from administradores where Nombre_Admin = ? AND  Contrasenia = ?";
private static final String buscarID="Select ID_Admin from administradores where Nombre_Admin = ?";
private static final String DevolverInfo="Select * from administradores where Nombre_Admin = ?";
	
	public boolean VerificarExiste(String admin, String password) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion=Conexion.getConexion();
		boolean adminitradorValido=false;
		
		
		try {
			statement=conexion.getSQLConexion().prepareStatement(buscar);
			statement.setString(1, admin);
			statement.setString(2, password);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				adminitradorValido=true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return adminitradorValido;
	}


	@Override
	public int BuscarID(String Admin) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion=Conexion.getConexion();
		int resu=0;
		try {
			statement=conexion.getSQLConexion().prepareStatement(buscarID);
			statement.setString(1, Admin);
			resultSet=statement.executeQuery();
		while(resultSet.next()) {
			resu=resultSet.getInt("ID_Admin");
		}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resu;
	}
	
	public entidades.Administrador DevolverInformacion(String Admin) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion=Conexion.getConexion();
		Administrador resu=new Administrador();
		try {
			statement=conexion.getSQLConexion().prepareStatement(DevolverInfo);
			statement.setString(1, Admin);
			resultSet=statement.executeQuery();
		while(resultSet.next()) {
			resu.setIdUsuario(resultSet.getInt("ID_Admin"));
			resu.setApellido(resultSet.getString("Apellido"));
			resu.setDni(resultSet.getString("DNI"));
			resu.setNombre(resultSet.getString("Nombre"));
			resu.setAd_Usuario(resultSet.getString("Nombre_Admin"));
			
		}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resu;
	}
	@Override
	public boolean actualizarDatosPersonales(Administrador administrador) throws SQLException {
	    Conexion conexion = Conexion.getConexion();
	    CallableStatement callableStatement = null;
	    boolean actualizado = false;
	    try {
	        System.out.println("Iniciando actualización en la base de datos para el administrador: " + administrador.getIdUsuario());

	        callableStatement = (CallableStatement) conexion.getSQLConexion()
	                .prepareCall("{CALL actualizarDatosPersonalesAdmin(?, ?, ?, ?)}");
	        callableStatement.setInt(1, administrador.getIdUsuario());
	        callableStatement.setString(2, administrador.getDni());
	        callableStatement.setString(3, administrador.getNombre());
	        callableStatement.setString(4, administrador.getApellido());

	        System.out.println("Parámetros del procedimiento almacenado: " +
	                "ID_Admin=" + administrador.getIdUsuario() +
	                ", DNI=" + administrador.getDni() +
	                ", Nombre=" + administrador.getNombre() +
	                ", Apellido=" + administrador.getApellido());

	        int filasAfectadas = callableStatement.executeUpdate();
	        actualizado = (filasAfectadas > 0);

	        System.out.println("Filas afectadas: " + filasAfectadas);
	        System.out.println("Actualización completada: " + actualizado);

	        // Confirmar los cambios manualmente si es necesario
	        if (actualizado) {
	            conexion.getSQLConexion().commit();
	            System.out.println("Cambios confirmados en la base de datos.");
	        } else {
	            System.out.println("No se realizaron cambios en la base de datos.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // En caso de error, revertir los cambios
	        try {
	            conexion.getSQLConexion().rollback();
	            System.out.println("Se han revertido los cambios debido a un error.");
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	            System.out.println("Error al revertir los cambios.");
	        }
	        throw new SQLException("Error al actualizar los datos personales del administrador", e);
	    } finally {
	        if (callableStatement != null) {
	            callableStatement.close();
	        }
	        conexion.cerrarConexion();
	    }
	    return actualizado;
	}
	
	@Override
	public boolean cambiarContraseniaAdmin(int adminID, String contraActual, String nuevaContra) throws SQLException {
	    Conexion conexion = Conexion.getConexion();
	    CallableStatement callableStatement = null;
	    boolean cambiado = false;
	    try {
	        System.out.println("Iniciando cambio de contraseña en la base de datos para el administrador ID: " + adminID);

	        callableStatement = (CallableStatement) conexion.getSQLConexion()
	                .prepareCall("{CALL cambiarContraseniaAdmin(?, ?, ?, ?)}");
	        callableStatement.setInt(1, adminID);
	        callableStatement.setString(2, contraActual);
	        callableStatement.setString(3, nuevaContra);
	        callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
	        
	        callableStatement.execute();
	        
	        int resultado = callableStatement.getInt(4);
	        cambiado = (resultado == 1);

	        // Confirmar los cambios manualmente si es necesario
	        conexion.getSQLConexion().commit();

	        System.out.println("Cambio de contraseña completado: " + cambiado);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // En caso de error, revertir los cambios
	        conexion.getSQLConexion().rollback();
	        throw new SQLException("Error al cambiar la contraseña del administrador", e);
	    } finally {
	        if (callableStatement != null) {
	            callableStatement.close();
	        }
	        conexion.cerrarConexion();
	    }
	    return cambiado;
	}
	

}
