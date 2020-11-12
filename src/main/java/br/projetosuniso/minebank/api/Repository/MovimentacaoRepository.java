package br.projetosuniso.minebank.api.Repository;

import br.projetosuniso.minebank.api.Model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}

