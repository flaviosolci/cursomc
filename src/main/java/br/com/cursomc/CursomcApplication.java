package br.com.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Endereco;
import br.com.cursomc.domain.Estado;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
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

	/** Repositório usado para inserir clientes default quando a aplicação inicia */
	@Autowired
	private ClienteRepository clienteRepo;

	/**
	 * Repositório usado para inserir endereços default do clientes quando a
	 * aplicação inicia
	 */
	@Autowired
	private EnderecoRepository enderecoRepo;

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

		final Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "123456", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("455412657", "454554"));

		final Endereco e1 = new Endereco("Rua Flores", "300", "Jardim", "3822544", cli1, c1);
		final Endereco e2 = new Endereco("Avenida Matos", "105", "Centro", "1254", cli1, c2);

		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));

	}

}
