package com.transacao_api.controller;

import com.transacao_api.business.services.TransacaoService;
import com.transacao_api.controller.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint para adicionar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "transação gravada com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Erro requisição"),
            @ApiResponse(responseCode = "500", description = "ERRO INTERNO DE SERVIDOR")
    })
    public ResponseEntity<Void> addTransacao(@RequestBody TransacaoRequestDTO transacaoRequest) {
        log.info("Iniciando processo de addTransacao");
        transacaoService.adicionarTransacao(transacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint para deletar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "transação deletar com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro requisição"),
            @ApiResponse(responseCode = "500", description = "ERRO INTERNO DE SERVIDOR")
    })
    public ResponseEntity<Void> removeTransacao(@RequestBody TransacaoRequestDTO transacaoRequest) {
        transacaoService.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
