package br.com.cursomc.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.pagamento.EstadoPagamento;
import br.com.cursomc.domain.pagamento.PagamentoComBoleto;
import br.com.cursomc.domain.pedido.ItemPedido;
import br.com.cursomc.domain.pedido.Pedido;
import br.com.cursomc.repositories.ItemPedidoRepository;
import br.com.cursomc.repositories.PagamentoRepository;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.services.exception.ObjectNotFoundException;
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

	/**
	 * Busca um Pedido pelo ID
	 *
	 * @param id ID do Pedido
	 * @return Pedido ou lança uma exceção se não encontrado
	 */
	public Pedido find(final Integer id) {
		return repository.findById(id)
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
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);

		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			boletoService.preencherPagamentoComBoleto((PagamentoComBoleto) pedido.getPagamento(), pedido.getInstante());

		}
		pgtoRepo.save(pedido.getPagamento());

		for (final ItemPedido item : pedido.getItens()) {
			item.setDesconto(BigDecimal.ZERO);
			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}

		itemPedidoRepo.saveAll(pedido.getItens());
		return repository.save(pedido);

	}

}
