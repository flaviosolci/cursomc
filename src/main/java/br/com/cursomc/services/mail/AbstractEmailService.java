/** */
package br.com.cursomc.services.mail;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.cursomc.domain.pedido.Pedido;

/**
 * Classe abstrata para envio de email
 *
 * @author Flavio Solci
 *
 */
public abstract class AbstractEmailService implements EmailService {

	/** Propriedade vindo do application.properties */
	@Value("${default.sender}")
	private String defaultSender;

	/** Transforma um template Thymeleaf em String */
	@Autowired
	private TemplateEngine templateEngine;

	/** */
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(final Pedido pedido) {
		final SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);

	}

	@Override
	public void sendOrderConfirmationHtmlEmail(final Pedido pedido) {
		try {
			final MimeMessage mm = prepareMimeMessageFromPedido(pedido);
			sendHtmlEmail(mm);
		} catch (final MessagingException e) {
			sendOrderConfirmationEmail(pedido);
		}

	}

	/**
	 * Cria um SimpleMailMessage baseado no pedido
	 *
	 * @param pedido Peido que terá um email enviado
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

	/**
	 * Responsável por retornar o HTML preenchido com os dados de um pedido, a
	 * partir do template Thymeleaf
	 *
	 * @param pedido Peido que terá um email enviado
	 * @return mensagem do email
	 */
	protected String htmlFromTemplatePedido(final Pedido pedido) {
		final Context context = new Context();
		context.setVariable("pedido", pedido);

		return templateEngine.process("email/confirmacaoPedido", context);

	}

	/**
	 * Cria um {@link MimeMessage} para enviar um email com template HTML
	 *
	 * @param pedido Peido que terá um email enviado
	 * @return MimeMessage
	 * @throws MessagingException
	 */
	private MimeMessage prepareMimeMessageFromPedido(final Pedido pedido) throws MessagingException {
		final MimeMessage message = javaMailSender.createMimeMessage();
		final MimeMessageHelper mmh = new MimeMessageHelper(message, true);
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(defaultSender);
		mmh.setSubject("** Pedido confirmado! Codigo: " + pedido.getId());
		mmh.setSentDate(new Date());
		mmh.setText(htmlFromTemplatePedido(pedido), true);

		return message;
	}

}
