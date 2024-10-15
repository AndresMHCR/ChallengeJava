package microservicesCuenta.Cuenta.services;

import microservicesCuenta.Cuenta.models.Cuenta;
import microservicesCuenta.Cuenta.models.Movimiento;
import microservicesCuenta.Cuenta.repositories.ICuentaRepository;
import microservicesCuenta.Cuenta.repositories.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CuentaService {

    private final ICuentaRepository cuentaRepository;
    private final IMovimientoRepository movimientoRepository;

    @Autowired
    public CuentaService(ICuentaRepository cuentaRepository, IMovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public Cuenta getCuenta(int idCuenta) {
        return cuentaRepository.findById(idCuenta).orElse(null);
    }

    public Cuenta getCuentaByNumCuenta(String numCuenta) {
        return cuentaRepository.findByNumerocuenta(numCuenta).orElse(null);
    }

    public void createCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    public void updateCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(int idCuenta) {
        cuentaRepository.deleteById(idCuenta);
    }

    public Movimiento getMovimiento(int idMovimiento) {
        return movimientoRepository.findById(idMovimiento).orElse(null);
    }

    public Movimiento getUltimoMovimientoPorCuenta(int idCuenta) {
        return movimientoRepository.findByIdcuenta(idCuenta).orElse(null);
    }

    public void createMovimiento(Movimiento movimiento) {
        movimientoRepository.save(movimiento);
    }

    public void updateMovimiento(Movimiento movimiento) {
        movimientoRepository.save(movimiento);
    }

    public void deleteMovimiento(int idMovimiento) {
        movimientoRepository.deleteById(idMovimiento);
    }

    public Cuenta getReporteMovimiento(String identificacion, Date fechaInicio, Date fechaFin) {
        // Implementar la lógica de reporte aquí
        return cuentaRepository.findByIdentificacioncli(identificacion);
    }
}
