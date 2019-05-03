package br.com.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cursomc.domain.pedido.Pedido;
import br.com.cursomc.services.PedidoService;

/**
 * API REST para acesso aos Pedidos
 *
 * @author Flavio Solci
 *
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	/** Classe de acesso os pedidos */
	@Autowired
	private PedidoService service;

	/**
	 * Procura um Pedido no BD e retorna caso tenha encontrado
	 *
	 * @param id ID do Pedido que será buscado
	 * @return Pedido ou NULL, se não encontra no BD
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable(name = "id") final Integer id) {
		final Pedido buscar = service.find(id);
		return ResponseEntity.ok(buscar);
	}

	/**
	 * Salva um Pedido no BD
	 *
	 * @param pedido Peido para ser salvo
	 * @return Response com URI para a pedido salvo (201)
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody final Pedido pedido) {
		final Pedido pedidoSalvo = service.insert(pedido);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pedidoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	/**
	 * Procura um Pedido no BD usando paginação e retorna caso tenha encontrado
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 *
	 * @return Lista de pedidos paginados
	 */
	@GetMapping()
	public ResponseEntity<Page<Pedido>> findWithPage(
			@RequestParam(value = "page", defaultValue = "0") final Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instante") final String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") final String direction) {

		final Page<Pedido> pedido = service.findWithPage(page, linesPerPage, orderBy, Direction.valueOf(direction));
		return ResponseEntity.ok(pedido);
	}
}
