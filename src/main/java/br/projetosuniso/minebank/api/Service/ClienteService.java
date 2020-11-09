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

    public List<Cliente> listarClientes() {
        return _cr.findAll();
    }

    public Optional<Cliente> obterPorCpf(String cpf) {
        return _cr.findByCpf(cpf);
    }

    public Optional<Cliente> obterPorId(Long id) {
        return _cr.findById(id);
    }
}
