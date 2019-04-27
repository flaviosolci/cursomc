package br.com.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.repositories.CategoriaRepository;

/**
 * Classe que inicia a aplicação
 *
 * @author Flavio Solci
 *
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	/**
	 * Repositório usado para inserir categorias default quando a aplicação inicia
	 */
	@Autowired
	private CategoriaRepository repository;

	/**
	 * Start the application
	 *
	 * @param args Command line ags
	 */
	public static void main(final String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	/**
	 * Adiciona algumas entradas no banco. Como o banco é em memoria, toda vez que a
	 * aplicação sobe ele está vazio. Com esse método, nos inserimos algumas
	 * entradas todo vez que se inicia a aplicação
	 *
	 * @param args Command line args
	 */
	@Override
	public void run(final String... args) throws Exception {
		final Categoria categoria = new Categoria("Informática");
		final Categoria categoria2 = new Categoria("Escritório");
		repository.saveAll(Arrays.asList(categoria, categoria2));

	}

}
