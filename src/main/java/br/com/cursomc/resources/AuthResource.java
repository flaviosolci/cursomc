package br.com.cursomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursomc.security.JWTUtil;
import br.com.cursomc.security.UserSpringSecurity;
import br.com.cursomc.services.UserService;

/**
 * REST para refreshar o token
 *
 * @author Flavio Solci
 *
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	/**
	 * Atualiza o token do cliente
	 *
	 * @param response Response aonde será setado o token
	 * @return No Content
	 */
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(final HttpServletResponse response) {
		final UserSpringSecurity user = UserService.authenticated();
		final String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
