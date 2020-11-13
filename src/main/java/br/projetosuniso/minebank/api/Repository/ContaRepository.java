package br.projetosuniso.minebank.api.Repository;

import br.projetosuniso.minebank.api.Model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    /*Metodo da interface que faz o select no banco retornando um tipo de Cliente*/
    @Query(value = "select new br.projetosuniso.minebank.api.Model.Conta(c.id, c.numero, c.agencia, c.senha, c.cliente, c.saldo) from Conta c where c.cliente.cpf = :cpf order by c.id")
    Optional<Conta> findByCpf(@Param("cpf") String cpf);
}
