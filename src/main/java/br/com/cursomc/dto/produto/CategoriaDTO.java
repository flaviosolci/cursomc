package br.com.cursomc.dto.produto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	@NotEmpty(message = "Nome da categoria é obrigatório")
	@Size(min = 5, max = 80, message = "Nome da categoria deve ter entre 5 e 80 caracteres")
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
