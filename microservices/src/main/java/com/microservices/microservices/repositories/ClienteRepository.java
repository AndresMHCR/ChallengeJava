package com.microservices.microservices.repositories;

import com.microservices.microservices.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Obtener cliente por idCliente
    @Async
    CompletableFuture<Cliente> findByIdcliente(Integer idcliente);

    // Obtener cliente por idPersona
    @Async
    CompletableFuture<Cliente> findByIdpersonaNavigation_Idpersona(Integer idpersona);
}
