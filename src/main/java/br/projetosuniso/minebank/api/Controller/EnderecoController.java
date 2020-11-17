package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Endereco;
import br.projetosuniso.minebank.api.Service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Retorna um endereço especifíco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o endereço"),
            @ApiResponse(code = 400, message = "Endereço não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@Valid @PathVariable(value = "id") Long id) {

        Optional<Endereco> endereco = _es.obterPorId(id);

        return endereco
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @ApiOperation(value = "Atualiza um endereço especifíco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Endereço não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@Valid @PathVariable(value = "id") Long id, @Valid @RequestBody Endereco novoEndereco) {

        Optional<Endereco> oldEndereco = _es.obterPorId(id);

        if (oldEndereco.isPresent()) {
            Endereco endereco = oldEndereco.get();

            _es.atualizarEndereco(endereco, novoEndereco);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body("Endereço não encontrado");
    }
}