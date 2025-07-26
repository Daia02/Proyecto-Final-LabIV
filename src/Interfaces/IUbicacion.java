package Interfaces;


import java.util.List;

import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;

public interface IUbicacion {

	public List<Nacionalidad> obtenerNacionalidades();
	
	public List<Provincia> obtenerProvincias();
	
	public List<Localidad> obtenerLocalidades();
	
	
}
