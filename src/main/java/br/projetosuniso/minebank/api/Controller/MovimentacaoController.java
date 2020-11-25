package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Movimentacao;
import br.projetosuniso.minebank.api.Service.MovimentacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiposMovimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService _ms;

    @ApiOperation(value = "Retorna todos os tipos de movimentações, usado apenas para testes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os tipos de movimentação"),
            @ApiResponse(code = 400, message = "não existem tipos de movimentação"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public ResponseEntity listar() {

        List<Movimentacao> listaMovimentacao = _ms.listar();

        if (!listaMovimentacao.isEmpty())
            return ResponseEntity.ok(listaMovimentacao);
        else
            return ResponseEntity.badRequest().build();

    }

    @ApiOperation(value = "Retorna um tipo de movimentação de acordo com a chave")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o tipo movimentação"),
            @ApiResponse(code = 400, message = "Tipo não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/{chave}")
    public ResponseEntity<Movimentacao> obterHistorico(@Valid @PathVariable(value = "chave") String chave) {

        Optional<Movimentacao> movimentacao = _ms.obterMovimentacao(chave);

        return movimentacao
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
