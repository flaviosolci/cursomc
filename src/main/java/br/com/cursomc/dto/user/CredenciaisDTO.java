package br.com.cursomc.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * DTO para autenticação de usuário
 *
 * @author Flavio Solci
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** E-mail do usuário (username) */
	private String email;

	/** Senha do usuário */
	@ToString.Exclude
	private String senha;

}
