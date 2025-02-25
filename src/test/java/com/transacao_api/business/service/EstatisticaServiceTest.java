package com.transacao_api.business.service;

import com.transacao_api.business.services.EstatiscasService;
import com.transacao_api.business.services.TransacaoService;
import com.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.transacao_api.controller.dtos.TransacaoRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {
    @InjectMocks
    EstatiscasService estatisticaService;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacaoRequestDTO;
    EstatisticasResponseDTO estatisticasResponseDTO;
    @BeforeEach
    void setUp() {
        transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticasResponseDTO = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatiscasComSucesso(){
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.singletonList(transacaoRequestDTO));
    EstatisticasResponseDTO res = estatisticaService.getEstatisticas(60);

    verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(res).usingRecursiveComparison().isEqualTo(estatisticasResponseDTO);
    }

    @Test
    void calcularEstatiscasQuandoListaEstaVazia(){
        EstatisticasResponseDTO estatisticaEsperada = new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.emptyList());
        EstatisticasResponseDTO res = estatisticaService.getEstatisticas(60);

        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(res).usingRecursiveComparison().isEqualTo(estatisticaEsperada);
    }
}
