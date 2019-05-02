package br.com.cursomc.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * Perfis de usuário disponíveis
 *
 * @author Flavio Solci
 *
 */
@AllArgsConstructor
@ToString
@Getter
public enum Perfil {

	/** Administrador. Full access */
	ADMIN(1, "ROLE_ADMIN"),
	/** Cliente. Acesso a própria conta */
	CLIENTE(2, "ROLE_CLIENTE");

	/** Código do perfil */
	private int codigo;

	/** Tipo do perfil */
	private String descricao;

	/**
	 * Transforma um código em um {@link Perfil}. Se não for encontrado lança
	 * exceção
	 *
	 * @param codigo Código do {@link Perfil}
	 * @return TipoCliente ou lança uma exceção, caso não encontrando
	 */
	public static Perfil toPerfil(final int codigo) {
		switch (codigo) {
		case 1:
			return ADMIN;
		case 2:
			return CLIENTE;
		default:
			throw new IllegalArgumentException("ID Inválido " + codigo);
		}

	}

}
