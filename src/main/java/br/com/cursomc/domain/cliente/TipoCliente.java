package br.com.cursomc.domain.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * Tipos de clientes disponíveis
 *
 * @author Flavio Solci
 *
 */
@AllArgsConstructor
@ToString
@Getter
public enum TipoCliente {

	/** Pessoa física */
	PESSOA_FISICA(1, "Pessoa Física"),
	/** Pessoa jurídica */
	PESSOA_JURIDICA(2, "Pessoa Jurídica");

	/** Código do tipo cliente */
	private int codigo;

	/** Tipo cliente descrição */
	private String descricao;

	/**
	 * Transforma um código em um {@link TipoCliente}. Se não for encontrado lança
	 * exceção
	 *
	 * @param codigo Código do {@link TipoCliente}
	 * @return TipoCliente ou lança uma exceção, caso nao encontrando
	 */
	public static TipoCliente toTipoCliente(final int codigo) {
		switch (codigo) {
		case 1:
			return PESSOA_FISICA;
		case 2:
			return PESSOA_JURIDICA;
		default:
			throw new IllegalArgumentException("ID Inválido " + codigo);
		}

	}

}
