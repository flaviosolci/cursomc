package br.com.cursomc.services.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementação real de envio de email
 *
 * @author Flavio Solci
 *
 */
@Slf4j
public class SmtpEmailService extends AbstractEmailService {

	/** Responsável por enviar email com text plano */
	@Autowired
	private MailSender mailSender;

	/** Responsável por enviar email com HTML */
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(final SimpleMailMessage mailMessage) {
		log.info("Enviando email ...");
		mailSender.send(mailMessage);
		log.info("Email enviado!");

	}

	@Override
	public void sendHtmlEmail(final MimeMessage msg) {
		log.info("Enviando email HTML...");
		javaMailSender.send(msg);
		log.info("Email enviado!");

	}
}
