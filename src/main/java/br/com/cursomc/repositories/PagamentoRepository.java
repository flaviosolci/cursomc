package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.pagamento.Pagamento;

/**
 * Acesso as informações dos pagamentos no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
