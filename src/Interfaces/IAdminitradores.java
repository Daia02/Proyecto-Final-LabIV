package Interfaces;

import java.sql.SQLException;

import entidades.Administrador;

public interface IAdminitradores {
	public boolean VerificarExiste(String admin, String password);
	public int BuscarID(String Admin);
	public entidades.Administrador DevolverInformacion(String Admin);
	boolean actualizarDatosPersonales(Administrador administrador) throws SQLException;
	boolean cambiarContraseniaAdmin(int adminID, String contraActual, String nuevaContra) throws SQLException;
	
}
