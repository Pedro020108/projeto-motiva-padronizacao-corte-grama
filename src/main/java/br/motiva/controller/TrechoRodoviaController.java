
package br.motiva.controller;

import br.motiva.model.TrechoRodovia;
import br.motiva.service.TrechoRodoviaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trechos")
public class TrechoRodoviaController {

    private final TrechoRodoviaService service;

    public TrechoRodoviaController(TrechoRodoviaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TrechoRodovia>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrechoRodovia> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/umidade/{trechoUmido}")
    public ResponseEntity<List<TrechoRodovia>> listarPorUmidade(
            @PathVariable Boolean trechoUmido) {
        return ResponseEntity.ok(
                service.listarPorUmidade(trechoUmido));
    }

    @PostMapping
    public ResponseEntity<TrechoRodovia> criar(
            @RequestBody TrechoRodovia trecho) {
        TrechoRodovia criado = service.salvar(trecho);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrechoRodovia> atualizar(
            @PathVariable Long id,
            @RequestBody TrechoRodovia trecho) {
        return ResponseEntity.ok(service.atualizar(id, trecho));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
