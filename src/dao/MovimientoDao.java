package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.Conexion;

import entidades.Movimiento;
import entidades.TipoMovimiento;

public class MovimientoDao {

    public MovimientoDao() {
        super();
        
    }

    public List<Movimiento> obtenerMovimientosPorCuenta(int nroCuenta) {
        List<Movimiento> movimientos = new ArrayList<>();
        String queryMovimiento = "SELECT m.Nro_Movimiento, m.Nro_Tipo_movimiento, m.CUIL_emisor_mo, m.CUIL_receptor_mo, m.Fecha, "
                                   + "m.Detalle, m.Importe, m.Nro_cuenta, t.Tipo_de_movimiento "  // Alias m para movimientos y t para tipos_de_movimiento
                                   + "FROM movimientos m "
                                   + "JOIN tipos_de_movimiento t ON m.Nro_Tipo_movimiento = t.Nro_Tipo_movimiento "
                                   + "WHERE m.Nro_cuenta = ?";  // Usar alias m para referirse a Nro_cuenta

        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = conn.prepareStatement(queryMovimiento)) {

            ps.setInt(1, nroCuenta);  // Establecer el parámetro de la cuenta

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Obtener los datos de la fila
                    int Nro_Movimiento = rs.getInt("Nro_Movimiento");
                    int nroTipoMov = rs.getInt("Nro_Tipo_movimiento");
                    long cuilEmisor = rs.getLong("CUIL_emisor_mo");
                    long cuilReceptor = rs.getLong("CUIL_receptor_mo");
                    Date fecha = rs.getDate("Fecha");
                    String detalle = rs.getString("Detalle");
                    double importe = rs.getDouble("Importe");
                    int nroCuentaDb = rs.getInt("Nro_cuenta");
                    String tipoMovimientoStr = rs.getString("Tipo_de_movimiento");  // Obtenemos el tipo de movimiento desde la tabla tipos_de_movimiento

                    // Crear el objeto TipoMovimiento
                    TipoMovimiento tipoMovimiento = new TipoMovimiento(nroTipoMov, tipoMovimientoStr);

                    // Crear el objeto Movimiento
                    Movimiento movimiento = new Movimiento(Nro_Movimiento, tipoMovimiento, cuilEmisor, cuilReceptor, fecha, detalle, importe, nroCuentaDb);
                    movimientos.add(movimiento);  // Añadir el movimiento a la lista
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los movimientos: " + e.getMessage());
        }
        return movimientos;
    }


    
    // Método para obtener el objeto TipoMovimiento desde la base de datos
    private TipoMovimiento obtenerTipoMovimiento(int nroTipoMov, Connection conn) throws SQLException {
        String queryTipoMovimiento = "SELECT Nro_Tipo_movimiento, Tipo_de_movimiento FROM tipos_de_movimiento WHERE Nro_Tipo_movimiento = ?";
        try (PreparedStatement ps = conn.prepareStatement(queryTipoMovimiento)) {
            ps.setInt(1, nroTipoMov);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idTipoMov = rs.getInt("Nro_Tipo_movimiento");
                    String descripcion = rs.getString("Tipo_de_movimiento");
                    return new TipoMovimiento(idTipoMov, descripcion);
                } else {
                    return null;  // En caso de que no exista el tipo de movimiento
                }
            }
        }
    }
    
 // Obtener todos los movimientos
    /*
    public ArrayList<Movimiento> obtenerMovimientos() {
        ArrayList<Movimiento> lista = new ArrayList<>();
        String query = "SELECT id_movimiento, nro_Tipo_movimiento, CUIL_emisor_mo, CUIL_receptor_mo, Fecha, Detalle, Importe, Saldo, Tipo_Movimiento FROM movimientos";

        try (Connection conn = Conexion.getConexion().getSQLConexion();
             Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Aquí se permite retroceder
             ResultSet rs = st.executeQuery(query)) {

            // Verificar si hay resultados
            if (!rs.next()) {
                System.out.println("No hay movimientos en la base de datos.");
            } else {
                // Volver al inicio del ResultSet
                rs.beforeFirst();

                // Mapeando los resultados
                while (rs.next()) {
                    Movimiento movimientoRs = new Movimiento();
                    movimientoRs.setNroMovimiento(rs.getInt("id_movimiento")); // Usando id_movimiento
                    movimientoRs.setNroTipoMov(rs.getInt("nro_Tipo_movimiento"));
                    movimientoRs.setCuilEmisor(rs.getLong("CUIL_emisor_mo"));
                    movimientoRs.setCuilReceptor(rs.getLong("CUIL_receptor_mo"));
                    movimientoRs.setFecha(rs.getDate("Fecha"));
                    movimientoRs.setDetalle(rs.getString("Detalle"));
                    movimientoRs.setImporte(rs.getDouble("Importe"));  // Usando double
                    movimientoRs.setSaldo(rs.getDouble("Saldo"));      // Usando double
                    movimientoRs.setTipoMovimiento(rs.getString("Tipo_Movimiento")); // Nuevo campo
                    lista.add(movimientoRs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los movimientos: " + e.getMessage());
        }
        return lista;
    }
*/



}

 /*   // Obtener todos los movimientos de un cliente (por ID de usuario)
    public ArrayList<Movimiento> obtenerTodosMovimientosUnCuil(int idUsuario) {
        ArrayList<Movimiento> lista = new ArrayList<>();
        String query = "SELECT movimientos.Nro_movimiento, movimientos.Nro_Tipo_movimiento, " +
                "movimientos.CUIL_emisor_mo, movimientos.CUIL_receptor_mo, movimientos.Fecha, " +
                "movimientos.Detalle, movimientos.Importe FROM Usuarios " +
                "INNER JOIN Clientes ON Usuarios.ID_Usuario = Clientes.ID_Usuario " +
                "INNER JOIN Movimientos ON Clientes.CUIL_cl = movimientos.CUIL_emisor_mo " +
                "OR Clientes.CUIL_cl = movimientos.CUIL_receptor_mo WHERE Usuarios.ID_Usuario = ?";

        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, idUsuario); // Evitar inyección SQL
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Movimiento aux = mapearMovimiento(rs);
                    lista.add(aux);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Obtener movimientos con un filtro de importe y criterio
    /*public ArrayList<Movimiento> obtenerTodosMovimientosConImporte(double importe, String criterio, int idUsuario) {
        ArrayList<Movimiento> lista = new ArrayList<>();
        String query = "SELECT movimientos.Nro_movimiento, movimientos.Nro_Tipo_movimiento, " +
                "movimientos.CUIL_emisor_mo, movimientos.CUIL_receptor_mo, movimientos.Fecha, " +
                "movimientos.Detalle, movimientos.Importe FROM Usuarios " +
                "INNER JOIN Clientes ON Usuarios.ID_Usuario = Clientes.ID_Usuario " +
                "INNER JOIN Movimientos ON Clientes.CUIL_cl = movimientos.CUIL_emisor_mo " +
                "OR Clientes.CUIL_cl = movimientos.CUIL_receptor_mo WHERE Usuarios.ID_Usuario = ? " +
                "AND movimientos.Importe " + criterio + " ?";

        try (Connection conn = Conexion.getConexion().getSQLConexion();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, idUsuario); // Evitar inyección SQL
            statement.setDouble(2, importe); // Evitar inyección SQL
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Movimiento aux = mapearMovimiento(rs);
                    lista.add(aux);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método auxiliar para mapear un ResultSet a un objeto Movimiento
    private Movimiento mapearMovimiento(ResultSet rs) throws SQLException {
        Movimiento movimiento = new Movimiento();
        movimiento.setNroMovimiento(rs.getInt("Nro_Movimiento"));
        movimiento.setNroTipoMov(rs.getInt("Nro_Tipo_movimiento"));
        movimiento.setCuilEmisor(rs.getString("CUIL_emisor_mo"));
        movimiento.setCuilReceptor(rs.getString("CUIL_receptor_mo"));
        movimiento.setFecha(rs.getDate("Fecha"));
        movimiento.setDetalle(rs.getString("Detalle"));
        movimiento.setImporte(rs.getDouble("Importe"));
        movimiento.setSaldo(rs.getDouble("Saldo"));
        return movimiento;
    }
}*/
