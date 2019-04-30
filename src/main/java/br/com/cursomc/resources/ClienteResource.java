package br.com.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.services.ClienteService;

/**
 * API REST para acesso aos Clientes
 *
 * @author Flavio Solci
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	/** Classe de acesso aos clientes */
	@Autowired
	private ClienteService service;

	/**
	 * Procura um Cliente no BD e retorna caso tenha encontrado
	 *
	 * @param id ID do cliente que será buscado
	 * @return Cliente ou NULL, se não encontra no BD
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable(name = "id") final Integer id) {
		final Cliente buscar = service.find(id);
		return ResponseEntity.ok(buscar);
	}

}
