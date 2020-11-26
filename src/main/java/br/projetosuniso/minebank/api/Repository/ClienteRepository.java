package br.projetosuniso.minebank.api.Repository;

import br.projetosuniso.minebank.api.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /*Metodo que verifica se um cpf já foi cadastrado na base*/
    @Query(value = "select 1 from Cliente c where c.cpf = :cpf order by c.id")
    Integer verifyCpfExists(@Param("cpf") String cpf);

    /*Metodo que verifica se um email já foi cadastrado na base*/
    @Query(value = "select 1 from Cliente c where c.email = :email order by c.id")
    Integer verifyEmailExists(@Param("email") String email);
}

