package br.com.cursomc.domain.pedido;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
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
	@JsonManagedReference
	private Set<ItemPedido> itens = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	@JsonManagedReference
	private Pagamento pagamento;

}
