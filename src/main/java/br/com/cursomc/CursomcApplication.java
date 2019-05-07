package br.com.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe que inicia a aplicação
 *
 * @author Flavio Solci
 *
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	/**
	 * Start the application
	 *
	 * @param args Command line ags
	 */
	public static void main(final String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		// future

	}

}
