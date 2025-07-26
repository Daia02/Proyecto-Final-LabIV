package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Interfaces.ICliente;
import entidades.Cliente;

public class ClienteDao implements ICliente {

	@Override
	public Cliente obtenerClienteUnico(String CUIL) {
	    Cliente cliente = new Cliente();
	    String query = "SELECT * FROM clientes WHERE CUIL_cl = ?";
	    
	    try (Connection connection = Conexion.getConexion().getSQLConexion();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        
	        statement.setString(1, CUIL);
	        try (ResultSet rs = statement.executeQuery()) {
	            if (rs.next()) {
	                cliente.setCuilCl(String.valueOf(rs.getLong("CUIL_cl")));
	                cliente.setIdUsuario(String.valueOf(rs.getInt("ID_Usuario")));
	                cliente.setNroLocalidad(String.valueOf(rs.getInt("Nro_Localidad")));
	                cliente.setNroProvincia(String.valueOf(rs.getInt("Nro_Provincia")));
	                cliente.setNroNacionalidad(String.valueOf(rs.getInt("Nro_Nacionalidad")));
	                cliente.setDni(rs.getString("DNI"));
	                cliente.setNombre(rs.getString("Nombre"));
	                cliente.setApellido(rs.getString("Apellido"));
	                cliente.setSexo(rs.getString("Sexo"));
	                cliente.setFechaNacimiento(rs.getDate("Fecha_de_nacimiento"));
	                cliente.setDireccion(rs.getString("Dirección"));
	                cliente.setCorreoElectronico(rs.getString("Correo_electrónico"));
	                cliente.setTelefono(rs.getString("Teléfono"));
	                cliente.setActivo(rs.getBoolean("Estado"));
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener cliente con CUIL: " + CUIL);
	        e.printStackTrace();
	        return null;
	    }
	    return cliente.getCuilCl() != null && !cliente.getCuilCl().isEmpty() ? cliente : null;
	}

	@Override
	public List<Cliente> obtenerClientesFiltrados(String cuil, String idUsuario, String dni, String nombre, String apellido, String sexo, String nacionalidad, String provincia, String localidad) {
	    List<Cliente> clientes = new ArrayList<>();
	    String query = "{call FiltrarClientes(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

	    try (Connection conexion = Conexion.getConexion().getSQLConexion();
	         CallableStatement callableStatement = conexion.prepareCall(query)) {

	        // Configurar parámetros, manejando nulos y vacíos
	        callableStatement.setString(1, cuil != null && !cuil.trim().isEmpty() ? cuil : null);
	        callableStatement.setString(2, idUsuario != null && !idUsuario.trim().isEmpty() ? idUsuario : null);
	        callableStatement.setString(3, dni != null && !dni.trim().isEmpty() ? dni : null);
	        callableStatement.setString(4, nombre != null && !nombre.trim().isEmpty() ? nombre : null);
	        callableStatement.setString(5, apellido != null && !apellido.trim().isEmpty() ? apellido : null);
	        callableStatement.setString(6, sexo != null && !sexo.trim().isEmpty() ? sexo : null);
	        callableStatement.setString(7, nacionalidad != null && !nacionalidad.equals("0") ? nacionalidad : null);
	        callableStatement.setString(8, provincia != null && !provincia.equals("0") ? provincia : null);
	        callableStatement.setString(9, localidad != null && !localidad.equals("0") ? localidad : null);

	        try (ResultSet resultSet = callableStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Cliente cliente = new Cliente();
	                cliente.setCuilCl(resultSet.getString("CUIL_cl"));
	                cliente.setIdUsuario(resultSet.getString("ID_Usuario"));
	                cliente.setNroLocalidad(resultSet.getString("Nro_Localidad"));
	                cliente.setNroProvincia(resultSet.getString("Nro_Provincia"));
	                cliente.setNroNacionalidad(resultSet.getString("Nro_Nacionalidad"));
	                cliente.setDni(resultSet.getString("DNI"));
	                cliente.setNombre(resultSet.getString("Nombre"));
	                cliente.setApellido(resultSet.getString("Apellido"));
	                cliente.setSexo(resultSet.getString("Sexo"));
	                cliente.setFechaNacimiento(resultSet.getDate("Fecha_de_nacimiento"));
	                cliente.setDireccion(resultSet.getString("Dirección"));
	                cliente.setCorreoElectronico(resultSet.getString("Correo_electrónico"));
	                cliente.setTelefono(resultSet.getString("Teléfono"));
	                cliente.setActivo(resultSet.getBoolean("Estado"));
	                clientes.add(cliente);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return clientes;
	}



    // Actualizar un cliente
	@Override
    public boolean actualizarCliente(Cliente cliente) {
        String query = "{call ActualizarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getConexion().getSQLConexion();
             CallableStatement statement = conexion.prepareCall(query)) {

            configurarParametros(statement, cliente);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            realizarRollback();
        }
        return false;
    }

	@Override
    public boolean agregarCliente(Cliente cliente) {
        boolean insertado = false;
        String INSERT_CLIENTE = "INSERT INTO Clientes "
                + "(CUIL_cl, ID_Usuario, Nro_Localidad, Nro_Provincia, Nro_Nacionalidad, DNI, Nombre, Apellido, Sexo, Fecha_de_nacimiento, Dirección, Correo_electrónico, Teléfono, Estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conexion = Conexion.getConexion().getSQLConexion();
             PreparedStatement statement = conexion.prepareStatement(INSERT_CLIENTE)) {
            
            // Configurar parámetros
            statement.setLong(1, Long.parseLong(cliente.getCuilCl()));
            statement.setInt(2, Integer.parseInt(cliente.getIdUsuario()));
            statement.setInt(3, Integer.parseInt(cliente.getNroLocalidad()));
            statement.setInt(4, Integer.parseInt(cliente.getNroProvincia()));
            statement.setInt(5, Integer.parseInt(cliente.getNroNacionalidad()));
            statement.setString(6, cliente.getDni());
            statement.setString(7, cliente.getNombre());
            statement.setString(8, cliente.getApellido());
            statement.setString(9, cliente.getSexo().substring(0, 1).toUpperCase());
            statement.setDate(10, cliente.getFechaNacimiento());
            statement.setString(11, cliente.getDireccion());
            statement.setString(12, cliente.getCorreoElectronico());
            statement.setString(13, cliente.getTelefono());
            statement.setBoolean(14, cliente.isActivo());

            // Ejecutar
            if (statement.executeUpdate() > 0) {
                conexion.commit();
                insertado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertado;
    }


    // Obtener todos los clientes
	@Override
    public List<Cliente> obtenerTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Clientes WHERE Estado = true;";
        try (Connection conexion = Conexion.getConexion().getSQLConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = mapearCliente(rs);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Métodos auxiliares

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setCuilCl(rs.getString("CUIL_cl"));
        cliente.setIdUsuario(rs.getString("ID_Usuario"));
        cliente.setNroLocalidad(rs.getString("Nro_Localidad"));
        cliente.setNroProvincia(rs.getString("Nro_Provincia"));
        cliente.setNroNacionalidad(rs.getString("Nro_Nacionalidad"));
        cliente.setDni(rs.getString("DNI"));
        cliente.setNombre(rs.getString("Nombre"));
        cliente.setApellido(rs.getString("Apellido"));
        cliente.setSexo(rs.getString("Sexo"));
        cliente.setFechaNacimiento(rs.getDate("Fecha_de_nacimiento"));
        cliente.setDireccion(rs.getString("Dirección"));
        cliente.setCorreoElectronico(rs.getString("Correo_electrónico"));
        cliente.setTelefono(rs.getString("Teléfono"));
        cliente.setActivo(rs.getBoolean("Estado"));
        return cliente;
    }
    
    
    public List<String> obtenerTodosLosCuils() {
        List<String> cuiles = new ArrayList<>();
       
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection con = Conexion.getConexion().getSQLConexion();
            String query = "SELECT CUIL_cl FROM Clientes WHERE Estado = 1"; // Solo clientes activos
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                cuiles.add(rs.getString("CUIL_cl"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cuiles;
    }


    private void configurarParametros(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setLong(1, Long.parseLong(cliente.getCuilCl()));
        statement.setInt(2, Integer.parseInt(cliente.getIdUsuario()));
        statement.setInt(3, Integer.parseInt(cliente.getNroLocalidad()));
        statement.setInt(4, Integer.parseInt(cliente.getNroProvincia()));
        statement.setInt(5, Integer.parseInt(cliente.getNroNacionalidad()));
        statement.setString(6, cliente.getDni());
        statement.setString(7, cliente.getNombre());
        statement.setString(8, cliente.getApellido());
        statement.setString(9, cliente.getSexo().substring(0, 1).toUpperCase());
        statement.setDate(10, cliente.getFechaNacimiento());
        statement.setString(11, cliente.getDireccion());
        statement.setString(12, cliente.getCorreoElectronico());
        statement.setString(13, cliente.getTelefono());
        statement.setBoolean(14, cliente.isActivo());
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
	public boolean bajaLogicaCliente(long cuil) {
		Connection con = Conexion.getConexion().getSQLConexion();
		try {
			String query = "{ CALL BajaLogicaCliente(?) }";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, cuil);
			int result = ps.executeUpdate();
			con.commit();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return false;
		} finally {
			Conexion.getConexion().cerrarConexion();
		}
	}
    

    
}
