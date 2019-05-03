/** */
package br.com.cursomc.services.exception;

/**
 * Erro lançado em casos de problema de autorização
 *
 * @author Flavio Solci
 *
 */
public class AuthorizationException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message Mensagem de erro
	 * @param cause
	 */
	public AuthorizationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message Mensagem de erro
	 */
	public AuthorizationException(final String message) {
		super(message);
	}

}
