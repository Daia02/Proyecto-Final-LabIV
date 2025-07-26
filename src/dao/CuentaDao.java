package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import entidades.Cuenta;

public class CuentaDao {

    public boolean agregarCuenta(Cuenta crearCuenta) {
        boolean seAgrego = false;
        Conexion conexion = Conexion.getConexion();
        
        String query = "INSERT INTO `Cuentas` (`Nro_cuenta`, `CUIL_cu`, `Nro_Tipo_cuenta`, `Fecha_de_creación`, `CBU`, `Saldo`, `Estado`) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = conexion.getSQLConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, crearCuenta.getNroCuenta());
            preparedStatement.setLong(2, crearCuenta.getCuilCu());
            preparedStatement.setInt(3, crearCuenta.getNroTipoCuenta());
            
            java.sql.Date fechaSql = new java.sql.Date(crearCuenta.getFechaCreacion().getTime());
            preparedStatement.setDate(4, fechaSql);
            
            preparedStatement.setString(5, crearCuenta.getCbu());
            preparedStatement.setDouble(6, crearCuenta.getSaldo());
            preparedStatement.setBoolean(7, true); // Estado activo
            
            if (preparedStatement.executeUpdate() > 0) {
                conexion.getSQLConexion().commit();
                seAgrego = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        
        return seAgrego;
    }

    // Método corregido para obtener el promedio de cuentas nuevas
    public static double obtenerPromedioCuentasNuevas(String startDate, String endDate) {
        double promedio = 0.0;
        String query = "SELECT COUNT(*) AS total_cuentas, DATEDIFF(?, ?) AS dias "
                + "FROM cuentas WHERE fecha_de_creación BETWEEN ? AND ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConexion().getSQLConexion();  // Obtener conexión
            stmt = conn.prepareStatement(query);

            // Establecer los parámetros de la consulta
            stmt.setString(1, endDate);
            stmt.setString(2, startDate);
            stmt.setString(3, startDate);
            stmt.setString(4, endDate);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int totalCuentas = rs.getInt("total_cuentas");
                int dias = rs.getInt("dias");

                // Se calcula el promedio de cuentas creadas por día
                promedio = dias > 0 ? (double) totalCuentas / dias : totalCuentas;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();  // Cerrar PreparedStatement
                }
                if (conn != null) {
                    conn.close();  // Cerrar Connection
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return promedio;
    }

    
    public boolean eliminarCuenta(int nroCuenta) {
    	 boolean seActualizo = false;
    	    Conexion conexion = Conexion.getConexion();
    	    
    	    String query = "UPDATE Cuentas SET Estado = 0 WHERE Nro_cuenta = ?";
    	    
    	    try (PreparedStatement preparedStatement = conexion.getSQLConexion().prepareStatement(query)) {
    	        preparedStatement.setInt(1, nroCuenta);
    	        
    	        if (preparedStatement.executeUpdate() > 0) {
    	            conexion.getSQLConexion().commit();
    	            seActualizo = true;
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        conexion.cerrarConexion();
    	    }
    	    
    	    return seActualizo;
    }

    
    public static int obtenerTotalCuentasNuevas(String startDate, String endDate) {
        int totalCuentas = 0;
        String query = "SELECT COUNT(*) AS total_cuentas FROM cuentas WHERE fecha_de_creación BETWEEN ? AND ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConexion().getSQLConexion();  // Obtener conexión
            stmt = conn.prepareStatement(query);

            // Establecer los parámetros de la consulta
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalCuentas = rs.getInt("total_cuentas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();  // Cerrar PreparedStatement
                }
                if (conn != null) {
                    conn.close();  // Cerrar Connection
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return totalCuentas;
    }

    
    public ArrayList<Cuenta> obtenerCuentas() {
        ArrayList<Cuenta> lista = new ArrayList<>();
        String query = "SELECT Nro_cuenta, CUIL_cu, Nro_Tipo_cuenta, Fecha_de_creación, CBU, Saldo, Estado FROM Cuentas WHERE Estado = 1";

        try (Connection conn = Conexion.getConexion().getSQLConexion();
             Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Permitir retroceder
             ResultSet rs = st.executeQuery(query)) {

            // Verificar si hay resultados
            if (!rs.next()) {
                System.out.println("No hay cuentas en la base de datos.");
            } else {
                // Volver al inicio del ResultSet
                rs.beforeFirst();

                // Mapeando los resultados
                while (rs.next()) {
                    Cuenta cuentaRs = new Cuenta();
                    cuentaRs.setNroCuenta(rs.getInt("Nro_cuenta"));
                    cuentaRs.setCuilCu(rs.getLong("CUIL_cu"));
                    cuentaRs.setNroTipoCuenta(rs.getInt("Nro_Tipo_cuenta"));
                    cuentaRs.setFechaCreacion(rs.getDate("Fecha_de_creación"));
                    cuentaRs.setCbu(rs.getString("CBU"));
                    cuentaRs.setSaldo(rs.getDouble("Saldo"));
                    cuentaRs.setActivo(rs.getBoolean("Estado"));
                    lista.add(cuentaRs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener las cuentas: " + e.getMessage());
        }
        return lista;
    }


    public int ultimoIDCuenta() {
        int ultimo = 1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Conexion conexion = Conexion.getConexion();
        try {
            Statement statement = conexion.getSQLConexion().createStatement();
            String query = "SELECT MAX(Nro_cuenta) AS numCuenta FROM Cuentas WHERE Estado = 1";
            ResultSet resultado = statement.executeQuery(query);
            if (resultado.next()) {
                ultimo = resultado.getInt("numCuenta");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        
        return ultimo + 1;
    }

    public long totalCuentas(long l) {
        long total = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Conexion conexion = Conexion.getConexion();
        try {
            Statement statement = conexion.getSQLConexion().createStatement();
            String query = "SELECT COUNT(*) AS TotalCuenta FROM cuentas WHERE CUIL_cu = ?";
            try (PreparedStatement stmt = conexion.getSQLConexion().prepareStatement(query)) {
                stmt.setLong(1, l);
                ResultSet resultado = stmt.executeQuery();
                if (resultado.next()) {
                    total = resultado.getLong("TotalCuenta");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        
        return total;
    }
    
    
    //Metodo para modificar una cuenta:
    
    public boolean modificarCuenta(Cuenta cuenta) {
        boolean seModifico = false;
        Conexion conexion = Conexion.getConexion();
        String query = "{ CALL sp_modificarCuenta(?, ?, ?, ?, ?, ?) }"; // Llamada al procedimiento almacenado

        try (CallableStatement callableStatement = (CallableStatement) conexion.getSQLConexion().prepareCall(query)) {
            // Establecer los parámetros del procedimiento
            callableStatement.setInt(1, cuenta.getNroCuenta());           // Número de cuenta
            callableStatement.setLong(2, cuenta.getCuilCu());             // CUIL
            callableStatement.setInt(3, cuenta.getNroTipoCuenta());      // Tipo de cuenta
            callableStatement.setDate(4, new java.sql.Date(cuenta.getFechaCreacion().getTime())); // Fecha de creación
            callableStatement.setString(5, cuenta.getCbu());             // CBU
            callableStatement.setDouble(6, cuenta.getSaldo());           // Saldo

            // Ejecutar el procedimiento
            boolean resultado = callableStatement.execute(); // Usamos execute() para procedimientos que pueden devolver resultados

            // Verificar el resultado del procedimiento almacenado
            if (resultado) {
                // Si se obtuvo un result set, leer el mensaje de confirmación
                ResultSet resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    String mensaje = resultSet.getString("mensaje");
                    if (mensaje != null && mensaje.contains("actualizado exitosamente")) {
                        seModifico = true;
                    }
                }
            }

            // Confirmar transacción si es necesario
            conexion.getSQLConexion().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }

        return seModifico;
    }

    
    
    public Cuenta obtenerCuentaPorNumero(int nroCuenta) {
        Cuenta cuenta = null;
        Conexion conexion = Conexion.getConexion();
        
        String query = "SELECT Nro_cuenta, CUIL_cu, Nro_Tipo_cuenta, Fecha_de_creación, CBU, Saldo, Estado " +
                       "FROM Cuentas WHERE Nro_cuenta = ?";
        
        try (PreparedStatement preparedStatement = conexion.getSQLConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, nroCuenta);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setNroCuenta(rs.getInt("Nro_cuenta"));
                cuenta.setCuilCu(rs.getLong("CUIL_cu"));
                cuenta.setNroTipoCuenta(rs.getInt("Nro_Tipo_cuenta"));
                cuenta.setFechaCreacion(rs.getDate("Fecha_de_creación"));
                cuenta.setCbu(rs.getString("CBU"));
                cuenta.setSaldo(rs.getDouble("Saldo"));
                cuenta.setActivo(rs.getBoolean("Estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        
        return cuenta;
    }
    
    public boolean verificarCuilRepetido(long cuil) {
        boolean cuilRepetido = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Asegúrate de usar el driver adecuado
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Conexion conexion = Conexion.getConexion();
        try {
            String query = "SELECT COUNT(*) AS Total FROM cuentas WHERE CUIL_cu = ?";
            try (PreparedStatement stmt = conexion.getSQLConexion().prepareStatement(query)) {
                stmt.setLong(1, cuil);
                ResultSet resultado = stmt.executeQuery();
                if (resultado.next()) {
                    int total = resultado.getInt("Total");
                    // Si el total es mayor a 0, significa que el CUIL ya existe
                    if (total > 0) {
                        cuilRepetido = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }

        return cuilRepetido;
    }
    
    public boolean verificarCbuRepetido(String cbu) {
        boolean repetido = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Conexion conexion = Conexion.getConexion();
        try {
            String query = "SELECT COUNT(*) AS total FROM cuentas WHERE CBU = ?";
            try (PreparedStatement stmt = conexion.getSQLConexion().prepareStatement(query)) {
                stmt.setString(1, cbu);
                ResultSet resultado = stmt.executeQuery();
                if (resultado.next() && resultado.getInt("total") > 0) {
                    repetido = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }

        return repetido;
    }

    public String obtenerCbuPorNumeroCuenta(int nroCuenta) {
        String cbu = null;
        Conexion conexion = Conexion.getConexion();
        
        String query = "SELECT CBU FROM Cuentas WHERE Nro_cuenta = ?";
        
        try (PreparedStatement preparedStatement = conexion.getSQLConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, nroCuenta);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                cbu = rs.getString("CBU");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        
        return cbu;
    }



}
