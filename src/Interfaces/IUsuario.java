package Interfaces;
import java.sql.SQLException;
import java.util.Map;

import entidades.Cliente;
import entidades.Usuario;


public interface IUsuario {

    
    boolean VerificarExiste(String client, String password);

    
    boolean VerificarExistexID(String id);

    
    Cliente devolverInformacionUsuario(String nombre);

    
    Usuario buscarUsuarioPorNombre(String nombreUsuario);

    
    boolean buscarAdministrador(String nombreUsuario, String contrasenia);
    
    public Map<String, Object> obtenerInformacionUsuario(long cuil);


	boolean cambiarContrasenia(int usuarioID, String contraActual, String nuevaContra) throws SQLException;


	boolean actualizarInformacionContacto(Cliente cliente) throws SQLException;
}