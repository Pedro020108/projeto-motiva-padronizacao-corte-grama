
package br.motiva.controller;

import br.motiva.model.EquipeManutencao;
import br.motiva.service.EquipeManutencaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeManutencaoController {

    private final EquipeManutencaoService service;

    public EquipeManutencaoController(
            EquipeManutencaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EquipeManutencao>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeManutencao> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EquipeManutencao>> buscarPorNome(
            @RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<EquipeManutencao> criar(
            @RequestBody EquipeManutencao equipe) {
        EquipeManutencao criada = service.salvar(equipe);

        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeManutencao> atualizar(
            @PathVariable Long id,
            @RequestBody EquipeManutencao equipe) {
        return ResponseEntity.ok(service.atualizar(id, equipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
