package br.com.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Estado;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.repositories.ProdutoRepository;

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
	private CategoriaRepository categoriaRepo;

	/** Repositório usado para inserir produtos default quando a aplicação inicia */
	@Autowired
	private ProdutoRepository produtoRepo;

	/** Repositório usado para inserir cidades default quando a aplicação inicia */
	@Autowired
	private CidadeRepository cidadeRepo;

	/** Repositório usado para inserir estados default quando a aplicação inicia */
	@Autowired
	private EstadoRepository estadoRepo;

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

		categoriaRepo.saveAll(Arrays.asList(categoria, categoria2));

		final Produto produto = new Produto("Computador", BigDecimal.valueOf(2000.00));
		produto.getCategorias().add(categoria);
		final Produto produto2 = new Produto("Impressora", BigDecimal.valueOf(800.00));
		produto2.getCategorias().addAll(Arrays.asList(categoria, categoria2));
		final Produto produto3 = new Produto("Mouse", BigDecimal.valueOf(80.00));
		produto3.getCategorias().add(categoria);

		produtoRepo.saveAll(Arrays.asList(produto, produto2, produto3));

		final Estado est1 = new Estado("Minas Gerais");
		final Estado est2 = new Estado("São Paulo");
		final Cidade c1 = new Cidade("Uberlândia", est1);
		final Cidade c2 = new Cidade("São Paulo", est2);
		final Cidade c3 = new Cidade("Campinas", est2);

		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));

	}

}
