package br.com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursomc.domain.produto.Categoria;

/**
 * Acesso as informações de categoria no BD
 *
 * @author Flavio Solci
 *
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
