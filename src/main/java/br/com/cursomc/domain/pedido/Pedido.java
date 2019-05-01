package br.com.cursomc.domain.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.domain.cliente.Endereco;
import br.com.cursomc.domain.pagamento.Pagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Modelo Pedido
 *
 * @author Flavio Solci
 *
 */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id do pedido */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	/** data criação do pedido */
	@Temporal(TemporalType.TIMESTAMP)
	@NonNull
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date instante;
	/** Cliente que fez o pedido */
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@NonNull
	private Cliente cliente;
	/** Endereço de entrega */
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	@NonNull
	private Endereco enderecoDeEntrega;

	@OneToMany(mappedBy = "itemPedidoPK.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	@JsonManagedReference
	private Pagamento pagamento;

	/**
	 * @return Valor total do pedido
	 */
	public BigDecimal getValorTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (final ItemPedido itemPedido : itens) {
			total = total.add(itemPedido.getSubTotal());
		}
		return total;

	}

	@Override
	public String toString() {
		final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		final SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));

		final StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ").append(id);
		builder.append(", Instante: ").append(spf.format(instante));
		builder.append(", Cliente: ").append(cliente.getNome());
		builder.append(", Situação do Pagamento: ").append(pagamento.getEstado().getDescricao());
		builder.append("\nDetalhes:\n");
		for (final ItemPedido itemPedido : itens) {
			builder.append(itemPedido);
			builder.append('\n');
		}
		builder.append("Valor Total: ").append(nf.format(getValorTotal()));
		return builder.toString();
	}

}
