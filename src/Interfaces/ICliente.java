package Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entidades.Cliente;

public interface ICliente {

	public Cliente obtenerClienteUnico(String CUIL);
	
	public List<Cliente> obtenerClientesFiltrados(String cuil, String idUsuario, String dni, String nombre, String apellido, String sexo, String nacionalidad, String provincia, String localidad);
	
	public boolean actualizarCliente(Cliente cliente);
	
	 public boolean agregarCliente(Cliente cliente);
	 
	 public List<Cliente> obtenerTodosClientes();
	 
	 public boolean bajaLogicaCliente(long cuil);
}
