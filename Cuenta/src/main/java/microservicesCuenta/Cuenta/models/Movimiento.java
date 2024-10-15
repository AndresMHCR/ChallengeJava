package microservicesCuenta.Cuenta.models;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmovimiento")
    private Integer idmovimiento;

    @Column(name = "idcuenta")
    private Integer idcuenta;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta", insertable = false, updatable = false)
    private Cuenta idcuentaNavigation;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "tipomovimiento")
    private String tipomovimiento;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo = BigDecimal.ZERO; // Valor por defecto de 0

    public Movimiento() {
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Cuenta getIdcuentaNavigation() {
        return idcuentaNavigation;
    }

    public void setIdcuentaNavigation(Cuenta idcuentaNavigation) {
        this.idcuentaNavigation = idcuentaNavigation;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
