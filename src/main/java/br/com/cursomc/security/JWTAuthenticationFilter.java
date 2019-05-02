package br.com.cursomc.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cursomc.dto.user.CredenciaisDTO;

/**
 * Filtro de autenticação
 *
 * @author Flavio Solci
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	/** processador de autenticações */
	private final AuthenticationManager authenticationManager;

	/** Classe útil para gerar o token */
	private final JWTUtil jwtUtil;

	/**
	 * Construtor
	 *
	 * @param authenticationManager processador de autenticações
	 */
	public JWTAuthenticationFilter(final AuthenticationManager authenticationManager, final JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			final CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);

			final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					creds.getEmail(), creds.getSenha(), new ArrayList<>());

			return authenticationManager.authenticate(authToken);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
		final String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
		final String token = jwtUtil.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token);
	}

	/**
	 * Spring lança um HTPP 403 para informar o erro de autenticação. Essa classe
	 * fornece uma implementação alternativa
	 *
	 * @author Flavio Solci
	 *
	 */
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
				final AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json());
		}

		/**
		 * @return Cria a mensagem de erro
		 */
		private String json() {
			final long date = new Date().getTime();
			return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
			+ "\"message\": \"Email ou senha inválidos\", " + "\"path\": \"/login\"}";
		}
	}

}
