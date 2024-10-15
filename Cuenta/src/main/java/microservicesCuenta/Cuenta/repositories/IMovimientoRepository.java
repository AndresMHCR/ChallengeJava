package microservicesCuenta.Cuenta.repositories;

import microservicesCuenta.Cuenta.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Integer> {
    Optional<Movimiento> findByIdcuenta(Integer idCuenta);
}
