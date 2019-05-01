package br.com.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cursomc.domain.produto.Categoria;
import br.com.cursomc.dto.produto.CategoriaDTO;
import br.com.cursomc.services.CategoriaService;

/**
 * API REST para acesso as categorias
 *
 * @author Flavio Solci
 *
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	/** Classe de acesso as categorias */
	@Autowired
	private CategoriaService service;

	/**
	 * Procura uma categoria no BD e retorna caso tenha encontrado
	 *
	 * @param id ID da categoria que será buscada
	 * @return Categoria ou NULL, se não encontra no BD
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> find(@PathVariable(name = "id") final Integer id) {
		final Categoria categoria = service.find(id);
		return ResponseEntity.ok(new CategoriaDTO(categoria));
	}

	/**
	 * Salva uma categoria no BD
	 *
	 * @param categoriaDTO categoria para ser salva
	 * @return Response com URI para a categoria salva (201)
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody final CategoriaDTO categoriaDTO) {
		final Categoria categoria = new Categoria(categoriaDTO);
		final Categoria categoriaSalva = service.insert(categoria);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaSalva.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	/**
	 * Atualiza uma categoria
	 *
	 * @param id           ID da categoria a ser atualizada
	 * @param categoriaDTO categoria atualizada
	 * @return No content (204)
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") final Integer id,
			@Valid @RequestBody final CategoriaDTO categoriaDTO) {
		categoriaDTO.setId(id);
		final Categoria categoria = new Categoria(categoriaDTO);
		service.update(categoria);
		return ResponseEntity.noContent().build();

	}

	/**
	 * Delete uma categoria do DB
	 *
	 * @param id ID da categoria que será deletada
	 * @return No content (204)
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") final Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Procura uma categoria no BD e retorna caso tenha encontrado
	 *
	 * @return Lista de categorias
	 */
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		final List<Categoria> categorias = service.findAll();
		final List<CategoriaDTO> categoriaDTOs = categorias.stream().map(CategoriaDTO::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(categoriaDTOs);
	}

	/**
	 * Procura uma categoria no BD usando paginação e retorna caso tenha encontrado
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 *
	 * @return Lista de categorias paginadas
	 */
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findWithPage(
			@RequestParam(value = "page", defaultValue = "0") final Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") final String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") final String direction) {

		final Page<Categoria> categorias = service.findWithPage(page, linesPerPage, orderBy,
				Direction.valueOf(direction));
		final Page<CategoriaDTO> categoriaDTOs = categorias.map(CategoriaDTO::new);
		return ResponseEntity.ok(categoriaDTOs);
	}

}
