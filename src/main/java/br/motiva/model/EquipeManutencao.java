
package br.motiva.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EQUIPE_MANUTENCAO")
public class EquipeManutencao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_equipe_manutencao"
    )
    @SequenceGenerator(
            name = "seq_equipe_manutencao",
            sequenceName = "SEQ_EQUIPE_MANUTENCAO",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "QTD_FUNCIONARIOS", nullable = false)
    private Integer qtdFuncionarios;

    public EquipeManutencao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(Integer qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }
}
