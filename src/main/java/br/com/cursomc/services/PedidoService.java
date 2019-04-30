package br.com.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.pedido.Pedido;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.services.exception.ObjectNotFoundException;

/**
 * Serviço de acesso aos Pedidos
 *
 * @author Flavio Solci
 *
 */
@Service
public class PedidoService {

	/** Acesso ao BD */
	@Autowired
	private PedidoRepository repository;

	/**
	 * Busca um cliente pelo ID
	 *
	 * @param id ID do cliente
	 * @return Cliente ou lança uma exceção se não encontrado
	 */
	public Pedido buscar(final Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido com o ID " + id + " não existe!"));
	}

}
