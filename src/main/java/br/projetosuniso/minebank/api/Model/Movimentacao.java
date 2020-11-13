package br.projetosuniso.minebank.api.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Movimentacao")
public class Movimentacao {

    @Id
    private int id;

    private String chave;

    private String descricao;

    @OneToMany(mappedBy = "movimentacao")
    private Set<HistoricoMovimentacao> movimentacaos;

    public Movimentacao() {

    }

    public Movimentacao(int id, String chave, String descricao) {

        setId(id);
        setChave(chave);
        setDescricao(descricao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
