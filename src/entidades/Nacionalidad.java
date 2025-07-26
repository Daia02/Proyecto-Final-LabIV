package entidades;

public class Nacionalidad {
	private int nroNacionalidad;
    private String nacionalidad;
    
	public Nacionalidad(int nroNacionalidad, String nacionalidad) {
		this.nroNacionalidad = nroNacionalidad;
		this.nacionalidad = nacionalidad;
	}
    
	public Nacionalidad() {
		
	}

	public int getNroNacionalidad() {
		return nroNacionalidad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNroNacionalidad(int nroNacionalidad) {
		this.nroNacionalidad = nroNacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Nacionalidad [nroNacionalidad=" + nroNacionalidad + ", nacionalidad=" + nacionalidad + "]";
	}
	
	
    
}
