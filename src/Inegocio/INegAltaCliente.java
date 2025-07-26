package Inegocio;

import java.util.List;

import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;

public interface INegAltaCliente {
	public boolean CargarCliente(Cliente cliente);
	public List<Nacionalidad> ObtenerNacionalidades();
	public List<Provincia> ObtenerProvincias();
	public List<Localidad> ObtenerLocalidades();
}