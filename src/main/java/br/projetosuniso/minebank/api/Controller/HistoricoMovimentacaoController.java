package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.HistoricoMovimentacao;
import br.projetosuniso.minebank.api.Service.HistoricoMovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimentacao")
public class HistoricoMovimentacaoController {

    @Autowired
    private HistoricoMovimentacaoService _ms;

    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody HistoricoMovimentacao movimentacao) {

        try {
            _ms.adicionarMovimentacao(movimentacao);

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obterHistorico(@Valid @PathVariable(value = "id") Long id) {

        Optional<HistoricoMovimentacao> movimentacao = _ms.obterMovimentacao(id);

        if (movimentacao.isPresent())
            return new ResponseEntity(movimentacao, HttpStatus.FOUND);
        else
            return new ResponseEntity("não existem movimentações para essa conta", HttpStatus.NOT_FOUND);

    }

    @GetMapping
    public ResponseEntity listar() {

        List<HistoricoMovimentacao> lista = _ms.listarMovimentacoes();

        if (!lista.isEmpty())
            return new ResponseEntity(lista, HttpStatus.FOUND);
        else
            return new ResponseEntity("não existem contas criadas", HttpStatus.NOT_FOUND);

    }
}