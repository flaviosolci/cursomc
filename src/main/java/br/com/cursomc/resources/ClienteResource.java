package br.com.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
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

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.dto.cliente.ClienteDTO;
import br.com.cursomc.dto.cliente.ClienteNewDTO;
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

	/**
	 * Salva um Cliente no BD
	 *
	 * @param clienteNewDTO cliente para ser salvo
	 * @return Response com URI para o cliente salvo (201)
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<Void> insert(@Valid @RequestBody final ClienteNewDTO clienteNewDTO) {
		final Cliente cliente = new Cliente(clienteNewDTO);
		final Cliente clienteSalvo = service.insert(cliente);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clienteSalvo.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	/**
	 * Atualiza um cliente
	 *
	 * @param id         ID de um cliente a ser atualizado
	 * @param clienteDTO cliente atualizado
	 * @return No content (204)
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") final Integer id,
			@Valid @RequestBody final ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		service.update(clienteDTO);
		return ResponseEntity.noContent().build();

	}

	/**
	 * Deleta um cliente do DB
	 *
	 * @param id ID do cliente que será deletado
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") final Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Procura um cliente no BD e retorna caso tenha encontrado
	 *
	 * @return Lista de clientes
	 */
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		final List<Cliente> clientes = service.findAll();
		final List<ClienteDTO> clienteDTO = clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(clienteDTO);
	}

	/**
	 * Procura um Cliente no BD usando paginação e retorna caso tenha encontrado
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 *
	 * @return Lista de cliente paginados
	 */
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findWithPage(
			@RequestParam(value = "page", defaultValue = "0") final Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") final String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") final String direction) {

		final Page<Cliente> clientes = service.findWithPage(page, linesPerPage, orderBy, Direction.valueOf(direction));
		final Page<ClienteDTO> clientesDTO = clientes.map(ClienteDTO::new);
		return ResponseEntity.ok(clientesDTO);
	}

}
