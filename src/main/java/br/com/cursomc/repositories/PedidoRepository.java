package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.Pedido;

/**
 * Acesso as informações dos pedidos no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
