package negocio;

import java.util.List;

import dao.ClienteDao;
import entidades.Cliente;
import dao.UsuarioDao;
import Inegocio.INegCliente;

public class NegocioCliente implements INegCliente {
	private ClienteDao cliente;
	
	
	public NegocioCliente() {
		cliente = new ClienteDao();
	}
	
	@Override
	public Cliente buscarClienteUnico(String CUIT) {
		return cliente.obtenerClienteUnico(CUIT);
	}
	
	@Override
	public List<Cliente> ObtenerTodosClientes(){
		return cliente.obtenerTodosClientes();
	}
	
	@Override
	public List<Cliente> obtenerClientesFiltrados(String cuil, String idUsuario, String dni, String nombre, String apellido, String sexo, String nacionalidad, String provincia, String localidad) { 
		return cliente.obtenerClientesFiltrados(cuil, idUsuario, dni, nombre, apellido, sexo, nacionalidad, provincia, localidad);
	}
	
	@Override
	public Cliente obtenerClientePorCuil(String cuil) {
	    return cliente.obtenerClienteUnico(cuil); 
	}
	
	@Override
	public List<String> obtenerTodosLosCuils() {
	    return cliente.obtenerTodosLosCuils(); 
	}

	@Override
	public boolean ActualizarCliente(Cliente cli) {
		
		UsuarioDao usuario = new UsuarioDao();
		
		if(usuario.VerificarExistexID(cli.getIdUsuario())) {
			return cliente.actualizarCliente(cli);
		}
		
		return false;
		
	}
	
	@Override
	public boolean bajaLogicaCliente(long cuil) {
		ClienteDao clienteDao = new ClienteDao();
		return clienteDao.bajaLogicaCliente(cuil);
	}
	

	
}