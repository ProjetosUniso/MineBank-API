package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Cliente;
import br.projetosuniso.minebank.api.Model.Conta;
import br.projetosuniso.minebank.api.Model.Endereco;
import br.projetosuniso.minebank.api.Service.ClienteService;
import br.projetosuniso.minebank.api.Service.ContaService;
import br.projetosuniso.minebank.api.Service.EnderecoService;
import br.projetosuniso.minebank.api.Service.HistoricoMovimentacaoService;
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

    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody Conta conta) {

        try {
            Cliente cliente = conta.getCliente();
            Endereco endereco = cliente.getEndereco();

            _enderecoservice.adicionarEndereco(endereco);
            _clientesservice.adicionarNovoCliente(cliente);
            _contaservice.adicionarConta(conta);

            conta.setSaldo(BigDecimal.valueOf(0));

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity listar() {

        List<Conta> listaContas = _contaservice.listarContas();

        if (!listaContas.isEmpty())
            return new ResponseEntity(listaContas, HttpStatus.FOUND);
        else
            return new ResponseEntity("não existem contas criadas", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarPorId(@Valid @PathVariable(value = "id") Long id) {

        Optional<Conta> conta = _contaservice.obterPorId(id);

        if(conta.isPresent())
            return new ResponseEntity(conta.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity("Conta não encontrada", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarPorCpf(@Valid @PathVariable(value = "cpf") String cpf) {
        Optional<Conta> conta = _contaservice.obterPorCpf(cpf);

        if(conta.isPresent())
            return new ResponseEntity(conta.get(), HttpStatus.FOUND);
        else
            return new ResponseEntity("Conta não encontrada", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@Valid @PathVariable(value = "id") Long id, @Valid @RequestBody Conta novaConta) {

        Optional<Conta> oldConta = _contaservice.obterPorId(id);

        if (oldConta.isPresent()) {
            Conta conta = oldConta.get();

            _contaservice.atualizarConta(conta, novaConta);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity("Conta não encontrada", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@Valid @PathVariable(value = "id") Long id) {
        Optional<Conta> conta = _contaservice.obterPorId(id);

        if (conta.isPresent()) {
            Cliente cliente = conta.get().getCliente();
            Endereco endereco = cliente.getEndereco();

            _enderecoservice.deletarEndereco(endereco);
            _clientesservice.deletarCliente(cliente);
            _historicomovimentacaoservice.deletaMovimentacao(id);
            _contaservice.deletarConta(conta.get());

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity("Conta não encontrada", HttpStatus.NOT_FOUND);
    }
}
