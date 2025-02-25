package com.transacao_api.controller;


import com.transacao_api.business.services.EstatiscasService;
import com.transacao_api.controller.dtos.EstatisticasResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    EstatisticasController estatisticaController;

    @Mock
    EstatiscasService estatiscasService;
    EstatisticasResponseDTO estatisticas;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
        estatisticas = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);

    }

    @Test
    void deveBuscarEstatisticaComSucesso() throws Exception {

        when(estatiscasService.getEstatisticas(60)).
                thenReturn(estatisticas);

        mockMvc.perform(get("/estatistica").param("intervaloBusca",  "60")
                .param("intervaloBusca",  "60")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(estatisticas.count()));
    }
}
