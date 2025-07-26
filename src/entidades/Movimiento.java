package entidades;

import java.sql.Date;

public class Movimiento {
    private int nroMovimiento;
    private TipoMovimiento tipoMovimiento;
    private long cuilEmisor;
    private long cuilReceptor;
    private Date fecha;
    private String detalle;
    private double importe;
    private int nroCuenta;

    // Constructor
    public Movimiento(int nroMovimiento, TipoMovimiento tipoMovimiento, long cuilEmisor, long cuilReceptor, Date fecha, String detalle, double importe, int nroCuenta) {
        this.nroMovimiento = nroMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.cuilEmisor = cuilEmisor;
        this.cuilReceptor = cuilReceptor;
        this.fecha = fecha;
        this.detalle = detalle;
        this.importe = importe;
        this.nroCuenta = nroCuenta;
    }

    // Getters y setters
    public int getNroMovimiento() { return nroMovimiento; }
    public void setNroMovimiento(int nroMovimiento) { this.nroMovimiento = nroMovimiento; }

    public TipoMovimiento getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public long getCuilEmisor() { return cuilEmisor; }
    public void setCuilEmisor(long cuilEmisor) { this.cuilEmisor = cuilEmisor; }

    public long getCuilReceptor() { return cuilReceptor; }
    public void setCuilReceptor(long cuilReceptor) { this.cuilReceptor = cuilReceptor; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }

    public int getNroCuenta() { return nroCuenta; }
    public void setNroCuenta(int nroCuenta) { this.nroCuenta = nroCuenta; }
}
