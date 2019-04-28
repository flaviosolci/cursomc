package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.Cidade;

/**
 * Acesso as informações das Cidades no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
