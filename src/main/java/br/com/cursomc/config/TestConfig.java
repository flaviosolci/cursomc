package br.com.cursomc.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cursomc.services.config.DBService;
import br.com.cursomc.services.mail.EmailService;
import br.com.cursomc.services.mail.MockEmailService;

/**
 * Configurações de Teste
 *
 * @author Flavio Solci
 *
 */
@Configuration
@Profile("test")
public class TestConfig {

	/**
	 * Cria entradas dummies no DB
	 *
	 * @param dbService Criado de entradas dummies
	 * @return true
	 * @throws ParseException
	 */
	@Bean
	public boolean instantiateDatabase(final DBService dbService) throws ParseException {
		dbService.instantiateDabase();
		return true;

	}

	/**
	 * @return Cria o email service dummy
	 */
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
