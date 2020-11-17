package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Cliente;
import br.projetosuniso.minebank.api.Model.Conta;
import br.projetosuniso.minebank.api.Model.Endereco;
import br.projetosuniso.minebank.api.Service.ClienteService;
import br.projetosuniso.minebank.api.Service.ContaService;
import br.projetosuniso.minebank.api.Service.EnderecoService;
import br.projetosuniso.minebank.api.Service.HistoricoMovimentacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService _contaservice;
    @Autowired
    private ClienteService _clientesservice;
    @Autowired
    private EnderecoService _enderecoservice;
    @Autowired
    private HistoricoMovimentacaoService _historicomovimentacaoservice;

    @ApiOperation(value = "Adiciona uma nova conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta adicionada com sucesso"),
            @ApiResponse(code = 400, message = "dados para registro invalido"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping
    public ResponseEntity<String> adicionar(@Valid @RequestBody Conta conta) {

        try {
            Cliente cliente = conta.getCliente();
            Endereco endereco = cliente.getEndereco();

            conta.setSaldo(BigDecimal.valueOf(0));

            _enderecoservice.adicionarEndereco(endereco);
            _clientesservice.adicionarNovoCliente(cliente);
            _contaservice.adicionarConta(conta);

            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Lista todas as conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista as contas"),
            @ApiResponse(code = 400, message = "não existem contas regristradas"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public ResponseEntity listar() {

        List<Conta> listaContas = _contaservice.listarContas();

        if (!listaContas.isEmpty())
            return ResponseEntity.ok(listaContas);
        else
            return ResponseEntity.badRequest().build();

    }

    @ApiOperation(value = "Retorna uma conta especifíca, baseada no id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta"),
            @ApiResponse(code = 400, message = "Conta não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Conta> buscarPorId(@Valid @PathVariable(value = "id") Long id) {

        Optional<Conta> conta = _contaservice.obterPorId(id);

        return conta
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @ApiOperation(value = "Retorna uma conta especifíca, baseada no cpf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta"),
            @ApiResponse(code = 400, message = "Conta não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Conta> buscarPorCpf(@Valid @PathVariable(value = "cpf") String cpf) {
        Optional<Conta> conta = _contaservice.obterPorCpf(cpf);

        return conta
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @ApiOperation(value = "Atualiza uma conta especifíca")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Conta não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@Valid @PathVariable(value = "id") Long id, @Valid @RequestBody Conta novaConta) {

        Optional<Conta> oldConta = _contaservice.obterPorId(id);

        if (oldConta.isPresent()) {
            Conta conta = oldConta.get();

            _contaservice.atualizarConta(conta, novaConta);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body("Conta não encontrado");
    }

    @ApiOperation(value = "Deleta uma conta especifíca")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta deletada com sucesso"),
            @ApiResponse(code = 400, message = "Conta não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable(value = "id") Long id) {
        Optional<Conta> conta = _contaservice.obterPorId(id);

        if (conta.isPresent()) {
            Cliente cliente = conta.get().getCliente();
            Endereco endereco = cliente.getEndereco();

            _historicomovimentacaoservice.deletaMovimentacao(id);

            _enderecoservice.deletarEndereco(endereco);
            _clientesservice.deletarCliente(cliente);
            _contaservice.deletarConta(conta.get());


            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body("Conta não encontrado");
    }

    @ApiOperation(value = "Verifica se uma conta já foi cadastrada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna true ou false, para caso o conta exista"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/login/{cpf&senha}")
    public boolean verificaLogin(@PathVariable(value = "cpf&senha") String login) {

        String[] loginParam = login.split("&");

        String cpf = loginParam[0];
        Long senha = Long.valueOf(loginParam[1]);

        Integer existeConta = _contaservice.verificaContaExiste(cpf, senha);

        return existeConta != null;

    }
}
