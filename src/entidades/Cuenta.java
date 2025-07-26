package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L; // Necesario para la serialización

    private int nroCuenta;
    private long cuilCu;
    private int nroTipoCuenta;
    private java.sql.Date fechaCreacion;
    private String cbu;
    private double saldo;
    private boolean activo;
    private TipoCuenta tipoCuenta;

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Cuenta(int nroCuenta, long cuilCu, int nroTipoCuenta, Date fechaCreacion, String cbu, double saldo) {
        this.nroCuenta = nroCuenta;
        this.cuilCu = cuilCu;
        this.nroTipoCuenta = nroTipoCuenta;
        this.fechaCreacion = fechaCreacion;
        this.cbu = cbu;
        this.saldo = saldo;
    }

    public Cuenta() {
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public long getCuilCu() {
        return cuilCu;
    }

    public void setCuilCu(long cuilCu) {
        this.cuilCu = cuilCu;
    }

    public int getNroTipoCuenta() {
        return nroTipoCuenta;
    }

    public void setNroTipoCuenta(int nroTipoCuenta) {
        this.nroTipoCuenta = nroTipoCuenta;
    }

    public java.sql.Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.sql.Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cuenta [nroCuenta=" + nroCuenta + ", cuilCu=" + cuilCu + ", nroTipoCuenta=" + nroTipoCuenta
                + ", fechaCreacion=" + fechaCreacion + ", cbu=" + cbu + ", saldo=" + saldo + ", activo=" + activo + "]";
    }

    public String toDropdownString() {
        return "Cuenta: " + nroCuenta + " | Saldo: " + saldo;
    }
}
