package com.microservices.microservices.controllers;

import com.microservices.microservices.models.Persona;
import com.microservices.microservices.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/personas")
public class PersonasController {

    @Autowired
    private ClienteService personaService;

    // Obtener una persona por ID
    @GetMapping("/{idpersona}")
    public CompletableFuture<ResponseEntity<Persona>> getPerson(@PathVariable Integer idpersona) {
        return personaService.getPerson(idpersona)
                .thenApply(persona -> persona != null ? ResponseEntity.ok(persona) : ResponseEntity.notFound().build());
    }

    // Obtener una persona por su NUI
    @GetMapping("/identificacion/{identificacion}")
    public CompletableFuture<ResponseEntity<Persona>> getPersonByNui(@PathVariable String identificacion) {
        return personaService.getPersonByNui(identificacion)
                .thenApply(persona -> persona != null ? ResponseEntity.ok(persona) : ResponseEntity.notFound().build());
    }

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody Persona persona) {
        if (personaService.getPersonPresent(persona.getIdpersona()))
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Conflicto, ya existe
       
        }
        personaService.createPerson(persona);
        return ResponseEntity.ok().build();
    }

    // Actualizar una persona
    @PutMapping("/{idpersona}")
    public ResponseEntity<Void> updatePerson(@PathVariable Integer idpersona, @RequestBody Persona persona) {
        persona.setIdpersona(idpersona);
        personaService.updatePerson(persona);
        return ResponseEntity.ok().build();
    }
}

