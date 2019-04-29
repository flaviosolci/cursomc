package br.com.cursomc.domain;

import javax.persistence.Entity;

import br.com.cursomc.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Modelo para pagamentos com cartão
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagamentoComCartao extends Pagamento {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Numero de parcelas do pagamento com cartão */
	private Integer numeroDeParcelas;

	public PagamentoComCartao(final EstadoPagamento estadoPgto, final Pedido pedido, final Integer numeroDeParacelas) {
		super(estadoPgto.getCodigo(), pedido);
		numeroDeParcelas = numeroDeParacelas;
	}

}
