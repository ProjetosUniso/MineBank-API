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
    public ResponseEntity<Cliente> adicionar(@Valid @RequestBody Cliente cliente) {

        try {
            _cs.adicionarNovoCliente(cliente);

            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {

        try {
            List<Cliente> listaClientes = _cs.listarClientes();

            return new ResponseEntity<>(listaClientes, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> buscarPorId(@Valid @PathVariable(value = "id") Long id) {
        Optional<Cliente> cliente = _cs.obterPorId(id);

        if(cliente.isPresent())
            return ResponseEntity.ok(cliente.get());
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@Valid @PathVariable(value = "cpf") String cpf) {
        Optional<Cliente> cliente = _cs.obterPorCpf(cpf);

        if(cliente.isPresent())
            return ResponseEntity.ok(cliente.get());
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
