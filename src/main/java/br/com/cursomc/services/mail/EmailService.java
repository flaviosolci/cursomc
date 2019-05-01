package br.com.cursomc.services.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.cursomc.domain.pedido.Pedido;

/**
 * Interface para envio de email
 *
 * @author Flavio Solci
 *
 */
public interface EmailService {

	/**
	 * Envia uma confirmação de ordem por email
	 *
	 * @param pedido Pedido realizado
	 */
	void sendOrderConfirmationEmail(Pedido pedido);

	/**
	 * Chama a API para enviar um email
	 *
	 * @param mailMessage Mensagem do email
	 */
	void sendEmail(SimpleMailMessage mailMessage);

	/**
	 * Envia uma confirmação de ordem por email com um template HTML
	 *
	 * @param pedido Pedido realizado
	 */
	void sendOrderConfirmationHtmlEmail(Pedido pedido);

	/**
	 * Chama a API para enviar um email com um template HTML
	 *
	 * @param msg mensagem do email
	 */
	void sendHtmlEmail(MimeMessage msg);

}
