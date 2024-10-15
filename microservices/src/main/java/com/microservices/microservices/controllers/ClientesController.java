package com.microservices.microservices.controllers;

import com.microservices.microservices.models.Cliente;
import com.microservices.microservices.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    @Autowired
    private ClienteService clienteService;

    // Obtener un cliente por ID
    @GetMapping("/{idCliente}")
    public CompletableFuture<ResponseEntity<Cliente>> getClient(@PathVariable Integer idCliente) {
        return clienteService.getClient(idCliente)
                .thenApply(cliente -> cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build());
    }

    // Obtener un cliente por el ID de la persona
    @GetMapping("/persona/{idPersona}")
    public CompletableFuture<ResponseEntity<Cliente>> getClientByPerson(@PathVariable Integer idPersona) {
        return clienteService.getClientByPerson(idPersona)
                .thenApply(cliente -> cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build());
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody Cliente cliente) {
        clienteService.createClient(cliente);
        return ResponseEntity.ok().build(); 
    }

    // Actualizar un cliente
    @PutMapping("/{idCliente}")
    public ResponseEntity<Void> updateClient(@PathVariable Integer idCliente, @RequestBody Cliente cliente) {
        cliente.setIdcliente(idCliente);
        clienteService.updateClient(cliente);
        return ResponseEntity.ok().build();
    }

    // Eliminar un cliente
    @DeleteMapping("/{idCliente}")
    public CompletableFuture<ResponseEntity<Void>> deleteClient(@PathVariable Integer idCliente) {
        return clienteService.getClient(idCliente).thenApply(cliente -> {
            if (cliente != null) {
                clienteService.deleteClient(cliente);
                return ResponseEntity.noContent().build(); // Responder con 204 No Content si se elimina
            } else {
                return ResponseEntity.notFound().build(); // Responder con 404 Not Found si no existe el cliente
            }
        });
    }

}
