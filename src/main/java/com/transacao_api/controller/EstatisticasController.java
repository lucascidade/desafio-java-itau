package com.transacao_api.controller;

import com.transacao_api.business.services.EstatiscasService;
import com.transacao_api.controller.dtos.EstatisticasResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatiscasService estatiscasService;

    @GetMapping
    public ResponseEntity<EstatisticasResponseDTO> getEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
    return ResponseEntity.ok(estatiscasService.getEstatisticas(intervaloBusca));

    }
}
