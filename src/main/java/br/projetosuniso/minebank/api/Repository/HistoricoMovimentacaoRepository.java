package br.projetosuniso.minebank.api.Repository;

import br.projetosuniso.minebank.api.Model.HistoricoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoMovimentacaoRepository extends JpaRepository<HistoricoMovimentacao, Long> {
}
