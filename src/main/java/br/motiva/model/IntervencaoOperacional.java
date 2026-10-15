
package br.motiva.model;

import jakarta.persistence.*;

@Entity
@Table(name = "INTERVENCAO_OPERACIONAL")
public class IntervencaoOperacional {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_intervencao_operacional"
    )
    @SequenceGenerator(
            name = "seq_intervencao_operacional",
            sequenceName = "SEQ_INTERVENCAO_OPERACIONAL",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "TRECHO_ID", nullable = false)
    private Long trechoId;

    public IntervencaoOperacional() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getTrechoId() {
        return trechoId;
    }

    public void setTrechoId(Long trechoId) {
        this.trechoId = trechoId;
    }
}
