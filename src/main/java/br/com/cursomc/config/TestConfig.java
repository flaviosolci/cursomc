package br.com.cursomc.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cursomc.services.DBService;

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

}