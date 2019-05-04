package br.com.cursomc;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cursomc.services.amazon.S3Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que inicia a aplicação
 *
 * @author Flavio Solci
 *
 */
@SpringBootApplication
@Slf4j
public class CursomcApplication implements CommandLineRunner {

	/** Serviço de acesso ao amazon S3 */
	@Autowired
	private S3Service s3Service;

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
		s3Service.uploadFile(Paths.get(getClass().getResource("/img/supernatural.jpg").toURI()));

	}

}
