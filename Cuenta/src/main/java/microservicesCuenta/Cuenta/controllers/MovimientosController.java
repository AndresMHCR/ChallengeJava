package microservicesCuenta.Cuenta.controllers;

import microservicesCuenta.Cuenta.models.Cuenta;
import microservicesCuenta.Cuenta.models.Movimiento;
import microservicesCuenta.Cuenta.models.Dto.GenerarMovimientoDto;
import microservicesCuenta.Cuenta.services.CuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/movimientos")
public class MovimientosController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{idMovimiento}")
    public ResponseEntity<Movimiento> getMovimiento(@PathVariable int idMovimiento) {
        if (idMovimiento == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Movimiento movimiento = cuentaService.getMovimiento(idMovimiento);
        if (movimiento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registerMovimiento(@RequestBody GenerarMovimientoDto movimientoDto) {
        if (movimientoDto == null) {
            return new ResponseEntity<>("Movimiento no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        Cuenta cuenta = cuentaService.getCuentaByNumCuenta(movimientoDto.getNumCuenta());
        if (cuenta == null) {
            return new ResponseEntity<>("Cuenta no existe", HttpStatus.BAD_REQUEST);
        }

        Movimiento ultimoMovimiento = cuentaService.getUltimoMovimientoPorCuenta(cuenta.getIdcuenta());

        // Calcular el valor y saldo del movimiento
        BigDecimal valorMovimiento = movimientoDto.getTipoMovimiento().equalsIgnoreCase("RETIRO") ? movimientoDto.getValor().negate() : movimientoDto.getValor();
        BigDecimal saldo = (ultimoMovimiento == null) ? cuenta.getSaldoinicial().add(valorMovimiento) : ultimoMovimiento.getSaldo().add(valorMovimiento);

        if (movimientoDto.getTipoMovimiento().equalsIgnoreCase("RETIRO") && saldo.compareTo(BigDecimal.ZERO) < 0) {
            return new ResponseEntity<>("Saldo insuficiente", HttpStatus.BAD_REQUEST);
        }

        Movimiento nuevoMovimiento = new Movimiento();
        nuevoMovimiento.setIdcuenta(cuenta.getIdcuenta());
        nuevoMovimiento.setFecha(movimientoDto.getFecha());
        nuevoMovimiento.setTipomovimiento(movimientoDto.getTipoMovimiento().toUpperCase());
        nuevoMovimiento.setValor(valorMovimiento);
        nuevoMovimiento.setSaldo(saldo);

        cuentaService.createMovimiento(nuevoMovimiento);

        return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idMovimiento}")
    public ResponseEntity<Object> deleteMovimiento(@PathVariable int idMovimiento) {
        if (idMovimiento == 0) {
            return new ResponseEntity<>("ID Movimiento no válido", HttpStatus.BAD_REQUEST);
        }

        Movimiento movimiento = cuentaService.getMovimiento(idMovimiento);
        if (movimiento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        cuentaService.deleteMovimiento(idMovimiento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{idMovimiento}")
    public ResponseEntity<Object> updateMovimiento(@PathVariable int idMovimiento, @RequestBody Movimiento movimiento) {
        if (movimiento == null || idMovimiento != movimiento.getIdmovimiento()) {
            return new ResponseEntity<>("Datos no válidos", HttpStatus.BAD_REQUEST);
        }

        Movimiento movimientoExistente = cuentaService.getMovimiento(idMovimiento);
        if (movimientoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Movimiento movimientoActualizado = movimientoExistente;
        movimientoActualizado.setFecha(movimiento.getFecha());
        movimientoActualizado.setTipomovimiento(movimiento.getTipomovimiento());
        movimientoActualizado.setValor(movimiento.getValor());
        movimientoActualizado.setSaldo(movimiento.getSaldo());

        cuentaService.updateMovimiento(movimientoActualizado);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
