package com.microservices.microservices.services;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.microservices.models.Cliente;
import com.microservices.microservices.models.Persona;
import com.microservices.microservices.repositories.ClienteRepository;
import com.microservices.microservices.repositories.PersonaRepository;

@Service
public class ClienteService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener una Persona por su ID
    public CompletableFuture<Persona> getPerson(Integer idpersona) {
        return personaRepository.findByIdpersona(idpersona);
    }

    // Verificar si existe una Persona por su ID
    public boolean getPersonPresent(Integer idpersona) {
        return personaRepository.existsByIdpersona(idpersona);
    }

    // Obtener una Persona por su NUI
    public CompletableFuture<Persona> getPersonByNui(String nui) {
        return personaRepository.findByIdentificacion(nui);
    }

    // Crear una nueva Persona
    public void createPerson(Persona persona) {
        personaRepository.save(persona);
    }

    // Actualizar una Persona
    public void updatePerson(Persona persona) {
        personaRepository.save(persona);
    }

    // Obtener un Cliente por su ID
    public CompletableFuture<Cliente> getClient(Integer idcliente) {
        return clienteRepository.findByIdcliente(idcliente);
    }

    // Obtener un Cliente por el ID de la Persona
    public CompletableFuture<Cliente> getClientByPerson(Integer idpersona) {
        return clienteRepository.findByIdpersonaNavigation_Idpersona(idpersona);
    }

    // Crear un nuevo Cliente
    public void createClient(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // Actualizar un Cliente
    public void updateClient(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // Eliminar un Cliente
    public void deleteClient(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
