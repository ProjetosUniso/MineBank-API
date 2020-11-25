package br.projetosuniso.minebank.api.Repository;

import br.projetosuniso.minebank.api.Model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    /*Metodo da interface que faz o select no banco retornando uma Movimentacao*/
    @Query(value = "select new br.projetosuniso.minebank.api.Model.Movimentacao(m.id, m.chave, m.descricao) from Movimentacao m where m.chave = :chave")
    Optional<Movimentacao> findByChave(@Param("chave") String chave);
}

