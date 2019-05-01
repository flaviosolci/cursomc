package br.com.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.cursomc.domain.pagamento.PagamentoComBoleto;

/**
 * Dummy service para simular um serviço externo de boleto
 *
 * @author Flavio Solci
 *
 */
@Service
public class BoletoService {

	/**
	 * Preenche o vencimento de um pagamento com boleto. A data de vencimento é
	 * sempre 1 semana após a data do pedido
	 *
	 * @param pgto             Pagamento copm boleto
	 * @param instanteDoPedido Data do pedido
	 */
	public void preencherPagamentoComBoleto(final PagamentoComBoleto pgto, final Date instanteDoPedido) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}

}
