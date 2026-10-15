
package br.motiva.controller;

import br.motiva.model.IntervencaoOperacional;
import br.motiva.service.IntervencaoOperacionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intervencoes")
public class IntervencaoOperacionalController {

    private final IntervencaoOperacionalService service;

    public IntervencaoOperacionalController(
            IntervencaoOperacionalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<IntervencaoOperacional>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntervencaoOperacional> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<IntervencaoOperacional>> buscarPorTipo(
            @RequestParam String valor) {
        return ResponseEntity.ok(service.buscarPorTipo(valor));
    }

    @GetMapping("/trecho/{trechoId}")
    public ResponseEntity<List<IntervencaoOperacional>> buscarPorTrecho(
            @PathVariable Long trechoId) {
        return ResponseEntity.ok(service.buscarPorTrecho(trechoId));
    }

    @PostMapping
    public ResponseEntity<IntervencaoOperacional> criar(
            @RequestBody IntervencaoOperacional intervencao) {
        IntervencaoOperacional criada = service.salvar(intervencao);

        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntervencaoOperacional> atualizar(
            @PathVariable Long id,
            @RequestBody IntervencaoOperacional intervencao) {
        return ResponseEntity.ok(
                service.atualizar(id, intervencao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
