package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.Cliente;

/**
 * Acesso as informações dos Clientes no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
