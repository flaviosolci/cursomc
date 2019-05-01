package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cursomc.domain.cliente.Cliente;

/**
 * Acesso as informações dos Clientes no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/**
	 * Procura um cliente pelo email. O Spring automaticamente detecta o nome do
	 * metodo e faz a logica para buscar no banco
	 *
	 * @param email Email do cliente
	 * @return Cliente se encontrado
	 */
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
