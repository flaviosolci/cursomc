package br.com.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Security Utils
 *
 * @author Flavio Solci
 *
 */
@Component
public class JWTUtil {

	/** Segredo para gerar tokens */
	@Value("${jwt.secret}")
	private String secret;

	/** Tempo de expiração do token em milissegundos */
	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 * Gera o token JWT based no email
	 *
	 * @param email email do usuário
	 * @return Token
	 */
	public String generateToken(final String email) {
		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

	}

	/**
	 * Verificam se o token é valido
	 *
	 * @param token token de autenticação
	 * @return verdadeiro se o token for válido
	 */
	public boolean tokenValido(final String token) {
		final Claims claims = getClaims(token);
		if (claims != null) {
			final String username = claims.getSubject();
			final Date expirationDate = claims.getExpiration();
			final Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Pega o usuário baseado no token
	 *
	 * @param token Token de autenticação
	 * @return Email do usuário ou null se token nao for válido
	 */
	public String getUsername(final String token) {
		final Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(final String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (final Exception e) {
			return null;
		}
	}

}
