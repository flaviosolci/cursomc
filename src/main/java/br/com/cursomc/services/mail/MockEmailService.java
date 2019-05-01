/** */
package br.com.cursomc.services.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Flavio Solci
 *
 */
@Slf4j
public class MockEmailService extends AbstractEmailService {

	@Override
	public void sendEmail(final SimpleMailMessage mailMessage) {
		log.info("Simulando envio de e-mail...");
		log.info(mailMessage.toString());
		log.info("E-mail enviado!");
	}

	@Override
	public void sendHtmlEmail(final MimeMessage msg) {
		log.info("Simulando envio de e-mail HTML...");
		log.info(msg.toString());
		log.info("E-mail enviado!");

	}

}
