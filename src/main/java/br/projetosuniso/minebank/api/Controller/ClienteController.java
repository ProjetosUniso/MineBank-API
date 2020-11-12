package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Cliente;
import br.projetosuniso.minebank.api.Model.Endereco;
import br.projetosuniso.minebank.api.Service.ClienteService;
import br.projetosuniso.minebank.api.Service.EnderecoService;
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

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@Valid @PathVariable(value = "id") Long id) {

        Optional<Cliente> cliente = _cs.obterPorId(id);

        if(cliente.isPresent())
            return new ResponseEntity(cliente.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/cpfExiste/{cpf}")
    public boolean buscarPorCpf(@Valid @PathVariable(value = "cpf") String cpf) {
        Integer existeCpf =  _cs.verificaCpfExiste(cpf);

        return existeCpf != null;
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

}
