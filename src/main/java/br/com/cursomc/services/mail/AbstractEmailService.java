/** */
package br.com.cursomc.services.mail;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.cursomc.domain.pedido.Pedido;

/**
 * Classe abstrata para envio de email
 *
 * @author Flavio Solci
 *
 */
public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String defaultSender;

	@Override
	public void sendOrderConfirmationEmail(final Pedido pedido) {
		final SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);

	}

	/**
	 * Cria um SimpleMailMessage baseado no pedido
	 *
	 * @param pedido Peido que teá um email enviado
	 * @return SimpleMailMessage
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(final Pedido pedido) {
		final SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(defaultSender);
		sm.setSubject("** Pedido confirmado! Codigo: " + pedido.getId());
		sm.setSentDate(new Date());
		sm.setText(pedido.toString());
		return sm;
	}

}
