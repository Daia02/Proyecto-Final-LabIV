package Inegocio;

import java.util.List;

import entidades.Cliente;

public interface INegCliente {
	public Cliente buscarClienteUnico(String CUIT);
	public List<Cliente> ObtenerTodosClientes();
	public List<Cliente> obtenerClientesFiltrados(String cuil, String idUsuario, String dni, String nombre, String apellido, String sexo, String nacionalidad, String provincia, String localidad);
	public Cliente obtenerClientePorCuil(String cuil);
	public boolean ActualizarCliente(Cliente cli);
	public boolean bajaLogicaCliente(long cuil);
	List<String> obtenerTodosLosCuils();
}