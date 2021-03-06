package br.com.cursomc.config;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cursomc.services.config.DBService;
import br.com.cursomc.services.mail.EmailService;
import br.com.cursomc.services.mail.MockEmailService;
import br.com.cursomc.services.mail.SmtpEmailService;

/**
 * Configurações de DEV
 *
 * @author Flavio Solci
 *
 */
@Configuration
@Profile("dev")
public class DevConfig {

	/** Pega o valor da property dentro do application-dev.properties */
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	/**
	 * Cria entradas dummies no DB
	 *
	 * @param dbService Criado de entradas dummies
	 * @return true
	 * @throws ParseException
	 */
	@Bean
	public boolean instantiateDatabase(final DBService dbService) throws ParseException {
		if (!StringUtils.equals("create", strategy)) {
			return false;
		}
		dbService.instantiateDabase();
		return true;

	}

	/**
	 * Aqui deveria cria um {@link SmtpEmailService}, porém como nao quero
	 * configurar o email padrão, vou deixar o dummy mesmo
	 *
	 * @return Cria o email service real
	 */
	@Bean
	public EmailService emailService() {
		// return new SmtpEmailService();
		return new MockEmailService();
	}

}
