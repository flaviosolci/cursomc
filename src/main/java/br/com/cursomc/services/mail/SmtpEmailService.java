package br.com.cursomc.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementação real de envio de email
 *
 * @author Flavio Solci
 *
 */
@Slf4j
public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	@Override
	public void sendEmail(final SimpleMailMessage mailMessage) {
		log.info("Enviando email ...");
		mailSender.send(mailMessage);
		log.info("Email enviado!");

	}

}
