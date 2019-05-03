package br.com.cursomc.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.domain.pedido.Pedido;

/**
 * Acesso as informações dos pedidos no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	/**
	 * Busca Pedido por cliente e id ordenando por data
	 *
	 * @param Id      do pedido
	 * @param cliente Cliente que os pedidos devem ser linkados
	 * @return Pedido do cliente, se encontrado
	 */
	@Transactional(readOnly = true)
	Optional<Pedido> findByIdAndClienteOrderByInstanteDesc(Integer id, Cliente cliente);

	/**
	 * Busca todos os Pedido por cliente ordenando por data
	 *
	 * @param cliente Cliente que os pedidos devem ser linkados
	 * @param page    Paginação
	 * @return Pedidos do cliente, se encontrado
	 */
	@Transactional(readOnly = true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable page);

}
