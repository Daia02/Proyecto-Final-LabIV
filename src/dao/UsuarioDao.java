package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IUsuario;

import java.sql.CallableStatement;

import entidades.Cliente;
import entidades.Usuario;
import dao.Conexion;

public class UsuarioDao implements IUsuario{
    private static final String HOLA = "SELECT " +
        "c.CUIL_cl, " +
        "c.Nombre, " +
        "c.Apellido, " +
        "u.Nombre_usuario, " +
        "u.Estado " +
        "FROM clientes c " +
        "INNER JOIN usuarios u ON c.ID_Usuario = u.ID_Usuario " +
        "WHERE u.Nombre_usuario = ?";

    // Método para verificar si un usuario existe
    @Override
    public boolean VerificarExiste(String client, String password) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion conexion = Conexion.getConexion();
        String queryInicio = "Select * from usuarios where Nombre_usuario = ? AND Contrasenia = ?";
        boolean adminitradorValido = false;

        try {
            statement = conexion.getSQLConexion().prepareStatement(queryInicio);
            statement.setString(1, client);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                adminitradorValido = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return adminitradorValido;
    }

    // Método para verificar si un usuario existe por ID
    @Override
    public boolean VerificarExistexID(String id) {
        String query = "SELECT * FROM Usuarios WHERE ID_Usuario = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion conexion = Conexion.getConexion();
        boolean adminitradorValido = false;

        try {
            statement = conexion.getSQLConexion().prepareStatement(query);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                adminitradorValido = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return adminitradorValido;
    }

    // Método para devolver información del usuario
    @Override
    public Cliente devolverInformacionUsuario(String nombre) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion conexion = Conexion.getConexion();
        Cliente cliente = new Cliente();
        try {
            statement = conexion.getSQLConexion().prepareStatement(HOLA);
            statement.setString(1, nombre);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cliente.setCuilCl(resultSet.getString("CUIL_cl"));
                
                // Asignar otros campos necesarios a cliente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }

    
    
    
    // Método para buscar un usuario por nombre usando procedimiento almacenado
    @Override
    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        Usuario usuario = null;
        Conexion conexion = Conexion.getConexion();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = (CallableStatement) conexion.getSQLConexion().prepareCall("{CALL BuscarUsuarioPorNombre(?)}");
            callableStatement.setString(1, nombreUsuario);
            resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("ID_Usuario"));
                usuario.setNombre(resultSet.getString("Nombre_usuario"));
                usuario.setContraseña(resultSet.getString("Contrasenia"));
                usuario.setEstado(resultSet.getBoolean("Estado"));
                // Asignar tipo de usuario si corresponde
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                conexion.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }

    // Método para buscar un administrador usando procedimiento almacenado
    @Override
    public boolean buscarAdministrador(String nombreUsuario, String contrasenia) {
        boolean existe = false;
        Conexion conexion = Conexion.getConexion();
        CallableStatement callableStatement = null;

        try {
            callableStatement = (CallableStatement) conexion.getSQLConexion().prepareCall("{CALL buscarAdministrador(?, ?, ?)}");
            callableStatement.setString(1, nombreUsuario);
            callableStatement.setString(2, contrasenia);
            callableStatement.registerOutParameter(3, java.sql.Types.BOOLEAN);

            callableStatement.execute();

            existe = callableStatement.getBoolean(3);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                conexion.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;
    }
    @Override
    public Map<String, Object> obtenerInformacionUsuario(long cuil) {
        Map<String, Object> informacionUsuario = new HashMap<>();
        Conexion conexion = Conexion.getConexion();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = (CallableStatement) conexion.getSQLConexion().prepareCall("{CALL ObtenerInformacionUsuario(?)}");
            callableStatement.setLong(1, cuil);
            resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                // Mapear resultados al mapa
                informacionUsuario.put("CUIL", resultSet.getLong("CUIL"));
                informacionUsuario.put("DNI", resultSet.getString("DNI"));
                informacionUsuario.put("Nombre", resultSet.getString("Nombre"));
                informacionUsuario.put("Apellido", resultSet.getString("Apellido"));
                informacionUsuario.put("Sexo", resultSet.getString("Sexo"));
                informacionUsuario.put("Fecha de Nacimiento", resultSet.getDate("Fecha de Nacimiento"));
                informacionUsuario.put("Dirección", resultSet.getString("Dirección"));
                informacionUsuario.put("Correo Electrónico", resultSet.getString("Correo Electrónico"));
                informacionUsuario.put("Teléfono", resultSet.getString("Teléfono"));
                informacionUsuario.put("Estado Cliente", resultSet.getBoolean("Estado Cliente"));

                Map<String, Object> usuario = new HashMap<>();
                usuario.put("Nombre de Usuario", resultSet.getString("Nombre de Usuario"));
                usuario.put("Contraseña", resultSet.getString("Contraseña"));
                usuario.put("Estado Usuario", resultSet.getBoolean("Estado Usuario"));
                informacionUsuario.put("Usuario", usuario);

                Map<String, Object> localidad = new HashMap<>();
                localidad.put("Nombre de Localidad", resultSet.getString("Nombre de Localidad"));
                informacionUsuario.put("Localidad", localidad);

                Map<String, Object> provincia = new HashMap<>();
                provincia.put("Nombre de Provincia", resultSet.getString("Nombre de Provincia"));
                informacionUsuario.put("Provincia", provincia);

                Map<String, Object> nacionalidad = new HashMap<>();
                nacionalidad.put("Nacionalidad", resultSet.getString("Nacionalidad"));
                informacionUsuario.put("Nacionalidad", nacionalidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                conexion.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return informacionUsuario;
    }
    @Override
    public boolean actualizarInformacionContacto(Cliente cliente) throws SQLException {
        Conexion conexion = Conexion.getConexion();
        CallableStatement callableStatement = null;
        boolean actualizado = false;
        try {
            System.out.println("Iniciando actualización en la base de datos para el cliente: " + cliente.getCuilCl());

            callableStatement = (CallableStatement) conexion.getSQLConexion()
                    .prepareCall("{CALL actualizarInformacionContacto(?, ?, ?, ?, ?, ?)}");
            callableStatement.setLong(1, Long.parseLong(cliente.getCuilCl()));
            callableStatement.setInt(2, Integer.parseInt(cliente.getNroLocalidad()));
            callableStatement.setInt(3, Integer.parseInt(cliente.getNroProvincia()));
            callableStatement.setString(4, cliente.getDireccion());
            callableStatement.setString(5, cliente.getCorreoElectronico());
            callableStatement.setString(6, cliente.getTelefono());
            
            int filasAfectadas = callableStatement.executeUpdate();
            actualizado = (filasAfectadas > 0);

            // Confirmar los cambios manualmente si es necesario
            conexion.getSQLConexion().commit();

            System.out.println("Actualización completada: " + actualizado);
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error, revertir los cambios
            conexion.getSQLConexion().rollback();
            throw new SQLException("Error al actualizar la información de contacto", e);
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            conexion.cerrarConexion();
        }
        return actualizado;
    }
    
    @Override
    public boolean cambiarContrasenia(int usuarioID, String contraActual, String nuevaContra) throws SQLException {
        Conexion conexion = Conexion.getConexion();
        CallableStatement callableStatement = null;
        boolean cambiado = false;
        try {
            System.out.println("Iniciando cambio de contraseña en la base de datos para el usuario ID: " + usuarioID);

            callableStatement = (CallableStatement) conexion.getSQLConexion()
                    .prepareCall("{CALL cambiarContrasenia(?, ?, ?, ?)}");
            callableStatement.setInt(1, usuarioID);
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
            throw new SQLException("Error al cambiar la contraseña", e);
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            conexion.cerrarConexion();
        }
        return cambiado;
    }
    
    
 // Método para obtener los números de cuenta del cliente asociado a un usuario
    public List<Integer> obtenerNumerosDeCuentaPorUsuario(int idUsuario) {
        List<Integer> numerosDeCuenta = new ArrayList<>();
        Conexion conexion = Conexion.getConexion();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            // Llamamos al procedimiento almacenado
            callableStatement = (CallableStatement) conexion.getSQLConexion().prepareCall("{CALL obtener_numeros_de_cuenta(?)}");
            callableStatement.setInt(1, idUsuario);  // Pasamos el ID_Usuario como parámetro
            resultSet = callableStatement.executeQuery();

            // Recorremos el resultado y obtenemos los números de cuenta
            while (resultSet.next()) {
                numerosDeCuenta.add(resultSet.getInt("Nro_cuenta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                conexion.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return numerosDeCuenta;
    }
    
  
    
    public Integer obtenerIdUsuarioPorNombre(String nombreUsuario) {
        Conexion conexion = Conexion.getConexion();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Integer idUsuario = null;

        try {
            // Preparar la llamada al procedimiento almacenado
            callableStatement = (CallableStatement) conexion.getSQLConexion().prepareCall("{CALL obtener_id_usuario_por_nombre(?)}");
            callableStatement.setString(1, nombreUsuario);  // Pasamos el nombre de usuario como parámetro
            
            // Ejecutamos la consulta y obtenemos los resultados
            resultSet = callableStatement.executeQuery();
            
            // Si el procedimiento devuelve el ID, lo asignamos a la variable idUsuario
            if (resultSet.next()) {
                // Verificamos si la columna contiene el ID de usuario
                if (resultSet.getString("ID_Usuario") != null) {
                    idUsuario = resultSet.getInt("ID_Usuario");
                } else {
                    System.out.println("No se encontró un usuario con ese nombre.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                conexion.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return idUsuario;  // Devuelve el ID de usuario encontrado o null si no se encontró
    }
    
 // Método para obtener los números de cuenta de un usuario
    public List<Integer> obtenerNumerosDeCuenta(int idUsuario) {
        List<Integer> numerosDeCuenta = new ArrayList<>();
        Conexion conexion = Conexion.getConexion();
     
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = (CallableStatement) conexion.getSQLConexion().prepareCall("{CALL obtener_numeros_de_cuenta(?)}");
            callableStatement.setInt(1, idUsuario);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                try {
                    numerosDeCuenta.add(resultSet.getInt("Nro_cuenta"));
                } catch (SQLException e) {
                    // Manejo del caso en que la consulta devuelva un mensaje de error en lugar de un número de cuenta
                    String errorMensaje = resultSet.getString("Error");
                    System.err.println("Error: " + errorMensaje);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                conexion.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return numerosDeCuenta;
    }

    
}
