package com.microservices.microservices.repositories;

import com.microservices.microservices.models.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    // Método para obtener una Persona por su ID
    @Async
    CompletableFuture<Persona> findByIdpersona(Integer idpersona);

    // Método para obtener una Persona por su NUI
    @Async
    CompletableFuture<Persona> findByIdentificacion(String identificacion);

    boolean existsByIdpersona(Integer idpersona);
}
