package br.projetosuniso.minebank.api.Service;

import br.projetosuniso.minebank.api.Model.HistoricoMovimentacao;
import br.projetosuniso.minebank.api.Repository.HistoricoMovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoMovimentacaoService {

    @Autowired
    private HistoricoMovimentacaoRepository _hmr;

    public void adicionarMovimentacao(HistoricoMovimentacao movimentacao) {
        _hmr.save(movimentacao);
    }

    public Optional<HistoricoMovimentacao> obterMovimentacao(Long id) {
        return _hmr.findById(id);
    }

    public List<HistoricoMovimentacao> listarMovimentacoes() {
        return _hmr.findAll();
    }
}

