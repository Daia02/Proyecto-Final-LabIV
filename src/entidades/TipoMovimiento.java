package entidades;

public class TipoMovimiento {
    private int nroTipoMovimiento;
    private String tipoMovimiento;

    // Constructor
    public TipoMovimiento(int nroTipoMovimiento, String tipoMovimiento) {
        this.nroTipoMovimiento = nroTipoMovimiento;
        this.tipoMovimiento = tipoMovimiento;
    }

    // Getters y setters
    public int getNroTipoMovimiento() { return nroTipoMovimiento; }
    public void setNroTipoMovimiento(int nroTipoMovimiento) { this.nroTipoMovimiento = nroTipoMovimiento; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
}

    
    
