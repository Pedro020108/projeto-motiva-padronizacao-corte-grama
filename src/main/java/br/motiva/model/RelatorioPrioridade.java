
package br.motiva.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RELATORIO_PRIORIDADE")
public class RelatorioPrioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_relatorio_prioridade")
    @SequenceGenerator(
            name = "seq_relatorio_prioridade",
            sequenceName = "SEQ_RELATORIO_PRIORIDADE",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "QT_URGENTE", nullable = false)
    private Integer qtUrgente;

    @Column(name = "QT_CRITICO", nullable = false)
    private Integer qtCritico;

    @Column(name = "QT_ATENCAO", nullable = false)
    private Integer qtAtencao;

    @Column(name = "QT_NORMAL", nullable = false)
    private Integer qtNormal;

    @Column(name = "RESUMO", length = 1000)
    private String resumo;

    @Column(name = "DATA_GERACAO", nullable = false)
    private LocalDateTime dataGeracao;

    public RelatorioPrioridade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtUrgente() {
        return qtUrgente;
    }

    public void setQtUrgente(Integer qtUrgente) {
        this.qtUrgente = qtUrgente;
    }

    public Integer getQtCritico() {
        return qtCritico;
    }

    public void setQtCritico(Integer qtCritico) {
        this.qtCritico = qtCritico;
    }

    public Integer getQtAtencao() {
        return qtAtencao;
    }

    public void setQtAtencao(Integer qtAtencao) {
        this.qtAtencao = qtAtencao;
    }

    public Integer getQtNormal() {
        return qtNormal;
    }

    public void setQtNormal(Integer qtNormal) {
        this.qtNormal = qtNormal;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
