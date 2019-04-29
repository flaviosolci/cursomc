package br.com.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

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
	private Pedido pedido;

}
