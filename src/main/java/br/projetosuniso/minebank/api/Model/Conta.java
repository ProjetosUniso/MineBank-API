package br.projetosuniso.minebank.api.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Conta")
public class Conta {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @NotNull
    @Min(100000)
    @Max(999999)
    private Long numero;

    @NotNull
    @Min(1000)
    @Max(9999)
    private int agencia;

    @NotNull
    @Min(100000)
    @Max(999999)
    private Long senha;

    @PositiveOrZero
    private BigDecimal saldo;

    @PositiveOrZero
    private BigDecimal poupanca;

    @JsonIgnoreProperties
    @OneToOne
    @JoinColumn(name = "idCliente", unique = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "conta")
    private Set<HistoricoMovimentacao> movimentacaos;


    public Conta() {

    }

    public Conta(Long id, Long numero, int agencia, Long senha, Cliente cliente, BigDecimal saldo, BigDecimal poupanca) {

        setId(id);
        setNumero(numero);
        setAgencia(agencia);
        setSenha(senha);
        setCliente(cliente);
        setSaldo(saldo);
        setPoupanca(poupanca);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public Long getSenha() {
        return senha;
    }

    public void setSenha(Long senha) {
        this.senha = senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getPoupanca() {
        return poupanca;
    }

    public void setPoupanca(BigDecimal poupanca) {
        this.poupanca = poupanca;
    }
}
