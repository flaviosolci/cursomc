package br.com.cursomc.dto.produto;

import java.io.Serializable;

import br.com.cursomc.domain.produto.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para categorias
 *
 * @author Flavio Solci
 *
 */
@Data
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID da categoria */
	private Integer id;

	/** Nome da Categoria */
	private String nome;

	/**
	 * Construtor com parâmetro do objeto original
	 *
	 * @param categoria categoria par ser transformada em DTO
	 */
	public CategoriaDTO(final Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();

	}

}
