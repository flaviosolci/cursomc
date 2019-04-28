package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.Endereco;

/**
 * Acesso as informações dos enderecos dos Clientes no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
