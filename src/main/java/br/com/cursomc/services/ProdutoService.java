package br.com.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.produto.Categoria;
import br.com.cursomc.domain.produto.Produto;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.ProdutoRepository;
import br.com.cursomc.services.exception.ObjectNotFoundException;

/**
 * Serviço de acesso aos Produtos
 *
 * @author Flavio Solci
 *
 */
@Service
public class ProdutoService {

	/** Acesso a tabela de Produtos */
	@Autowired
	private ProdutoRepository prodRepo;

	/** Acesso a tabela de Categorias */
	@Autowired
	private CategoriaRepository catRepo;

	/**
	 * Busca um Produto pelo ID
	 *
	 * @param id ID do Produto
	 * @return Produto ou lança uma exceção se não encontrado
	 */
	public Produto find(final Integer id) {
		return prodRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto com o ID " + id + " não existe!"));
	}

	/**
	 * Encontra todos os produtos que tem o mesmo nome informado (LIKE) e estão na
	 * mesma categoria informadas
	 *
	 * @param nomeProduto  Nome parcial ou completo do produto a ser procurado
	 * @param idCategorias IDs das categorias para filtrar os produtos
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 * @return Página com Produtos encontrados
	 */
	public Page<Produto> search(final String nomeProduto, final List<Integer> idCategorias, final Integer page,
			final Integer linesPerPage, final String orderBy, final Direction direction) {
		final PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		final List<Categoria> categorias = catRepo.findAllById(idCategorias);
		return prodRepo.findDistinctByNomeContainingAndCategoriasIn(nomeProduto, categorias, pageRequest);

	}

}
