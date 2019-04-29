package br.com.cursomc.domain.pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Estados válidos do pagamento
 *
 * @author Flavio Solci
 *
 */
@AllArgsConstructor
@Getter
public enum EstadoPagamento {

	/** Pagamento quitado */
	QUITADO(1, "Pagamento Quitado"),
	/** pagamento ainda pendente */
	PENDENTE(2, "Pagamento Pendente"),
	/** Pagamento cancelado */
	CANCELADO(3, "Pagamento Cancelado");

	/** Código do estado */
	private int codigo;

	/** Descrição do estado */
	private String descricao;

	/**
	 * Transforma um código em um {@link EstadoPagamento}. Se não for encontrado
	 * lança exceção
	 *
	 * @param codigo Código do {@link EstadoPagamento}
	 * @return EstadoPagamento ou lança uma exceção, caso nao encontrando
	 */
	public static EstadoPagamento toEstadoPagamento(final int codigo) {
		switch (codigo) {
		case 1:
			return QUITADO;
		case 2:
			return PENDENTE;
		case 3:
			return CANCELADO;
		default:
			throw new IllegalArgumentException("ID Inválido " + codigo);
		}

	}

}
