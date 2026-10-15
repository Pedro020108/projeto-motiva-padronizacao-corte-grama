
package br.motiva.service;

import br.motiva.exception.RecursoNaoEncontradoException;
import br.motiva.model.RelatorioPrioridade;
import br.motiva.model.TrechoRodovia;
import br.motiva.repository.RelatorioPrioridadeRepository;
import br.motiva.repository.TrechoRodoviaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelatorioPrioridadeService {

    private final RelatorioPrioridadeRepository relatorioRepository;
    private final TrechoRodoviaRepository trechoRepository;

    public RelatorioPrioridadeService(
            RelatorioPrioridadeRepository relatorioRepository,
            TrechoRodoviaRepository trechoRepository) {
        this.relatorioRepository = relatorioRepository;
        this.trechoRepository = trechoRepository;
    }

    public RelatorioPrioridade gerarRelatorio() {
        List<TrechoRodovia> trechos = trechoRepository.findAll();

        int urgente = 0;
        int critico = 0;
        int atencao = 0;
        int normal = 0;

        for (TrechoRodovia trecho : trechos) {
            double nivel = trecho.getNivelVegetacao();

            if (nivel > 45) {
                urgente++;
            } else if (nivel > 30) {
                critico++;
            } else if (nivel > 25) {
                atencao++;
            } else {
                normal++;
            }
        }

        String resumo = String.format(
                "Total de trechos: %d. Urgentes: %d, críticos: %d, atenção: %d, normais: %d.",
                trechos.size(), urgente, critico, atencao, normal
        );

        RelatorioPrioridade relatorio = new RelatorioPrioridade();
        relatorio.setQtUrgente(urgente);
        relatorio.setQtCritico(critico);
        relatorio.setQtAtencao(atencao);
        relatorio.setQtNormal(normal);
        relatorio.setResumo(resumo);
        relatorio.setDataGeracao(LocalDateTime.now());

        return relatorioRepository.save(relatorio);
    }

    public List<RelatorioPrioridade> listarTodos() {
        return relatorioRepository.findAll();
    }

    public List<RelatorioPrioridade> listarPorPeriodo(
            LocalDateTime inicio, LocalDateTime fim) {

        if (inicio == null || fim == null) {
            throw new IllegalArgumentException(
                    "As datas inicial e final são obrigatórias."
            );
        }

        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException(
                    "A data inicial não pode ser posterior à data final."
            );
        }

        return relatorioRepository.findByDataGeracaoBetween(inicio, fim);
    }

    public RelatorioPrioridade buscarPorId(Long id) {
        return relatorioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Relatório não encontrado: " + id
                ));
    }
}
