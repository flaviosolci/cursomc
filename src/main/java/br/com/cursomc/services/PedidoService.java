package br.com.cursomc.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.domain.pagamento.EstadoPagamento;
import br.com.cursomc.domain.pagamento.PagamentoComBoleto;
import br.com.cursomc.domain.pedido.ItemPedido;
import br.com.cursomc.domain.pedido.Pedido;
import br.com.cursomc.repositories.ItemPedidoRepository;
import br.com.cursomc.repositories.PagamentoRepository;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.security.UserSpringSecurity;
import br.com.cursomc.services.exception.AuthorizationException;
import br.com.cursomc.services.exception.ObjectNotFoundException;
import br.com.cursomc.services.mail.EmailService;
import lombok.NonNull;

/**
 * Serviço de acesso aos Pedidos
 *
 * @author Flavio Solci
 *
 */
@Service
public class PedidoService {

	/** Acesso ao BD */
	@Autowired
	private PedidoRepository repository;

	/** Dummy service para simular um serviço externo de boleto */
	@Autowired
	private BoletoService boletoService;

	/** Repository para inserir pagamentos com os pedidos */
	@Autowired
	private PagamentoRepository pgtoRepo;

	/** Service para pegar informações dos produtos */
	@Autowired
	private ProdutoService produtoService;

	/** Insere um item do pedido */
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	/** Busca clientes */
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	/**
	 * Busca um Pedido pelo ID
	 *
	 * @param id ID do Pedido
	 * @return Pedido ou lança uma exceção se não encontrado
	 */
	public Pedido find(final Integer id) {
		final UserSpringSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado!");
		}
		final Cliente cliente = clienteService.find(user.getId());

		return repository.findByIdAndClienteOrderByInstanteDesc(id, cliente)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido com o ID " + id + " não existe!"));

	}

	/**
	 * Insere um novo pedido no BD
	 *
	 * @param pedido Pedido a ser inserido
	 * @return Pedido inserido
	 */
	public Pedido insert(@NonNull final Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);

		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			boletoService.preencherPagamentoComBoleto((PagamentoComBoleto) pedido.getPagamento(), pedido.getInstante());

		}
		pgtoRepo.save(pedido.getPagamento());

		for (final ItemPedido item : pedido.getItens()) {
			item.setDesconto(BigDecimal.ZERO);
			item.setPedido(pedido);
			item.setProduto(produtoService.find(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
		}

		itemPedidoRepo.saveAll(pedido.getItens());
		final Pedido pedidoSalvo = repository.save(pedido);
		// envia email HTML
		emailService.sendOrderConfirmationHtmlEmail(pedidoSalvo);
		return pedidoSalvo;

	}

	/**
	 * Encontra todas os Pedidos com paginação
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 * @return Página com Pedidos
	 */
	public Page<Pedido> findWithPage(final Integer page, final Integer linesPerPage, final String orderBy,
			final Direction direction) {
		final UserSpringSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado!");
		}
		final Cliente cliente = clienteService.find(user.getId());

		final PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		return repository.findByCliente(cliente, pageRequest);
	}
}
