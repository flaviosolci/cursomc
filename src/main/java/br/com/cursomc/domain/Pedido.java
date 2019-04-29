package br.com.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private Date instante;
	//	/** Pagamento relacionado ao pedido */
	//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	//	private Pagamento pagamento;
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

}
