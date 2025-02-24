package com.transacao_api.business.services;

import com.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatiscasService {

    private final TransacaoService transacaoService;

    public EstatisticasResponseDTO getEstatisticas(Integer intervaloBusca) {
        log.info("Iniciando processo de getEstatisticas com intervalo {} seg", intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);
        log.info("mapeamento transacoes: {}", transacoes);
        DoubleSummaryStatistics estatisticas = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        if(transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0,0.0,0.0,0.0);
        }
        log.info("retorno de estatisticas OK");
        return new EstatisticasResponseDTO(estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
