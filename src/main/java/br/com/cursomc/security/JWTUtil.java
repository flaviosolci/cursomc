package br.com.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

}
