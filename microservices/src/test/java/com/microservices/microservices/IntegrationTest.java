package com.microservices.microservices; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateClient() throws Exception {
        String newClientJson = "{ \"idpersona\": 2, \"clienteid\": \"MarMon007\", \"contrasena\": \"P@ssw0rd\", \"estadocliente\": true }";

        mockMvc.perform(post("/api/clientes") // Cambia esta ruta según tu configuración
                .contentType("application/json")
                .content(newClientJson))
                .andExpect(status().is2xxSuccessful());
    }

}
