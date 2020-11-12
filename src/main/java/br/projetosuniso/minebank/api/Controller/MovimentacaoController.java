package br.projetosuniso.minebank.api.Controller;

import br.projetosuniso.minebank.api.Model.Movimentacao;
import br.projetosuniso.minebank.api.Service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService _ms;

    @GetMapping
    public ResponseEntity listar() {

        List<Movimentacao> listaMovimentacao = _ms.listar();

        if (!listaMovimentacao.isEmpty())
            return new ResponseEntity(listaMovimentacao, HttpStatus.FOUND);
        else
            return new ResponseEntity("não existem movimentações criadas", HttpStatus.NOT_FOUND);

    }
}
