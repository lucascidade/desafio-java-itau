package com.transacao_api.business.service;
import com.transacao_api.business.services.TransacaoService;
import com.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.transacao_api.infrastructe.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacaoRequestDTO;

    EstatisticasResponseDTO estatisticasResponseDTO;

    @BeforeEach
    void setUp() {
        transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticasResponseDTO = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);

    }

    @Test
    void deveAdicionarTransacaoComSucesso() {
        transacaoService.adicionarTransacao(transacaoRequestDTO);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacaoRequestDTO));
    }

    @Test
    void deveLancarExcecaoQuandoValorMenorQueZero() {
        UnprocessableEntity ex = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(-20.0, OffsetDateTime.now())));
        assertEquals("Valor da transação não pode ser negativo.", ex.getMessage());
    }

    @Test
    void deveLancarExeccaoQuandoDataForMaiorQueDataAtual() {
        UnprocessableEntity ex = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(20.0, OffsetDateTime.now().plusSeconds(30))));
        assertEquals("Data da transação não pode ser posterior a data atual. ", ex.getMessage());
    }

    @Test
    void deveLimparTransacaoComSucesso() {
        transacaoService.limparTransacoes();
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);
        assertTrue(transacoes.isEmpty());
    }



    @Test
    void deveBuscarTransacaoDentroDoIntervaloDeBusca() {
        transacaoService.adicionarTransacao(transacaoRequestDTO);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);
        assertEquals(1, transacoes.size());
    }







}

