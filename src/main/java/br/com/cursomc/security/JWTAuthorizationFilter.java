package br.com.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Faz a autorização do usuário. Autorização quer dizer qual página o usuário
 * pode acessar
 *
 * @author Flavio Solci
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	/** Token */
	private final JWTUtil jwtUtil;

	/** Acesso ao servicos do usuário */
	private final UserDetailsService userDetailsService;

	/**
	 * Construtor
	 */
	public JWTAuthorizationFilter(final AuthenticationManager authenticationManager, final JWTUtil jwtUtil,
			final UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;

	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws IOException, ServletException {
		final String header = request.getHeader("Authorization");

		if (StringUtils.isNoneEmpty(header) && StringUtils.startsWith(header, "Bearer ")) {
			final UsernamePasswordAuthenticationToken auth = getAuthentication(StringUtils.substring(header, 7));
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(final String token) {
		if (jwtUtil.tokenValido(token)) {
			final String username = jwtUtil.getUsername(token);
			final UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}

}
