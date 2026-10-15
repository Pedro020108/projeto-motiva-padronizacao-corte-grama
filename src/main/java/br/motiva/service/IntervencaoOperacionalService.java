
package br.motiva.service;

import br.motiva.exception.RecursoNaoEncontradoException;
import br.motiva.model.IntervencaoOperacional;
import br.motiva.repository.IntervencaoOperacionalRepository;
import br.motiva.repository.TrechoRodoviaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervencaoOperacionalService {

    private final IntervencaoOperacionalRepository repository;
    private final TrechoRodoviaRepository trechoRepository;

    public IntervencaoOperacionalService(
            IntervencaoOperacionalRepository repository,
            TrechoRodoviaRepository trechoRepository) {
        this.repository = repository;
        this.trechoRepository = trechoRepository;
    }

    public List<IntervencaoOperacional> listarTodos() {
        return repository.findAll();
    }

    public IntervencaoOperacional buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Intervenção não encontrada: " + id));
    }

    public IntervencaoOperacional salvar(
            IntervencaoOperacional intervencao) {
        validarIntervencao(intervencao);
        intervencao.setId(null);
        return repository.save(intervencao);
    }

    public IntervencaoOperacional atualizar(
            Long id, IntervencaoOperacional intervencao) {
        IntervencaoOperacional existente = buscarPorId(id);

        validarIntervencao(intervencao);
        intervencao.setId(existente.getId());

        return repository.save(intervencao);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Intervenção não encontrada: " + id);
        }

        repository.deleteById(id);
    }

    public List<IntervencaoOperacional> buscarPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o tipo da intervenção.");
        }

        return repository.findByTipoIgnoreCase(tipo);
    }

    public List<IntervencaoOperacional> buscarPorTrecho(Long trechoId) {
        if (trechoId == null ||
                !trechoRepository.existsById(trechoId)) {
            throw new RecursoNaoEncontradoException(
                    "Trecho não encontrado: " + trechoId);
        }

        return repository.findByTrechoId(trechoId);
    }

    private void validarIntervencao(
            IntervencaoOperacional intervencao) {
        if (intervencao == null) {
            throw new IllegalArgumentException(
                    "A intervenção é obrigatória.");
        }

        if (intervencao.getTipo() == null ||
                intervencao.getTipo().isBlank()) {
            throw new IllegalArgumentException(
                    "O tipo da intervenção é obrigatório.");
        }

        if (intervencao.getTipo().length() > 50) {
            throw new IllegalArgumentException(
                    "O tipo deve ter no máximo 50 caracteres.");
        }

        if (intervencao.getTrechoId() == null ||
                !trechoRepository.existsById(
                        intervencao.getTrechoId())) {
            throw new IllegalArgumentException(
                    "Informe um ID de trecho existente.");
        }
    }
}
