package br.com.cursomc.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.cursomc.domain.user.Perfil;
import lombok.EqualsAndHashCode;

/**
 * Model do usuário do sistema. Padrão do Spring
 *
 * @author Flavio Solci
 *
 */
@EqualsAndHashCode(callSuper = true)
public class UserSpringSecurity extends User {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id do cliente */
	private final Integer id;

	/**
	 * Constructor
	 *
	 * @param id     Id do cliente
	 * @param email  email do cliente
	 * @param senha  senha
	 * @param perfis Perfis de acesso
	 */
	public UserSpringSecurity(final Integer id, final String email, final String senha, final Set<Perfil> perfis) {
		super(email, senha, perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
				.collect(Collectors.toList()));
		this.id = id;
	}

	/**
	 * @return Id do cliente
	 */
	public Integer getId() {
		return id;
	}

}
