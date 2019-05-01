package br.com.cursomc.domain.pagamento;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.com.cursomc.domain.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Modelo Pagamento
 *
 * @author Flavio Solci
 *
 */
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
@RequiredArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({ @JsonSubTypes.Type(value = PagamentoComBoleto.class),
	@JsonSubTypes.Type(value = PagamentoComCartao.class) })
public abstract class Pagamento implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id do Pagamento */
	@Id
	private Integer id;
	/** estado atual do pagamento */
	@NonNull
	private Integer estado;
	/** Id do pedido */
	@JoinColumn(name = "pedido_id")
	@OneToOne
	@MapsId
	@NonNull
	@JsonBackReference
	private Pedido pedido;

	/**
	 * @param estadoPagamento Estado do pagamento
	 */
	public void setEstado(final EstadoPagamento estadoPagamento) {
		estado = estadoPagamento.getCodigo();

	}

	/**
	 * @return EstadoPagamento
	 */
	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEstadoPagamento(estado);
	}

}
