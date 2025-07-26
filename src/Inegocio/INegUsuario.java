package Inegocio;

import entidades.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import entidades.Cliente;

public interface INegUsuario {
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario);
	public String obtenerTipoDeUsuario(String nombreUsuario);
	public boolean devolverusuario(String nombre, String contra);
	public boolean esAdministrador(String nombreUsuario, String contrasenia);
	public Cliente obtenerInformacionCliente(String nombreUsuario);
	public Map<String, Object> obtenerInformacionUsuario(long cuil);
	boolean cambiarContrasenia(Usuario usuario, String contraActual, String nuevaContra) throws SQLException;
	boolean actualizarInformacionContacto(Cliente cliente) throws SQLException;
	public List<Integer> obtenerNumerosDeCuentaPorUsuario(int idUsuario);
	Integer obtenerIdUsuarioPorNombre(String nombreUsuario);
}