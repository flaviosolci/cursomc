package br.com.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cursomc.security.UserSpringSecurity;
import lombok.experimental.UtilityClass;

/**
 * Service de usuários
 *
 * @author Flavio Solci
 *
 */
@UtilityClass
public class UserService {

	/**
	 * @return Usuário logado
	 */
	public static UserSpringSecurity authenticate() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (final Exception e) {
			// case usuário não estiver logado pode dar exceção de cast. Nesse caso só
			// retornamos nulo
			return null;
		}

	}

}
