package entidades;

import java.math.BigDecimal;
import java.sql.Date;

import com.sun.xml.internal.ws.api.server.InstanceResolver;

public class Prestamo {
	private Cliente cliente;
    private BigDecimal importe;
    private int coutas;
    private int numeroCuenta;
    private boolean estado;
   private BigDecimal interes;
   private BigDecimal total;
    
   public Prestamo() {};
	public Prestamo(Cliente cliente, BigDecimal importe, int coutas) {
		super();
		this.cliente = cliente;
		this.importe = importe;
		this.coutas = coutas;
		
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public int getCoutas() {
		return coutas;
	}
	public void setCoutas(int coutas) {
		this.coutas = coutas;
	}
	
	public int getnumeroCuenta() {
		return numeroCuenta;
	}
	public void setnumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public BigDecimal getInteres() {
		return interes;
	}
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
    
	
	    
}
