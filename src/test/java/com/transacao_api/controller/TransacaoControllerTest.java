package com.transacao_api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.transacao_api.business.services.EstatiscasService;
import com.transacao_api.business.services.TransacaoService;
import com.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.transacao_api.infrastructe.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

    @InjectMocks
    TransacaoController transacaoController;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacaoRequestDTO;

    MockMvc mockMvc;


    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.of(2025,2,25,14,30,0,0, ZoneOffset.UTC));

    }

    @Test
    void deveBuscarEstatisticaComSucesso() throws Exception {

        doNothing().when(transacaoService).adicionarTransacao(transacaoRequestDTO);

        mockMvc.perform(post("/transacao").param("intervaloBusca",  "60")
                .content(objectMapper.writeValueAsString(transacaoRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveGerarExcecaoAoAdicionarTransacao() throws Exception {
        doThrow(new UnprocessableEntity("Erro requisição")).when(transacaoService).adicionarTransacao(transacaoRequestDTO);
        mockMvc.perform(post("/transacao").param("intervaloBusca",  "60")
                        .content(objectMapper.writeValueAsString(transacaoRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void deveLimparTransacoesComSucesso () throws Exception {
        doNothing().when(transacaoService).limparTransacoes();

        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}
