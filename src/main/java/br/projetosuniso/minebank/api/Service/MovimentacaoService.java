package br.projetosuniso.minebank.api.Service;

import br.projetosuniso.minebank.api.Model.Movimentacao;
import br.projetosuniso.minebank.api.Repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository _mr;

    public void adicionarMovimentacao(Movimentacao movimentacao) {
        _mr.save(movimentacao);
    }

    public List<Movimentacao> listar() {
        return _mr.findAll();
    }

    public Optional<Movimentacao> obterMovimentacao(String chave) {
        return _mr.findByChave(chave);
    }
}
