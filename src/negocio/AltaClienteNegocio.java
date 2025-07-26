package negocio;

import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;

import java.util.List;

import dao.ClienteDao;
import dao.UsuarioDao;
import dao.UbicacionDao;
import Inegocio.INegAltaCliente;

public class AltaClienteNegocio implements INegAltaCliente {
	
	@Override
	public boolean CargarCliente(Cliente cliente) {
		ClienteDao daocliente = new ClienteDao();
		UsuarioDao daousuario = new UsuarioDao();
		
		
		//Revisa que el cliente no exista previamente en la bd y que el id de usuario corresponda a un usuario
		if((daocliente.obtenerClienteUnico(cliente.getCuilCl()) == null) && (daousuario.VerificarExistexID(cliente.getIdUsuario()))) {
			return daocliente.agregarCliente(cliente);
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public List<Nacionalidad> ObtenerNacionalidades() {
		UbicacionDao ubi = new UbicacionDao();
		return ubi.obtenerNacionalidades();
	}
	
	@Override
	public List<Provincia> ObtenerProvincias() {
		UbicacionDao ubi = new UbicacionDao();
		return ubi.obtenerProvincias();
	}
	
	@Override
	public List<Localidad> ObtenerLocalidades() {
		UbicacionDao ubi = new UbicacionDao();
		return ubi.obtenerLocalidades();
	}
	
	
	
}