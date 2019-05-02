package br.com.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cursomc.services.mail.EmailService;
import br.com.cursomc.services.mail.MockEmailService;
import br.com.cursomc.services.mail.SmtpEmailService;

/**
 * Configurações de Prod
 *
 * @author Flavio Solci
 *
 */
@Configuration
@Profile("prod")
public class ProdConfig {

	/**
	 * Aqui deveria cria um {@link SmtpEmailService}, porém como nao quero
	 * configurar o email padrão, vou deixar o dummy mesmo
	 *
	 * @return Cria o email service real
	 */
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
