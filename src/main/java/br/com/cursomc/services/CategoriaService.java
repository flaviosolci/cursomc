package br.com.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.produto.Categoria;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.services.exception.DataIntegrityException;
import br.com.cursomc.services.exception.ObjectNotFoundException;

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
	 * @return Categoria ou lança uma exceção se não encontrado
	 */
	public Categoria find(final Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria com o ID " + id + " não existe!"));
	}

	/**
	 * Insere uma nova categoria
	 *
	 * @param categoria Categoria a ser inserida
	 * @return Categoria inserida
	 */
	public Categoria insert(final Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}

	/**
	 * Atualiza uma categoria, se ela não existir lança uma exceção
	 *
	 * @param categoria categoria para ser atualizada
	 * @return Categoria atualizada
	 */
	public Categoria update(final Categoria categoria) {
		// se categoria não for encontrada lança uma exceção
		find(categoria.getId());
		return repository.save(categoria);
	}

	/**
	 * Deleta uma categoria, se ela não existir lança uma exceção
	 *
	 * @param id Id para ser deletada
	 */
	public void delete(final Integer id) {
		// se categoria não for encontrada lança uma exceção
		find(id);
		try {
			repository.deleteById(id);
		} catch (final DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com Produtos!");
		}
	}

	/**
	 * @return Todas as categorias do DB
	 */
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	/**
	 * Encontra todas as categorias com paginação
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 * @return Página com categorias
	 */
	public Page<Categoria> findWithPage(final Integer page, final Integer linesPerPage, final String orderBy,
			final Direction direction) {
		final PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		return repository.findAll(pageRequest);
	}

}
