package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Endereco;
import br.projetosuniso.minebank.api.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService _es;

    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody Endereco endereco) {

        try {
            _es.adicionarEndereco(endereco);

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@Valid @PathVariable(value = "id") Long id) {

        Optional<Endereco> endereco = _es.obterPorId(id);

        if(endereco.isPresent())
            return new ResponseEntity(endereco.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);

    }
}