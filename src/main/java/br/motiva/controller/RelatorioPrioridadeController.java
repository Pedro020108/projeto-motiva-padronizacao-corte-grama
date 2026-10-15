
package br.motiva.controller;

import br.motiva.model.RelatorioPrioridade;
import br.motiva.service.RelatorioPrioridadeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioPrioridadeController {

    private final RelatorioPrioridadeService service;

    public RelatorioPrioridadeController(
            RelatorioPrioridadeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RelatorioPrioridade> gerarRelatorio() {
        RelatorioPrioridade relatorio = service.gerarRelatorio();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(relatorio);
    }

    @GetMapping
    public ResponseEntity<List<RelatorioPrioridade>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioPrioridade> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<RelatorioPrioridade>> listarPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fim) {

        return ResponseEntity.ok(
                service.listarPorPeriodo(inicio, fim)
        );
    }
}
