package br.projetosuniso.minebank.api.Repository;

import br.projetosuniso.minebank.api.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /*Metodo da interface que faz o select no banco retornando um tipo de Cliente*/
    @Query(value = "select new br.projetosuniso.minebank.api.Model.Cliente(c.id, c.nome, c.cpf, c.rg, c.email, c.dataNascimento) from Cliente c where c.cpf = :cpf")
    Optional<Cliente> findByCpf(@Param("cpf") String cpf);
}
