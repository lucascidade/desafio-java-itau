package com.transacao_api.controller;

import com.transacao_api.business.services.EstatiscasService;
import com.transacao_api.business.services.TransacaoService;
import com.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
@Slf4j
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> addTransacao(@RequestBody TransacaoRequestDTO transacaoRequest) {
        log.info("Iniciando processo de addTransacao");
        transacaoService.adicionarTransacao(transacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeTransacao(@RequestBody TransacaoRequestDTO transacaoRequest) {
        transacaoService.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
