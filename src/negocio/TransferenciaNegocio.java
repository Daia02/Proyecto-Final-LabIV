package negocio;

import dao.TransferenciaDao;
import entidades.Cuenta;
import java.sql.SQLException;
import java.util.List;

public class TransferenciaNegocio {

    private TransferenciaDao transferenciaDAO; // Agregamos la variable

    public TransferenciaNegocio() {
        this.transferenciaDAO = new TransferenciaDao(); // Inicializamos la variable
    }

    public void realizarTransferencia(String cbuOrigen, String cbuDestino, double monto) throws SQLException {
        // Llamar al DAO para ejecutar el procedimiento almacenado
        transferenciaDAO.transferirDineroPorCBU(cbuOrigen, cbuDestino, monto);
    }
    
    // Método para obtener las cuentas de un usuario
    public List<Cuenta> obtenerCuentasPorUsuario(int idUsuario) throws SQLException {
        return transferenciaDAO.obtenerCuentasPorUsuario(idUsuario);
    }
}
