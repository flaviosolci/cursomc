package br.com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		final Pedido buscar = service.buscar(id);
		return ResponseEntity.ok(buscar);
	}

}
