package br.projetosuniso.minebank.api.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="Historico_Movimentacao")
public class HistoricoMovimentacao {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private BigDecimal valor;

    @NotEmpty
    private String descricao;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name="idMovimentacao", nullable=false, referencedColumnName = "id")
    private Movimentacao movimentacao;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name="idConta", nullable=false, referencedColumnName = "id")
    private Conta conta;

    private Long idContaTransferencia;

    @DateTimeFormat
    private Date dataInclusao;

    public HistoricoMovimentacao() {
    }

    public HistoricoMovimentacao(Long id, BigDecimal valor, String descricao, Movimentacao movimentacao, Conta conta, Long idContaTransferencia, Date dataInclusao) {
        setId(id);
        setValor(valor);
        setDescricao(descricao);
        setMovimentacao(movimentacao);
        setConta(conta);
        setIdContaTransferencia(idContaTransferencia);
        setDataInclusao(dataInclusao);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Long getIdContaTransferencia() {
        return idContaTransferencia;
    }

    public void setIdContaTransferencia(Long idContaTransferencia) {
        this.idContaTransferencia = idContaTransferencia;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
