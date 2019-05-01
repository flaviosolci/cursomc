package br.com.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.dto.cliente.ClienteDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.services.exception.DataIntegrityException;
import br.com.cursomc.services.exception.ObjectNotFoundException;

/**
 * Serviço de acesso aos Clientes
 *
 * @author Flavio Solci
 *
 */
@Service
public class ClienteService {

	/** Acesso ao BD */
	@Autowired
	private ClienteRepository repository;

	/**
	 * Busca um cliente pelo ID
	 *
	 * @param id ID do cliente
	 * @return Cliente ou lança uma exceção se não encontrado
	 */
	public Cliente find(final Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente com o ID " + id + " não existe!"));
	}

	/**
	 * Atualiza um Cliente, se ele não existir lança uma exceção
	 *
	 * @param cliente Cliente para ser atualizado
	 * @return Cliente atualizado
	 */
	public Cliente update(final ClienteDTO clienteDTO) {
		// se o cliente não for encontrado lança uma exceção
		final Cliente clienteFound = find(clienteDTO.getId());
		updateClienteWithDTO(clienteFound, clienteDTO);
		return repository.save(clienteFound);
	}

	/**
	 * Deleta um cliente, se ele não existir lança uma exceção
	 *
	 * @param id Id para ser deletado
	 */
	public void delete(final Integer id) {
		// se o cliente não for encontrada lança uma exceção
		find(id);
		try {
			repository.deleteById(id);
		} catch (final DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente com entidades relacionadas!");
		}
	}

	/**
	 * @return Todas os Clientes do DB
	 */
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/**
	 * Encontra todas os clientes com paginação
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 * @return Página com Clientes
	 */
	public Page<Cliente> findWithPage(final Integer page, final Integer linesPerPage, final String orderBy,
			final Direction direction) {
		final PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		return repository.findAll(pageRequest);
	}

	// =============================
	// == HELPER
	// =============================

	/**
	 * Atualiza um cliente do BD com as informações do DTO, assim o cliente será
	 * atualizado apenas com as informações relevantes e o resto permanecerá igual
	 *
	 * @param clienteFound Cliente encontrando no BD
	 * @param clienteDTO   Cliente vindo do serviço REST para ser atualizado
	 */
	private void updateClienteWithDTO(final Cliente clienteFound, final ClienteDTO clienteDTO) {
		clienteFound.setNome(clienteDTO.getNome());
		clienteFound.setEmail(clienteDTO.getEmail());
	}

}
