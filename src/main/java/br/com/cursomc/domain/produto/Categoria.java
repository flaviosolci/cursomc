package br.com.cursomc.domain.produto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cursomc.dto.produto.CategoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Classe modelo para Categorias. Tabela: CATEGORIA
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID da categoria */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	/** Nome da Categoria */
	@NonNull
	private String nome;

	/**
	 * Construtor com parâmetro do objeto dto
	 *
	 * @param categoria dto para ser transformado em categoria
	 */
	public Categoria(final CategoriaDTO categoriaDTO) {
		id = categoriaDTO.getId();
		nome = categoriaDTO.getNome();

	}
}
