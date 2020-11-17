package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.HistoricoMovimentacao;
import br.projetosuniso.minebank.api.Service.HistoricoMovimentacaoService;
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
@RequestMapping("/movimentacao")
public class HistoricoMovimentacaoController {

    @Autowired
    private HistoricoMovimentacaoService _ms;

    @ApiOperation(value = "Adiciona uma nova movimentação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro adicionado com sucesso"),
            @ApiResponse(code = 400, message = "dados para registro invalido"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping
    public ResponseEntity<String> adicionar(@Valid @RequestBody HistoricoMovimentacao movimentacao) {

        try {

            _ms.adicionarMovimentacao(movimentacao);

            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Retorna o historico de movimentações de uma conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o historico"),
            @ApiResponse(code = 400, message = "Conta não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/{idConta}")
    public ResponseEntity obterHistorico(@Valid @PathVariable(value = "idConta") Long id) {

        List<HistoricoMovimentacao> list = _ms.obterMovimentacao(id);

        if (!list.isEmpty())
            return ResponseEntity.ok(list);
        else
            return ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Retorna todos os historicos, usado apenas para testes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os historicos"),
            @ApiResponse(code = 400, message = "não existem historicos"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public ResponseEntity listar() {

        List<HistoricoMovimentacao> lista = _ms.listarMovimentacoes();

        if (!lista.isEmpty())
            return ResponseEntity.ok(lista);
        else
            return ResponseEntity.badRequest().build();

    }
}