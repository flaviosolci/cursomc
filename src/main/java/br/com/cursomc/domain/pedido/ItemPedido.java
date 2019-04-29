/** */
package br.com.cursomc.domain.pedido;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Item de um pedido
 *
 * @author Flavio Solci
 *
 */
@Data
@RequiredArgsConstructor
@NonNull
public class ItemPedido implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Desconto do item, se houver */
	@NonNull
	private BigDecimal desconto;

	/** Quantidade do item */
	@NonNull
	private Integer quantidade;

	/** Preco do item */
	@NonNull
	private BigDecimal preco;

}
