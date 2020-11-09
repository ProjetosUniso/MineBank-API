package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Cliente;
import br.projetosuniso.minebank.api.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService _cs;

    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody Cliente cliente) {

        try {
            _cs.adicionarNovoCliente(cliente);

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity listar() {

        List<Cliente> listaClientes = _cs.listarClientes();

        if (!listaClientes.isEmpty())
            return new ResponseEntity(listaClientes, HttpStatus.FOUND);
        else
            return new ResponseEntity("não existem clientes", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarPorId(@Valid @PathVariable(value = "id") Long id) {

        Optional<Cliente> cliente = _cs.obterPorId(id);

        if(cliente.isPresent())
            return new ResponseEntity(cliente.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarPorCpf(@Valid @PathVariable(value = "cpf") String cpf) {
        Optional<Cliente> cliente = _cs.obterPorCpf(cpf);

        if(cliente.isPresent())
            return new ResponseEntity(cliente.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@Valid @PathVariable(value = "id") Long id, @Valid @RequestBody Cliente novoCliente) {

        Optional<Cliente> oldCliente = _cs.obterPorId(id);

        if (oldCliente.isPresent()) {
            Cliente cliente = oldCliente.get();

            _cs.atualizarCliente(cliente, novoCliente);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@Valid @PathVariable(value = "id") Long id) {
        Optional<Cliente> cliente = _cs.obterPorId(id);

        if (cliente.isPresent()) {
            _cs.deletarCliente(cliente.get());

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
    }
}
