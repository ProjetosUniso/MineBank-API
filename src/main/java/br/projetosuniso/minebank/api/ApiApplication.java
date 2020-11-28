package br.projetosuniso.minebank.api;

import br.projetosuniso.minebank.api.Model.Movimentacao;
import br.projetosuniso.minebank.api.Service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    /*
    PROFESSOR INFO:
    Essa API foi feita para ser consumida pela aplicação mobile do MineBank
    sua documentação se encontra no link : https://minebank-api-service.herokuapp.com/swagger-ui.html
     */

    @Autowired
    private MovimentacaoService _ms;

    @Override
    public void run(String... args) throws Exception {

        ArrayList<Movimentacao> list = new ArrayList<Movimentacao>() {
            {
                add(new Movimentacao(1, "SAQUE", "Saque"));
            }
            {
                add(new Movimentacao(2, "DEPOSITO", "Deposito"));
            }
            {
                add(new Movimentacao(3, "REALTRANSFE", "Realiaza tranferência"));
            }
            {
                add(new Movimentacao(4, "RECETRANSFE", "Recebe tranferência"));
            }
            {
                add(new Movimentacao(5, "RESGPOUPANCA", "Resgate poupança"));
            }
            {
                add(new Movimentacao(6, "DEPPOUPANCA", "Deposito poupança"));
            }
            {
                add(new Movimentacao(7, "PAGBOLETO", "Pagamento em boleto"));
            }
        };

        for (Movimentacao movimentacao: list) {
            _ms.adicionarMovimentacao(movimentacao);
        }
    }
}
