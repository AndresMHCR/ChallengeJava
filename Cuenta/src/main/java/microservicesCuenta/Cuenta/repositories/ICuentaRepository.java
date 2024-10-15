package microservicesCuenta.Cuenta.repositories;

import microservicesCuenta.Cuenta.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Integer> {
    Optional<Cuenta> findByNumerocuenta(String numCuenta);
    
    @Query("SELECT c FROM Cuenta c WHERE c.identificacioncli = ?1")
    Cuenta findByIdentificacioncli(String identificacioncli);
}
