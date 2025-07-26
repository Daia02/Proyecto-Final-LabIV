package Inegocio;

import entidades.Administrador;
import java.sql.SQLException;

public interface INegocioAdministrador {
	public boolean buscarAdmin(String admin, String contra);
	public entidades.Administrador DevolverInformacion(String Admin);
	boolean actualizarDatosPersonales(Administrador administrador) throws SQLException ;
	boolean cambiarContraseniaAdmin(int adminID, String contraActual, String nuevaContra) throws SQLException;
}
