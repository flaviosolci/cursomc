package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.pedido.ItemPedido;
import br.com.cursomc.domain.pedido.ItemPedidoPK;

/**
 * Acesso as informações dos itens do peido no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
