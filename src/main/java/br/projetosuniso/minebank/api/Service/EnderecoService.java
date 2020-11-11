package br.projetosuniso.minebank.api.Service;

import br.projetosuniso.minebank.api.Model.Endereco;
import br.projetosuniso.minebank.api.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository _er;

    public void adicionarEndereco(Endereco endereco) {
        _er.save(endereco);
    }

    public Optional<Endereco> obterPorId(Long id) {
        return _er.findById(id);
    }

    public void atualizarEndereco(Endereco enderecoAtual, Endereco novoEndereco) {

        enderecoAtual.setRua(novoEndereco.getRua());
        enderecoAtual.setCidade(novoEndereco.getCidade());
        enderecoAtual.setNumero(novoEndereco.getNumero());
        enderecoAtual.setUF(novoEndereco.getUF());

        _er.save(enderecoAtual);
    }

    public void deletarEndereco(Endereco endereco) {
        _er.delete(endereco);
    }
}
