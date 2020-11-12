package br.projetosuniso.minebank.api.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
    private Conta conta;

    @NotNull
    private String nome;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Size(min = 9, max = 9)
    private String rg;

    @NotNull
    @Email
    private String email;

    @DateTimeFormat
    private Date dataNascimento;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEndereco", referencedColumnName = "id")
    private Endereco endereco;

    public Cliente() {

    }

    public Cliente(Long id, String nome, String cpf, String rg, String email, Date dataNascimento, Endereco endereco) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setRg(rg);
        setEmail(email);
        setDataNascimento(dataNascimento);
        setEndereco(endereco);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}

