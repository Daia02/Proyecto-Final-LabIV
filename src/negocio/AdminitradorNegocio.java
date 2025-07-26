package negocio;

import java.sql.SQLException;

import Inegocio.INegocioAdministrador;
import dao.AdminitradorDao;
import entidades.Administrador;

public class AdminitradorNegocio implements INegocioAdministrador  {

	AdminitradorDao dao=new AdminitradorDao();


	public boolean buscarAdmin(String admin, String contra) {
		
		return dao.VerificarExiste(admin, contra);
	}
	public entidades.Administrador DevolverInformacion(String Admin){
		return dao.DevolverInformacion(Admin);
		
	}

	
	@Override
    public boolean actualizarDatosPersonales(Administrador administrador) throws SQLException {
        boolean resultado = false;
        try {
            resultado = dao.actualizarDatosPersonales(administrador);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error en UsuarioNegocio al actualizar los datos personales del administrador", e);
        }
        return resultado;
    }
    
    @Override
    public boolean cambiarContraseniaAdmin(int adminID, String contraActual, String nuevaContra) throws SQLException {
        boolean resultado = false;
        try {
            resultado = dao.cambiarContraseniaAdmin(adminID, contraActual, nuevaContra);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error en AdminitradorNegocio al cambiar la contraseña del administrador", e);
        }
        return resultado;
    }
}
