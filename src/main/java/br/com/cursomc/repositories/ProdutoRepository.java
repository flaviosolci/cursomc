package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.produto.Produto;

/**
 * Acesso as informações dos produtos no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
