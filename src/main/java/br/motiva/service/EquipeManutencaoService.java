
package br.motiva.service;

import br.motiva.exception.RecursoNaoEncontradoException;
import br.motiva.model.EquipeManutencao;
import br.motiva.repository.EquipeManutencaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeManutencaoService {

    private final EquipeManutencaoRepository repository;

    public EquipeManutencaoService(
            EquipeManutencaoRepository repository) {
        this.repository = repository;
    }

    public List<EquipeManutencao> listarTodos() {
        return repository.findAll();
    }

    public EquipeManutencao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Equipe não encontrada: " + id));
    }

    public EquipeManutencao salvar(EquipeManutencao equipe) {
        validarEquipe(equipe);
        equipe.setId(null);
        return repository.save(equipe);
    }

    public EquipeManutencao atualizar(
            Long id, EquipeManutencao equipe) {
        EquipeManutencao existente = buscarPorId(id);

        validarEquipe(equipe);
        equipe.setId(existente.getId());

        return repository.save(equipe);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Equipe não encontrada: " + id);
        }

        repository.deleteById(id);
    }

    public List<EquipeManutencao> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe um nome para a busca.");
        }

        return repository.findByNomeContainingIgnoreCase(nome);
    }

    private void validarEquipe(EquipeManutencao equipe) {
        if (equipe == null) {
            throw new IllegalArgumentException(
                    "A equipe é obrigatória.");
        }

        if (equipe.getNome() == null ||
                equipe.getNome().isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da equipe é obrigatório.");
        }

        if (equipe.getNome().length() > 100) {
            throw new IllegalArgumentException(
                    "O nome deve ter no máximo 100 caracteres.");
        }

        if (equipe.getQtdFuncionarios() == null ||
                equipe.getQtdFuncionarios() <= 0) {
            throw new IllegalArgumentException(
                    "A equipe deve ter pelo menos um funcionário.");
        }
    }
}
