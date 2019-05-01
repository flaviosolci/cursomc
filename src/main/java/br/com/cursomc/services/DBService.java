/** */
package br.com.cursomc.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.cliente.Cidade;
import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.domain.cliente.Endereco;
import br.com.cursomc.domain.cliente.Estado;
import br.com.cursomc.domain.cliente.TipoCliente;
import br.com.cursomc.domain.pagamento.EstadoPagamento;
import br.com.cursomc.domain.pagamento.Pagamento;
import br.com.cursomc.domain.pagamento.PagamentoComBoleto;
import br.com.cursomc.domain.pagamento.PagamentoComCartao;
import br.com.cursomc.domain.pedido.ItemPedido;
import br.com.cursomc.domain.pedido.Pedido;
import br.com.cursomc.domain.produto.Categoria;
import br.com.cursomc.domain.produto.Produto;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.repositories.ItemPedidoRepository;
import br.com.cursomc.repositories.PagamentoRepository;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.repositories.ProdutoRepository;

/**
 * Cria entradas dummies no DB
 *
 * @author Flavio Solci
 *
 */
@Service
public class DBService {
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

	/** Repositório usado para inserir pedidos default quando a aplicação inicia */
	@Autowired
	private PedidoRepository pedidoRepo;

	/**
	 * Repositório usado para inserir pagamentos quando a aplicação inicia
	 */
	@Autowired
	private PagamentoRepository pgtoRepo;

	/**
	 * Repositório usado para inserir itens de pedido quando a aplicação inicia
	 */
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	/**
	 * Cria entradas dummies no DB
	 *
	 * @throws ParseException
	 */
	public void instantiateDabase() throws ParseException {
		final Categoria categoria = new Categoria("Informática");
		final Categoria categoria2 = new Categoria("Escritório");
		final Categoria categoria3 = new Categoria("Cama, mesa e banho");
		final Categoria categoria4 = new Categoria("Jardinagem");
		final Categoria categoria5 = new Categoria("Cozinha");
		final Categoria categoria6 = new Categoria("Games");
		final Categoria categoria7 = new Categoria("Perfumaria");

		categoriaRepo.saveAll(
				Arrays.asList(categoria, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));

		final Produto produto = new Produto("Computador", BigDecimal.valueOf(2000.00));
		final Produto produto2 = new Produto("Impressora", BigDecimal.valueOf(800.00));
		final Produto produto3 = new Produto("Mouse", BigDecimal.valueOf(80.00));
		final Produto produto4 = new Produto("Mesa de escritório", BigDecimal.valueOf(300.00));
		final Produto produto5 = new Produto("Toalha", BigDecimal.valueOf(50.00));
		final Produto produto6 = new Produto("Colcha", BigDecimal.valueOf(200.00));
		final Produto produto7 = new Produto("TV True Color", BigDecimal.valueOf(1200.00));
		final Produto produto8 = new Produto("Roçadeira", BigDecimal.valueOf(800.00));
		final Produto produto9 = new Produto("Abajour", BigDecimal.valueOf(100.00));
		final Produto produto10 = new Produto("Pendente", BigDecimal.valueOf(180.00));
		final Produto produto11 = new Produto("Shampoo", BigDecimal.valueOf(90.00));

		produto.getCategorias().addAll(Arrays.asList(categoria, categoria4));
		produto2.getCategorias().addAll(Arrays.asList(categoria2, categoria4));
		produto3.getCategorias().addAll(Arrays.asList(categoria, categoria4));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));

		produtoRepo.saveAll(Arrays.asList(produto, produto2, produto3, produto4, produto5, produto6, produto7, produto8,
				produto9, produto10, produto11));

		final Estado est1 = new Estado("Minas Gerais");
		final Estado est2 = new Estado("São Paulo");
		final Cidade c1 = new Cidade("Uberlândia", est1);
		final Cidade c2 = new Cidade("São Paulo", est2);
		final Cidade c3 = new Cidade("Campinas", est2);

		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));

		final Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "123456", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("455412657", "454554"));

		final Cliente cli2 = new Cliente("Flavio Solci", "flaviosolci@gmail.com", "123456",
				TipoCliente.PESSOA_JURIDICA);
		cli2.getTelefones().addAll(Arrays.asList("434434", "55555"));

		final Endereco e1 = new Endereco("Rua Flores", "300", "Jardim", "3822544", c1);
		final Endereco e2 = new Endereco("Avenida Matos", "105", "Centro", "1254", c2);
		final Endereco e3 = new Endereco("Rua Albano Buzo", "273", "Jardim Mariana", "273", c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		enderecoRepo.saveAll(Arrays.asList(e1, e2, e3));
		clienteRepo.saveAll(Arrays.asList(cli1, cli2));

		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		final Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, e1);
		final Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, e2);

		final Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
		final Pagamento pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"));
		pgtoRepo.saveAll(Arrays.asList(pagto1, pagto2));
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));

		final ItemPedido ip1 = new ItemPedido(ped1, produto, BigDecimal.ZERO, 1, BigDecimal.valueOf(2000.00));
		final ItemPedido ip2 = new ItemPedido(ped1, produto3, BigDecimal.ZERO, 2, BigDecimal.valueOf(80.00));
		final ItemPedido ip3 = new ItemPedido(ped2, produto2, BigDecimal.valueOf(100.00D), 1,
				BigDecimal.valueOf(800.00));

		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
