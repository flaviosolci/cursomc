package br.com.cursomc.services.exception;

/**
 * Erro para ser usado quando um object do BD não é encontrado. Ex.: findByID de
 * uma categoria que não existe
 *
 * @author Flavio Solci
 *
 */
public class ObjectNotFoundException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erro com mensagem
	 *
	 * @param msg Mensagem de erro
	 */
	public ObjectNotFoundException(final String msg) {
		super(msg);
	}

	/**
	 * Erro com mensagem e cause
	 *
	 * @param message Mensagem
	 * @param cause   Causa da exceção
	 */
	public ObjectNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
