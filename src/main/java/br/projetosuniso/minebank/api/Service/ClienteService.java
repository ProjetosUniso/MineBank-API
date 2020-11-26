package br.projetosuniso.minebank.api.Service;

import br.projetosuniso.minebank.api.Model.Cliente;
import br.projetosuniso.minebank.api.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository _cr;

    public void adicionarNovoCliente(Cliente cliente) {
        _cr.save(cliente);
    }

    public Integer verificaCpfExiste(String cpf) {
        return _cr.verifyCpfExists(cpf);
    }

    public Integer verificaEmailExiste(String email) {
        return _cr.verifyEmailExists(email);
    }

    public Optional<Cliente> obterPorId(Long id) {
        return _cr.findById(id);
    }

    public void atualizarCliente(Cliente clienteAtual, Cliente novoCliente) {

        clienteAtual.setNome(novoCliente.getNome());
        clienteAtual.setCpf(novoCliente.getCpf());
        clienteAtual.setRg(novoCliente.getRg());
        clienteAtual.setEmail(novoCliente.getEmail());
        clienteAtual.setDataNascimento(novoCliente.getDataNascimento());

        _cr.save(clienteAtual);
    }

    public void deletarCliente(Cliente cliente) {
        _cr.delete(cliente);
    }

}

