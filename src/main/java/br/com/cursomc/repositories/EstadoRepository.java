package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.Estado;

/**
 * Acesso as informações dos Estados no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
