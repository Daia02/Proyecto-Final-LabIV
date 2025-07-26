package entidades;

public class Localidad {
	private int nroLocalidad;
    private int nroProvincia;  // Relacionado con Provincia
    private String localidad;
    
	public Localidad(int nroLocalidad, int nroProvincia, String localidad) {
		this.nroLocalidad = nroLocalidad;
		this.nroProvincia = nroProvincia;
		this.localidad = localidad;
	}
	
	public Localidad() {
		
	}

	public int getNroLocalidad() {
		return nroLocalidad;
	}

	public int getNroProvincia() {
		return nroProvincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setNroLocalidad(int nroLocalidad) {
		this.nroLocalidad = nroLocalidad;
	}

	public void setNroProvincia(int nroProvincia) {
		this.nroProvincia = nroProvincia;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "Localidad [nroLocalidad=" + nroLocalidad + ", nroProvincia=" + nroProvincia + ", localidad=" + localidad
				+ "]";
	}
	
    
}
