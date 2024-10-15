package microservicesCuenta.Cuenta.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcuenta")
    private Integer idcuenta;

    @Column(name = "numerocuenta")
    private String numerocuenta;

    @Column(name = "tipocuenta")
    private String tipocuenta;

    @Column(name = "saldoinicial")
    private BigDecimal saldoinicial;

    @Column(name = "estadocuenta")
    private Boolean estadocuenta;

    @Column(name = "identificacioncli")
    private String identificacioncli;

    @JsonManagedReference
    @OneToMany(mappedBy = "idcuentaNavigation", cascade = CascadeType.ALL)
    private Set<Movimiento> movimientos = new HashSet<>();

    public Cuenta() {
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public BigDecimal getSaldoinicial() {
        return saldoinicial;
    }

    public void setSaldoinicial(BigDecimal saldoinicial) {
        this.saldoinicial = saldoinicial;
    }

    public Boolean getEstadocuenta() {
        return estadocuenta;
    }

    public void setEstadocuenta(Boolean estadocuenta) {
        this.estadocuenta = estadocuenta;
    }

    public String getIdentificacioncli() {
        return identificacioncli;
    }

    public void setIdentificacioncli(String identificacioncli) {
        this.identificacioncli = identificacioncli;
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
