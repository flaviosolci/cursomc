package br.com.cursomc.domain.pagamento;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.cursomc.domain.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Modelo para pagamentos com boleto
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagamentoComBoleto extends Pagamento {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Data do vencimento do boleto */
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	/** Data do pagamento do boleto */
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date dataPagamento;

	/**
	 * Construtor sem data de pagamento
	 *
	 * @param estadoPgto     Estado do pagamento
	 * @param pedido         Pedido relacionado com o pgto
	 * @param dataVencimento data do vencimento
	 */
	public PagamentoComBoleto(final EstadoPagamento estadoPgto, final Pedido pedido, final Date dataVencimento) {
		super(estadoPgto.getCodigo(), pedido);
		this.dataVencimento = dataVencimento;
	}

	/**
	 * Construtor
	 *
	 * @param estadoPgto     Estado do pagamento
	 * @param pedido         Pedido relacionado com o pgto
	 * @param dataVencimento data do vencimento
	 * @param dataPgto       Data do Pagamento
	 */
	public PagamentoComBoleto(final EstadoPagamento estadoPgto, final Pedido pedido, final Date dataVencimento,
			final Date dataPgto) {
		super(estadoPgto.getCodigo(), pedido);
		this.dataVencimento = dataVencimento;
		dataPagamento = dataPgto;
	}
}
