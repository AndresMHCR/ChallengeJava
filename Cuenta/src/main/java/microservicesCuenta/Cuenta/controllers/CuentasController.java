package microservicesCuenta.Cuenta.controllers;

import microservicesCuenta.Cuenta.models.Cuenta;
import microservicesCuenta.Cuenta.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cuentas")
public class CuentasController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentasController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{idCuenta}")
    public ResponseEntity<Cuenta> getCuenta(@PathVariable int idCuenta) {
        if (idCuenta <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        Cuenta cuenta = cuentaService.getCuenta(idCuenta);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        if (cuenta == null || cuenta.getIdcuenta() > 0) {
            return ResponseEntity.badRequest().build();
        }

        Cuenta existingCuenta = cuentaService.getCuentaByNumCuenta(cuenta.getNumerocuenta());
        if (existingCuenta != null) {
            return ResponseEntity.badRequest().build();
        }

        cuentaService.createCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create("/cuentas/" + cuenta.getIdcuenta()))
                .body(cuenta);
    }

    @DeleteMapping("/{idCuenta}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable int idCuenta) {
        if (idCuenta <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Cuenta cuenta = cuentaService.getCuenta(idCuenta);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }

        cuentaService.deleteCuenta(idCuenta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCuenta}")
    public ResponseEntity<Void> updateCuenta(@PathVariable int idCuenta, @RequestBody Cuenta cuenta) {
        if (cuenta == null || idCuenta != cuenta.getIdcuenta()) {
            return ResponseEntity.badRequest().build();
        }

        Cuenta existingCuenta = cuentaService.getCuenta(idCuenta);
        if (existingCuenta == null) {
            return ResponseEntity.notFound().build();
        }

        existingCuenta.setNumerocuenta(cuenta.getNumerocuenta());
        existingCuenta.setTipocuenta(cuenta.getTipocuenta());
        existingCuenta.setSaldoinicial(cuenta.getSaldoinicial());
        existingCuenta.setEstadocuenta(cuenta.getEstadocuenta());

        cuentaService.updateCuenta(existingCuenta);
        return ResponseEntity.noContent().build();
    }
}
