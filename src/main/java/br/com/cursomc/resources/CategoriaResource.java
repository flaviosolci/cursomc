package br.com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.Categoria;
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
	public ResponseEntity<Categoria> find(@PathVariable(name = "id") final Integer id) {
		final Categoria buscar = service.buscar(id);
		return ResponseEntity.ok(buscar);
	}

}
