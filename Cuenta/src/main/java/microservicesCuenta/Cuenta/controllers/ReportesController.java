package microservicesCuenta.Cuenta.controllers;

import microservicesCuenta.Cuenta.models.Dto.PersonaDto;
import microservicesCuenta.Cuenta.models.Dto.ReporteMovimientoDto;
import microservicesCuenta.Cuenta.services.CuentaService;
import microservicesCuenta.Cuenta.models.Cuenta;
import microservicesCuenta.Cuenta.models.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<?> reporteMovimientos(@RequestParam String identificacion, @RequestParam String fecha) {
        try {
            // Obtener fechas desde el parámetro de query
            String[] fechas = fecha.split(";");
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = formatoFecha.parse(fechas[0]);
            Date fechaFin = formatoFecha.parse(fechas[1]);

            if (fechaFin.before(fechaInicio)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La fecha final no puede ser anterior a la fecha inicial.");
            }

            // Obtener la cuenta por identificación y fechas
            Cuenta resp = cuentaService.getReporteMovimiento(identificacion, fechaInicio, fechaFin);

            // Llamada al servicio externo para obtener la información de la persona
            PersonaDto persona = obtenerPersona(identificacion);

            // Validaciones de cuenta y movimientos
            if (resp.getMovimientos().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existen Movimientos registrados.");
            }

            if (persona == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existen Persona registrada.");
            }

            // Generar reporte
            List<ReporteMovimientoDto> reporte = new ArrayList<>();
            for (Movimiento mov : resp.getMovimientos()) {
                ReporteMovimientoDto dto = new ReporteMovimientoDto();
                dto.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(mov.getFecha()));
                dto.setCliente(persona.getNombre());
                dto.setNumeroCuenta(resp.getNumerocuenta());
                dto.setTipoCuenta(resp.getTipocuenta());
                dto.setSaldoInicial(resp.getSaldoinicial());
                dto.setEstado(resp.getEstadocuenta());
                dto.setValorMovimiento(mov.getValor());
                dto.setSaldoDisponible(mov.getSaldo());

                reporte.add(dto);
            }

            return ResponseEntity.ok(reporte);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error: " + ex.getMessage());
        }
    }

    // Método para consumir el servicio REST que obtiene la información de la persona
    private PersonaDto obtenerPersona(String identificacion) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8081/api/personas/identificacion/" + identificacion;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            ResponseEntity<PersonaDto> response = restTemplate.getForEntity(url, PersonaDto.class);

            return response.getBody();
        } catch (Exception ex) {
            return null;
        }
    }
}
