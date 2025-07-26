package entidades;

public class Provincia {
	private int nroProvincia;
    private String provincia;
    private int nroNacionalidad; 
    
    
   
	
	public Provincia(int nroProvincia, String provincia, int nroNacionalidad) {
		this.nroProvincia = nroProvincia;
		this.provincia = provincia;
		this.nroNacionalidad = nroNacionalidad;
	}

	public Provincia() {
	
	}

	public int getNroProvincia() {
		return nroProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setNroProvincia(int nroProvincia) {
		this.nroProvincia = nroProvincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Provincia [nroProvincia=" + nroProvincia + ", provincia=" + provincia + "]";
	}

	public int getNroNacionalidad() {
		return nroNacionalidad;
	}

	public void setNroNacionalidad(int nroNacionalidad) {
		this.nroNacionalidad = nroNacionalidad;
	}
    
	
    
    
}
