package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Cliente;
import br.projetosuniso.minebank.api.Model.Endereco;
import br.projetosuniso.minebank.api.Service.ClienteService;
import br.projetosuniso.minebank.api.Service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Retorna um cliente especifíco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente"),
            @ApiResponse(code = 400, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Cliente> buscarPorId(@Valid @PathVariable(value = "id") Long id) {

        Optional<Cliente> cliente = _cs.obterPorId(id);

        return cliente
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @ApiOperation(value = "Verifica se o cpf já foi cadastrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna true ou false, para caso o cpf exista"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/cpfExiste/{cpf}")
    public boolean buscarPorCpf(@Valid @PathVariable(value = "cpf") String cpf) {
        Integer existeCpf =  _cs.verificaCpfExiste(cpf);

        return existeCpf != null;
    }

    @ApiOperation(value = "Verifica se um email já foi cadastrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna true ou false, para caso o email exista"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/emailExiste/{email}")
    public boolean buscarPorEmail(@Valid @PathVariable(value = "email") String email) {
        Integer existeEmail =  _cs.verificaEmailExiste(email);

        return existeEmail != null;
    }

    @ApiOperation(value = "Atualiza um cliente especifíco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@Valid @PathVariable(value = "id") Long id, @Valid @RequestBody Cliente novoCliente) {

        Optional<Cliente> oldCliente = _cs.obterPorId(id);

        if (oldCliente.isPresent()) {
            Cliente cliente = oldCliente.get();

            _cs.atualizarCliente(cliente, novoCliente);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body("Cliente não encontrado");
    }
}
