package com.transacao_api.business.services;

import com.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.transacao_api.infrastructe.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        log.info("inicio adicionarTransacao");
        if(transacaoRequestDTO.dataHora().isAfter(OffsetDateTime.now())){
            log.error("data e hora maior que data atual (transacao futura);");
            throw new UnprocessableEntity("Data da transação não pode ser posterior a data atual. ");
        }
        if(transacaoRequestDTO.valor() < 0){
            log.error("valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor da transação não pode ser negativo.");
        }

        listaTransacoes.add(transacaoRequestDTO);
    }

    public void limparTransacoes() {
        log.info("inicio limparTransacoes");
        listaTransacoes.clear();
    }
}
