package br.motiva.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TRECHO_RODOVIA")
public class TrechoRodovia {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_trecho_rodovia"
    )
    @SequenceGenerator(
            name = "seq_trecho_rodovia",
            sequenceName = "SEQ_TRECHO_RODOVIA",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "KM_INICIAL", nullable = false)
    private Double kmInicial;

    @Column(name = "KM_FINAL", nullable = false)
    private Double kmFinal;

    @Column(name = "NIVEL_VEGETACAO", nullable = false)
    private Double nivelVegetacao;

    @Column(name = "TRECHO_UMIDO", nullable = false)
    private Boolean trechoUmido;

    public TrechoRodovia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Double kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Double getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Double kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Double getNivelVegetacao() {
        return nivelVegetacao;
    }

    public void setNivelVegetacao(Double nivelVegetacao) {
        this.nivelVegetacao = nivelVegetacao;
    }

    public Boolean getTrechoUmido() {
        return trechoUmido;
    }

    public void setTrechoUmido(Boolean trechoUmido) {
        this.trechoUmido = trechoUmido;
    }
}