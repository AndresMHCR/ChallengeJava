package microservicesCuenta.Cuenta.models.Dto;
import java.math.BigDecimal;
import java.util.Date;

public class GenerarMovimientoDto {
    private String numCuenta;
    private Date fecha;
    private String tipoMovimiento;
    private BigDecimal valor;

    // Constructor vacío
    public GenerarMovimientoDto() {}

    // Constructor con parámetros
    public GenerarMovimientoDto(String numCuenta, Date fecha, String tipoMovimiento, BigDecimal valor) {
        this.numCuenta = numCuenta;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
