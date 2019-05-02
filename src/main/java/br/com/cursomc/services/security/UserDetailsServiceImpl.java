package br.com.cursomc.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.security.UserSpringSecurity;

/**
 * Autenticação dos usuários
 *
 * @author Flavio Solci
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/** Busca informações do cliente */
	@Autowired
	private ClienteRepository clienteRepo;

	/**
	 * Localiza um usuário
	 *
	 * @param email email do usuário
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(final String email) {
		final Cliente cliente = clienteRepo.findByEmail(email);
		if (cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
