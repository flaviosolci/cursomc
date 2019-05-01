package br.com.cursomc.domain.pedido;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.com.cursomc.domain.produto.Produto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

/**
 * Item de um pedido
 *
 * @author Flavio Solci
 *
 */
@Data
@NonNull
@Entity
public class ItemPedido implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Primary do Item Peido */
	@EmbeddedId
	@Getter(value = AccessLevel.PRIVATE)
	private ItemPedidoPK itemPedidoPK;

	/** Desconto do item, se houver */
	@NonNull
	private BigDecimal desconto;

	/** Quantidade do item */
	@NonNull
	private Integer quantidade;

	/** Preço do item */
	@NonNull
	private BigDecimal preco;

	/**
	 * Contrutor
	 */
	public ItemPedido() {
		itemPedidoPK = new ItemPedidoPK();
	}

	/**
	 * Construtor
	 *
	 * @param pedido     Pedido relacionado a esse item
	 * @param produto    item
	 * @param desconto   desconto do item
	 * @param quantidade quantidade do produto
	 * @param preco      proco do produto
	 */
	public ItemPedido(@NonNull final Pedido pedido, @NonNull final Produto produto, @NonNull final BigDecimal desconto,
			@NonNull final Integer quantidade, @NonNull final BigDecimal preco) {
		itemPedidoPK = new ItemPedidoPK(pedido, produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	/**
	 * @return Produto do item. Usado pelo JSON/Spring para mostrar os produtos
	 *         desse item
	 */
	public Produto getProduto() {
		return getItemPedidoPK().getProduto();
	}

	/**
	 * @param produto Produto do item.
	 */
	public void setProduto(final Produto produto) {
		getItemPedidoPK().setProduto(produto);
	}

	/**
	 * @param pedido Pedido que esse item pertence
	 */
	public void setPedido(final Pedido pedido) {
		getItemPedidoPK().setPedido(pedido);
	}

	/**
	 * Calcula o subtotal do Item
	 *
	 * @return subtotal (preco - desconto) * quantidade
	 */
	public BigDecimal getSubTotal() {
		return preco.subtract(desconto).multiply(BigDecimal.valueOf(quantidade));
	}

}
