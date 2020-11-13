package br.projetosuniso.minebank.api.Repository;

import br.projetosuniso.minebank.api.Model.HistoricoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoMovimentacaoRepository extends JpaRepository<HistoricoMovimentacao, Long> {

    @Query(value =  "select new br.projetosuniso.minebank.api.Model.HistoricoMovimentacao" +
            "(hm.id, hm.valor, hm.descricao, hm.movimentacao, hm.conta, hm.idContaTransferencia, hm.dataInclusao) " +
            "from HistoricoMovimentacao hm where hm.conta.id = :id order by hm.id")
    List<HistoricoMovimentacao> findByIdConta(@Param("id") Long id);
}
