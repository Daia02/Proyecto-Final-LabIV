package entidades;

import java.math.BigDecimal;
import java.util.Date;

public class PagoPrestamo {
    private int nroPrestamo;
    private int nroCuenta;
    private String tipoCuenta;
    private BigDecimal importeAprobado;
    private BigDecimal totalAPagar;
    private int plazoDePago;
    private BigDecimal interesAplicado;
    private int cantidadCuotas;
    private int cuotasRestantes;
    private Date fechaAprobacion;
    private Date fechaVencimiento;
    private BigDecimal montoPorCuota;
    private String estado;

    // Getters y Setters

    public int getNroPrestamo() {
        return nroPrestamo;
    }

    public void setNroPrestamo(int nroPrestamo) {
        this.nroPrestamo = nroPrestamo;
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getImporteAprobado() {
        return importeAprobado;
    }

    public void setImporteAprobado(BigDecimal importeAprobado) {
        this.importeAprobado = importeAprobado;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public int getPlazoDePago() {
        return plazoDePago;
    }

    public void setPlazoDePago(int plazoDePago) {
        this.plazoDePago = plazoDePago;
    }

    public BigDecimal getInteresAplicado() {
        return interesAplicado;
    }

    public void setInteresAplicado(BigDecimal interesAplicado) {
        this.interesAplicado = interesAplicado;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public int getCuotasRestantes() {
        return cuotasRestantes;
    }

    public void setCuotasRestantes(int cuotasRestantes) {
        this.cuotasRestantes = cuotasRestantes;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMontoPorCuota() {
        return montoPorCuota;
    }

    public void setMontoPorCuota(BigDecimal montoPorCuota) {
        this.montoPorCuota = montoPorCuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
