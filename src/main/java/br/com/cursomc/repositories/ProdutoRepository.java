package br.com.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cursomc.domain.produto.Categoria;
import br.com.cursomc.domain.produto.Produto;

/**
 * Acesso as informações dos produtos no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/**
	 * Encontra todos os produtos que tem o mesmo nome informado (LIKE) e estão na
	 * mesma categoria informadas
	 *
	 * @param nomeProduto Nome do Produto
	 * @param categorias  Lista de categorias
	 * @param pageRequest Paginação
	 * @return Page<Produto>
	 */
	@Transactional(readOnly = true)
	//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nomeProduto% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nomeProduto, List<Categoria> categorias,
			Pageable pageRequest);

}
