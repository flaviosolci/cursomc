package br.com.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.repositories.ClienteRepository;
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
	public Cliente buscar(final Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente com o ID " + id + " não existe!"));
	}

}
