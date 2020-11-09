package br.projetosuniso.minebank.api.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {

    private Long id;

    @NotNull(message = "O valor nome não pode ser nulo")
    @NotBlank(message = "O valor nome não pode ser vazio")
    private String nome;


    private String cpf;

    private String rg;

    private String email;

    public Cliente() {

    }

    public Cliente(Long id, String nome, String cpf, String rg, String email)
    {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setRg(rg);
        setEmail(email);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
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
}
