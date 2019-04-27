package br.com.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.repositories.CategoriaRepository;

/**
 * Serviço de acesso a Categorias
 *
 * @author Flavio Solci
 *
 */
@Service
public class CategoriaService {

	/** Acesso ao BD */
	@Autowired
	private CategoriaRepository repository;

	/**
	 * Busca uma categoria pelo ID
	 *
	 * @param id ID da categoria
	 * @return Categoria ou null se não encontrado
	 */
	public Categoria buscar(final Integer id) {
		return repository.findById(id).orElse(null);
	}

}
