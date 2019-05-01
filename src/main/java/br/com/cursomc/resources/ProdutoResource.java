package br.com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.produto.Produto;
import br.com.cursomc.dto.produto.ProdutoDTO;
import br.com.cursomc.resources.utils.URLUtils;
import br.com.cursomc.services.ProdutoService;

/**
 * API REST para acesso aos Produtos
 *
 * @author Flavio Solci
 *
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	/** Classe de acesso os produtos */
	@Autowired
	private ProdutoService service;

	/**
	 * Procura um Produto no BD e retorna caso tenha encontrado
	 *
	 * @param id ID do Produto que será buscado
	 * @return Produto ou NULL, se não encontra no BD
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable(name = "id") final Integer id) {
		final Produto buscar = service.find(id);
		return ResponseEntity.ok(buscar);
	}

	/**
	 * Procura um Produto no BD usando paginação e retorna caso tenha encontrado
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 *
	 * @return Lista de categorias paginadas
	 */
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findWithPage(
			@RequestParam(value = "nomeProduto", defaultValue = "") final String nomeProduto,
			@RequestParam(value = "categorias", defaultValue = "") final String idCategorias,
			@RequestParam(value = "page", defaultValue = "0") final Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") final String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") final String direction) {


		final Page<Produto> produtos = service.search(URLUtils.decodeParam(nomeProduto),
				URLUtils.decodeParametersIntoList(idCategorias), page, linesPerPage, orderBy,
				Direction.valueOf(direction));
		final Page<ProdutoDTO> produtoDtos = produtos.map(ProdutoDTO::new);
		return ResponseEntity.ok(produtoDtos);
	}

}
