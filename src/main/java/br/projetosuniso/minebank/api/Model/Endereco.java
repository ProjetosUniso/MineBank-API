package br.projetosuniso.minebank.api.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "endereco")
    private Cliente cliente;

    @NotNull
    private String rua;

    @NotNull
    private String cidade;

    @NotNull
    private Long numero;

    @NotNull
    @Size(min = 2, max = 2)
    private String UF;

    public Endereco() {

    }

    public Endereco(String rua, String cidade, Long numero, String uf) {
        setRua(rua);
        setCidade(cidade);
        setNumero(numero);
        setUF(uf);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

}

