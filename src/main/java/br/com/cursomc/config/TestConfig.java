package br.com.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
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

	/** Criador de entradas dummies */
	@Autowired
	private DBService dbService;

	/**
	 * Cria entradas dummies no DB
	 *
	 * @return true
	 * @throws ParseException
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateDabase();
		return true;

	}

}
