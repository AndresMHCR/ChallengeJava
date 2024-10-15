package com.microservices.microservices;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.microservices.microservices.controllers.ClientesController;
import com.microservices.microservices.models.Cliente;
import com.microservices.microservices.services.ClienteService;


import java.util.concurrent.CompletableFuture;

public class MicroservicesApplicationTests {

    @Mock
    private ClienteService mockRepository;

    @InjectMocks
    private ClientesController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void createClient_ValidClient_ReturnsClient() throws Exception {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setIdcliente(0);
        cliente.setIdpersona(2);
        cliente.setClienteid("MarMon007");
        cliente.setContrasena("P@ssw0rd");
        cliente.setEstadocliente(true);

        when(mockRepository.getClient(cliente.getIdcliente())).thenReturn(CompletableFuture.completedFuture(null));

        // Act & Assert
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idcliente\":0,\"idpersona\":2,\"clienteid\":\"MarMon007\",\"contrasena\":\"P@ssw0rd\",\"estadocliente\":true}"))
                .andExpect(status().is2xxSuccessful());
    }

}

