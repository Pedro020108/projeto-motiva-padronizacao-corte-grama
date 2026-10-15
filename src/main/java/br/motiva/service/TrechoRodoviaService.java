
package br.motiva.service;

import br.motiva.model.TrechoRodovia;
import br.motiva.repository.TrechoRodoviaRepository;
import org.springframework.stereotype.Service;
import br.motiva.exception.RecursoNaoEncontradoException;
import java.util.List;

@Service
public class TrechoRodoviaService {

    private final TrechoRodoviaRepository repository;

    public TrechoRodoviaService(TrechoRodoviaRepository repository) {
        this.repository = repository;
    }

    public List<TrechoRodovia> listarTodos() {
        return repository.findAll();
    }
    public TrechoRodovia buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Trecho não encontrado: " + id));
    }

    public TrechoRodovia salvar(TrechoRodovia trecho) {
        validarTrecho(trecho);
        return repository.save(trecho);
    }

    public TrechoRodovia atualizar(Long id, TrechoRodovia trecho) {
        TrechoRodovia existente = buscarPorId(id);

        trecho.setId(existente.getId());

        validarTrecho(trecho);

        return repository.save(trecho);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Trecho não encontrado: " + id);
        }

        repository.deleteById(id);

    }

    public List<TrechoRodovia> listarPorUmidade(Boolean trechoUmido) {
        return repository.findByTrechoUmido(trechoUmido);
    }

    private void validarTrecho(TrechoRodovia trecho) {
        if (trecho == null) {
            throw new IllegalArgumentException("O trecho é obrigatório.");
        }

        if (trecho.getKmInicial() == null || trecho.getKmFinal() == null) {
            throw new IllegalArgumentException("Os quilômetros são obrigatórios.");
        }

        if (trecho.getKmInicial() < 0 ||
                trecho.getKmFinal() <= trecho.getKmInicial()) {
            throw new IllegalArgumentException("Intervalo de quilômetros inválido.");
        }

        if (trecho.getNivelVegetacao() == null ||
                trecho.getNivelVegetacao() < 0) {
            throw new IllegalArgumentException(
                    "O nível de vegetação não pode ser negativo.");
        }

        if (trecho.getTrechoUmido() == null) {
            throw new IllegalArgumentException(
                    "Informe se o trecho está úmido.");
        }
    }
}
