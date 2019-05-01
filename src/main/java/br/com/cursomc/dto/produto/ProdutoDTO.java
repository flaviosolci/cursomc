package br.com.cursomc.dto.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.cursomc.domain.produto.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para PRodutos
 *
 * @author Flavio Solci
 *
 */
@Data
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID unico do produto */
	private Integer id;
	/** Nome do produto */
	private String nome;
	/** Preço do produto */
	private BigDecimal preco;

	/**
	 * Construtor com parâmetro do objeto original
	 *
	 * @param produto Produto par ser transformada em DTO
	 */
	public ProdutoDTO(final Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		preco = produto.getPreco();

	}

}
