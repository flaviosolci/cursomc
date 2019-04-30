package br.com.cursomc.domain.pedido;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.cursomc.domain.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Primary key do item pedido
 *
 * @author Flavio Solci
 * @see ItemPedido
 *
 */
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoPK implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Pedido em que esse item está contido */
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	/** Produto que representa esse item */
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

}
